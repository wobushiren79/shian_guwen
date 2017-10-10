package com.shian.shianlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsDetailsListActivity;
import com.shian.shianlife.adapter.base.BaseExpandableAdapter;
import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.bean.GoodsShoppingCartListGroupBean;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.DataUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderItem;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartChangeNumberPresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartDeletePresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsShoppingCartChangeNumberPresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsShoppingCartDeletePresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartChangeNumberView;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartDeleteView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zm.
 */

public class GoodsShoppingCartListAdapter extends BaseExpandableAdapter<GoodsShoppingCartListGroupBean, GoodsShoppingCartListChildBean> implements IGoodsShoppingCartChangeNumberView, IGoodsShoppingCartDeleteView {
    private CallBack callBack;
    private IGoodsShoppingCartChangeNumberPresenter goodsShoppingCartChangeNumberPresenter;
    private IGoodsShoppingCartDeletePresenter goodsShoppingCartDeletePresenter;

    public GoodsShoppingCartListAdapter(Context context) {
        super(context, R.layout.item_goods_shopping_list_group, R.layout.item_goods_shopping_list_item);
        goodsShoppingCartChangeNumberPresenter = new GoodsShoppingCartChangeNumberPresenterImpl(this);
        goodsShoppingCartDeletePresenter = new GoodsShoppingCartDeletePresenterImpl(this);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void setGroupView(final GoodsShoppingCartListGroupBean groupAllData, View convertView, final int groupPosition, boolean isExpanded) {
        TextView tvDrive = (TextView) convertView.findViewById(R.id.tv_drive);
        if (groupPosition == 0)
            tvDrive.setVisibility(View.GONE);
        else
            tvDrive.setVisibility(View.VISIBLE);

        LinearLayout llCheck = (LinearLayout) convertView.findViewById(R.id.ll_check);
        CheckBox check = (CheckBox) convertView.findViewById(R.id.check);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvChange = (TextView) convertView.findViewById(R.id.tv_change);

        if (groupData != null)
            tvName.setText(groupAllData.getClassName());
        //設置顯示模式
        if (groupAllData.isEditMode())
            tvChange.setText("完成");
        else
            tvChange.setText("编辑");

        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupAllData.setEditMode(!groupAllData.isEditMode());
                GoodsShoppingCartListAdapter.this.notifyDataSetChanged();
            }
        });

        //设置checkbox点击事件
        if (groupAllData.isCheck())
            check.setChecked(true);
        else
            check.setChecked(false);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked;
                if (groupAllData.isCheck())
                    isChecked = false;
                else
                    isChecked = true;
                groupData.get(groupPosition).setCheck(isChecked);
                for (GoodsShoppingCartListChildBean item : itemData.get(groupPosition)) {
                    item.setCheckGoods(isChecked);
                }
                getIsAllCheck();
                getSelectGoods();
                GoodsShoppingCartListAdapter.this.notifyDataSetChanged();
            }
        };
        check.setOnClickListener(onClickListener);
        llCheck.setOnClickListener(onClickListener);
    }

    @Override
    public void setItemView(final GoodsShoppingCartListChildBean itemAllData, View convertView, final int groupPosition, final int childPosition) {
        CheckBox check = (CheckBox) convertView.findViewById(R.id.check);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
        ImageView ivPackage = (ImageView) convertView.findViewById(R.id.iv_package);
        LinearLayout llModeEdit = (LinearLayout) convertView.findViewById(R.id.ll_mode_edit);
        LinearLayout llModeShow = (LinearLayout) convertView.findViewById(R.id.ll_mode_show);

        final GoodsDetailsListResultBean data = itemAllData.getResultBean();
        //设置图片
        Utils.loadPic(mContext, ivIcon, AppContansts.Goods_PicUrl + "/" + data.getTitle_img(), R.drawable.zhy_pic_loading);
        if (groupData.get(groupPosition).isEditMode()) {
            llModeShow.setVisibility(View.GONE);
            llModeEdit.setVisibility(View.VISIBLE);
            setEditMode(convertView, itemAllData, groupPosition);
        } else {
            llModeShow.setVisibility(View.VISIBLE);
            llModeEdit.setVisibility(View.GONE);
            setShowMode(convertView, itemAllData);
        }

        View.OnClickListener goodsDetailsClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GoodsDetailsListActivity.class);
                List<GoodsOrderItem> listData= DataUtils.packageGoodsToGoodsData(itemAllData.getResultBean().getGoods());
                intent.putExtra(IntentName.INTENT_LIST_DATA, (Serializable) listData);
                getContext().startActivity(intent);
            }
        };


        if (data.getIs_package() != null && data.getIs_package() == 1) {
            ivPackage.setVisibility(View.VISIBLE);
            llModeShow.setOnClickListener(goodsDetailsClick);
        } else if (data.getIs_package() != null && data.getIs_package() == 0) {
            ivPackage.setVisibility(View.GONE);
            llModeShow.setOnClickListener(null);
        } else {
            ivPackage.setVisibility(View.GONE);
            llModeShow.setOnClickListener(null);
        }

        //设置checkbox点击事件
        if (itemAllData.isCheckGoods())
            check.setChecked(true);
        else
            check.setChecked(false);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked;
                if (itemAllData.isCheckGoods())
                    isChecked = false;
                else
                    isChecked = true;
                itemData.get(groupPosition).get(childPosition).setCheckGoods(isChecked);
                boolean isAllChecked = true;
                for (GoodsShoppingCartListChildBean item : itemData.get(groupPosition)) {
                    if (!item.isCheckGoods()) {
                        isAllChecked = false;
                    }
                }
                if (isAllChecked)
                    groupData.get(groupPosition).setCheck(true);
                else
                    groupData.get(groupPosition).setCheck(false);
                getIsAllCheck();
                getSelectGoods();
                GoodsShoppingCartListAdapter.this.notifyDataSetChanged();
            }
        };
        check.setOnClickListener(onClickListener);
        convertView.setOnClickListener(onClickListener);
    }

    /**
     * 设置编辑界面
     *
     * @param convertView
     * @param itemAllData
     */
    private void setEditMode(View convertView, final GoodsShoppingCartListChildBean itemAllData, final int groupPosition) {
        final GoodsDetailsListResultBean data = itemAllData.getResultBean();

        final RelativeLayout rlNumberSubEdit = (RelativeLayout) convertView.findViewById(R.id.rl_number_sub_edit);
        final RelativeLayout rlNumberAddEdit = (RelativeLayout) convertView.findViewById(R.id.rl_number_add_edit);
        final TextView tvGoodsRemoveEdit = (TextView) convertView.findViewById(R.id.tv_goods_remove_edit);
        TextView tvGoodsNumberEdit = (TextView) convertView.findViewById(R.id.tv_goods_number_edit);
        TextView tvGoodsSpecEdit = (TextView) convertView.findViewById(R.id.tv_goods_spec_edit);


        if (data.getShoppingCartNumber() != null)
            tvGoodsNumberEdit.setText(data.getShoppingCartNumber() + "");
        if (data.getSpec_name() != null)
            tvGoodsSpecEdit.setText(data.getSpec_name());


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == rlNumberSubEdit) {
                    //减少
                    goodsShoppingCartChangeNumberPresenter.changeGoodsShoppingCartNumber
                            (data, data.getShoppingCartNumber() - 1);
                } else if (v == rlNumberAddEdit) {
                    //增加
                    goodsShoppingCartChangeNumberPresenter.changeGoodsShoppingCartNumber
                            (data, data.getShoppingCartNumber() + 1);
                } else if (v == tvGoodsRemoveEdit) {
                    //删除
                    goodsShoppingCartDeletePresenter.deleteGoodsShoppingCart(itemAllData, itemData.get(groupPosition));
                }
            }
        };
        rlNumberSubEdit.setOnClickListener(onClickListener);
        rlNumberAddEdit.setOnClickListener(onClickListener);
        tvGoodsRemoveEdit.setOnClickListener(onClickListener);
    }

    /**
     * 设置展示界面
     *
     * @param convertView
     * @param itemAllData
     */
    private void setShowMode(View convertView, GoodsShoppingCartListChildBean itemAllData) {
        final GoodsDetailsListResultBean data = itemAllData.getResultBean();

        TextView tvGoodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
        TextView tvGoodsSpec = (TextView) convertView.findViewById(R.id.tv_goods_spec);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);

        String goodsName = null;
        if (data.getGoods_name() != null)
            goodsName = data.getGoods_name();
        if (data.getPackage_name() != null)
            goodsName = data.getPackage_name();

        String goodsSlogan = null;
        if (data.getGoods_slogan() != null)
            goodsSlogan = data.getGoods_slogan();
        if (data.getPackage_slogan() != null)
            goodsSlogan = data.getPackage_slogan();

        StringBuffer name = new StringBuffer();
        if (goodsName != null)
            name.append(goodsName);
        if (goodsSlogan != null)
            name.append(" " + goodsSlogan);
        tvGoodsName.setText(name.toString());

        if (data.getSpec_name() != null)
            tvGoodsSpec.setText(data.getSpec_alias() + "：" + data.getSpec_name());

        if (data.getSpec_price() != null)
            tvPrice.setText("￥" + data.getSpec_price());

        if (data.getShoppingCartNumber() != null)
            tvNumber.setText("x" + data.getShoppingCartNumber());
    }

    /**
     * 設置是否全選
     *
     * @param isChecked
     */
    public void setAllChecked(boolean isChecked) {
        for (GoodsShoppingCartListGroupBean item : groupData) {
            item.setCheck(isChecked);
        }
        for (List<GoodsShoppingCartListChildBean> item : itemData) {
            for (GoodsShoppingCartListChildBean childItem : item) {
                childItem.setCheckGoods(isChecked);
            }
        }
        getIsAllCheck();
        getSelectGoods();
        this.notifyDataSetChanged();
    }

    /**
     * 獲取是否全選
     *
     * @return
     */
    public boolean getIsAllCheck() {
        boolean isAllCheck = true;
        for (List<GoodsShoppingCartListChildBean> item : itemData) {
            for (GoodsShoppingCartListChildBean childItem : item) {
                if (!childItem.isCheckGoods())
                    isAllCheck = false;
            }
        }
        if (callBack != null)
            callBack.isAllCheck(isAllCheck);
        return isAllCheck;
    }


    /**
     * 獲取選中商品
     */
    public List<GoodsShoppingCartListChildBean> getSelectGoods() {
        ArrayList<GoodsShoppingCartListChildBean> goodsList = new ArrayList<>();
        for (List<GoodsShoppingCartListChildBean> item : itemData) {
            for (GoodsShoppingCartListChildBean childItem : item) {
                if (childItem.isCheckGoods()) {
                    goodsList.add(childItem);
                }
            }
        }
        if (callBack != null)
            callBack.getSelectGoods(goodsList);
        return goodsList;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void changeShoppingCartNumberSuccess(GoodsShoppingCartChangeNumberResultBean resultBean) {
//        ToastUtils.show(getContext(), "数量修改成功");
        getSelectGoods();
        this.notifyDataSetChanged();
    }

    @Override
    public void changeShoppingCartNumberFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void deleteGoodsShoppingCartSuccess(GoodsShoppingCartDeleteResultBean resultBean) {
        getSelectGoods();
        clearUnnecessaryData();
        this.notifyDataSetChanged();
    }

    @Override
    public void deleteGoodsShoppingCartFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }


    /**
     * 去除空的数据
     */
    private void clearUnnecessaryData() {
        ArrayList<List<GoodsShoppingCartListChildBean>> tempChilds = new ArrayList<>();
        ArrayList<GoodsShoppingCartListGroupBean> tempGroups = new ArrayList<>();
        for (int i = 0; i < itemData.size(); i++) {
            List<GoodsShoppingCartListChildBean> items = itemData.get(i);
            if (items.size() == 0 || items == null) {
                tempChilds.add(items);
                tempGroups.add(groupData.get(i));
            }
        }
        groupData.removeAll(tempGroups);
        itemData.removeAll(tempChilds);
    }

    public interface CallBack {
        void isAllCheck(boolean isAllCheck);

        void getSelectGoods(ArrayList<GoodsShoppingCartListChildBean> selectGoods);
    }
}
