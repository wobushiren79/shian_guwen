package com.shian.shianlife.view.writeview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.map.MapLocation;


import java.util.List;


/**
 * Created by Administrator on 2017/3/17.
 */

public class MapSelectViewNormal extends BaseWriteView {
    View view;
    TextView mTVIsImportant;
    TextView mTVTitleName;
    AutoCompleteTextView mAutoTextView;
    ImageView mIVMapCheck;

    private ArrayAdapter<String> arrayAdapter;
    private int numView;
    public static String THE_ACTION = "MapLocationData";
    private String longitude = "";
    private String latitude = "";
    public MapSelectViewNormal(Context context) {
        this(context, null);
    }

    public MapSelectViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.zhy_write_mapselect_normal, this);
        initView();
        initData();
    }

    private void initView() {
        mTVIsImportant = (TextView) view.findViewById(R.id.tv_important);
        mTVTitleName = (TextView) view.findViewById(R.id.tv_titlename);
        mAutoTextView = (AutoCompleteTextView) view.findViewById(R.id.auto_textview);
        mIVMapCheck = (ImageView) view.findViewById(R.id.iv_mapcheck);

        mIVMapCheck.setOnClickListener(onClickListener);
        mAutoTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoTextView.showDropDown();
            }
        });
    }

    private void initData() {
        mTVTitleName.setText(titleName);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(THE_ACTION);
        filter.setPriority(Integer.MAX_VALUE);
        getContext().registerReceiver(locationDataReceiver, filter);
    }

    public void initAutoTextView(String[] arr) {
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arr);
        mAutoTextView.setAdapter(arrayAdapter);
    }

    public void initAutoTextView(List<String> arr) {
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arr);
        mAutoTextView.setAdapter(arrayAdapter);
    }

    public String getData() {
        return mAutoTextView.getText().toString();
    }
    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setData(String location) {
        mAutoTextView.setText(location);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVMapCheck) {
                mapCheck();
            }
        }
    };

    /**
     * 通过地图得到地址
     */
    private void mapCheck() {
        Intent intent = new Intent(getContext(), MapLocation.class);
        intent.putExtra("numView", numView);
        getContext().startActivity(intent);
    }

    private BroadcastReceiver locationDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int num = intent.getIntExtra("numView", 0);
            if (num == numView) {
                String location = intent.getStringExtra("location");
                longitude = intent.getStringExtra("longitude");
                latitude = intent.getStringExtra("latitude");
                mAutoTextView.setText(location);
            }
        }
    };

    public void setNumView(int numView) {
        this.numView = numView;
    }
}
