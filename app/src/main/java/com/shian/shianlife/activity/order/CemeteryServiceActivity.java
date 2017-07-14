package com.shian.shianlife.activity.order;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ViewPageAdapter;
import com.shian.shianlife.common.view.order.BaseOrderView;
import com.shian.shianlife.common.view.order.CemeteryBuildView;
import com.shian.shianlife.common.view.order.ListFwpdzView;
import com.shian.shianlife.common.view.order.OverServiceView;
import com.shian.shianlife.common.view.order.QTView;
import com.shian.shianlife.common.view.order.WaitAuditView;
import com.shian.shianlife.common.view.order.WaitMoneyView;
import com.shian.shianlife.common.view.order.WaitServiceView;
import com.shian.shianlife.thisenum.AppRolePermition;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class CemeteryServiceActivity extends BaseActivity {
    public TabPageIndicator mIndicator;
    private ViewPager mViewPager;
    private List<String> arrTitles = new ArrayList<String>();

    public static boolean C_bOrder_isRefresh;
    public static OrderFragmentCallBack orderFragmentCallBack;

    private BaseOrderView mOrderView;
    private int mIndex;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cemetery_service);
        initView();
        initStates();
        initData();
        if (views.size() > 0) {
            ((BaseOrderView) views.get(mIndex)).refresh();
        }
    }


    private void initView() {
        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setTitle("公墓服务");
    }

    private void initStates() {
        arrTitles.clear();
        //检测权限
        if (AppContansts.userCemetery != null) {
            for (int i = 0; i < AppContansts.userCemetery.getPermitionCodes().size(); i++) {
                if (AppContansts.userCemetery.getPermitionCodes().get(i).equals(AppRolePermition.ADVISOR.getCode())) {
                    arrTitles.add("公墓单");
                }
            }
        }
    }

    private void initData() {
        views = new ArrayList<View>();
        ViewPageAdapter adapter = new ViewPageAdapter(views) {
            @Override
            public CharSequence getPageTitle(int position) {
                // TODO Auto-generated method stub
                return arrTitles.get(position);
            }
        };
        initPagerAdapter(views);
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                mIndex = arg0;
                BaseOrderView orderView = (BaseOrderView) views.get(arg0);
                mOrderView = orderView;
                orderView.refresh();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }


    public void setCallBack(OrderFragmentCallBack orderFragmentCallBack) {
        this.orderFragmentCallBack = orderFragmentCallBack;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (views.size() > 0 && C_bOrder_isRefresh) {
            if (orderFragmentCallBack != null)
                orderFragmentCallBack.changeMsgNum();
            ((BaseOrderView) views.get(mIndex)).refresh();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        C_bOrder_isRefresh = false;
    }


    private void initPagerAdapter(List<View> views) {
        for (String n : arrTitles) {
            if (n.equals("公墓单")) {
                CemeteryBuildView view = new CemeteryBuildView(this);
                views.add(view);
            }
        }
    }

    public interface OrderFragmentCallBack {
        void changeMsgNum();
    }
}
