package com.shian.shianlife.view.goods;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.view.taglayout.TagLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class GoodsSpecSelectView extends BaseLayout implements View.OnClickListener, GoodsNumberChangeView.CallBack {
    private TagLayout tagLayout;
    private TextView tvSpecGoodsName;
    private TextView tvGoodsSpecSelectName;
    private TextView tvSpecGoodsPrice;
    private GoodsNumberChangeView goodsNumberChange;

    private Integer selectPosition;
    private List<GoodsDetailsResultBean.SpecpriceBean> listData;
    private Integer goodsNumber;

    private List<TextView> listItem;

    public GoodsSpecSelectView(Context context) {
        this(context, null);
    }

    public GoodsSpecSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_spec_select, attrs);
    }

    @Override
    protected void initView() {
        tagLayout = (TagLayout) findViewById(R.id.tag_layout);
        tvSpecGoodsName = (TextView) findViewById(R.id.tv_goods_spec);
        tvSpecGoodsPrice = (TextView) findViewById(R.id.tv_good_sprice_select);
        tvGoodsSpecSelectName = (TextView) findViewById(R.id.tv_goods_spec_select_name);
        goodsNumberChange = (GoodsNumberChangeView) findViewById(R.id.goods_number);
        goodsNumberChange.setCallBack(this);
    }

    @Override
    protected void initData() {
        listItem = new ArrayList<>();
        listData = new ArrayList<>();

        goodsNumberChange.setData(1);
    }


    public void setData(List<GoodsDetailsResultBean.SpecpriceBean> listData) {
        this.listItem.clear();
        this.listData.clear();
        if (listData != null) {
            this.listData = listData;
            for (GoodsDetailsResultBean.SpecpriceBean item : listData) {
                TextView tv = new TextView(getContext());
                int dp16 = getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_16dp);
                tv.setPadding(2 * dp16, dp16, 2 * dp16, dp16);
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(Color.parseColor("#A5A5A5"));
                tv.setBackgroundResource(R.drawable.zhy_tag_uncheck_style_1);
                tv.setText(item.getSpec_name());
                tv.setOnClickListener(this);

                tagLayout.addView(tv);
                listItem.add(tv);
                tvSpecGoodsName.setText(item.getSpec_alias());
            }
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < listItem.size(); i++) {
            TextView tv = listItem.get(i);
            if (tv == v) {
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundResource(R.drawable.zhy_tag_check_style_1);
                selectPosition = i;
                goodsNumberChange.setData(1);
            } else {
                tv.setTextColor(Color.parseColor("#A5A5A5"));
                tv.setBackgroundResource(R.drawable.zhy_tag_uncheck_style_1);
            }
        }
    }

    @Override
    public void numberChange(View view, Integer number) {
        goodsNumber = number;
        setSelectSpecGoodsPrice();
    }


    private void setSelectSpecGoodsPrice() {
        if (goodsNumber != null && selectPosition != null) {
            GoodsDetailsResultBean.SpecpriceBean data = listData.get(selectPosition);
            float price = goodsNumber * data.getSpec_price();
            tvSpecGoodsPrice.setText("￥" + price);
            tvGoodsSpecSelectName.setText("已选择 " + data.getSpec_name());
        }
    }

    /**
     * 获取数据
     *
     * @return
     */
    public GoodsDetailsResultBean.SpecpriceBean getData() {
        if (selectPosition == null) {
            return null;
        } else {
            return listData.get(selectPosition);
        }
    }

    /**
     * 获取数量
     *
     * @return
     */
    public Integer getNumber() {
        return goodsNumber;
    }

}
