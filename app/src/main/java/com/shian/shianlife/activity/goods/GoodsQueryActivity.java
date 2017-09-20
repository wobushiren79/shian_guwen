package com.shian.shianlife.activity.goods;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.thisenum.OrderByEnum;
import com.shian.shianlife.view.drawerlayout.GoodsQueryDrawerLayout;
import com.shian.shianlife.view.goods.GoodsQueryConditionButton;
import com.shian.shianlife.view.goods.GoodsShoppingCartButton;
import com.shian.shianlife.view.listview.GoodsQueryListView;
import com.shian.shianlife.view.searchview.CustomSearchView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.shian.shianlife.adapter.GoodsQueryListAdapter.Order_Form_Price;
import static com.shian.shianlife.adapter.GoodsQueryListAdapter.Order_Form_Sale;

public class GoodsQueryActivity extends BaseActivity implements GoodsQueryConditionButton.CallBack, CustomSearchView.CallBack, View.OnClickListener, GoodsQueryDrawerLayout.CallBack {
    @InjectView(R.id.search_view)
    CustomSearchView searchView;
    @InjectView(R.id.query_volume_condition)
    GoodsQueryConditionButton queryVolumeCondition;
    @InjectView(R.id.query_price_condition)
    GoodsQueryConditionButton queryPriceCondition;
    @InjectView(R.id.tv_filtrate)
    TextView tvFiltrate;
    @InjectView(R.id.goods_query_list)
    GoodsQueryListView goodsQueryList;
    @InjectView(R.id.bt_goods_shopping_cart)
    GoodsShoppingCartButton btGoodsShoppingCart;

    @InjectView(R.id.goods_query_drawer)
    GoodsQueryDrawerLayout goodsQueryDrawer;
    @InjectView(R.id.main_drawerlayout)
    DrawerLayout mainDrawerlayout;

    private Long classId;
    private Long classAttrId;
    private String goodsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_query);
        ButterKnife.inject(this);
        initView();
        initData();
        initShoppingCart();
    }

    private void initView() {
        setTitle("商品列表");
        queryVolumeCondition.setCallBack(this);
        queryPriceCondition.setCallBack(this);

        tvFiltrate.setOnClickListener(this);
        searchView.setCallBack(this);
        goodsQueryDrawer.setCallBack(this);


    }

    private void initData() {
        classId = getIntent().getLongExtra(IntentName.INTENT_CLASS_ID, -1);
        classAttrId = getIntent().getLongExtra(IntentName.INTENT_CLASSATTR_ID, -1);
        goodsName = getIntent().getStringExtra(IntentName.INTENT_GOODSNAME);

        searchView.setData(goodsName);
        setQueryData(classId, classAttrId, goodsName);
    }

    /**
     * 初始化购物车按钮
     */
    private void initShoppingCart() {
        //获取屏幕宽高
        int Width = metrics.widthPixels;
        int Height = metrics.heightPixels;
        btGoodsShoppingCart.setWHData(Width, Height - getResources().getDimensionPixelOffset(R.dimen.dimen_160dp));
    }

    @Override
    public void changeMode(View view, int mode) {
        if (view == queryVolumeCondition) {
            queryVolumeCondition.setCheckStatus(true);
            queryPriceCondition.setCheckStatus(false);
            if (mode == GoodsQueryConditionButton.Mode_DESC)
                goodsQueryList.startOrderBy(Order_Form_Sale, OrderByEnum.DESC);
            else
                goodsQueryList.startOrderBy(Order_Form_Sale, OrderByEnum.ASC);
        } else if (view == queryPriceCondition) {
            queryVolumeCondition.setCheckStatus(false);
            queryPriceCondition.setCheckStatus(true);
            if (mode == GoodsQueryConditionButton.Mode_DESC)
                goodsQueryList.startOrderBy(Order_Form_Price, OrderByEnum.DESC);
            else
                goodsQueryList.startOrderBy(Order_Form_Price, OrderByEnum.ASC);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        setQueryData(null, -1L, query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == tvFiltrate) {
            drawerControl();
        }
    }

    private void drawerControl() {
        if (mainDrawerlayout.isDrawerOpen(goodsQueryDrawer)) {
            mainDrawerlayout.closeDrawer(goodsQueryDrawer);
        } else {
            mainDrawerlayout.openDrawer(goodsQueryDrawer);
        }
    }

    @Override
    public void changeClassAttr(View view, Long classId, Long classAttrId) {
        setQueryData(classId, classAttrId, "");
        drawerControl();
    }

    private void setQueryData(Long classId, Long classAttrId, String goodsName) {
        goodsQueryList.setClassId(classId);
        goodsQueryList.setClassAttId(classAttrId);
        goodsQueryList.setGoodsName(goodsName);
        goodsQueryList.startQuery();
    }

}
