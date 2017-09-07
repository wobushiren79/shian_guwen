package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.WebActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.AnimUtils;
import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsDetailsPresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartCreatePresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartNumberPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsDetailsPresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsShoppingCartCreatePresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsShoppingCartNumberPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsDetailsView;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartCreateView;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartNumberView;
import com.shian.shianlife.view.carousel.CarouselView;
import com.shian.shianlife.view.dialog.DataShowDialog;
import com.shian.shianlife.view.goods.GoodsDescribeLayout;
import com.shian.shianlife.view.goods.GoodsSpecSelectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.shian.shianlife.common.contanst.AppContansts.KF5_BaseUrl;

public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener, AppBarLayout.OnOffsetChangedListener, IGoodsDetailsView, IGoodsShoppingCartNumberView, IGoodsShoppingCartCreateView {
    @InjectView(R.id.garouseview)
    CarouselView garouseview;
    @InjectView(R.id.tv_temp_back)
    TextView tvBack;
    @InjectView(R.id.iv_pic_back)
    ImageView ivBack;
    @InjectView(R.id.iv_pic_share)
    ImageView ivShare;

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
    @InjectView(R.id.iv_all_price)
    ImageView ivAllPrice;

    @InjectView(R.id.tv_price_original)
    TextView tvPriceOriginal;
    @InjectView(R.id.tv_sale_number)
    TextView tvSaleNumber;
    @InjectView(R.id.tv_location)
    TextView tvLocation;
    @InjectView(R.id.goods_spec_select)
    GoodsSpecSelectView goodsSpecSelect;
    @InjectView(R.id.goods_describle)
    GoodsDescribeLayout goodsDescrible;

    @InjectView(R.id.tv_service)
    LinearLayout tvService;
    @InjectView(R.id.tv_shopipingcart)
    LinearLayout tvShopipingcart;
    @InjectView(R.id.tv_add_shopingcart)
    TextView tvAddShopingcart;
    @InjectView(R.id.tv_buy)
    TextView tvBuy;
    @InjectView(R.id.tv_msg_number)
    TextView tvMsgNumber;
    @InjectView(R.id.tv_msg_number_temp)
    TextView tvMsgNumberTemp;

    private Long gooodsId;
    private Long gooodsClassId;
    private Long gooodsClassAttrId;
    private Boolean isFirstGetShoppingNumber;//是否第一次獲取購物車數量

    private GoodsDetailsResultBean detailsData;
    private List<GoodsDetailsResultBean.SpecpriceBean> specListData;

    private IGoodsDetailsPresenter goodsDetailsPresenter;
    private IGoodsShoppingCartNumberPresenter goodsShoppingCartNumberPresenter;
    private IGoodsShoppingCartCreatePresenter goodsShoppingCartCreatePresenter;

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
    }

    private void initData() {
        isFirstGetShoppingNumber = true;

        gooodsId = getIntent().getLongExtra(IntentName.INTENT_GOODS_ID, -1);
        gooodsClassId = getIntent().getLongExtra(IntentName.INTENT_CLASS_ID, -1);
        gooodsClassAttrId = getIntent().getLongExtra(IntentName.INTENT_CLASSATTR_ID, -1);

        goodsDetailsPresenter = new GoodsDetailsPresenterImpl(this);
        goodsShoppingCartNumberPresenter = new GoodsShoppingCartNumberPresenterImpl(this);
        goodsShoppingCartCreatePresenter = new GoodsShoppingCartCreatePresenterImpl(this);

        goodsDetailsPresenter.getGoodsDetails();
        goodsShoppingCartNumberPresenter.getShoppingCartNumber();
    }

    @Override
    public void onClick(View v) {
        if (v == ivAllPrice) {
            showAllPrice();
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
    public void getGoodsDetailsSuccess(GoodsDetailsResultBean resultBean) {
        this.detailsData = resultBean;
    }

    @Override
    public void getGoodsDetailsFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void createGoodsShoppingCartSuccess(GoodsShoppingCartCreateResultBean resultBean) {
        goodsShoppingCartNumberPresenter.getShoppingCartNumber();
        ToastUtils.show(this, "加入购物车成功");
    }

    @Override
    public void createGoodsShoppingCartFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public Long getGoodsId() {
        return gooodsId;
    }

    @Override
    public Integer getSpecNum() {
        return goodsSpecSelect.getNumber();
    }

    @Override
    public Integer getChannelId() {
        return goodsSpecSelect.getData().getChannel_id();
    }

    @Override
    public Long getClassifyAttrId() {
        return gooodsClassAttrId;
    }

    @Override
    public Long getClassifyId() {
        if (detailsData == null)
            return null;
        return detailsData.getGoods_cate_id();
    }

    @Override
    public Long getGoodsSpecId() {
        return goodsSpecSelect.getData().getGoods_spec_id();
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
        this.specListData = data;
        goodsSpecSelect.setData(data);
        ivAllPrice.setOnClickListener(this);
    }

    @Override
    public void setGoodsDescribeDetails(String html) {
        goodsDescrible.setDetailsData(html);
    }

    @Override
    public void setGoodsApplyBury(String applyBury) {
        goodsDescrible.setApplyBury(applyBury);
    }

    @Override
    public void setGoodsApplyPerson(String applyPerson) {
        goodsDescrible.setApplyPerson(applyPerson);
    }

    @Override
    public void setGoodsApplyPhase(String applyPhase) {
        goodsDescrible.setApplyPhase(applyPhase);
    }

    @Override
    public void setGoodsApplyAge(String applyAge) {
        goodsDescrible.setApplyAge(applyAge);
    }

    @Override
    public void setGoodsApplyLocation(String location) {
        goodsDescrible.setApplyLocation(location);
    }

    @OnClick({R.id.tv_service, R.id.tv_shopipingcart, R.id.tv_add_shopingcart, R.id.tv_buy, R.id.tv_temp_back, R.id.iv_pic_back, R.id.iv_pic_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_service:
                openService();
                break;
            case R.id.tv_shopipingcart:
                openShoppingCart();
                break;
            case R.id.tv_add_shopingcart:
                addShoppingCart();
                break;
            case R.id.tv_buy:
                directBuy();
                break;
            case R.id.iv_pic_share:
                shareDetails();
                break;
            case R.id.tv_temp_back:
            case R.id.iv_pic_back:
                finish();
                break;
        }
    }

    /**
     * 分享
     */
    private void shareDetails() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }

    /**
     * 直接购买
     */
    private void directBuy() {

    }

    /**
     * 加入购物车
     */
    private void addShoppingCart() {
        if (goodsSpecSelect.getData() == null) {
            ToastUtils.show(this, "还没有选择规格商品");
            return;
        }
        goodsShoppingCartCreatePresenter.createGoodsShoppingCartData();
    }

    /**
     * 开启购物车
     */
    private void openShoppingCart() {
        Intent intent = new Intent(this, GoodsShoppingCartActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 开启在线客服
     */
    private void openService() {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(IntentName.INTENT_URL, KF5_BaseUrl);
        startActivity(intent);
    }

    /**
     * 展示所有圆满价
     */
    private void showAllPrice() {
        List<DataShowDialog.DataShowDialogResultBean> listData = new ArrayList<>();
        if (specListData != null) {
            for (GoodsDetailsResultBean.SpecpriceBean item : specListData) {
                DataShowDialog.DataShowDialogResultBean data = new DataShowDialog.DataShowDialogResultBean(item.getSpec_name(), "￥" + item.getAdviser_price());
                listData.add(data);
            }
            DataShowDialog dataShowDialog = new DataShowDialog(this);
            dataShowDialog.setTitle("圆满价");
            dataShowDialog.setData(listData);
            dataShowDialog.setCancelOnClick(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dataShowDialog.show();
        }
    }

    @Override
    public void getShoppingCartNumberSuccess(GoodsShoppingCartNumberResultBean resultBean) {

    }

    @Override
    public void getShoppingCartNumberFail(String msg) {

    }

    @Override
    public void setShoppingCartNumber(String number) {
        if (CheckUtils.isEmpty(number)) {
            tvMsgNumber.setVisibility(View.GONE);
            tvMsgNumberTemp.setVisibility(View.GONE);
        } else {
            tvMsgNumber.setText(number);
            tvMsgNumber.setVisibility(View.VISIBLE);
            tvMsgNumberTemp.setVisibility(View.VISIBLE);
            if (isFirstGetShoppingNumber) {
                isFirstGetShoppingNumber = false;
                AnimUtils.setShoppingCartAnim(tvMsgNumber, 200);
            } else {
                if (goodsSpecSelect.getNumber() > 99) {
                    tvMsgNumberTemp.setText("99+");
                } else {
                    tvMsgNumberTemp.setText(goodsSpecSelect.getNumber() + "");
                }
                AnimUtils.addShoppingCartAnim(tvMsgNumberTemp, 1000);
            }
        }

    }
}
