package com.shian.shianlife.activity.goods;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.StoreOrderGoodsListAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.DataUtils;
import com.shian.shianlife.common.utils.ViewUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsDetailsListActivity extends BaseActivity {

    @InjectView(R.id.goods_expand_list_view)
    ExpandableListView goodsExpandListView;

    private StoreOrderGoodsListAdapter goodsListAdapter;
    private List<GoodsItemPerform> selectGoods;//選中的商品

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details_list);
        ButterKnife.inject(this);

        initView();
        initData();
    }

    private void initView() {
        setTitle("商品列表");
    }

    private void initData() {
        selectGoods = (ArrayList<GoodsItemPerform>) getIntent().getSerializableExtra(IntentName.INTENT_LIST_DATA);
        Map<String, List<GoodsItemPerform>> mapGoodsData = DataUtils.getMapForGoodsItemPerform(selectGoods);
        goodsListAdapter = new StoreOrderGoodsListAdapter(this, false);
        if (selectGoods != null)
            goodsListAdapter.setData(mapGoodsData);
        goodsExpandListView.setAdapter(goodsListAdapter);
        //默认展开
        ViewUtils.expandGroup(goodsExpandListView, goodsListAdapter);
    }

}
