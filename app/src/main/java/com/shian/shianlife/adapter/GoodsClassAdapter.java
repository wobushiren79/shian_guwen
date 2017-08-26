package com.shian.shianlife.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.utils.StringUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;

/**
 * Created by zm.
 */

public class GoodsClassAdapter extends BaseRCAdapter<GoodsClassResultBean> {
    private Integer checkNumber = 0;
    private CallBack callBack;

    /**
     * 单布局初始化
     *
     * @param context
     */
    public GoodsClassAdapter(Context context) {
        super(context, R.layout.item_goods_class_list);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void convert(BaseViewHolder holder, GoodsClassResultBean goodsClassResultBean, final int index) {
        LinearLayout llContent = holder.getView(R.id.ll_content);
        TextView tvName = holder.getView(R.id.tv_name);
        String name = goodsClassResultBean.getName();
        if (!StringUtils.isEmpty(name))
            tvName.setText(name);
        else
            tvName.setText("未知");

        if (checkNumber == index) {
            llContent.setBackgroundColor(mContext.getResources().getColor(R.color.zhy_backgroud_1));
        } else {
            llContent.setBackgroundColor(Color.WHITE);
        }

        llContent.setOnClickListener(onCheck(index));
    }

    @NonNull
    private View.OnClickListener onCheck(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectItem(index);
            }
        };
    }

    public void setSelectItem(int index) {
        if (mDatas.size() > 0 && index <= (mDatas.size() - 1)) {
            checkNumber = index;
            GoodsClassAdapter.this.notifyDataSetChanged();
            if (callBack != null)
                callBack.selectItem(index, mDatas.get(index));
        }
    }

    public interface CallBack {
        void selectItem(int index, GoodsClassResultBean data);
    }
}
