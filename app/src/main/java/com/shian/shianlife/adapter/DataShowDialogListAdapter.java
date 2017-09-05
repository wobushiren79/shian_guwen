package com.shian.shianlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.view.dialog.DataShowDialog;

/**
 * Created by zm.
 */

public class DataShowDialogListAdapter extends BaseRCAdapter<DataShowDialog.DataShowDialogResultBean> {
    /**
     * 单布局初始化
     *
     * @param context
     */
    public DataShowDialogListAdapter(Context context) {
        super(context, R.layout.dialog_show_data_layout_item);
    }

    @Override
    public void convert(BaseViewHolder holder, DataShowDialog.DataShowDialogResultBean content, int index) {
        int dp38 = mContext.getResources().getDimensionPixelOffset(R.dimen.dimen_38dp);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp38);
        holder.itemView.setLayoutParams(layoutParams);

        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);

        tvTitle.setText(content.getTitle());
        tvContent.setText(content.getContent());
    }
}
