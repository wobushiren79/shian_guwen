package com.shian.shianlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseExpandableAdapter;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassAttrPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsClassAttrPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsClassAttrView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsFiltrateAdapter extends BaseExpandableAdapter<GoodsClassResultBean, GoodsClassAttrResultBean> implements IGoodsClassAttrView {

    private int checkGroupPosition = 0;
    private IGoodsClassAttrPresenter goodsClassAttrPresenter;

    public GoodsFiltrateAdapter(Context context) {
        super(context, R.layout.item_goods_filtrate_group, R.layout.item_goods_filtrate_child);
        goodsClassAttrPresenter = new GoodsClassAttrPresenterImpl(this);
    }



    @Override
    public void setGroupView(GoodsClassResultBean groupData, View convertView, int groupPosition, boolean isExpanded) {
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
        tvName.setText(groupData.getName());
        if (isExpanded) {
            ivIcon.setRotation(90);
        } else {
            ivIcon.setRotation(0);
        }
    }

    @Override
    public void setItemView(GoodsClassAttrResultBean itemData, View convertView, int groupPosition, int childPosition) {
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
        tvName.setText(itemData.getName());
        Utils.loadPic(mContext, ivIcon, AppContansts.Goods_PicUrl + "/" + itemData.getTitle_img(),R.drawable.zhy_pic_loading);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(mContext, msg);
    }

    @Override
    public void getGoodsClassAttrDataSuccess(List<GoodsClassAttrResultBean> listData) {
        itemData.get(checkGroupPosition).addAll(listData);
        this.notifyDataSetChanged();
    }

    @Override
    public void getGoodsClassAttrDataFail(String msg) {
        ToastUtils.show(mContext, msg);
    }

    @Override
    public Long getGoodsClassId() {
        GoodsClassResultBean itemData = groupData.get(checkGroupPosition);
        return itemData.getId();
    }

    public void getClassAttrData(int checkGroupPosition) {
        this.checkGroupPosition = checkGroupPosition;
        if (itemData.get(checkGroupPosition).size() == 0)
            goodsClassAttrPresenter.getGoodsClassAttrData();
    }


}
