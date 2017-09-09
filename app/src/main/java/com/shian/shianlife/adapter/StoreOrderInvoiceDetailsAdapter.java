package com.shian.shianlife.adapter;

import android.content.Context;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.mvp.goods.bean.GoodsInvoiceDetailsItem;


/**
 * Created by zm.
 */

public class StoreOrderInvoiceDetailsAdapter extends BaseRCAdapter<GoodsInvoiceDetailsItem> {

    /**
     * 单布局初始化
     *
     * @param context
     */
    public StoreOrderInvoiceDetailsAdapter(Context context) {
        super(context, R.layout.layout_store_order_invoice_item);
    }


    @Override
    public void convert(BaseViewHolder holder, GoodsInvoiceDetailsItem data, int index) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);
        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getContent());
    }

}
