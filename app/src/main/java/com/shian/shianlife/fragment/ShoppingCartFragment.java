package com.shian.shianlife.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsOrderSettlementActivity;
import com.shian.shianlife.base.BaseFragment;
import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.DataUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsDetailsListPresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartListPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsDetailsListPresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsShoppingCartListPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsDetailsListView;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartListView;
import com.shian.shianlife.view.listview.GoodsShoppingCartListView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created by zm.
 */

public class ShoppingCartFragment extends BaseFragment implements GoodsShoppingCartListView.CallBack {
    private View view;

    @InjectView(R.id.listview)
    GoodsShoppingCartListView shoppingCartListView;
    @InjectView(R.id.check)
    CheckBox check;
    @InjectView(R.id.tv_check_all)
    TextView tvCheckAll;
    @InjectView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;


    private ArrayList<GoodsShoppingCartListChildBean> selectGoods;//選中的商品

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shoppingcart, null, false);
        ButterKnife.inject(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {

    }

    private void initData() {
        shoppingCartListView.setCallBack(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void getIsAllCheck(boolean isAllCheck) {
        check.setChecked(isAllCheck);
    }

    @Override
    public void getSelectGoods(ArrayList<GoodsShoppingCartListChildBean> selectGoods) {
        this.selectGoods = selectGoods;
        float totalPrice = 0f;
        for (GoodsShoppingCartListChildBean item : selectGoods) {
            if (item.getResultBean() != null && item.getResultBean().getSpec_price() != null) {
                BigDecimal old = new BigDecimal(totalPrice);
                BigDecimal add = new BigDecimal(item.getResultBean().getSpec_price() * item.getResultBean().getShoppingCartNumber());
                totalPrice = old.add(add).setScale(2, RoundingMode.HALF_UP).floatValue();
            }
        }
        setTotalPrice("￥" + totalPrice);
    }


    @OnClick({R.id.check, R.id.tv_submit, R.id.tv_check_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check:
                setCheckStatus();
                break;
            case R.id.tv_check_all:
                check.setChecked(!check.isChecked());
                setCheckStatus();
                break;
            case R.id.tv_submit:
                submitData();
                break;
        }
    }

    /**
     * 設置全選狀態
     */
    private void setCheckStatus() {
        shoppingCartListView.setAllCheck(check.isChecked());
    }

    /**
     * 提交數據
     */
    private void submitData() {
        if (this.selectGoods == null || this.selectGoods.size() <= 0) {
            ToastUtils.show(getContext(), "还没有选择商品");
            return;
        }
        Intent intent = new Intent(getContext(), GoodsOrderSettlementActivity.class);
        ArrayList<GoodsItemPerform> listData = DataUtils.shoppingCartToGoodsData(selectGoods);
        intent.putExtra(IntentName.INTENT_LIST_DATA, listData);
        startActivity(intent);
    }

    /**
     * 設置總價值
     *
     * @param price
     */
    private void setTotalPrice(String price) {
        tvTotalPrice.setText(price);
    }


    /**
     * 开始查询数据
     */
    public void startFindData() {
        shoppingCartListView.startFindData();
    }
}
