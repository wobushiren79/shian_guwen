package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/21.
 */

public class BaseDialog extends Dialog {
    View view;

    TextView mTVCancel;
    TextView mTVSubmit;
    TextView mTVTitle;

    RelativeLayout mRLContent;




    public BaseDialog(Context context) {
        super(context, R.style.CustomDialog);
        view = View.inflate(context, R.layout.dialog_base, null);
        setContentView(view);
        initView();
    }

    private void initView() {
        mTVCancel = (TextView) view.findViewById(R.id.cancel);
        mTVSubmit = (TextView) view.findViewById(R.id.submit);
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mRLContent = (RelativeLayout) view.findViewById(R.id.rl_content);
    }


    public void setTitle(String title) {
        mTVTitle.setText(title);
    }

    public void setCancelName(String name) {
        mTVCancel.setText(name);
    }

    public void setSubmit(String name) {
        mTVSubmit.setText(name);
    }

    public  void setContent(View contentView) {
        mRLContent.addView(contentView);
    }




}
