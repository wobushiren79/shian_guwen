package com.shian.shianlife.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;

import butterknife.InjectView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ViewPageAdapter;
import com.shian.shianlife.common.view.customer.BaseCustomerView;
import com.shian.shianlife.common.view.customer.CustomerGMView;
import com.shian.shianlife.common.view.customer.CustomerHTView;
import com.shian.shianlife.common.view.customer.CustomerJBRView;
import com.shian.shianlife.common.view.customer.CustomerQtView;
import com.shian.shianlife.common.view.customer.CustomerSZView;
import com.shian.shianlife.common.view.customer.CustomerYBView;
import com.shian.shianlife.common.view.customer.CustomerZSView;
import com.viewpagerindicator.TabPageIndicator;

public class CustomerActivity extends BaseActivity {
    @InjectView(R.id.indicator_customer)
    TabPageIndicator indicator;
    @InjectView(R.id.pager_customer)
    ViewPager viewPager;
    int khxqType;
//	private String[] titles = { "洽谈信息", "逝者信息", "经办人信息", "合同信息", "公墓信息",
//			"预备信息", "治丧信息" };

//	private String[] titles = {  "逝者信息", "经办人信息", "公墓信息",
//			"治丧信息" };

    private String[] titles1 = {"合同信息", "预备信息", "经办人信息",
            "逝者信息"};

    private String[] titles2 = {"逝者信息", "经办人信息", "治丧信息",};

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_customer);
        khxqType = getIntent().getIntExtra("khxqtype", -1);
        Log.v("this", "CustomerActivity khxqType:" + khxqType);
        setTitle("客户详情");
        initView();
    }

    private void initView() {
        final List<View> views = new ArrayList<View>();
        ViewPageAdapter adapter = new ViewPageAdapter(views) {
            @Override
            public CharSequence getPageTitle(int position) {
                if (khxqType == 0) {
                    return titles1[position];
                } else if (khxqType == 1) {
                    return titles2[position];
                } else {
                    return titles1[position];//待定
                }
            }
        };
        initPagerAdapter(views);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        ((BaseCustomerView) views.get(0)).initData();
        indicator.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                ((BaseCustomerView) views.get(arg0)).initData();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

//	private void initPagerAdapter(List<View> views) {
//		for (int i = 0; i < 7; i++) {
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
//	}

//	private void initPagerAdapter(List<View> views) {
//		for (int i = 0; i < 4; i++) {
//			 if (i == 0) {
//				CustomerSZView szView = new CustomerSZView(this);
//				views.add(szView);
//			} else if (i == 1) {
//				CustomerJBRView jbrView = new CustomerJBRView(this);
//				views.add(jbrView);
//			} else if (i == 2) {
//				CustomerGMView gmView = new CustomerGMView(this);
//				views.add(gmView);
//			} else if (i == 3) {
//				CustomerZSView ybView1 = new CustomerZSView(this);
//				views.add(ybView1);
//			}
//		}
//	}

    private void initPagerAdapter(List<View> views) {
        if (khxqType == 0) {
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    CustomerHTView htView = new CustomerHTView(this);
                    views.add(htView);
                } else if (i == 1) {
                    CustomerYBView ybView = new CustomerYBView(this);
                    views.add(ybView);
                } else if (i == 2) {
                    CustomerJBRView jbrView = new CustomerJBRView(this);
                    views.add(jbrView);
                } else if (i == 3) {
                    CustomerSZView szView = new CustomerSZView(this);
                    views.add(szView);
                }
            }
        } else if (khxqType == 1) {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    CustomerSZView szView = new CustomerSZView(this);
                    views.add(szView);
                } else if (i == 1) {
                    CustomerJBRView jbrView = new CustomerJBRView(this);
                    views.add(jbrView);
                } else if (i == 2) {
                    CustomerZSView zsView = new CustomerZSView(this);
                    views.add(zsView);
                }
            }
        }

    }
}
