package com.shian.shianlife.activity.map;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.CemeteryServiceActivity;
import com.shian.shianlife.activity.order.FuneralServiceActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpChangeLocation;
import com.shian.shianlife.view.dialog.MapEditModeDialog;

import okhttp3.Request;

public class NewMapChoiceActivity extends Activity implements BaiduMap.OnMapClickListener {

    MapView mMapView;
    BaiduMap mBaiduMap;
    Button mBTSubmit;
    Button mBTBack;
    Button mBTMyLocation;
    Button mBTET;


    MapPoi mapPoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_map_choice);

        initView();
        initMap();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.map);
        mBTSubmit = (Button) findViewById(R.id.bt_submit);
        mBTBack = (Button) findViewById(R.id.map_back);
        mBTMyLocation = (Button) findViewById(R.id.map_mylocation);
        mBTET = (Button) findViewById(R.id.map_et);

        mBaiduMap = mMapView.getMap();

        mBTSubmit.setOnClickListener(onClickListener);
        mBTBack.setOnClickListener(onClickListener);
        mBTMyLocation.setOnClickListener(onClickListener);
        mBTET.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTSubmit) {
                dataSubmit();
            } else if (view == mBTBack) {
                finish();
            } else if (view == mBTMyLocation) {
                backMyLocation();
            } else if (view == mBTET) {
                inputMode();
            }
        }
    };

    /**
     * 输入框模式
     */
    private void inputMode() {
        MapEditModeDialog dialog = new MapEditModeDialog(NewMapChoiceActivity.this, R.style.CustomDialog);
        dialog.setCallBack(new MapEditModeDialog.MapEditModeDialogCallBack() {
            @Override
            public void changeData(String location) {
                changePoint(location);
            }
        });
        dialog.show();
    }


    /**
     * 数据提交
     */
    private void dataSubmit() {
        if (mapPoi != null) {
            changePoint(mapPoi.getName());
        } else {
            ToastUtils.show(NewMapChoiceActivity.this, "还没有选择需要修改的地点");
        }
    }


    /**
     * 改变地点
     */
    private void changePoint(String location) {
        HpChangeLocation params = new HpChangeLocation();
        params.setConsultId(NewRoutePlanOtherActivity.consultId);
        params.setOperationType(NewRoutePlanOtherActivity.locationType);
        params.setAddressDetail(location);
        MHttpManagerFactory.getFuneralManager().changeLocation(NewMapChoiceActivity.this, params, new HttpResponseHandler<Object>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(NewMapChoiceActivity.this, "修改地址成功");
                FuneralServiceActivity.C_bOrder_isRefresh = true;
                CemeteryServiceActivity.C_bOrder_isRefresh = true;
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(NewMapChoiceActivity.this, "修改地址失败");
            }
        });
    }


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
        this.mapPoi = mapPoi;
        //获取经纬度
        double latitude = mapPoi.getPosition().latitude;
        double longitude = mapPoi.getPosition().longitude;
        LatLng point = new LatLng(latitude, longitude);
        mBaiduMap.clear();
        drawLocation(point);
        return false;
    }

    /**
     * 绘制地点
     *
     * @param locationLatLng
     */
    private void drawLocation(LatLng locationLatLng) {
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.zhy_map_point_2);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(locationLatLng)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBTSubmit.setText("确认");
    }

    /**
     * 返回我的位置
     */
    private void backMyLocation() {
        if (mapPoi != null) {
            MapStatus mMapStatus = new MapStatus.Builder().target(mapPoi.getPosition()).zoom(18).build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            mBaiduMap.setMapStatus(mMapStatusUpdate);
        }
    }
}
