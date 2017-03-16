package com.shian.shianlife.view.writeview;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator
 */

public class EditTextViewNormal extends BaseWriteView {
    View view;
    TextView mTVTitleName;
    TextView mTVIsImportant;
    EditText mETInput;


    public EditTextViewNormal(Context context) {
        this(context,null);
    }

    public EditTextViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);

        view = View.inflate(context, R.layout.zhy_write_edittextview_normal, this);
        initView();
        initData();
    }

    private void initView() {
        mTVIsImportant = (TextView) view.findViewById(R.id.tv_important);
        mTVTitleName = (TextView) view.findViewById(R.id.tv_titlename);
        mETInput = (EditText) view.findViewById(R.id.et_input);
    }

    private void initData() {
        mTVTitleName.setText(titleName);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }
        if(isLonger){
            mETInput.setLines(3);
        }
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
        initData();
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
        initData();
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
        initData();
    }

    public String getData() {
        return mETInput.getText().toString();
    }
}
