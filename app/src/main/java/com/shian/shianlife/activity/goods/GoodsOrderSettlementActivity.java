package com.shian.shianlife.activity.goods;

import android.os.Bundle;
import android.view.View;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.StoreOrderGoodsListAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.view.ScrollExpandableListView;
import com.shian.shianlife.view.goods.GoodsServiceInfoLayout;
import com.shian.shianlife.view.goods.StoreExpandableTitleView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderSettlementActivity extends BaseActivity implements StoreExpandableTitleView.CallBack {

    @InjectView(R.id.layout_service_info)
    GoodsServiceInfoLayout layoutServiceInfo;
    @InjectView(R.id.goods_expand_title)
    StoreExpandableTitleView goodsExpandTitle;
    @InjectView(R.id.goods_expand_list_view)
    ScrollExpandableListView goodsExpandListView;

    public static boolean isSaveServiceInfo = false;
    public static boolean isSaveInvoiceInfo = false;

    private StoreOrderGoodsListAdapter goodsListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_settlement);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("结算");
        layoutServiceInfo.setMode(GoodsServiceInfoLayout.Mode_Edit);
    }

    private void initData() {
        goodsExpandTitle.setCallBack(this);
        goodsListAdapter = new StoreOrderGoodsListAdapter(this);
        goodsListAdapter.setIsShowPerformInfo(false);
        goodsExpandListView.setAdapter(goodsListAdapter);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isSaveServiceInfo) {
            layoutServiceInfo.getDataForShared();
        }
        if (isSaveInvoiceInfo) {

        }
    }

    @Override
    public void onClickExpandable(View view, boolean isExpandable) {
        if (view == goodsExpandTitle) {
            if (isExpandable)
                goodsExpandListView.setVisibility(View.VISIBLE);
            else
                goodsExpandListView.setVisibility(View.GONE);
        }
    }
}
