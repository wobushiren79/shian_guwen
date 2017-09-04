package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by zm.
 */

public class GoodsNumberChangeView extends BaseLayout implements TextWatcher {

    @InjectView(R.id.iv_sub)
    ImageView ivSub;
    @InjectView(R.id.et_number)
    EditText etNumber;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;

    private Integer number;
    private CallBack callBack;

    public GoodsNumberChangeView(Context context) {
        this(context, null);
    }

    public GoodsNumberChangeView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.view_good_number_change, attrs);

    }

    @Override
    protected void initView() {
        etNumber.addTextChangedListener(this);
    }

    @Override
    protected void initData() {

    }

    public void setData(Integer number) {
        this.number = number;
        etNumber.setText(number + "");
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @OnClick({R.id.iv_sub, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sub:
                subNumber();
                break;
            case R.id.iv_add:
                addNumber();
                break;
        }
    }

    private void subNumber() {
        if (number > 1) {
            number--;
            etNumber.setText(number + "");
        }
    }

    private void addNumber() {
        number++;
        etNumber.setText(number + "");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().equals("")) {
            number = 1;
            etNumber.setText(number + "");
        } else {
            number = Integer.valueOf(s.toString());
            if (callBack != null)
                callBack.numberChange(this, number);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    public interface CallBack {
        void numberChange(View view, Integer number);
    }
}
