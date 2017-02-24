package com.shian.shianlife.activity.map;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.shian.shianlife.R;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;

public class NewMapChoiceActivity extends Activity  implements BaiduMap.OnMapClickListener{

    MapView mMapView;
    BaiduMap mBaiduMap;
    Button mBTSubmit;


    boolean isChoicePoint = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_map_choice);

        initView();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.map);
        mBTSubmit = (Button) findViewById(R.id.bt_submit);
        mBaiduMap=mMapView.getMap();

        mBTSubmit.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTSubmit) {
                if (isChoicePoint) {

                } else {
                    ToastUtils.show(NewMapChoiceActivity.this,"还没有选择需要修改的地点");
                }
            }
        }
    };


    /**
     * 初始化地图设置
     */
    private void initMap() {
        // 地图点击事件处理
        mBaiduMap.setOnMapClickListener(this);
        //设定中心点坐标
        LatLng cenpt = new LatLng(AppContansts.LOCAL_latitude, AppContansts.LOCAL_longitude);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(18).build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        //去掉百度图标
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        //去掉缩放按钮
        mMapView.showZoomControls(false);
        //隐藏比例尺
        mMapView.showScaleControl(false);
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
}
