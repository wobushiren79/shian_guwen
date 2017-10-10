package com.shian.shianlife.view.goods;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.view.dialog.GoodsSelectDialog;

/**
 * Created by zm.
 */

public class GoodsSpecSelectDialogView extends BaseLayout implements View.OnClickListener, GoodsSelectDialog.CallBack {
    public GoodsSpecSelectDialogView(Context context) {
        this(context, null);
    }

    public GoodsSpecSelectDialogView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_spec_select_dialog, attrs);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        this.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        GoodsSelectDialog dialog = new GoodsSelectDialog(getContext());
        dialog.setCallBack(this);
        dialog.show();
    }

    @Override
    public void onSubmitClick(Dialog dialog) {

    }
}
