package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.ImagePreviewActivity;
import com.shian.shianlife.adapter.GoodsPackageListAdapter;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.view.goods.GoodsNumberChangeView;
import com.shian.shianlife.view.piccorner.RoundCornerImageView;
import com.shian.shianlife.view.taglayout.TagLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zm.
 */

public class GoodsSelectDialog extends Dialog implements View.OnClickListener {
    private TextView tvPrice;
    private TextView tvInventory;
    private RoundCornerImageView ivGoodsSpec;
    private TextView tvTagName;
    private TagLayout tagLayout;
    private GoodsNumberChangeView viewNumberChange;

    private LinearLayout packageGoodsLayout;
    private RecyclerView packageGoodsList;
    private GoodsPackageListAdapter goodsPackageListAdapter;

    private TextView tvSubmit;
    private CallBack callBack;
    private List<TextView> listItem;
    private Integer selectPosition;
    private List<GoodsDetailsResultBean.SpecpriceBean> data;
    private String unit;
    private String picUrl;

    public GoodsSelectDialog(@NonNull Context context) {
        super(context, R.style.GoodsSelectDialog);
        setContentView(R.layout.dialog_goods_select);
        initView();
    }

    public void setData(List<GoodsDetailsResultBean.SpecpriceBean> data) {
        this.data = data;
        for (GoodsDetailsResultBean.SpecpriceBean item : data) {
            TextView tv = new TextView(getContext());
            int dp16 = getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_16dp);
            tv.setPadding(2 * dp16, dp16 / 2, 2 * dp16, dp16 / 2);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.parseColor("#A5A5A5"));
            tv.setBackgroundResource(R.drawable.zhy_tag_uncheck_style_1);
            tv.setText(item.getSpec_name());
            tv.setOnClickListener(this);

            tagLayout.addView(tv);
            listItem.add(tv);
            tvTagName.setText(item.getSpec_alias());
        }

    }

    public void setUnit(String unit) {
        if (unit != null)
            this.unit = unit;
    }

    public void setPic(String picUrl) {
        this.picUrl = picUrl;
        Utils.loadPic(getContext(), ivGoodsSpec, AppContansts.Goods_PicUrl + "/" + picUrl);
    }


    /**
     * 設置默認選擇第几个
     *
     * @param selectPosition
     */
    public void setSelect(Integer selectPosition, Integer selectNumber) {
        if (selectPosition < data.size()) {
            this.selectPosition = selectPosition;
            GoodsDetailsResultBean.SpecpriceBean itemData = data.get(selectPosition);
            tvPrice.setText("推荐价￥" + itemData.getSpec_price());
            tvInventory.setText("库存 " + itemData.getSpec_stock() + unit);
            if (itemData.getTitle_img() != null)
                setPic(itemData.getTitle_img());
            //設置選擇數量
            if (selectNumber == null)
                viewNumberChange.setData(1);
            else
                viewNumberChange.setData(selectNumber);

            TextView tv = listItem.get(selectPosition);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundResource(R.drawable.zhy_tag_check_style_1);

            if (itemData.getSpec_goods() != null && itemData.getSpec_goods().size() > 0)
                packageGoodsLayout.setVisibility(View.VISIBLE);
            else
                packageGoodsLayout.setVisibility(View.GONE);

            goodsPackageListAdapter.setData(itemData.getSpec_goods());
        }
    }

    private void initView() {
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        ivGoodsSpec = (RoundCornerImageView) findViewById(R.id.iv_goods_spec);
        tvTagName = (TextView) findViewById(R.id.tv_tag_name);
        tagLayout = (TagLayout) findViewById(R.id.tag_layout);
        viewNumberChange = (GoodsNumberChangeView) findViewById(R.id.view_number_change);
        tvInventory = (TextView) findViewById(R.id.tv_inventory);
        packageGoodsLayout = (LinearLayout) findViewById(R.id.ll_package_goods_list);
        packageGoodsList = (RecyclerView) findViewById(R.id.package_goods_list);
        tvSubmit.setOnClickListener(this);
        ivGoodsSpec.setOnClickListener(this);

        Window window = this.getWindow();
        //设置弹出动画
        window.setWindowAnimations(R.style.goodsSelectDialogWindowAnim);

        //设置dialog大小
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        this.getWindow().setAttributes(params);

        goodsPackageListAdapter = new GoodsPackageListAdapter(getContext());
        packageGoodsList.setLayoutManager
                (new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        packageGoodsList.setAdapter(goodsPackageListAdapter);

        listItem = new ArrayList<>();
        viewNumberChange.setData(1);
        unit = "件";
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    public void onClick(View v) {
        if (v == tvSubmit) {
            submit();
        } else if (v == ivGoodsSpec) {
            Intent intent = new Intent(getContext(), ImagePreviewActivity.class);
            intent.putExtra(IntentName.INTENT_URL, AppContansts.Goods_PicUrl + "/" + picUrl);
            getContext().startActivity(intent);
        } else {
            for (int i = 0; i < listItem.size(); i++) {
                TextView tv = listItem.get(i);
                if (tv == v) {
                    setSelect(i, 1);
                } else {
                    tv.setTextColor(Color.parseColor("#A5A5A5"));
                    tv.setBackgroundResource(R.drawable.zhy_tag_uncheck_style_1);
                }
            }
        }
    }

    /**
     * 提交
     */
    private void submit() {
        if (callBack != null)
            callBack.onSubmitClick(this, selectPosition, viewNumberChange.getNumber());
        cancel();
    }

    public interface CallBack {
        void onSubmitClick(Dialog dialog, Integer position, Integer number);
    }
}
