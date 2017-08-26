package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.shian.shianlife.adapter.GoodsClassAttrAdapter;

/**
 * Created by zm.
 */

public class GoodsClassAttrListView extends RecyclerView {
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
}
