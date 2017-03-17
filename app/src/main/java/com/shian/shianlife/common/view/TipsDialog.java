package com.shian.shianlife.common.view;

import com.shian.shianlife.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TipsDialog extends Dialog {

    private DisplayMetrics outMetrics;

    @InjectView(R.id.tv_title)
    TextView tv_title;

    @InjectView(R.id.tv_btn1)
    TextView tv_btn1;

    @InjectView(R.id.tv_btn2)
    TextView tv_btn2;

    String title;

    String btn1Text;

    String btn2Text;

    OnClickListener topClickListener;

    OnClickListener bottomClickListener;

    public TipsDialog(Context context) {
        super(context, R.style.tipsDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tips);
        ButterKnife.inject(this);
        tv_title.setText(title);
        tv_btn1.setText(btn1Text);
        tv_btn2.setText(btn2Text);
    }

    /**
     * 设置标题栏
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置按钮1
     *
     * @param text
     * @param onClickListener
     */
    public void setTopButton(String text, OnClickListener onClickListener) {
        btn1Text = text;
        topClickListener = onClickListener;
    }

    /**
     * 设置按钮2
     *
     * @param text
     * @param onClickListener
     */
    public void setBottomButton(String text, OnClickListener onClickListener) {
        btn2Text = text;
        bottomClickListener = onClickListener;
    }

    @OnClick(R.id.tv_btn1)
    void topClick() {
        cancel();
        if (topClickListener != null) {
            topClickListener.onClick(this, 0);
        }
    }

    @OnClick(R.id.tv_btn2)
    void bottomClick() {
        cancel();
        if (bottomClickListener != null) {
            bottomClickListener.onClick(this, 0);
        }
    }

    @Override
    public void show() {
        super.show();
//        outMetrics = new DisplayMetrics();
//        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
//        LayoutParams params = getWindow().getAttributes();
//        params.width = (int) (outMetrics.widthPixels * 0.67);
//        params.height = (int) (outMetrics.heightPixels * 0.41);
////        params.height = LayoutParams.WRAP_CONTENT;
//        params.gravity = Gravity.CENTER;
//        getWindow().setAttributes(params);

    }

}
