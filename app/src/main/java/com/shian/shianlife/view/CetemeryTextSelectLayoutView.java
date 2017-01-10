package com.shian.shianlife.view;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */

public class CetemeryTextSelectLayoutView extends LinearLayout {
    private View view;

    TextView mTVName;
    Spinner mSPContent;
    ImageView mIVSelect;
    ArrayAdapter<String> adapter;

    List<String> data = new ArrayList<>();

    public CetemeryTextSelectLayoutView(Context context) {
        this(context, null);
    }

    public CetemeryTextSelectLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public void setName(String name) {
        mTVName.setText(name);
    }

    public void setData(List<String> data) {
        this.data = data;

        //适配器
        adapter= new ArrayAdapter<String>(getContext(), R.layout.spinner_item_1, data);
        //设置样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        mSPContent.setAdapter(adapter);
    }

    private void initView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_cemetery_text_select, this);

        mTVName = (TextView) view.findViewById(R.id.tv_name);
        mSPContent = (Spinner) view.findViewById(R.id.sp_content);
        mIVSelect = (ImageView) view.findViewById(R.id.iv_select);

        mIVSelect.setOnClickListener(onClickListener);
    }


    OnClickListener onClickListener=new OnClickListener() {
        @Override
        public void onClick(View view) {
             if(view==mIVSelect){
                 mSPContent.performClick();
             }
        }
    };
}
