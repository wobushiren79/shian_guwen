package com.shian.shianlife.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends BaseActivity {
    ViewPager viewPager;
    ImageView mBTNext;

    RadioGroup radioGroup;
    List<RadioButton> listRadioButton = new ArrayList<>();
    int[] picData = {
            R.drawable.showactivity_ydy1,
            R.drawable.showactivity_ydy2,
            R.drawable.showactivity_ydy3,
            R.drawable.showactivity_ydy4,
            R.drawable.showactivity_ydy5
    };
    List<ImageView> listIV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        listRadioButton.add((RadioButton) findViewById(R.id.rb_1));
        listRadioButton.add((RadioButton) findViewById(R.id.rb_2));
        listRadioButton.add((RadioButton) findViewById(R.id.rb_3));
        listRadioButton.add((RadioButton) findViewById(R.id.rb_4));
        listRadioButton.add((RadioButton) findViewById(R.id.rb_5));
        initView();
        initData();
    }

    private void initData() {

        for (int i = 0; i < picData.length; i++) {
            ImageView imageView = new ImageView(this);
            ImageLoader.getInstance().displayImage("drawable://" + picData[i],
                    imageView);
            listIV.add(imageView);


        }


        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return picData.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(listIV.get(position));
                return listIV.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(listIV.get(position));
            }

        });

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mBTNext = (ImageView) findViewById(R.id.next);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mBTNext.setVisibility(View.GONE);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == picData.length - 1) {
                    mBTNext.setVisibility(View.VISIBLE);
                } else {
                    mBTNext.setVisibility(View.GONE);
                }

                for (int i = 0; i < listRadioButton.size(); i++) {
                    if (position == i) {
                        listRadioButton.get(i).setChecked(true);
                    } else {
                        listRadioButton.get(i).setChecked(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBTNext.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTNext) {
                Intent intent = new Intent(ShowActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
