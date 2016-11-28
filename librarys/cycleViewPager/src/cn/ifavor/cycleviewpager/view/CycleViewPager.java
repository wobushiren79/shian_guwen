package cn.ifavor.cycleviewpager.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ifavor.cycleviewpager.cache.BitmapHelper;
import cn.ifavor.cycleviewpager.tools.DrawableUtils;


public class CycleViewPager extends RelativeLayout {


	private ViewPager mViewPager;

	private TextView mTvTitle;

	private List<String> mTitles;

	private List<Integer> mResIds;

	private List<String> mURLs;

	private int mCount;


	private LinkedHashMap<String, String> mURLMap;

	private LinkedHashMap<String, Integer> mResIdMap;;


	private List<ImageView> mImageViews;

	private LinearLayout mBottomContainer;

	private LinearLayout mRedPointContainer;

	private ViewPagerAutoRunTask mAutoRunTask;
	
	
	private int mBottomBackgroundColor = Color.argb(54, 0, 0, 0);
	private int mUnSelectedColor = Color.rgb(61, 59, 59);
	private int mSelectedColor = Color.rgb(255, 0, 0);

	private int mDuration = 3000; 

	private IndicatorDirection mIndicatorDirection = IndicatorDirection.CENTER;

	private int mIndicatorPointSize = 16;
	private int mIndicatorRadius = mIndicatorPointSize / 2;
	private int mIndicatorPointMargin = mIndicatorPointSize / 2;
	private boolean isShowTitle = true;
	private float mTitleTextSize = 12;
	private int mTitleTextColor = Color.rgb(255, 255, 255);
	private Handler mHandler;
	
	public CycleViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initView(attrs);
	}

	public CycleViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView(attrs);
	}

	public CycleViewPager(Context context) {
		super(context);

		initView(null);
	}


	private void initView(AttributeSet attrs) {
		

		mViewPager = new ViewPager(getContext());
		LayoutParams viewPagerParams = new LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		mViewPager.setLayoutParams(viewPagerParams);
		addView(mViewPager);


		mTvTitle = new TextView(getContext());


		mBottomContainer = new LinearLayout(getContext());


		mRedPointContainer = new LinearLayout(getContext());


		mBottomContainer.addView(mTvTitle);
		addView(mBottomContainer);
		mBottomContainer.addView(mRedPointContainer);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		if (mHandler == null){
			mHandler = new Handler();
		}
		
		

		valid();


		mCount = mURLMap != null ? mURLMap.size() : mResIdMap.size();


		convertMap2Set();


		mImageViews = new ArrayList<ImageView>();
		for (int i = 0; i < mCount; i++) {
			mImageViews.add(new ImageView(getContext()));
		}


		mTvTitle.setText("���Ǳ���");
		mTvTitle.setTextColor(mTitleTextColor );
		mTvTitle.setTextSize(mTitleTextSize );
		mTvTitle.setVisibility( isShowTitle ? View.VISIBLE : View.INVISIBLE);


		RelativeLayout.LayoutParams layoutParams = new LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mBottomContainer.setBackgroundColor(mBottomBackgroundColor );
		mBottomContainer.setLayoutParams(layoutParams);
		mBottomContainer.setOrientation(LinearLayout.VERTICAL);


		LinearLayout.LayoutParams redPointContainerParams = new android.widget.LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		
		redPointContainerParams.gravity = mIndicatorDirection.value();
		redPointContainerParams.bottomMargin = 5;
		redPointContainerParams.rightMargin = 5;
		redPointContainerParams.leftMargin = 5;
		redPointContainerParams.topMargin = 2;
		mRedPointContainer.setOrientation(LinearLayout.HORIZONTAL);
		mRedPointContainer.setLayoutParams(redPointContainerParams);


		for  (int i = 0; i < mCount; i++) {
			View view = new View(getContext());
			LinearLayout.LayoutParams redPointParams = new LinearLayout.LayoutParams(
					mIndicatorPointSize , mIndicatorPointSize);
			
			if (i > 0) {
				redPointParams.leftMargin = mIndicatorPointMargin ;
			}

			view.setBackground(DrawableUtils.createEnableDisableSelector(mIndicatorRadius ,
					mUnSelectedColor , mSelectedColor ));
			view.setEnabled(false);
			view.setLayoutParams(redPointParams);
			mRedPointContainer.addView(view);
		}


		if (mTitles != null && mTitles.size() > 0) {
			if (mTitles.get(0) != null) {
				mTvTitle.setText(mTitles.get(0));
			}
		}

		mViewPager.setAdapter(new MyPagerAdater());
		mRedPointContainer.getChildAt(0).setEnabled(true);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						int pos = position % mCount;
						for (int i = 0; i < mCount; i++) {
							View childAt = mRedPointContainer.getChildAt(i);
							childAt.setEnabled(i == pos);
						}

						if (mTitles.get(pos) != null) {
							mTvTitle.setText(mTitles.get(pos));
						}
					}
				});
		
		mViewPager.setCurrentItem(10000 * mCount);


		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					cancel();
					break;
				case MotionEvent.ACTION_UP:
					start();
					break;
				case MotionEvent.ACTION_CANCEL:
					start();
					break;
				}
				return false;
			}
		});
	}


	private void convertMap2Set() {
		mTitles = new ArrayList<String>();
		if (mURLMap != null) {
			mURLs = new ArrayList<String>();

			Iterator<Entry<String, String>> iterator = mURLMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String key = next.getKey();
				String value = next.getValue();

				mURLs.add(value);
				mTitles.add(key);
			}
		}

		if (mResIdMap != null) {
			mResIds = new ArrayList<Integer>();

			Iterator<Entry<String, Integer>> iterator = mResIdMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, Integer> next = iterator.next();
				String key = next.getKey();
				Integer value = next.getValue();

				mResIds.add(value);
				mTitles.add(key);
			}
		}
	}


	private void valid() {
		if (mURLMap == null && mResIdMap == null) {
			throw new NullPointerException("δ������Ҫ��ʾ��ͼƬ");
		}

		if (mURLMap != null && mURLMap.size() <= 0) {
			throw new NullPointerException("ͼƬ��Դ�����������0");
		}

		if (mResIdMap != null && mResIdMap.size() <= 0) {
			throw new NullPointerException("ͼƬ��Դ�����������0");
		}
	}

	private class MyPagerAdater extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int pos = position % mCount;
			ImageView imageView = mImageViews.get(pos);


			removeFromParent(imageView);
			imageView.setScaleType(ScaleType.FIT_XY);
			if (mResIds != null && mResIds.size() > 0) {
				imageView.setImageResource(mResIds.get(pos));
			} else if (mURLs != null && mURLs.size() > 0) {
				BitmapHelper.getInstance(getContext(), mHandler).display(imageView, mURLs.get(pos));
			}

			container.addView(imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((ImageView) object);
		}


		private void removeFromParent(View view) {
			ViewParent parent = view.getParent();
			if (parent != null && parent instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) parent;
				group.removeView(view);
			}
		}
	}


	private class ViewPagerAutoRunTask implements Runnable {
		private ViewPager mViewPager;

		public ViewPagerAutoRunTask(ViewPager viewPager) {
			this.mViewPager = viewPager;
		}

		@Override
		public void run() {
			int currItem = mViewPager.getCurrentItem();
			currItem++;
			if (currItem >= mViewPager.getAdapter().getCount()) {
				currItem = 0;
			}
			mViewPager.setCurrentItem(currItem);

			postDelayed(this, mDuration);
		}

		public void start(int duration) {
			cancel(this);
			postDelayed(this, mDuration);
		}

		public void cancel() {
			cancel(this);
		}
		
		private  void cancel(Runnable r) {
			if (mHandler != null && r != null){
				mHandler.removeCallbacks(r);
			}
		}
		
		private void postDelayed( Runnable r, int delayMillis) {
			if (mHandler != null && r != null){
				mHandler.postDelayed(r, delayMillis);
			}
		}
	}


	public void start() {

		if (mAutoRunTask != null) {
			mAutoRunTask.cancel();
			mAutoRunTask = null;
		}
		mAutoRunTask = new ViewPagerAutoRunTask(mViewPager);
		mAutoRunTask.start(mDuration);
	}


	public void cancel() {
		if (mAutoRunTask != null) {
			mAutoRunTask.cancel();
			mAutoRunTask = null;
		}
	}


	public Map<String, String> getURLMap() {
		return mURLMap;
	}


	public CycleViewPager setURLMap(LinkedHashMap<String, String> mURLMap) {
		this.mURLMap = mURLMap;
		return this;
	}


	public Map<String, Integer> getResIdMap() {
		return mResIdMap;
	}


	public CycleViewPager setResIdMap(LinkedHashMap<String, Integer> mResIdMap) {
		this.mResIdMap = mResIdMap;
		return this;
	}

	public enum IndicatorDirection {
		LEFT(Gravity.LEFT), CENTER(Gravity.CENTER), RIGHT(Gravity.RIGHT);
		IndicatorDirection(int value) {
			this._value = value;
		}

		private int _value;

		public int value() {
			return _value;
		}
	}


	public int getBottomBackgroundColor() {
		return mBottomBackgroundColor;
	}


	public int getIndicatorRadius() {
		return mIndicatorRadius;
	}


	public int getUnSelectedColor() {
		return mUnSelectedColor;
	}


	public int getSelectedColor() {
		return mSelectedColor;
	}


	public int getDuration() {
		return mDuration;
	}


	public IndicatorDirection getIndicatorDirection() {
		return mIndicatorDirection;
	}


	public int getIndicatorPointSize() {
		return mIndicatorPointSize;
	}


	public int getIndicatorPointMargin() {
		return mIndicatorPointMargin;
	}


	public boolean isShowTitle() {
		return isShowTitle;
	}


	public float getTitleTextSize() {
		return mTitleTextSize;
	}


	public int getTitleTextColor() {
		return mTitleTextColor;
	}


	public CycleViewPager setBottomBackgroundColor(int bottomBackgroundColor) {
		this.mBottomBackgroundColor = bottomBackgroundColor;
		return this;
	}


	public CycleViewPager setIndicatorRadius(int indicatorRadius) {
		this.mIndicatorRadius = indicatorRadius;
		return this;
	}


	public CycleViewPager setUnSelectedColor(int unSelectedColor) {
		this.mUnSelectedColor = unSelectedColor;
		return this;
	}


	public CycleViewPager setSelectedColor(int selectedColor) {
		this.mSelectedColor = selectedColor;
		return this;
	}


	public CycleViewPager setDuration(int duration) {
		this.mDuration = duration;
		return this;
	}


	public CycleViewPager setIndicatorDirection(IndicatorDirection indicatorDirection) {
		this.mIndicatorDirection = indicatorDirection;
		return this;
	}


	public CycleViewPager setIndicatorPointSize(int indicatorPointSize) {
		this.mIndicatorPointSize = indicatorPointSize;
		return this;
	}


	public CycleViewPager setIndicatorPointMargin(int indicatorPointMargin) {
		this.mIndicatorPointMargin = indicatorPointMargin;
		return this;
	}


	public CycleViewPager setShowTitle(boolean isShowTitle) {
		this.isShowTitle = isShowTitle;
		return this;
	}


	public CycleViewPager setTitleTextSize(float titleTextSize) {
		this.mTitleTextSize = titleTextSize;
		return this;
	}


	public CycleViewPager setTitleTextColor(int titleTextColor) {
		this.mTitleTextColor = titleTextColor;
		return this;
	}
	
	public Handler getHandler() {
		return mHandler;
	}

	public CycleViewPager setHandler(Handler handler) {
		this.mHandler = handler;
		return this;
	}
}
