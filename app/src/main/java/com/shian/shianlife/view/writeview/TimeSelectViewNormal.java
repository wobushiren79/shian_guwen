package com.shian.shianlife.view.writeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.SaveTalkFailActivity;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TimeSelectViewNormal extends BaseWriteView {
    View view;
    TextView mTVIsImportant;
    TextView mTVTitleName;
    TextView mTVTime;

    public TimeSelectViewNormal(Context context) {
        this(context, null);
    }

    public TimeSelectViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.zhy_write_timeselect_normal, this);
        initView();
        initData();
    }

    private void initView() {
        mTVIsImportant = (TextView) view.findViewById(R.id.tv_important);
        mTVTitleName = (TextView) view.findViewById(R.id.tv_titlename);
        mTVTime = (TextView) view.findViewById(R.id.tv_time);

        mTVTime.setOnClickListener(onClickListener);
    }

    private void initData() {
        mTVTitleName.setText(titleName);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVTime) {
                setTime();
            }
        }
    };

    /**
     * 设置洽谈时间
     */
    private void setTime() {
        //设置二次洽谈时间
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
                .getInstance(getContext());
        dialog.show();
        dialog.setOnSaveListener(new DateTimeSelectorDialogBuilder.OnSaveListener() {
            @Override
            public void onSaveSelectedDate(String selectedDate) {
                // TODO Auto-generated method stub
                mTVTime.setText(selectedDate);
            }
        });
    }

    public String getData() {
        return mTVTime.getText().toString();
    }

    public void setData(String time) {
        mTVTime.setText(time);
    }
}
