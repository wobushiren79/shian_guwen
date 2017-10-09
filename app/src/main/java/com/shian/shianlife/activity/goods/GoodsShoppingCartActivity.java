package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsShoppingCartActivity extends BaseActivity implements GoodsShoppingCartListView.CallBack {

    @InjectView(R.id.listview)
    GoodsShoppingCartListView shoppingCartListView;
    @InjectView(R.id.check)
    CheckBox check;
    @InjectView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;
    @InjectView(R.id.tv_check_all)
    TextView tvCheckAll;

    private ArrayList<GoodsShoppingCartListChildBean> selectGoods;//選中的商品

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_shopping_cart);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("服务车");
    }

    private void initData() {
        shoppingCartListView.setCallBack(this);
    }


    @Override
    public void getIsAllCheck(boolean isAllCheck) {
        check.setChecked(isAllCheck);
    }

    @Override
    public void getSelectGoods(ArrayList<GoodsShoppingCartListChildBean> selectGoods) {
        this.selectGoods = selectGoods;
        float totalPrice = 0;
        for (GoodsShoppingCartListChildBean item : selectGoods) {
            if (item.getResultBean() != null && item.getResultBean().getSpec_price() != null) {
                totalPrice += (item.getResultBean().getSpec_price() * item.getResultBean().getShoppingCartNumber());
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
            ToastUtils.show(this, "还没有选择商品");
            return;
        }
        Intent intent = new Intent(this, GoodsOrderSettlementActivity.class);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        shoppingCartListView.startFindData();
    }
}
