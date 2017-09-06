package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.os.Bundle;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsDetailsListPresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartListPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsDetailsListPresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsShoppingCartListPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsDetailsListView;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartListView;
import com.shian.shianlife.view.listview.GoodsShoppingCartListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsShoppingCartActivity extends BaseActivity implements IGoodsShoppingCartListView, IGoodsDetailsListView {

    @InjectView(R.id.listview)
    GoodsShoppingCartListView shoppingCartListView;

    private Integer pageNumber;
    private Integer pageSize;

    private List<GoodsShoppingCartListResultBean.Content> goodsShoppingCartIds;

    private IGoodsShoppingCartListPresenter goodsShoppingCartListPresenter;
    private IGoodsDetailsListPresenter goodsDetailsListPresenter;

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
        pageNumber = 0;
        pageSize = Integer.MAX_VALUE;

        goodsShoppingCartIds = new ArrayList<>();

        goodsShoppingCartListPresenter = new GoodsShoppingCartListPresenterImpl(this);
        goodsDetailsListPresenter = new GoodsDetailsListPresenterImpl(this);
        goodsShoppingCartListPresenter.getShoppingCartListData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void getShoppingCartListDataSuccess(GoodsShoppingCartListResultBean resultBean) {
        goodsShoppingCartIds = resultBean.getContent();
        goodsDetailsListPresenter.getGoodsDetailsList();
    }

    @Override
    public void getShoppingCartListDataFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public Integer getShoppingCartPageSize() {
        return pageSize;
    }

    @Override
    public Integer getShoppingCartPageNumber() {
        return pageNumber;
    }

    @Override
    public void getGoodsDetailsListDataSuccess(List<GoodsDetailsListResultBean> resultBeen) {
        for (GoodsDetailsListResultBean item : resultBeen) {
            for (GoodsShoppingCartListResultBean.Content ids : goodsShoppingCartIds) {
                if (item.getSpec_id() == ids.getGoodsSpecId())
                    item.setShoppingCartNumber(ids.getSpecNum());
            }
        }
        shoppingCartListView.setData(resultBeen);
    }

    @Override
    public void getGoodsDetailsListDataFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public List<Long> getGoodsIds() {
        List<Long> ids = new ArrayList<>();
        for (GoodsShoppingCartListResultBean.Content item : goodsShoppingCartIds) {
            if (item.getGoodsId() != null)
                ids.add(item.getGoodsId());
        }
        return ids;
    }

    @Override
    public List<Integer> getChannelIds() {
        List<Integer> ids = new ArrayList<>();
        for (GoodsShoppingCartListResultBean.Content item : goodsShoppingCartIds) {
            if (item.getChannelId() != null)
                ids.add(item.getChannelId());
        }
        return ids;
    }

    @Override
    public List<Long> getGoodsSpecIds() {
        List<Long> ids = new ArrayList<>();
        for (GoodsShoppingCartListResultBean.Content item : goodsShoppingCartIds) {
            if (item.getGoodsSpecId() != null)
                ids.add(item.getGoodsSpecId());
        }
        return ids;
    }
}
