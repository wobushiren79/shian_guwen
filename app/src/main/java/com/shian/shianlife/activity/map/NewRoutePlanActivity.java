package com.shian.shianlife.activity.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.shian.shianlife.R;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.mapapi.RouteLineAdapter;
import com.shian.shianlife.mapapi.overlayutil.DrivingRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.OverlayManager;
import com.shian.shianlife.mapapi.overlayutil.TransitRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.WalkingRouteOverlay;
import com.shian.shianlife.view.dialog.MapLineChoiceDialog;
import com.shian.shianlife.view.dialog.MapMoreDialog;

public class NewRoutePlanActivity extends Activity implements BaiduMap.OnMapClickListener, OnGetRoutePlanResultListener {
    Button mMapBack;
    Button mMapMore;
    Button mResetLocation;
    Button mMapMyLocation;

    ImageView mMapWalk;
    ImageView mMapBus;
    ImageView mMapDrive;

    MapView mMapView;

    BaiduMap mBaiduMap;

    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用

    private final static double mapCenterlatitude = 30.6634450000;
    private final static double mapCenterlongitude = 104.0722210000;

    String endPointStr;
    LatLng endPointLatLng;
    boolean isUseNumPoint = false;//是否使用经纬来搜索
    boolean isFirstSearch = true;//是否是第一次搜索

    int nowSearchType = 3;//当前的搜索状态。0跨城公交 1开车 2公车 3走路 4自行车

    CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route_plan);

        initView();//初始化控件
        initMap();//初始化地图设置
        initData();//初始化数据
        //开始搜索
        searchProcess(null);
    }

    private void initData() {
        //初始线路获取传过来的地址
        endPointStr = "";
        endPointStr = getIntent().getStringExtra("RoutePlanLocation");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mMapBack = (Button) findViewById(R.id.map_back);
        mMapMore = (Button) findViewById(R.id.map_more);
        mResetLocation = (Button) findViewById(R.id.map_resetlocation);
        mMapMyLocation = (Button) findViewById(R.id.map_mylocation);

        mMapWalk = (ImageView) findViewById(R.id.map_walk);
        mMapBus = (ImageView) findViewById(R.id.map_bus);
        mMapDrive = (ImageView) findViewById(R.id.map_drive);

        mMapView = (MapView) findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();

        mMapBack.setOnClickListener(onClickListener);
        mMapMore.setOnClickListener(onClickListener);
        mMapWalk.setOnClickListener(onClickListener);
        mMapBus.setOnClickListener(onClickListener);
        mMapDrive.setOnClickListener(onClickListener);
        mResetLocation.setOnClickListener(onClickListener);
        mMapMyLocation.setOnClickListener(onClickListener);
    }

    /**
     * 初始化地图设置
     */
    private void initMap() {
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
        // 地图点击事件处理
        mBaiduMap.setOnMapClickListener(this);
        //设定中心点坐标
        LatLng cenpt = new LatLng(mapCenterlatitude, mapCenterlongitude);
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
        //绘制我的位置
        drawMyLocation();
        //去掉缩放按钮
        mMapView.showZoomControls(false);
        //隐藏比例尺
        mMapView.showScaleControl(false);
        //设置为我的位置
        backMyLocation();
    }


    /**
     * 搜索
     *
     * @param v
     */
    private void searchProcess(View v) {
        // 处理搜索按钮响应
        // 设置起终点信息，对于tranist search 来说，城市名无意义
        LatLng latLngStart = new LatLng(AppContansts.LOCAL_latitude, AppContansts.LOCAL_longitude);
        PlanNode stNode = PlanNode.withLocation(latLngStart);
        PlanNode enNode = null;
        if (isUseNumPoint) {
            if (endPointLatLng == null) {
                ToastUtils.show(NewRoutePlanActivity.this, "还没有选择终点");
                return;
            }
            enNode = PlanNode.withLocation(endPointLatLng);
        } else {
            enNode = PlanNode.withCityNameAndPlaceName(AppContansts.LOCAL_CITY, endPointStr);
        }
        // 重置浏览节点的路线数据
        mBaiduMap.clear();
        // 实际使用中请对起点终点城市进行正确的设定
        if (v == null) {
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode).to(enNode));
            nowSearchType = 3;
        } else if (v.getId() == R.id.map_drive) {
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(stNode).to(enNode));
            nowSearchType = 1;
        } else if (v.getId() == R.id.map_bus) {
            mSearch.transitSearch((new TransitRoutePlanOption())
                    .from(stNode).city(AppContansts.LOCAL_CITY).to(enNode));
            nowSearchType = 2;
        } else if (v.getId() == R.id.map_walk) {
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode).to(enNode));
            nowSearchType = 3;
        }
        //        else if (v.getId() == R.id.mass) {
//            PlanNode stMassNode = PlanNode.withCityNameAndPlaceName("北京", "天安门");
//            PlanNode enMassNode = PlanNode.withCityNameAndPlaceName("上海", "东方明珠");
//            mSearch.masstransitSearch(new MassTransitRoutePlanOption().from(stMassNode).to(enMassNode));
//            nowSearchType = 0;
//        }
//        else if (v.getId() == R.id.bike) {
//            mSearch.bikingSearch((new BikingRoutePlanOption())
//                    .from(stNode).to(enNode));
//            nowSearchType = 4;
//        }
        //绘制我的位置
        drawMyLocation();
        dialog = new CustomDialog(NewRoutePlanActivity.this);
        dialog.show();
    }

    /**
     * 更多操作
     */
    private void moreOperate() {
        MapMoreDialog dialog=new MapMoreDialog(NewRoutePlanActivity.this,R.style.CustomDialog);
        dialog.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mMapBack) {
                //结束
                finish();
            } else if (v == mMapMore) {
                moreOperate();
            } else if (v == mMapMyLocation) {
                //返回我的位置
                backMyLocation();
            } else if (v == mResetLocation) {
                setPointMode();
            } else if (v == mMapWalk) {
                searchProcess(mMapWalk);
            } else if (v == mMapBus) {
                searchProcess(mMapBus);
            } else if (v == mMapDrive) {
                searchProcess(mMapDrive);
            }
        }
    };


    /**
     * 切换模式
     */
    private void setPointMode() {
        mBaiduMap.clear();
        drawMyLocation();
        endPointLatLng = null;
        if (isUseNumPoint) {
            isUseNumPoint = false;
            mResetLocation.setBackgroundResource(R.drawable.zhy_map_getpoint);
        } else {
            isUseNumPoint = true;
            mResetLocation.setBackgroundResource(R.drawable.zhy_map_getpoint_check);
        }
    }

    /**
     * 返回我的位置
     */
    private void backMyLocation() {
        LatLng cenpt = new LatLng(AppContansts.LOCAL_latitude, AppContansts.LOCAL_longitude);
        MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    /**
     * 绘制我的位置
     */
    private void drawMyLocation() {
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.zhy_map_point_1);
        //构建MarkerOption，用于在地图上添加Marker
        LatLng point = new LatLng(AppContansts.LOCAL_latitude, AppContansts.LOCAL_longitude);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
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
    }

    @Override
    public void onMapClick(LatLng latLng) {
//        mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        if (isUseNumPoint) {
            //获取经纬度
            double latitude = mapPoi.getPosition().latitude;
            double longitude = mapPoi.getPosition().longitude;
            String locationName = mapPoi.getName();
//            endNodeStr = locationName;
            LatLng point = new LatLng(latitude, longitude);
            this.endPointLatLng = point;
            mBaiduMap.clear();
            drawLocation(point);
            drawMyLocation();
            return false;
        } else {
            return false;
        }
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {
        resultDeal(result);
    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        resultDeal(result);
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {
        resultDeal(result);
    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult result) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult result) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult result) {

    }

    private void resultDeal(SearchResult result) {
        if (dialog != null) {
            dialog.cancel();
        }
        if (result == null) {
            //没有找到地址
            ToastUtils.showLongTime(NewRoutePlanActivity.this, "没有找到地址,请点击地图选择终点位置");
            return;
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            ToastUtils.showLongTime(NewRoutePlanActivity.this, "没有找到地址,请点击地图选择终点位置");
            return;
        }
        if (result.error == SearchResult.ERRORNO.ST_EN_TOO_NEAR) {
            ToastUtils.showLongTime(NewRoutePlanActivity.this, "目的地太近没有搜索到线路，请尝试其他搜索方式");
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            if (result instanceof WalkingRouteResult) {
                WalkingRouteResult walkingRouteResult = (WalkingRouteResult) result;
                WalkingRouteLine line = walkingRouteResult.getRouteLines().get(0);
                LatLng latLng = line.getTerminal().getLocation();
                drawLocation(latLng);
            } else if (result instanceof TransitRouteResult) {
                TransitRouteResult transitRouteResult = (TransitRouteResult) result;
                TransitRouteLine line = transitRouteResult.getRouteLines().get(0);
                LatLng latLng = line.getTerminal().getLocation();
                drawLocation(latLng);
            } else if (result instanceof DrivingRouteResult) {
                DrivingRouteResult drivingRouteResult = (DrivingRouteResult) result;
                DrivingRouteLine line = drivingRouteResult.getRouteLines().get(0);
                LatLng latLng = line.getTerminal().getLocation();
                drawLocation(latLng);
            }
            Intent intent = new Intent(NewRoutePlanActivity.this, NewMapLineActivity.class);
            intent.putExtra("MapLine", result);
            startActivity(intent);
        }
    }


}
