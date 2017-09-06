package com.shian.shianlife.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseExpandableAdapter;
import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.bean.GoodsShoppingCartListGroupBean;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;


/**
 * Created by zm.
 */

public class GoodsShoppingCartListAdapter extends BaseExpandableAdapter<GoodsShoppingCartListGroupBean, GoodsShoppingCartListChildBean> {

    public GoodsShoppingCartListAdapter(Context context) {
        super(context, R.layout.item_goods_shopping_list_group, R.layout.item_goods_shopping_list_item);
    }


    @Override
    public void setGroupView(GoodsShoppingCartListGroupBean groupAllData, View convertView, int groupPosition, boolean isExpanded) {
        TextView tvDrive = (TextView) convertView.findViewById(R.id.tv_drive);
        if (groupPosition == 0)
            tvDrive.setVisibility(View.GONE);
        else
            tvDrive.setVisibility(View.VISIBLE);

        CheckBox check = (CheckBox) convertView.findViewById(R.id.check);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvChange = (TextView) convertView.findViewById(R.id.tv_change);

        if (groupData != null)
            tvName.setText(groupAllData.getClassName());
    }

    @Override
    public void setItemView(GoodsShoppingCartListChildBean itemAllData, View convertView, int groupPosition, int childPosition) {
        CheckBox check = (CheckBox) convertView.findViewById(R.id.check);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
        TextView tvGoodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
        TextView tvGoodsSpec = (TextView) convertView.findViewById(R.id.tv_goods_spec);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);

        GoodsDetailsListResultBean itemData = new GoodsDetailsListResultBean();

        Utils.loadPic(mContext, ivIcon, AppContansts.Goods_PicUrl + "/" + itemData.getTitle_img(), R.drawable.zhy_pic_loading);

        String goodsName = itemData.getGoods_name();
        String goodsSlogan = itemData.getGoods_slogan();
        StringBuffer name = new StringBuffer();
        if (goodsName != null)
            name.append(goodsName);
        if (goodsSlogan != null)
            name.append(" " + goodsSlogan);
        tvGoodsName.setText(name.toString());

        if (itemData.getSpec_name() != null)
            tvGoodsSpec.setText("规格：" + itemData.getSpec_name());

        if (itemData.getSpec_price() != null)
            tvPrice.setText("￥" + itemData.getSpec_price());

        if (itemData.getShoppingCartNumber() != null)
            tvNumber.setText("x" + itemData.getShoppingCartNumber());
    }
}
