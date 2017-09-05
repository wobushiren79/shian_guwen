package com.shian.shianlife.activity.goods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;

public class GoodsShoppingCartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_shopping_cart);
        initView();
        initData();
    }

    private void initView() {
        setTitle("服务车");
    }

    private void initData() {

    }

}
