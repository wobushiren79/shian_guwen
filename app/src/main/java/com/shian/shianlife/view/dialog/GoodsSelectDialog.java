package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by zm.
 */

public class GoodsSelectDialog extends Dialog implements View.OnClickListener {

    private TextView tvSubmit;
    private CallBack callBack;

    public GoodsSelectDialog(@NonNull Context context) {
        super(context, R.style.GoodsSelectDialog);
        setContentView(R.layout.dialog_goods_select);
        initView();
    }


    private void initView() {
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);

        Window window = this.getWindow();
        //设置弹出动画
        window.setWindowAnimations(R.style.goodsSelectDialogWindowAnim);

        //设置dialog大小
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        this.getWindow().setAttributes(params);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void cancel() {
        super.cancel();
        if (callBack != null)
            callBack.onSubmitClick(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvSubmit) {
            submit();
        }
    }

    /**
     * 提交
     */
    private void submit() {
        this.cancel();
    }

    public interface CallBack {
        void onSubmitClick(Dialog dialog);
    }
}
