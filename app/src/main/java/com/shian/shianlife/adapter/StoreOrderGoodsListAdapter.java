package com.shian.shianlife.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseExpandableAdapter;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.goods.bean.GoodsPerform;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformResultBean;
import com.shian.shianlife.mvp.goods.presenter.IStoreOrderGetPerformPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.StoreOrderGetPerformPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IStoreOrderGetPerformView;
import com.shian.shianlife.thisenum.GoodsPerformStatusEnum;
import com.shian.shianlife.thisenum.GoodsPerformWayEnum;
import com.shian.shianlife.view.dialog.DataShowDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class StoreOrderGoodsListAdapter extends BaseExpandableAdapter<String, GoodsItemPerform> implements IStoreOrderGetPerformView {

    private IStoreOrderGetPerformPresenter storeOrderGetPerformPresenter;

    public StoreOrderGoodsListAdapter(Context context) {
        super(context, R.layout.store_order_expendlist_group, R.layout.store_order_expendlist_item);
        storeOrderGetPerformPresenter = new StoreOrderGetPerformPresenterImpl(this);
    }

    @Override
    public void setGroupView(String groupData, View convertView, int groupPosition, boolean isExpanded) {
        TextView content = (TextView) convertView.findViewById(R.id.tv_content);
        content.setText(groupData);
    }

    @Override
    public void setItemView(final GoodsItemPerform itemData, View convertView, int groupPosition, final int childPosition) {
        ImageView ivGoodsPic = (ImageView) convertView.findViewById(R.id.iv_goods_pic);
        TextView tvGoodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
        TextView tvGoodsSpecification = (TextView) convertView.findViewById(R.id.tv_goods_specification);
        TextView tvGoodsCustomerMoney = (TextView) convertView.findViewById(R.id.tv_goods_customer_money);
        TextView tvGoodsCounselorMoney = (TextView) convertView.findViewById(R.id.tv_goods_counselor_money);
        TextView tvGoodsNumb = (TextView) convertView.findViewById(R.id.tv_goods_numb);
        TextView tvPerformStatus = (TextView) convertView.findViewById(R.id.tv_perform_status);

        tvGoodsName.setText(itemData.getSpecOrderedVolume());
        tvGoodsSpecification.setText(itemData.getSpecAlias() + "：" + itemData.getSpecName());

        if (itemData.getEmentPrice() == null)
            tvGoodsCustomerMoney.setText("客户￥：" + "未知");
        else
            tvGoodsCustomerMoney.setText("客户￥：" + (float) itemData.getSpecOrderedPrice() / 100f);

        if (itemData.getAdviserPrice() == null)
            tvGoodsCounselorMoney.setText("顾问￥：" + "未知");
        else
            tvGoodsCounselorMoney.setText("顾问￥：" + (float) itemData.getAdviserPrice() / 100f);


        tvGoodsNumb.setText("x" + itemData.getSpecOrderedNum());
        Utils.loadPic(mContext, ivGoodsPic, AppContansts.Goods_PicUrl + "/" + itemData.getTitleImg());
        if (itemData.getGoodsPerform() != null) {
            final GoodsPerform goodsPerform = itemData.getGoodsPerform();
            tvPerformStatus.setText(GoodsPerformStatusEnum.getValueText(goodsPerform.getPerformStatus()));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeOrderGetPerformPresenter.getPerformInfo(goodsPerform.getId());
                }
            });
        }
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void getPerformInfoSuccess(StoreOrderGetPerformResultBean resultBean) {
        List<DataShowDialog.DataShowDialogResultBean> listData = new ArrayList<>();
        if (resultBean.getGoodsPerform() != null) {
            StoreOrderGetPerformResultBean.GoodsPerform goodsPerform = resultBean.getGoodsPerform();
            listData.add(new DataShowDialog.DataShowDialogResultBean("执行方式", GoodsPerformWayEnum.getValueText(goodsPerform.getPerformWay())));
            listData.add(new DataShowDialog.DataShowDialogResultBean("执行人姓名", goodsPerform.getPerformUserName()));
            listData.add(new DataShowDialog.DataShowDialogResultBean("执行人电话", goodsPerform.getPerformUserPhone()));
            listData.add(new DataShowDialog.DataShowDialogResultBean("备注", goodsPerform.getPerformComment()));
        }
        if (resultBean.getGoodsExpress() != null) {
            StoreOrderGetPerformResultBean.GoodsExpress goodsExpress = resultBean.getGoodsExpress();
            listData.add(new DataShowDialog.DataShowDialogResultBean("快递公司", goodsExpress.getExpressName()));
            listData.add(new DataShowDialog.DataShowDialogResultBean("快递单号", goodsExpress.getDeliveryNumber()));
        }
        DataShowDialog dataShowDialog = new DataShowDialog(mContext);
        dataShowDialog.setTitle("执行信息");
        dataShowDialog.setData(listData);
        dataShowDialog.setCancelOnClick(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dataShowDialog.show();
    }

    @Override
    public void getPerformInfoFail(String msg) {
        ToastUtils.show(mContext, msg);
    }

}
