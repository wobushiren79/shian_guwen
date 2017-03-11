package com.shian.shianlife.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import butterknife.InjectView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ViewPageAdapter;
import com.shian.shianlife.common.view.customer.BaseCustomerView;
import com.shian.shianlife.common.view.message.MessageListView;
import com.viewpagerindicator.TabPageIndicator;

public class MessageListActivity extends BaseActivity {
    @InjectView(R.id.indicator_customer)
    TabPageIndicator indicator;
    @InjectView(R.id.pager_customer)
    ViewPager viewPager;

    private String[] titles =
            {
                    "全部",
                    "服务",
                    "通知"};
    private ArrayList<View> views;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_messagelist);
//        setTitle("我的消息");
        setMessageVisible(View.GONE);
        initView();
    }

    private void initView() {
        views = new ArrayList<View>();
        ViewPageAdapter adapter = new ViewPageAdapter(views) {
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        initPagerAdapter(views);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        ((MessageListView) views.get(0)).refresh();
        indicator.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                ((MessageListView) views.get(arg0)).refresh();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    protected void onRestart() {
        ((MessageListView) views.get(viewPager.getCurrentItem())).refresh();
        super.onRestart();
    }

    private void initPagerAdapter(List<View> views) {

        for (int i = 0; i < 3; i++) {
            MessageListView view = new MessageListView(this, i + 1);
            views.add(view);
        }
//			if (i == 0) {
//				CustomerQtView qtView = new CustomerQtView(this);
//				views.add(qtView);
//			} else if (i == 1) {
//				CustomerSZView szView = new CustomerSZView(this);
//				views.add(szView);
//			} else if (i == 2) {
//				CustomerJBRView jbrView = new CustomerJBRView(this);
//				views.add(jbrView);
//			} else if (i == 3) {
//				CustomerHTView htView = new CustomerHTView(this);
//				views.add(htView);
//			} else if (i == 4) {
//				CustomerGMView gmView = new CustomerGMView(this);
//				views.add(gmView);
//			} else if (i == 5) {
//				CustomerYBView ybView = new CustomerYBView(this);
//				views.add(ybView);
//			} else if (i == 6) {
//				CustomerZSView ybView1 = new CustomerZSView(this);
//				views.add(ybView1);
//			}
//		}
    }
}
