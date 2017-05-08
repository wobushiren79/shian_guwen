package com.shian.shianlife.activity.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
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
import com.shian.shianlife.adapter.MapSearchAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.thisenum.MapLineStateEnum;
import com.shian.shianlife.view.dialog.MapMoreDialog;

import java.util.ArrayList;
import java.util.List;

public class NewRoutePlanOtherActivity extends BaseActivity implements BaiduMap.OnMapClickListener, OnGetRoutePlanResultListener, OnGetPoiSearchResultListener {
    Button mMapBack;
    Button mMapMore;
    Button mMapMyLocation;
    Button mMapSearch;
    ImageView mIVWalk;
    ImageView mIVBus;
    ImageView mIVDrive;
    LinearLayout mIVSearch;
    TextView mTVWalk;
    TextView mTVBus;
    TextView mTVDrive;
    LinearLayout mLLWalk;
    LinearLayout mLLBus;
    LinearLayout mLLDrive;

    EditText mETSearch;
    LinearLayout mLLSearch;
    ImageView mIVCenter;
    MapView mMapView;

    RecyclerView mListView;

    BaiduMap mBaiduMap;

    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    PoiSearch mPoiSearch = null;

    private final static double mapCenterlatitude = 30.6634450000;
    private final static double mapCenterlongitude = 104.0722210000;

    List<PoiInfo> searchListData = new ArrayList<>();
    MapSearchAdapter mapSearchAdapter;

    String endPointStr;
    String checkEndPointStr;
    LatLng endPointLatLng;
    boolean isUseNumPoint = false;//是否使用经纬来搜索
    boolean isSearchMod = false;
    int lineType = MapLineStateEnum.WALK.getCode();
    public static SearchResult result;
    public static int locationType = -1;//需要修改的地址类型 1经办人地址、2治丧地址、3往生者地址、4去世地址、5殡仪馆地址、6治丧约见地址、7出殡前地址、8出殡当天地址、9客户当前地址
    public static long consultId = -1;
    CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route_plan_other);

        initView();//初始化控件
        initMap();//初始化地图设置
        initData();//初始化数据
        //开始搜索
        searchProcess();
    }

    private void initData() {
        //初始线路获取传过来的地址
        endPointStr = "";
        endPointStr = getIntent().getStringExtra("RoutePlanLocation");
        locationType = getIntent().getIntExtra("LocationType", -1);
        consultId = getIntent().getLongExtra("ConsultId", -1);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mMapBack = (Button) findViewById(R.id.map_back);
        mMapMore = (Button) findViewById(R.id.map_more);
        mMapMyLocation = (Button) findViewById(R.id.map_mylocation);
        mMapSearch = (Button) findViewById(R.id.map_search);
        mIVCenter = (ImageView) findViewById(R.id.iv_center);
        mMapView = (MapView) findViewById(R.id.map);
        mListView = (RecyclerView) findViewById(R.id.list_data);
        mIVSearch = (LinearLayout) findViewById(R.id.iv_search);
        mIVWalk = (ImageView) findViewById(R.id.map_walk);
        mIVBus = (ImageView) findViewById(R.id.map_bus);
        mIVDrive = (ImageView) findViewById(R.id.map_drive);

        mTVBus = (TextView) findViewById(R.id.tv_bus);
        mTVWalk = (TextView) findViewById(R.id.tv_walk);
        mTVDrive = (TextView) findViewById(R.id.tv_drive);

        mLLWalk = (LinearLayout) findViewById(R.id.ll_walk);
        mLLBus = (LinearLayout) findViewById(R.id.ll_bus);
        mLLDrive = (LinearLayout) findViewById(R.id.ll_drive);
        mLLSearch = (LinearLayout) findViewById(R.id.ll_search);
        mETSearch = (EditText) findViewById(R.id.et_search);

        mBaiduMap = mMapView.getMap();

        mIVSearch.setOnClickListener(onClickListener);
        mMapBack.setOnClickListener(onClickListener);
        mMapMore.setOnClickListener(onClickListener);
        mMapSearch.setOnClickListener(onClickListener);
        mLLWalk.setOnClickListener(onClickListener);
        mLLBus.setOnClickListener(onClickListener);
        mLLDrive.setOnClickListener(onClickListener);
        mIVSearch.setOnClickListener(onClickListener);
        mMapMyLocation.setOnClickListener(onClickListener);

        mapSearchAdapter = new MapSearchAdapter(NewRoutePlanOtherActivity.this, searchListData);
        mListView.setAdapter(mapSearchAdapter);
        mListView.setLayoutManager(new LinearLayoutManager(NewRoutePlanOtherActivity.this));
        mapSearchAdapter.setCallBack(new MapSearchAdapter.CallBack() {
            @Override
            public void onItemClick(PoiInfo data) {
                endPointLatLng = data.location;
                checkEndPointStr = data.name;
                searchProcess();
            }
        });
    }

    /**
     * 初始化地图设置
     */
    private void initMap() {
        //地图滑动事件
        mBaiduMap.setOnMapStatusChangeListener(onMapStatusChangeListener);
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);

        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
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
     */
    private void searchProcess() {
        // 处理搜索按钮响应
        // 设置起终点信息，对于tranist search 来说，城市名无意义
        LatLng latLngStart = new LatLng(AppContansts.LOCAL_latitude, AppContansts.LOCAL_longitude);
        PlanNode stNode = PlanNode.withLocation(latLngStart);
        PlanNode enNode = null;
        if (isUseNumPoint) {
            if (endPointLatLng == null) {
                ToastUtils.show(NewRoutePlanOtherActivity.this, "还没有选择终点");
                return;
            }
            enNode = PlanNode.withLocation(endPointLatLng);
        } else {
            enNode = PlanNode.withCityNameAndPlaceName(AppContansts.LOCAL_CITY, endPointStr);
        }
        // 重置浏览节点的路线数据
        mBaiduMap.clear();
        // 实际使用中请对起点终点城市进行正确的设定
        if (lineType == MapLineStateEnum.WALK.getCode()) {
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode).to(enNode));
        } else if (lineType == MapLineStateEnum.DRIVE.getCode()) {
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(stNode).to(enNode));
        } else if (lineType == MapLineStateEnum.BUS.getCode()) {
            mSearch.transitSearch((new TransitRoutePlanOption())
                    .from(stNode).city(AppContansts.LOCAL_CITY).to(enNode));
        } else if (lineType == MapLineStateEnum.WALK.getCode()) {
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode).to(enNode));
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
//        drawMyLocation();
        dialog = new CustomDialog(NewRoutePlanOtherActivity.this);
        dialog.show();
    }

    /**
     * 更多操作
     */
    private void moreOperate() {
        MapMoreDialog dialog = new MapMoreDialog(NewRoutePlanOtherActivity.this, R.style.CustomDialog);
        dialog.setDialogCallBack(new MapMoreDialog.DialogCallBack() {
            @Override
            public void changeLocation() {
                finish();
            }
        });
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
            } else if (v == mMapSearch) {
                changeSearchMode(true);
            } else if (v == mLLWalk) {
                changeState(MapLineStateEnum.WALK.getCode());
            } else if (v == mLLBus) {
                changeState(MapLineStateEnum.BUS.getCode());
            } else if (v == mLLDrive) {
                changeState(MapLineStateEnum.DRIVE.getCode());
            } else if (v == mIVSearch) {
                searchCity();
            }
        }
    };

    void changeSearchMode(boolean isSearchMod) {
        this.isSearchMod = isSearchMod;
        if (isSearchMod) {
            mMapMyLocation.setVisibility(View.GONE);
            mMapSearch.setVisibility(View.GONE);
            mLLSearch.setVisibility(View.VISIBLE);
        } else {
            mMapMyLocation.setVisibility(View.VISIBLE);
            mMapSearch.setVisibility(View.VISIBLE);
            mLLSearch.setVisibility(View.GONE);
        }
    }

    /**
     * 改变线路方式
     */
    private void changeState(int type) {
        lineType = type;
        if (type == MapLineStateEnum.WALK.getCode()) {
            mIVWalk.setImageResource(R.drawable.zhy_map_walk_check);
            mIVBus.setImageResource(R.drawable.zhy_map_bus_uncheck);
            mIVDrive.setImageResource(R.drawable.zhy_map_drive_uncheck);
            mTVWalk.setTextColor(getResources().getColor(R.color.white));
            mTVBus.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            mTVDrive.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            mLLWalk.setBackgroundResource(R.drawable.zhy_map_radio_back);
            mLLBus.setBackgroundResource(0);
            mLLDrive.setBackgroundResource(0);
        } else if (type == MapLineStateEnum.BUS.getCode()) {
            mIVWalk.setImageResource(R.drawable.zhy_map_walk_uncheck);
            mIVBus.setImageResource(R.drawable.zhy_map_bus_check);
            mIVDrive.setImageResource(R.drawable.zhy_map_drive_uncheck);
            mTVWalk.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            mTVBus.setTextColor(getResources().getColor(R.color.white));
            mTVDrive.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            mLLWalk.setBackgroundResource(0);
            mLLBus.setBackgroundResource(R.drawable.zhy_map_radio_back);
            mLLDrive.setBackgroundResource(0);
        } else if (type == MapLineStateEnum.DRIVE.getCode()) {
            mIVWalk.setImageResource(R.drawable.zhy_map_walk_uncheck);
            mIVBus.setImageResource(R.drawable.zhy_map_bus_uncheck);
            mIVDrive.setImageResource(R.drawable.zhy_map_drive_check);
            mTVWalk.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            mTVBus.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            mTVDrive.setTextColor(getResources().getColor(R.color.white));
            mLLWalk.setBackgroundResource(0);
            mLLBus.setBackgroundResource(0);
            mLLDrive.setBackgroundResource(R.drawable.zhy_map_radio_back);
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
    private Overlay drawLocation(LatLng locationLatLng) {
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.zhy_map_point_2);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(locationLatLng)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        Overlay overlay = mBaiduMap.addOverlay(option);
        return overlay;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        changeSearchMode(false);
//        mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
//        if (isUseNumPoint) {
//            //获取经纬度
//            double latitude = mapPoi.getPosition().latitude;
//            double longitude = mapPoi.getPosition().longitude;
//            checkEndPointStr = mapPoi.getName();
//            LatLng point = new LatLng(latitude, longitude);
//            this.endPointLatLng = point;
//            mBaiduMap.clear();
//            drawLocation(point);
//            drawMyLocation();
//            return false;
//        } else {
//            return false;
//        }
        return false;
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

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult != null && poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
            searchListData.clear();

            PoiInfo myLocation = new PoiInfo();
            myLocation.name = "当前位置";
            myLocation.address = "当前地图中心所在位置";
            myLocation.location = endPointLatLng;
            searchListData.add(myLocation);

            searchListData.addAll(poiResult.getAllPoi());
            mapSearchAdapter.notifyDataSetChanged();
            List<Overlay> listOverlay = new ArrayList<>();
            for (PoiInfo data : searchListData) {
                if (!data.address.equals("当前位置")) {
                    Overlay overlay = drawLocation(data.location);
                    listOverlay.add(overlay);
                }
            }
            if (isSearchMod)
                zoomToSpan(listOverlay);
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        poiIndoorResult.getmArrayPoiInfo();
    }


    /**
     * 交通工具结果处理
     *
     * @param result
     */
    private void resultDeal(SearchResult result) {
        this.result = result;
        if (dialog != null) {
            dialog.cancel();
        }

        //第一次默认搜索
        if (!isUseNumPoint) {
            LatLng latlng = new LatLng(AppContansts.LOCAL_latitude, AppContansts.LOCAL_longitude);
            searchNearBy(latlng);
        }

        if (result == null) {
            //没有找到地址
            ToastUtils.showLongTime(NewRoutePlanOtherActivity.this, "没有找到地址,请点击地图选择终点位置");
            return;
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            ToastUtils.showLongTime(NewRoutePlanOtherActivity.this, "没有找到地址,请点击地图选择终点位置");
            return;
        }
        if (result.error == SearchResult.ERRORNO.ST_EN_TOO_NEAR) {
            ToastUtils.showLongTime(NewRoutePlanOtherActivity.this, "目的地太近没有搜索到线路，请尝试其他搜索方式");
            return;
        }
        if (result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            ToastUtils.showLongTime(NewRoutePlanOtherActivity.this, "没有找到可规划的路径");
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
            Intent intent = new Intent(NewRoutePlanOtherActivity.this, NewMapLineActivity.class);
            if (isUseNumPoint) {
                intent.putExtra("MapLineEndPoint", checkEndPointStr);
            } else {
                intent.putExtra("MapLineEndPoint", endPointStr);
            }
            startActivity(intent);
            isUseNumPoint = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        result = null;
        mPoiSearch.destroy();
    }

    /**
     * 地图滑动监听
     */
    BaiduMap.OnMapStatusChangeListener onMapStatusChangeListener = new BaiduMap.OnMapStatusChangeListener() {
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            // 滑动搜索\
            if (!isSearchMod) {
                endPointLatLng = mapStatus.target;
                searchNearBy(endPointLatLng);
            }
        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {
        }
    };

    /**
     * 搜索附近
     *
     * @param latlng
     */
    private void searchNearBy(LatLng latlng) {
        mBaiduMap.clear();
        drawMyLocation();
        endPointLatLng = latlng;
        iconAnim(mIVCenter);
        mPoiSearch.searchNearby
                (new PoiNearbySearchOption().location(endPointLatLng).radius(1000).keyword("小区"));
    }

    private void searchCity() {
        mBaiduMap.clear();
        drawMyLocation();
        iconAnim((mIVCenter));
        mPoiSearch.searchInCity(new PoiCitySearchOption().city(AppContansts.LOCAL_CITY + "").keyword(mETSearch.getText().toString()));
    }

    private void iconAnim(View view) {
        TranslateAnimation animation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -0.3f);
        animation.setDuration(200);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(1);
        animation.setInterpolator(new LinearInterpolator());
        view.startAnimation(animation);
    }

    /**
     * 缩放地图，使所有Overlay都在合适的视野内
     * <p>
     * 注： 该方法只对Marker类型的overlay有效
     * </p>
     */
    public void zoomToSpan(List<Overlay> mOverlayList) {
        if (mBaiduMap == null) {
            return;
        }
        if (mOverlayList.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Overlay overlay : mOverlayList) {
                // polyline 中的点可能太多，只按marker 缩放
                if (overlay instanceof Marker) {
                    builder.include(((Marker) overlay).getPosition());
                }
            }
            mBaiduMap.setMapStatus(MapStatusUpdateFactory
                    .newLatLngBounds(builder.build()));
        }
    }
}
