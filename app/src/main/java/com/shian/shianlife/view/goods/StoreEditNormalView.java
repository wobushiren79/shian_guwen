package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

import butterknife.InjectView;


/**
 * Created by zm.
 */

public class StoreEditNormalView extends BaseLayout implements View.OnClickListener {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.ll_check)
    LinearLayout llCheck;
    @InjectView(R.id.iv_line)
    TextView ivLine;

    public static final int Mode_Edit = 0;
    public static final int Mode_Check = 1;

    private CallBack callBack;

    public StoreEditNormalView(Context context) {
        super(context, R.layout.layout_store_edit_normal_view);
    }

    public StoreEditNormalView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_store_edit_normal_view, attrs);
    }



    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setMode(int mode) {
        switch (mode) {
            case Mode_Edit:
                etContent.setVisibility(VISIBLE);
                etContent.setEnabled(true);
                llCheck.setVisibility(GONE);
                llCheck.setOnClickListener(null);
                break;

            case Mode_Check:
                etContent.setVisibility(VISIBLE);
                etContent.setEnabled(false);
                llCheck.setVisibility(VISIBLE);
                llCheck.setOnClickListener(this);
                break;
        }
    }

    @Override
    protected void initView() {
        tvTitle.setText(titleName);
        etContent.setText(contentText);
        etContent.setHint(hintText);
        if (visibilityLine)
            ivLine.setVisibility(GONE);
        else
            ivLine.setVisibility(VISIBLE);
        setIsEnabled(!showMode);
        setMode(Mode_Edit);
    }


    @Override
    protected void initData() {
    }

    public void setData(String data) {
        etContent.setText(data);
    }

    public String getData() {
        return etContent.getText().toString();
    }

    /**
     * 设置编辑状态
     *
     * @param enabled
     */
    public void setIsEnabled(boolean enabled) {
        etContent.setEnabled(enabled);
        if (enabled) {
            etContent.setGravity(Gravity.LEFT);
        } else {
            etContent.setGravity(Gravity.RIGHT);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == llCheck) {
            if (callBack != null)
                callBack.onCheck(this);
        }
    }

    public interface CallBack {
        void onCheck(View view);
    }
}
