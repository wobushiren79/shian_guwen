package com.shian.shianlife.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MapLocation;
import com.shian.shianlife.activity.updata.WSZDataActivity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MapSelectLayoutView extends LinearLayout implements Serializable {

    EditText mMapText;
    ImageView mMapData;
    ImageView mMapSelect;

    int numView;

    public static String THE_ACTION = "MapLocationData";

    public MapSelectLayoutView(Context context) {
        super(context);
    }

    public MapSelectLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);


    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mapcheck, this);
        mMapText = (EditText) view.findViewById(R.id.tv_map_text);
        mMapData = (ImageView) view.findViewById(R.id.iv_data);
        mMapSelect = (ImageView) view.findViewById(R.id.iv_map);

        mMapSelect.setOnClickListener(onClickListener);
        mMapData.setOnClickListener(onClickListener);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mMapData) {

            } else if (view == mMapSelect) {
                Intent intent = new Intent(getContext(), MapLocation.class);
                intent.putExtra("numView", numView);
                getContext().startActivity(intent);
            }
        }
    };

    private BroadcastReceiver locationDataReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("this", "onReceive");
            int num = intent.getIntExtra("numView", 0);
            if (num == numView) {
                String location = intent.getStringExtra("location");
                mMapText.setText(location);
            }
        }
    };


    public void setData(int numView) {
        this.numView = numView;
        IntentFilter filter = new IntentFilter();
        filter.addAction(THE_ACTION);
        filter.setPriority(Integer.MAX_VALUE);
        getContext().registerReceiver(locationDataReceiver, filter);
    }

    public String getLocation() {
        return mMapText.getText().toString();
    }

    public void setLocation(String location) {
        mMapText.setText(location);
    }

    public MapSelectLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
