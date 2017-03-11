package com.shian.shianlife.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.view.customview.MessageListView;


import java.util.ArrayList;
import java.util.List;


public class NewMessageListActivity extends BaseActivity {
    RadioGroup mRGTitle;
    RadioButton mRBNotice;
    RadioButton mRBService;

    TextView mTVBack;

    ViewPager mViewPager;

    List<MessageListView> listMessageView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message_list);
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(listMessageView.size()!=0){
            for (MessageListView view:listMessageView) {
                 view.notifyDataSetChanged();
            }
        }
    }

    private void initData() {
        listMessageView.add(new MessageListView(NewMessageListActivity.this, 1));
        listMessageView.add(new MessageListView(NewMessageListActivity.this, 2));
    }

    private void initView() {
        mRBNotice = (RadioButton) findViewById(R.id.rb_notice);
        mRBService = (RadioButton) findViewById(R.id.rb_service);
        mRGTitle = (RadioGroup) findViewById(R.id.rg_title);

        mTVBack = (TextView) findViewById(R.id.tv_back);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mRGTitle.setOnCheckedChangeListener(onRGTitlecheck);
        mRBService.setOnCheckedChangeListener(onItemRBTitleCheck);
        mRBNotice.setOnCheckedChangeListener(onItemRBTitleCheck);


        mTVBack.setOnClickListener(onClickListener);

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(onPageChangeListener);
        setRBState(true, mRBService);
        setRBState(false, mRBNotice);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVBack) {
                finish();
            }
        }
    };


    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return listMessageView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(listMessageView.get(position));
            return listMessageView.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(listMessageView.get(position));
        }
    };


    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                mRGTitle.check(mRBService.getId());
            } else if (position == 1) {
                mRGTitle.check(mRBNotice.getId());
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    RadioGroup.OnCheckedChangeListener onRGTitlecheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            listMessageView.get(mViewPager.getCurrentItem()).setReadState(0);
            if (checkedId == mRBService.getId()) {
                mViewPager.setCurrentItem(0);
            } else if (checkedId == mRBNotice.getId()) {
                mViewPager.setCurrentItem(1);
            }
        }
    };


    CompoundButton.OnCheckedChangeListener onItemRBTitleCheck = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                setRBState(true, buttonView);
            } else {
                setRBState(false, buttonView);
            }
        }
    };


    private void setRBState(boolean isCheck, CompoundButton radioButton) {
        Drawable drawable;
        if (isCheck) {
            drawable = getResources().getDrawable(R.drawable.zhy_title_line);
            drawable.setBounds(0, 0, getResources().getDimensionPixelOffset(R.dimen.dimen_100dp), getResources().getDimensionPixelOffset(R.dimen.dimen_4dp));
        } else {
            drawable = getResources().getDrawable(R.drawable.zhy_title_line_2);
            drawable.setBounds(0, 0, getResources().getDimensionPixelOffset(R.dimen.dimen_100dp), getResources().getDimensionPixelOffset(R.dimen.dimen_4dp));
        }
        radioButton.setCompoundDrawables(null, null, null, drawable);
    }


}
