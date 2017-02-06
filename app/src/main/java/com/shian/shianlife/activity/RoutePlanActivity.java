/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.shian.shianlife.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.shian.shianlife.R;
import com.shian.shianlife.base.SaBaseApplication;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.mapapi.RouteLineAdapter;
import com.shian.shianlife.mapapi.overlayutil.BikingRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.DrivingRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.MassTransitRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.OverlayManager;
import com.shian.shianlife.mapapi.overlayutil.TransitRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.WalkingRouteOverlay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;


public class RoutePlanActivity extends Activity implements BaiduMap.OnMapClickListener,
        OnGetRoutePlanResultListener {
    private final String LOG_TAG = "ROUTEPLAN_ACTIVITY";

    // 浏览路线节点相关
    Button mBtnPre = null; // 上一个节点
    Button mBtnNext = null; // 下一个节点
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    RouteLine route = null;
    MassTransitRouteLine massroute = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    private TextView popupText = null; // 泡泡view
    boolean isMapChlick = false;
//    boolean isFirstShowText = true;
    boolean isUseNumPoint = false;//是否采用经纬定位
    LatLng latLng = new LatLng(mapCenterlatitude, mapCenterlongitude);

    EditText mETCity;
    EditText mETstrLocation;
    EditText mETendLocation;
    private final static double mapCenterlatitude = 30.6634450000;
    private final static double mapCenterlongitude = 104.0722210000;
    // 地图相关，使用继承MapView的MyRouteMapView目的是重写touch事件实现泡泡处理
    // 如果不处理touch事件，则无需继承，直接使用MapView即可
    MapView mMapView = null;    // 地图View
    BaiduMap mBaidumap = null;
    // 搜索相关
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用

    WalkingRouteResult nowResultwalk = null;
    BikingRouteResult nowResultbike = null;
    TransitRouteResult nowResultransit = null;
    DrivingRouteResult nowResultdrive = null;
    MassTransitRouteResult nowResultmass = null;

    int nowSearchType = -1; // 当前进行的检索，供判断浏览节点时结果使用。

    String startNodeStr = AppContansts.LOCAL_STREET + AppContansts.LOCAL_STREETNUM;
    String endNodeStr = "";
    String routeCity = AppContansts.LOCAL_CITY;


    Button mDrive;
    Button mTransit;
    Button mWalk;
    Button mBike;

    Button mBack;
    List<Button> listButton = new ArrayList<>();
    CustomDialog dialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_routeplan);
        CharSequence titleLable = "路线规划";
        setTitle(titleLable);
        // 初始化地图
        mDrive = (Button) findViewById(R.id.drive);
        mTransit = (Button) findViewById(R.id.transit);
        mWalk = (Button) findViewById(R.id.walk);
        mBike = (Button) findViewById(R.id.bike);
        mBack= (Button) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listButton.add(mDrive);
        listButton.add(mTransit);
        listButton.add(mWalk);
        listButton.add(mBike);

        mMapView = (MapView) findViewById(R.id.map);
        mBaidumap = mMapView.getMap();
        mBtnPre = (Button) findViewById(R.id.pre);
        mBtnNext = (Button) findViewById(R.id.next);
        mETCity = (EditText) findViewById(R.id.et_city);
        mETstrLocation = (EditText) findViewById(R.id.et_strlocation);
        mETendLocation = (EditText) findViewById(R.id.et_endlocation);
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
        // 地图点击事件处理
        mBaidumap.setOnMapClickListener(this);
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
        //初始线路

        String endStr = getIntent().getStringExtra("RoutePlanLocation");
        endNodeStr = endStr.replace("-", "");
        Log.v("this", "endNode:" + endNodeStr);
        //设定中心点坐标
        LatLng cenpt = new LatLng(mapCenterlatitude, mapCenterlongitude);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaidumap.setMapStatus(mMapStatusUpdate);
        searchButtonProcess(null);
        //去掉百度图标
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
    }

    private void dialogShow() {
        dialog = new CustomDialog(RoutePlanActivity.this);
        dialog.show();
    }

    private void dialogCancel() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    /**
     * 发起路线规划搜索
     *
     * @param v
     */
    public void searchButtonProcess(View v) {

        if (!mETstrLocation.getText().toString().equals("") && !mETstrLocation.getText().toString().equals("") && !mETstrLocation.getText().toString().equals("")) {
            startNodeStr = mETstrLocation.getText().toString();
            endNodeStr = mETendLocation.getText().toString();
            routeCity = mETCity.getText().toString();
        }
        setTagPagerStyle(v);
        dialogShow();
        Log.v(LOG_TAG, "城市:" + routeCity + " 起点：" + startNodeStr + " 终点:" + endNodeStr);
        // 重置浏览节点的路线数据
        route = null;
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
        mBaidumap.clear();
        // 处理搜索按钮响应
        // 设置起终点信息，对于tranist search 来说，城市名无意义

        LatLng latLngStart = new LatLng(AppContansts.LOCAL_latitude, AppContansts.LOCAL_longitude);
        Log.v("this",AppContansts.LOCAL_latitude+" "+AppContansts.LOCAL_longitude);
        Log.v("this","city:"+routeCity);
        Log.v("this","endNodeStr:"+endNodeStr);

        //        PlanNode stNode = PlanNode.withCityNameAndPlaceName(routeCity, startNodeStr);
        PlanNode stNode = PlanNode.withLocation(latLngStart);
        PlanNode enNode;
        if (isUseNumPoint) {
            enNode = PlanNode.withLocation(latLng);
        } else {
            enNode = PlanNode.withCityNameAndPlaceName(routeCity, endNodeStr);
        }
        // 实际使用中请对起点终点城市进行正确的设定
        if (v == null) {
//            mSearch.transitSearch((new TransitRoutePlanOption())
//                    .from(stNode).city(routeCity).to(enNode));
//            nowSearchType = 2;
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode).to(enNode));
            nowSearchType = 3;
        } else if (v.getId() == R.id.mass) {
            PlanNode stMassNode = PlanNode.withCityNameAndPlaceName("北京", "天安门");
            PlanNode enMassNode = PlanNode.withCityNameAndPlaceName("上海", "东方明珠");
            mSearch.masstransitSearch(new MassTransitRoutePlanOption().from(stMassNode).to(enMassNode));
            nowSearchType = 0;
        } else if (v.getId() == R.id.drive) {
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(stNode).to(enNode));
            nowSearchType = 1;
        } else if (v.getId() == R.id.transit) {
            mSearch.transitSearch((new TransitRoutePlanOption())
                    .from(stNode).city(routeCity).to(enNode));
            nowSearchType = 2;
        } else if (v.getId() == R.id.walk) {
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode).to(enNode));
            nowSearchType = 3;
        } else if (v.getId() == R.id.bike) {
            mSearch.bikingSearch((new BikingRoutePlanOption())
                    .from(stNode).to(enNode));
            nowSearchType = 4;
        }
    }

    /**
     * 设置标题路径方式的样式
     *
     * @param v
     */
    private void setTagPagerStyle(View v) {
        if (v == null) return;
        for (Button button : listButton) {
            if (button == v) {
                button.setBackgroundResource(R.drawable.base_tabpager_indicator_selected_2);
                button.setTextColor(getResources().getColor(R.color.chlickcolor));
            } else {
                button.setBackgroundResource(R.drawable.base_tabpager_indicator_default_2);
                button.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    /**
     * 节点浏览示例
     *
     * @param v
     */
    public void nodeClick(View v) {
        LatLng nodeLocation = null;
        String nodeTitle = null;
        Object step = null;

        if (nowSearchType != 0 && nowSearchType != -1) {
            // 非跨城综合交通
            if (route == null || route.getAllStep() == null) {
                return;
            }
            if (nodeIndex == -1 && v.getId() == R.id.pre) {
                return;
            }
            // 设置节点索引
            if (v.getId() == R.id.next) {
                if (nodeIndex < route.getAllStep().size() - 1) {
                    nodeIndex++;
                } else {
                    return;
                }
            } else if (v.getId() == R.id.pre) {
                if (nodeIndex > 0) {
                    nodeIndex--;
                } else {
                    return;
                }
            }
            // 获取节结果信息
            step = route.getAllStep().get(nodeIndex);
            if (step instanceof DrivingRouteLine.DrivingStep) {
                nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrance().getLocation();
                nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
            } else if (step instanceof WalkingRouteLine.WalkingStep) {
                nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrance().getLocation();
                nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
            } else if (step instanceof TransitRouteLine.TransitStep) {
                nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrance().getLocation();
                nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
            } else if (step instanceof BikingRouteLine.BikingStep) {
                nodeLocation = ((BikingRouteLine.BikingStep) step).getEntrance().getLocation();
                nodeTitle = ((BikingRouteLine.BikingStep) step).getInstructions();
            }
        } else if (nowSearchType == 0) {
            // 跨城综合交通  综合跨城公交的结果判断方式不一样


            if (massroute == null || massroute.getNewSteps() == null) {
                return;
            }
            if (nodeIndex == -1 && v.getId() == R.id.pre) {
                return;
            }
            boolean isSamecity = nowResultmass.getOrigin().getCityId() == nowResultmass.getDestination().getCityId();
            int size = 0;
            if (isSamecity) {
                size = massroute.getNewSteps().size();
            } else {
                for (int i = 0; i < massroute.getNewSteps().size(); i++) {
                    size += massroute.getNewSteps().get(i).size();
                }
            }

            // 设置节点索引
            if (v.getId() == R.id.next) {
                if (nodeIndex < size - 1) {
                    nodeIndex++;
                } else {
                    return;
                }
            } else if (v.getId() == R.id.pre) {
                if (nodeIndex > 0) {
                    nodeIndex--;
                } else {
                    return;
                }
            }
            if (isSamecity) {
                // 同城
                step = massroute.getNewSteps().get(nodeIndex).get(0);
            } else {
                // 跨城
                int num = 0;
                for (int j = 0; j < massroute.getNewSteps().size(); j++) {
                    num += massroute.getNewSteps().get(j).size();
                    if (nodeIndex - num < 0) {
                        int k = massroute.getNewSteps().get(j).size() + nodeIndex - num;
                        step = massroute.getNewSteps().get(j).get(k);
                        break;
                    }
                }
            }

            nodeLocation = ((MassTransitRouteLine.TransitStep) step).getStartLocation();
            nodeTitle = ((MassTransitRouteLine.TransitStep) step).getInstructions();
        }

        if (nodeLocation == null || nodeTitle == null) {
            return;
        }

        // 移动节点至中心
        mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
        // show popup
        popupText = new TextView(RoutePlanActivity.this);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setTextColor(0xFF000000);
        popupText.setText(nodeTitle);
        popupText.setGravity(Gravity.CENTER);
        mBaidumap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));
    }

    /**
     * 切换路线图标，刷新地图使其生效
     * 注意： 起终点图标使用中心对齐.
     */
    public void changeRouteIcon(View v) {
        if (routeOverlay == null) {
            return;
        }
        if (useDefaultIcon) {
            ((Button) v).setText("自定义起终点图标");
            Toast.makeText(this,
                    "将使用系统起终点图标",
                    Toast.LENGTH_SHORT).show();

        } else {
            ((Button) v).setText("系统起终点图标");
            Toast.makeText(this,
                    "将使用自定义起终点图标",
                    Toast.LENGTH_SHORT).show();

        }
        useDefaultIcon = !useDefaultIcon;
        routeOverlay.removeFromMap();
        routeOverlay.addToMap();
    }

    /**
     * 当起始点和结束点有多种选择时
     *
     * @param suggestAddrInfo
     */
    private void choicePoint(SuggestAddrInfo suggestAddrInfo) {

        if (suggestAddrInfo.getSuggestStartNode() != null) {
            Log.v("this", "startNode!=null");
            for (PoiInfo poiInfo : suggestAddrInfo.getSuggestStartNode()) {
                Log.v(LOG_TAG, "Point start");
                Log.v(LOG_TAG, "startPoint name:" + poiInfo.name);
            }
        }
        if (suggestAddrInfo.getSuggestEndNode() != null) {
            Log.v("this", "EndNode!=null");
            for (PoiInfo poiInfo : suggestAddrInfo.getSuggestEndNode()) {
                Log.v(LOG_TAG, "Point end");
                Log.v(LOG_TAG, "endPoint name:" + poiInfo.name);
            }
        }
        choicePointDialog(suggestAddrInfo);
    }

    private void choicePointDialog(final SuggestAddrInfo suggestAddrInfo) {
        isUseNumPoint=true;
        final Dialog choiceDialog = new Dialog(RoutePlanActivity.this, R.style.CustomDialog);

        View view = LayoutInflater.from(RoutePlanActivity.this).inflate(R.layout.dialog_choicepoint_2, null);
        final Button cancel = (Button) view.findViewById(R.id.cancel);
        final Button sure = (Button) view.findViewById(R.id.sure);
        final ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.expandable_listview);

        String strName;
        String endName;

        List<PoiInfo> listStartInfo = suggestAddrInfo.getSuggestStartNode();
        final List<PoiInfo> listEndInfo = suggestAddrInfo.getSuggestEndNode();

        List<String> strListData = new ArrayList<>();
        List<String> endListData = new ArrayList<>();

        final int choiceStartPointNum = 0;
        int choiceEndPointNum = 0;

        if (listStartInfo == null) {
            strName = startNodeStr;
        } else {
            strName = listStartInfo.get(0).name;
            for (int i = 0; i < listStartInfo.size(); i++) {
                strListData.add(listStartInfo.get(i).name);
            }
        }

        if (listEndInfo == null) {
            endName = endNodeStr;
        } else {
            endName = listEndInfo.get(0).name;
            endNodeStr=listEndInfo.get(0).name;
            latLng=listEndInfo.get(0).location;
            for (int i = 0; i < listEndInfo.size(); i++) {
                endListData.add(listEndInfo.get(i).name);
            }
        }


        final List<String> groupArray = new ArrayList<>();//组列表
        final List<List<String>> childArray = new ArrayList<>();//子列表

        groupArray.add(strName);
        groupArray.add(endName);
        childArray.add(strListData);
        childArray.add(endListData);

        View.OnClickListener dialogOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == cancel) {
                    Log.v("this", "cancel");
                    choiceDialog.cancel();
                    isMapChlick = true;
                } else if (view == sure) {
                    Log.v("this", "sure");
                    choiceDialog.cancel();
                    searchButtonProcess(null);
                }
            }
        };

        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter() {

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getGroupCount() {
                return groupArray.size();
            }

            @Override
            public int getChildrenCount(int i) {
                return childArray.get(i).size();
            }

            @Override
            public Object getGroup(int i) {
                return null;
            }

            @Override
            public Object getChild(int i, int i1) {
                return childArray.get(i).get(i1);
            }

            @Override
            public long getGroupId(int i) {
                return groupArray.size();
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                //父列表设置
                if (view == null) {
                    view = LayoutInflater.from(RoutePlanActivity.this).inflate(R.layout.view_map_dialog_father, null);
                }
                TextView textTitle = (TextView) view.findViewById(R.id.text_title);
                TextView textLocation = (TextView) view.findViewById(R.id.text_location);
                if (i == 0) {
                    //起始点
                    textTitle.setText("起");
                } else {
                    //结束点
                    textTitle.setText("终");
                }
                textLocation.setText(groupArray.get(i));
                return view;
            }

            @Override
            public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
                //子列表设置
                TextView textView = new TextView(RoutePlanActivity.this);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (i == 0) {
                            groupArray.set(0, childArray.get(i).get(i1));
                        } else {
                            groupArray.set(1, childArray.get(i).get(i1));
                            endNodeStr= childArray.get(i).get(i1);
                            latLng=listEndInfo.get(i1).location;
                        }
                        expandableListView.collapseGroup(i);
                    }
                });
                textView.setText(childArray.get(i).get(i1));
                return textView;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int i) {

            }

            @Override
            public void onGroupCollapsed(int i) {

            }

            @Override
            public long getCombinedChildId(long l, long l1) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long l) {
                return 0;
            }
        };


        expandableListView.setAdapter(expandableListAdapter);
        cancel.setOnClickListener(dialogOnClick);
        sure.setOnClickListener(dialogOnClick);


        choiceDialog.setContentView(view);
        choiceDialog.setCancelable(false);
        choiceDialog.show();
    }

    private void noFindPoint() {
//        if (isFirstShowText) {
            Toast.makeText(RoutePlanActivity.this, "抱歉，未找到终点，请自行在地图上点击设置终点位置", Toast.LENGTH_LONG).show();
//            isFirstShowText = false;
//
//        }
        isMapChlick = true;
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {
        dialogCancel();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            noFindPoint();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            SuggestAddrInfo suggestAddrInfo = result.getSuggestAddrInfo();
            choicePoint(suggestAddrInfo);
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
            mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);

            if (result.getRouteLines().size() > 1) {
                nowResultwalk = result;

                MyTransitDlg myTransitDlg = new MyTransitDlg(RoutePlanActivity.this,
                        result.getRouteLines(),
                        RouteLineAdapter.Type.WALKING_ROUTE);
                myTransitDlg.setCancelable(false);
                myTransitDlg.setOnItemInDlgClickLinster(new OnItemInDlgClickListener() {
                    public void onItemClick(int position) {
                        route = nowResultwalk.getRouteLines().get(position);
                        WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaidumap);
                        mBaidumap.setOnMarkerClickListener(overlay);
                        routeOverlay = overlay;
                        overlay.setData(nowResultwalk.getRouteLines().get(position));
                        overlay.addToMap();
                        overlay.zoomToSpan();
                    }

                });
                myTransitDlg.show();

            } else if (result.getRouteLines().size() == 1) {
                // 直接显示
                route = result.getRouteLines().get(0);
                WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaidumap);
                mBaidumap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

            } else {
                Log.d("route result", "结果数<0");
                return;
            }

        }

    }


    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {


        dialogCancel();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Log.v("this","noFindPoint");
            noFindPoint();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            Log.v("this","choicePoint");
            SuggestAddrInfo suggestAddrInfo = result.getSuggestAddrInfo();
            choicePoint(suggestAddrInfo);
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            Log.v("this","noerror");
            nodeIndex = -1;
            mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);


            if (result.getRouteLines().size() > 1) {
                nowResultransit = result;

                MyTransitDlg myTransitDlg = new MyTransitDlg(RoutePlanActivity.this,
                        result.getRouteLines(),
                        RouteLineAdapter.Type.TRANSIT_ROUTE);
                myTransitDlg.setCancelable(false);
                myTransitDlg.setOnItemInDlgClickLinster(new OnItemInDlgClickListener() {
                    public void onItemClick(int position) {
                        route = nowResultransit.getRouteLines().get(position);
                        TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaidumap);
                        mBaidumap.setOnMarkerClickListener(overlay);
                        routeOverlay = overlay;
                        overlay.setData(nowResultransit.getRouteLines().get(position));
                        overlay.addToMap();
                        overlay.zoomToSpan();
                    }

                });
                myTransitDlg.show();
            } else if (result.getRouteLines().size() == 1) {
                // 直接显示
                route = result.getRouteLines().get(0);
                TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaidumap);
                mBaidumap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

            } else {
                Log.d("route result", "结果数<0");
                return;
            }


        }
    }


    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult result) {
        dialogCancel();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            noFindPoint();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点模糊，获取建议列表
            SuggestAddrInfo suggestAddrInfo = result.getSuggestAddrInfo();
            choicePoint(suggestAddrInfo);
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nowResultmass = result;

            nodeIndex = -1;
            mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);


            // 列表选择
            MyTransitDlg myTransitDlg = new MyTransitDlg(RoutePlanActivity.this,
                    result.getRouteLines(),
                    RouteLineAdapter.Type.MASS_TRANSIT_ROUTE);
            myTransitDlg.setCancelable(false);
            nowResultmass = result;
            myTransitDlg.setOnItemInDlgClickLinster(new OnItemInDlgClickListener() {
                public void onItemClick(int position) {
                    MyMassTransitRouteOverlay overlay = new MyMassTransitRouteOverlay(mBaidumap);
                    mBaidumap.setOnMarkerClickListener(overlay);
                    routeOverlay = overlay;
                    massroute = nowResultmass.getRouteLines().get(position);
                    overlay.setData(nowResultmass.getRouteLines().get(position));

                    MassTransitRouteLine line = nowResultmass.getRouteLines().get(position);
                    overlay.setData(line);
                    if (nowResultmass.getOrigin().getCityId() == nowResultmass.getDestination().getCityId()) {
                        // 同城
                        overlay.setSameCity(true);
                    } else {
                        // 跨城
                        overlay.setSameCity(false);

                    }
                    overlay.addToMap();
                    overlay.zoomToSpan();
                }

            });
            myTransitDlg.show();
        }

    }


    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        dialogCancel();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            noFindPoint();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            SuggestAddrInfo suggestAddrInfo = result.getSuggestAddrInfo();
            choicePoint(suggestAddrInfo);
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;


            if (result.getRouteLines().size() > 1) {
                nowResultdrive = result;

                MyTransitDlg myTransitDlg = new MyTransitDlg(RoutePlanActivity.this,
                        result.getRouteLines(),
                        RouteLineAdapter.Type.DRIVING_ROUTE);
                myTransitDlg.setCancelable(false);
                myTransitDlg.setOnItemInDlgClickLinster(new OnItemInDlgClickListener() {
                    public void onItemClick(int position) {
                        route = nowResultdrive.getRouteLines().get(position);
                        DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaidumap);
                        mBaidumap.setOnMarkerClickListener(overlay);
                        routeOverlay = overlay;
                        overlay.setData(nowResultdrive.getRouteLines().get(position));
                        overlay.addToMap();
                        overlay.zoomToSpan();
                    }

                });
                myTransitDlg.show();

            } else if (result.getRouteLines().size() == 1) {
                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaidumap);
                routeOverlay = overlay;
                mBaidumap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                mBtnPre.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);
            } else {
                Log.d("route result", "结果数<0");
                return;
            }

        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult result) {
        dialogCancel();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            noFindPoint();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            SuggestAddrInfo suggestAddrInfo = result.getSuggestAddrInfo();
            choicePoint(suggestAddrInfo);
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
            mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);

            if (result.getRouteLines().size() > 1) {
                nowResultbike = result;

                MyTransitDlg myTransitDlg = new MyTransitDlg(RoutePlanActivity.this,
                        result.getRouteLines(),
                        RouteLineAdapter.Type.DRIVING_ROUTE);
                myTransitDlg.setCancelable(false);
                myTransitDlg.setOnItemInDlgClickLinster(new OnItemInDlgClickListener() {
                    public void onItemClick(int position) {
                        route = nowResultbike.getRouteLines().get(position);
                        BikingRouteOverlay overlay = new MyBikingRouteOverlay(mBaidumap);
                        mBaidumap.setOnMarkerClickListener(overlay);
                        routeOverlay = overlay;
                        overlay.setData(nowResultbike.getRouteLines().get(position));
                        overlay.addToMap();
                        overlay.zoomToSpan();
                    }

                });
                myTransitDlg.show();

            } else if (result.getRouteLines().size() == 1) {
                route = result.getRouteLines().get(0);
                BikingRouteOverlay overlay = new MyBikingRouteOverlay(mBaidumap);
                routeOverlay = overlay;
                mBaidumap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                mBtnPre.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);
            } else {
                Log.d("route result", "结果数<0");
                return;
            }

        }
    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    private class MyTransitRouteOverlay extends TransitRouteOverlay {

        public MyTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    private class MyBikingRouteOverlay extends BikingRouteOverlay {
        public MyBikingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }


    }

    private class MyMassTransitRouteOverlay extends MassTransitRouteOverlay {
        public MyMassTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }


    }

    @Override
    public void onMapClick(LatLng point) {
        mBaidumap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi latLng) {
        isUseNumPoint = true;
        if (isMapChlick) {
            mBtnPre.setVisibility(View.INVISIBLE);
            mBtnNext.setVisibility(View.INVISIBLE);
            //获取经纬度
            double latitude = latLng.getPosition().latitude;
            double longitude = latLng.getPosition().longitude;
            String locationName = latLng.getName();
            Log.v("this", "latitude=" + latitude + ",longitude=" + longitude + ",locationName=" + locationName + " name=");
            endNodeStr = locationName;
            LatLng point = new LatLng(latitude, longitude);
            this.latLng = point;
            mBaidumap.clear();
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_marka);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mBaidumap.addOverlay(option);
            return false;
        } else {
            return false;
        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mSearch != null) {
            mSearch.destroy();
        }
        mMapView.onDestroy();
        if (dialog != null) {
            dialog.cancel();
        }
        super.onDestroy();
    }

    // 响应DLg中的List item 点击
    interface OnItemInDlgClickListener {
        public void onItemClick(int position);
    }

    // 供路线选择的Dialog
    class MyTransitDlg extends Dialog {

        private List<? extends RouteLine> mtransitRouteLines;
        private ListView transitRouteList;
        private RouteLineAdapter mTransitAdapter;

        OnItemInDlgClickListener onItemInDlgClickListener;

        public MyTransitDlg(Context context, int theme) {
            super(context, theme);
        }

        public MyTransitDlg(Context context, List<? extends RouteLine> transitRouteLines, RouteLineAdapter.Type
                type) {
            this(context, 0);
            mtransitRouteLines = transitRouteLines;
            mTransitAdapter = new RouteLineAdapter(context, mtransitRouteLines, type);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_transit_dialog);

            transitRouteList = (ListView) findViewById(R.id.transitList);
            transitRouteList.setAdapter(mTransitAdapter);

            transitRouteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onItemInDlgClickListener.onItemClick(position);
                    mBtnPre.setVisibility(View.VISIBLE);
                    mBtnNext.setVisibility(View.VISIBLE);
                    dismiss();

                }
            });
        }

        public void setOnItemInDlgClickLinster(OnItemInDlgClickListener itemListener) {
            onItemInDlgClickListener = itemListener;
        }

    }
}
