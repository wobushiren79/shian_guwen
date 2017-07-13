package com.shian.shianlife.fragment;

import java.util.ArrayList;
import java.util.List;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MainActivity;
import com.shian.shianlife.base.BaseFragment;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewPageAdapter;
import com.shian.shianlife.common.view.order.BaseOrderView;
import com.shian.shianlife.common.view.order.CemeteryBuildView;
import com.shian.shianlife.common.view.order.CommonView;
import com.shian.shianlife.common.view.order.ListFwpdzView;
import com.shian.shianlife.common.view.order.OverServiceView;
import com.shian.shianlife.common.view.order.QTView;
import com.shian.shianlife.common.view.order.WaitAuditView;
import com.shian.shianlife.common.view.order.WaitMoneyView;
import com.shian.shianlife.common.view.order.WaitServiceView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.result.HrGetMsgNumberForUntreated;
import com.shian.shianlife.provide.result.HrLoginResult;
import com.shian.shianlife.thisenum.AppRolePermition;
import com.viewpagerindicator.TabPageIndicator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class OrderFragment extends BaseFragment {
    @InjectView(R.id.indicator)
    public TabPageIndicator indicator;
    @InjectView(R.id.pager)
    ViewPager viewPager;
    private View v;
    private String[] titles = {"洽谈", "待服务", "服务派单中", "待审核", "待付款", "服务结束"};
    private List<String> arrTitles = new ArrayList<String>();

    public static boolean C_bOrder_isRefresh_Change;
    public static OrderFragmentCallBack orderFragmentCallBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_order, null, false);
        ButterKnife.inject(this, v);
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initStates();
        initView();
        if (views.size() > 0) {
            ((BaseOrderView) views.get(mIndex)).refresh();
        }
        setMsgCorner();
    }

    public void setCallBack(OrderFragmentCallBack orderFragmentCallBack) {
        this.orderFragmentCallBack = orderFragmentCallBack;
    }

    private void setMsgCorner() {
        List<TabPageIndicator.TabView> listTabView = indicator.getListTabView();
        int cornerSize = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_30dp);
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
        if (views.size() > 0 && C_bOrder_isRefresh_Change) {
            orderFragmentCallBack.changeMsgNum();
            ((BaseOrderView) views.get(mIndex)).refresh();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        C_bOrder_isRefresh_Change = false;
    }

    private void initStates() {
        arrTitles.clear();
        //检测权限
        if (AppContansts.userCemetery != null) {
            for (int i = 0; i < AppContansts.userCemetery.getPermitionCodes().size(); i++) {
                if(AppContansts.userCemetery.getPermitionCodes().get(i).equals(AppRolePermition.ADVISOR.getCode())){
                    arrTitles.add("公墓单");
                }
            }
        }
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

    private BaseOrderView mOrderView;
    private int mIndex;
    private List<View> views;

    private void initView() {
        views = new ArrayList<View>();
        ViewPageAdapter adapter = new ViewPageAdapter(views) {
            @Override
            public CharSequence getPageTitle(int position) {
                // TODO Auto-generated method stub
                return arrTitles.get(position);
            }
        };
        initPagerAdapter(views);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new OnPageChangeListener() {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (mOrderView != null)
            mOrderView.refresh();
    }

    private void initPagerAdapter(List<View> views) {
        for (String n : arrTitles) {
            if (n.equals("洽谈")) {
                QTView qtView = new QTView(getActivity());
                views.add(qtView);
            } else if (n.equals("待服务")) {
                WaitServiceView waitServiceView = new WaitServiceView(
                        getActivity(), n);
                views.add(waitServiceView);
            } else if (n.equals("服务派单中")) {
                ListFwpdzView fwpdzView = new ListFwpdzView(getActivity());
                views.add(fwpdzView);
            } else if (n.equals("待收款")) {
                WaitMoneyView view = new WaitMoneyView(getActivity(), n);
                views.add(view);
            } else if (n.equals("待评审")) {
                WaitAuditView view = new WaitAuditView(getActivity(), n);
                views.add(view);
            } else if (n.equals("服务结束")) {
                OverServiceView view = new OverServiceView(getActivity(), n);
                views.add(view);
            } else if (n.equals("公墓单")) {
                CemeteryBuildView view = new CemeteryBuildView(getActivity());
                views.add(view);
            }
        }

        // for (int i = 0; i < 6; i++) {
        // if (i == 0) {
        // QTView qtView = new QTView(getActivity());
        // mOrderView = qtView;
        // views.add(qtView);
        // } else if (i == 1) {
        // WaitServiceView waitServiceView = new WaitServiceView(
        // getActivity(), titles[i]);
        // views.add(waitServiceView);
        // } else if (i == 2) {
        // ListFwpdzView fwpdzView = new ListFwpdzView(getActivity());
        // views.add(fwpdzView);
        // } else if (i == 3) {
        // WaitAuditView view = new WaitAuditView(getActivity(), titles[i]);
        // views.add(view);
        // } else if (i == 4) {
        // WaitMoneyView view = new WaitMoneyView(getActivity(), titles[i]);
        // views.add(view);
        // } else if (i == 5) {
        // OverServiceView view = new OverServiceView(getActivity(),
        // titles[i]);
        // views.add(view);
        // } else {
        // CommonView tv = new CommonView(getActivity(), titles[i]);
        // views.add(tv);
        // }
        // }
    }

    public interface OrderFragmentCallBack {
        void changeMsgNum();
    }
}
