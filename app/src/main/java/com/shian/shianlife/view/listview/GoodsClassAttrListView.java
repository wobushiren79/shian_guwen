package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.shian.shianlife.adapter.GoodsClassAttrAdapter;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.view.IGoodsClassAttrView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassAttrListView extends RecyclerView implements IGoodsClassAttrView{

    private GoodsClassAttrAdapter goodsClassAttrAdapter;

    public GoodsClassAttrListView(Context context) {
        this(context, null);
    }

    public GoodsClassAttrListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        goodsClassAttrAdapter = new GoodsClassAttrAdapter(context);
        this.setAdapter(goodsClassAttrAdapter);
        this.setLayoutManager(new GridLayoutManager(context, 3));
    }

    public void getData() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void getGoodsClassAttrDataSuccess(List<GoodsClassAttrResultBean> listData) {

    }

    @Override
    public void getGoodsClassAttrDataFail(String msg) {

    }
}
