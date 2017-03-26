package com.shian.shianlife.view.writeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/26.
 */

public class CheckBoxViewNormal extends LinearLayout {
    View view;
    CheckBox mCBCheck;
    TextView mTVLeft;
    TextView mTVRight;
    CheckBoxCheckChangeListener checkBoxCheckChangeListener;

    public CheckBoxViewNormal(Context context) {
        this(context, null);
    }

    public CheckBoxViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.zhy_write_checkbox_normal, this);
        initView();
    }

    private void initView() {
        mTVLeft = (TextView) view.findViewById(R.id.tv_left);
        mTVRight = (TextView) view.findViewById(R.id.tv_right);
        mCBCheck = (CheckBox) view.findViewById(R.id.cb_check);

        mCBCheck.setOnCheckedChangeListener(onCheckedChangeListListener);
    }

    public void setCheckBoxCheckChangeListener(CheckBoxCheckChangeListener checkBoxCheckChangeListener) {
        this.checkBoxCheckChangeListener = checkBoxCheckChangeListener;
    }

    public boolean isChecked() {
        return mCBCheck.isChecked();
    }

    public void setChecked(boolean b) {
        mCBCheck.setChecked(b);
    }

    public void setLeftText(String name) {
        mTVLeft.setText(name);
    }

    public void setRightText(String name) {
        mTVRight.setText(name);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (checkBoxCheckChangeListener != null) {
                checkBoxCheckChangeListener.onCheckedChanged(buttonView, isChecked);
            }
        }
    };

    public interface CheckBoxCheckChangeListener {
        void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
    }
}

