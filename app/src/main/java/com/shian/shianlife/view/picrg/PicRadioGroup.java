package com.shian.shianlife.view.picrg;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shian.shianlife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class PicRadioGroup extends LinearLayout implements RadioGroup.OnCheckedChangeListener {
    private View mView;
    private RadioGroup mRGContent;
    private List<RadioButton> mRBList;

    private CallBack callBack;

    public PicRadioGroup(Context context) {
        super(context);
    }

    public PicRadioGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = View.inflate(context, R.layout.view_pic_radio_group_layout, this);
        initView();
        initData();
    }

    private void initView() {
        mRGContent = (RadioGroup) mView.findViewById(R.id.rg_content);
    }


    private void initData() {
        mRBList = new ArrayList<>();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setData(int number) {
        mRBList.clear();
        mRGContent.removeAllViews();
        int dp16 = getResources().getDimensionPixelOffset(R.dimen.dimen_16dp);
        for (int i = 0; i < number; i++) {
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(dp16, dp16);
            layoutParams.leftMargin = dp16 / 2;
            layoutParams.rightMargin = dp16 / 2;
            RadioButton rb = new RadioButton(getContext());
            rb.setLayoutParams(layoutParams);
            rb.setBackgroundResource(R.drawable.zhy_radio_button_style_1);
            rb.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
            mRGContent.addView(rb);
            mRBList.add(rb);
        }

        mRGContent.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        for (int i = 0; i < mRBList.size(); i++) {
            RadioButton rb = mRBList.get(i);
            if (rb.getId() == checkedId) {
                if (callBack != null)
                    callBack.checkChange(PicRadioGroup.this, i);
            }
        }
    }

    public interface CallBack {
        void checkChange(View rgView, int number);
    }

    public void setSelectItem(int num) {
        if (num <= (mRBList.size() - 1)) {
            RadioButton rb = (RadioButton) mRGContent.getChildAt(num);
            rb.setChecked(true);
        }
    }
}
