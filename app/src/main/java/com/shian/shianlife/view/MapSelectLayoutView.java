package com.shian.shianlife.view;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MapLocation;
import com.shian.shianlife.activity.updata.WSZDataActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MapSelectLayoutView extends LinearLayout implements Serializable {

    EditText mMapText;
    ImageView mMapData;
    ImageView mMapSelect;

    int numView;
    List<String> listData=new ArrayList<>();

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
                listCheck();
            } else if (view == mMapSelect) {
                mapCheck();
            }
        }
    };

    /**
     * 选择已有地址
     */
    private void listCheck() {
        if (listData != null && listData.size() > 0) {
            Log.v("this","listData.size()"+listData.size());
            final AlertDialog dialog = new AlertDialog.Builder(getContext())
                    .setTitle("选择地址")
                    .create();
            ListView dataListview = new ListView(getContext());
            dataListview.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return listData.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    TextView tvData = new TextView(getContext());
                    tvData.setText(listData.get(position));
                    tvData.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMapText.setText(listData.get(position));
                            if (dialog != null) {
                                dialog.cancel();
                            }

                        }
                    });
                    return tvData;
                }
            });
            dialog.setView(dataListview);
            dialog.show();
        }
    }

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
            Log.v("this", "onReceive");
            int num = intent.getIntExtra("numView", 0);
            if (num == numView) {
                String location = intent.getStringExtra("location");
                mMapText.setText(location);
            }
        }
    };


    public void setData(int numView, List<String> listData) {
        this.numView = numView;
        this.listData = listData;
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
