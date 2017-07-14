package com.shian.shianlife.activity.order;

import android.annotation.SuppressLint;
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
import com.shian.shianlife.provide.result.HrLoginResult;
import com.shian.shianlife.thisenum.AppRolePermition;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class FuneralServiceActivity extends BaseActivity {
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
        setContentView(R.layout.activity_funeral_service);
        initView();
        initStates();
        initData();
        if (views.size() > 0) {
            ((BaseOrderView) views.get(mIndex)).refresh();
        }
        setMsgCorner();
    }

    private void initView() {
        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setTitle("殡仪服务");
    }

    private void initStates() {
        arrTitles.clear();
        //检测权限
        for (Integer role : AppContansts.userLoginInfo.getRoleIds()) {
            switch (role) {
                case 1:
                    arrTitles.add("洽谈");
                    break;
                case 2:
                    arrTitles.add("待服务");
                    arrTitles.add("服务派单中");
                    break;
                case 4:
                    arrTitles.add("待评审");
                    break;
                case 9:
                    arrTitles.add("待收款");
                    break;
                default:
                    break;
            }
        }
        arrTitles.add("服务结束");
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

    private void setMsgCorner() {
        List<TabPageIndicator.TabView> listTabView = mIndicator.getListTabView();
        int cornerSize = getResources().getDimensionPixelSize(R.dimen.dimen_30dp);
        if (AppContansts.MsgNumber != null) {
            for (TabPageIndicator.TabView tabview : listTabView) {
                String titel = tabview.getText().toString();
                switch (titel) {
                    case "洽谈":
                        tabview.setMsgCornerNumber(AppContansts.MsgNumber.getTalk(), cornerSize);
                        break;
                    case "待服务":
                        tabview.setMsgCornerNumber(AppContansts.MsgNumber.getService(), cornerSize);
                        break;
                    case "服务派单中":
                        tabview.setMsgCornerNumber(AppContansts.MsgNumber.getAssignment(), cornerSize);
                        break;
                    case "待评审":
                        tabview.setMsgCornerNumber(AppContansts.MsgNumber.getAuditing(), cornerSize);
                        break;
                    case "待收款":
                        tabview.setMsgCornerNumber(AppContansts.MsgNumber.getUnpaid(), cornerSize);
                        break;
                    case "服务结束":
                        //tabview.setMsgCornerNumber(AppContansts.MsgNumber.getEndService());
                        break;
                }
            }
        }

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
            if (n.equals("洽谈")) {
                QTView qtView = new QTView(this);
                views.add(qtView);
            } else if (n.equals("待服务")) {
                WaitServiceView waitServiceView = new WaitServiceView(this, n);
                views.add(waitServiceView);
            } else if (n.equals("服务派单中")) {
                ListFwpdzView fwpdzView = new ListFwpdzView(this);
                views.add(fwpdzView);
            } else if (n.equals("待收款")) {
                WaitMoneyView view = new WaitMoneyView(this, n);
                views.add(view);
            } else if (n.equals("待评审")) {
                WaitAuditView view = new WaitAuditView(this, n);
                views.add(view);
            } else if (n.equals("服务结束")) {
                OverServiceView view = new OverServiceView(this, n);
                views.add(view);
            }
        }
    }

    public interface OrderFragmentCallBack {
        void changeMsgNum();
    }
}
