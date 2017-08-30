package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by zm.
 */

public class GoodsShoppingCartButton extends BaseLayout {
    @InjectView(R.id.iv_shopping_cart)
    ImageView ivShoppingCart;

    public GoodsShoppingCartButton(Context context) {
        this(context, null);
    }

    public GoodsShoppingCartButton(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_shopping_cart_button, attrs);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.iv_shopping_cart)
    public void onViewClicked() {

    }
}
