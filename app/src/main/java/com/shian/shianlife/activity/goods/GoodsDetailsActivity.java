package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsDetailsPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsDetailsPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsDetailsView;
import com.shian.shianlife.view.carousel.CarouselView;
import com.shian.shianlife.view.goods.GoodsSpecSelectView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener, AppBarLayout.OnOffsetChangedListener, IGoodsDetailsView {
    @InjectView(R.id.garouseview)
    CarouselView garouseview;
    @InjectView(R.id.tv_temp_back)
    TextView tvBack;
    @InjectView(R.id.rl_temp_head)
    RelativeLayout rlHead;
    @InjectView(R.id.tv_temp_title)
    TextView tvTitle;
    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @InjectView(R.id.tv_price_range)
    TextView tvPriceRange;
    @InjectView(R.id.tv_price_original)
    TextView tvPriceOriginal;
    @InjectView(R.id.tv_sale_number)
    TextView tvSaleNumber;
    @InjectView(R.id.tv_location)
    TextView tvLocation;
    @InjectView(R.id.goods_spec_select)
    GoodsSpecSelectView goodsSpecSelect;

    private Long gooodsId;

    private IGoodsDetailsPresenter goodsDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        Utils.setTranslucent(this);
        appbar.addOnOffsetChangedListener(this);
        tvBack.setOnClickListener(this);
    }

    private void initData() {
        gooodsId = getIntent().getLongExtra(IntentName.INTENT_GOODS_ID, -1);

        goodsDetailsPresenter = new GoodsDetailsPresenterImpl(this);
        goodsDetailsPresenter.getGoodsDetails();
    }

    @Override
    public void onClick(View v) {
        if (v == tvBack) {
            finish();
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int dp = getResources().getDimensionPixelOffset(R.dimen.dimen_650dp);
        int maxShow = dp / 2;
        int offset = Math.abs(verticalOffset);
        if (offset > maxShow) {
            float alpha = ((offset - maxShow) * 2f) / (float) dp;
            rlHead.setAlpha(Math.abs(alpha));
            garouseview.setRGVisibility(View.GONE);
        } else {
            rlHead.setAlpha(0f);
            garouseview.setRGVisibility(View.VISIBLE);
        }
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
    public void getGoodsDetailsSuccess(GoodsDetailsResultBean listData) {

    }

    @Override
    public void getGoodsDetailsFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public Long getGoodsId() {
        return gooodsId;
    }

    @Override
    public void setCarouselPic(List<String> picList) {
        garouseview.setData(picList);
    }

    @Override
    public void setGoodsName(String name) {
        tvGoodsName.setText(name);
    }

    @Override
    public void setPriceRange(String range) {
        tvPriceRange.setText(range);
    }

    @Override
    public void setPriceOriginal(String original) {
        tvPriceOriginal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        tvPriceOriginal.setText(original);
    }

    @Override
    public void setSaleNumber(String number) {
        tvSaleNumber.setText(number);
    }

    @Override
    public void setLocation(String location) {
        tvLocation.setText(location);
    }

    @Override
    public void setGoodsSpecSelectData(List<GoodsDetailsResultBean.SpecpriceBean> data) {
        goodsSpecSelect.setData(data);
    }

}
