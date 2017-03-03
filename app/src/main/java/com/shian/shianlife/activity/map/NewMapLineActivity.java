package com.shian.shianlife.activity.map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mapapi.RouteLineAdapter;
import com.shian.shianlife.mapapi.overlayutil.DrivingRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.OverlayManager;
import com.shian.shianlife.mapapi.overlayutil.TransitRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.WalkingRouteOverlay;
import com.shian.shianlife.view.dialog.MapLineChoiceDialog;
import com.yinglan.scrolllayout.ScrollLayout;
import com.yinglan.scrolllayout.content.ContentListView;
import com.yinglan.scrolllayout.content.ContentScrollView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class NewMapLineActivity extends Activity {

    Button mMapNavigation;
    Button mMapBack;
    Button mMapMyLocation;
    Button mMapLineChange;

    TextView mTVTitle;
    TextView mTVTimeAndDistance;

    MapView mMapView;
    BaiduMap mBaiduMap;
    ScrollLayout mScrollLayout;
    ContentListView mLineListView;
    LinearLayout mLLHead;

    //线路绘制
    RouteLine route = null;
    OverlayManager routeOverlay = null;
    MassTransitRouteLine massroute = null;
    TextView popupText = null; // 泡泡view

    //返回的结果
    WalkingRouteResult walkResult = null;
    TransitRouteResult transitResult = null;
    DrivingRouteResult drivingResult = null;
    BikingRouteResult nowResultbike = null;
    MassTransitRouteResult nowResultmass = null;

    WalkingRouteLine walkrouteLine = null;
    TransitRouteLine transitRouteLine = null;
    DrivingRouteLine drivingRouteLine = null;

    boolean useDefaultIcon = true;//是否使用默认图标
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    int lineType = -1;//选择地图的类型 1.为步行 2为公交车 3为驾车
    String endPointStr;
    List<WalkingRouteLine> listDataLine = new ArrayList<>();
    List<RouteStep> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_map_line);
        initView();
        initMap();
        initData();
    }

    private void initData() {
        SearchResult result = NewRoutePlanActivity.result;
        endPointStr = getIntent().getStringExtra("MapLineEndPoint");
        if (result instanceof WalkingRouteResult) {
            lineType = 1;
            walkResult = (WalkingRouteResult) result;
            if (walkResult.getRouteLines().size() == 1) {
                walkLineOverlay(0);
            } else if (walkResult.getRouteLines().size() > 1) {
                mapLineChoice();
            }
        } else if (result instanceof TransitRouteResult) {
            lineType = 2;
            transitResult = (TransitRouteResult) result;
            if (transitResult.getRouteLines().size() == 1) {
                transitLineOverlay(0);
            } else if (transitResult.getRouteLines().size() > 1) {
                mapLineChoice();
            }
        } else if (result instanceof DrivingRouteResult) {
            lineType = 3;
            drivingResult = (DrivingRouteResult) result;
            if (drivingResult.getRouteLines().size() == 1) {
                drivingLineOverlay(0);
            } else if (drivingResult.getRouteLines().size() > 1) {
                mapLineChoice();
            }
        }
        backMyLocation();
    }

    private void drivingLineOverlay(int position) {
        DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
        mBaiduMap.setOnMarkerClickListener(overlay);
        routeOverlay = overlay;

        drivingRouteLine = drivingResult.getRouteLines().get(position);
        overlay.setData(drivingRouteLine);
        overlay.addToMap();
        overlay.zoomToSpan();
        listData.addAll(drivingResult.getRouteLines().get(position).getAllStep());
        int time = drivingResult.getRouteLines().get(position).getDuration();
        int distance = drivingResult.getRouteLines().get(position).getDistance();
        setHeadInfo(AppContansts.LOCAL_STREET + " 至 " + endPointStr, time, distance);
    }

    private void transitLineOverlay(int position) {
        TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaiduMap);
        mBaiduMap.setOnMarkerClickListener(overlay);
        routeOverlay = overlay;

        transitRouteLine = transitResult.getRouteLines().get(position);
        overlay.setData(transitRouteLine);
        overlay.addToMap();
        overlay.zoomToSpan();
        listData.addAll(transitResult.getRouteLines().get(position).getAllStep());
        int time = transitResult.getRouteLines().get(position).getDuration();
        int distance = transitResult.getRouteLines().get(position).getDistance();
        setHeadInfo(AppContansts.LOCAL_STREET + " 至 " + endPointStr, time, distance);
    }

    private void walkLineOverlay(int position) {
        WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
        mBaiduMap.setOnMarkerClickListener(overlay);
        routeOverlay = overlay;

        walkrouteLine = walkResult.getRouteLines().get(position);
        overlay.setData(walkrouteLine);
        overlay.addToMap();
        overlay.zoomToSpan();

        listData.addAll(walkResult.getRouteLines().get(position).getAllStep());
        int time = walkResult.getRouteLines().get(position).getDuration();
        int distance = walkResult.getRouteLines().get(position).getDistance();
        setHeadInfo(AppContansts.LOCAL_STREET + " 至 " + endPointStr, time, distance);
    }

    /**
     * 线路选择
     */
    private void mapLineChoice() {
        mBaiduMap.clear();
        MapLineChoiceDialog lineChoiceDialog = null;
        switch (lineType) {
            case 1:
                lineChoiceDialog = new MapLineChoiceDialog(NewMapLineActivity.this, walkResult.getRouteLines(), RouteLineAdapter.Type.WALKING_ROUTE);
                break;
            case 2:
                lineChoiceDialog = new MapLineChoiceDialog(NewMapLineActivity.this, transitResult.getRouteLines(), RouteLineAdapter.Type.TRANSIT_ROUTE);
                break;
            case 3:
                lineChoiceDialog = new MapLineChoiceDialog(NewMapLineActivity.this, drivingResult.getRouteLines(), RouteLineAdapter.Type.DRIVING_ROUTE);
                break;
        }
        if (lineChoiceDialog != null) {
            lineChoiceDialog.setMapLineChoiceCallBack(new MapLineChoiceDialog.MapLineChoiceCallBack() {
                @Override
                public void setMapLine(int position) {
                    listData.clear();
                    switch (lineType) {
                        case 1:
                            walkLineOverlay(position);
                            break;
                        case 2:
                            transitLineOverlay(position);
                            break;
                        case 3:
                            drivingLineOverlay(position);
                            break;
                    }
                }
            });
            lineChoiceDialog.setCanceledOnTouchOutside(false);
            lineChoiceDialog.show();
        }
    }

    /**
     * 初始化地图
     */
    private void initMap() {
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

    /**
     * 设置标题信息
     *
     * @param title
     * @param time
     * @param distance
     */
    private void setHeadInfo(String title, int time, int distance) {
        String timeString;
        String distranceString;

        if (time / 3600 == 0) {
            timeString = (time / 60) + "分钟";
        } else {
            timeString = time / 3600 + "小时" + (time % 3600) / 60 + "分钟";
        }
        distranceString = distance + "米";

        mTVTitle.setText(title);
        mTVTimeAndDistance.setText(timeString + " | " + distranceString);
    }

    private void initView() {
        mMapBack = (Button) findViewById(R.id.map_back);
        mMapMyLocation = (Button) findViewById(R.id.map_mylocation);
        mMapLineChange = (Button) findViewById(R.id.map_linechange);
        mMapNavigation = (Button) findViewById(R.id.bt_navigation);

        mTVTitle = (TextView) findViewById(R.id.tv_title);
        mTVTimeAndDistance = (TextView) findViewById(R.id.tv_timeanddistance);

        mMapView = (MapView) findViewById(R.id.map);
        mScrollLayout = (ScrollLayout) findViewById(R.id.scroll_down_layout);
        mLineListView = (ContentListView) findViewById(R.id.map_listview);
        mLLHead = (LinearLayout) findViewById(R.id.ll_head);

        mMapBack.setOnClickListener(onClickListener);
        mMapMyLocation.setOnClickListener(onClickListener);
        mLLHead.setOnClickListener(onClickListener);
        mMapLineChange.setOnClickListener(onClickListener);
        mMapNavigation.setOnClickListener(onClickListener);
        mBaiduMap = mMapView.getMap();

        /**设置 setting*/
        mScrollLayout.setMinOffset(0);
        mScrollLayout.setMaxOffset(BaseActivity.metrics.heightPixels / 3);
        mScrollLayout.setExitOffset(BaseActivity.metrics.heightPixels / 10);
        mScrollLayout.setIsSupportExit(true);
        mScrollLayout.setAllowHorizontalScroll(true);
        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
        mScrollLayout.setToExit();

        mLineListView.setAdapter(LineAdapter);

    }


    ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {

        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {

        }

        @Override
        public void onChildScroll(int top) {

        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mMapBack) {
                finish();
            } else if (v == mMapMyLocation) {
                backMyLocation();
            } else if (v == mLLHead) {
                mScrollLayout.setToOpen();
            } else if (v == mMapLineChange) {
                lineChange();
            } else if (v == mMapNavigation) {
                navigationFunction();
            }
        }
    };


    /**
     * 导航功能
     */
    private void navigationFunction() {
        RouteNode startLatLng = null;
        RouteNode endLatLng = null;
        switch (lineType) {
            case 1:
                if (walkrouteLine != null) {
                    startLatLng = walkrouteLine.getStarting();
                    endLatLng=walkrouteLine.getTerminal();
                }
                break;
            case 2:
                if (transitRouteLine != null) {
                    startLatLng = transitRouteLine.getStarting();
                    endLatLng=transitRouteLine.getTerminal();
                }
                break;
            case 3:
                if (drivingRouteLine != null) {
                    startLatLng = drivingRouteLine.getStarting();
                    endLatLng=drivingRouteLine.getTerminal();
                }
                break;
        }

        if(startLatLng!=null&&endLatLng!=null){
            Intent intent = new Intent();
            if (Utils.isInstalled(NewMapLineActivity.this, "com.baidu.BaiduMap")) {
                try {
                    intent = Intent.parseUri("intent://map/direction?" +
                            "origin=latlng:" + startLatLng.getLocation().latitude + "," + startLatLng.getLocation().longitude +
                            "|name:" + AppContansts.LOCAL_ADDRESS +
                            "&destination=latlng:" + endLatLng.getLocation().latitude + "," + endLatLng.getLocation().longitude +
                            "|name:" + endPointStr +
                            "&mode=driving" +
                            "&src=Name|AppName" +
                            "#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end", 0);
                } catch (URISyntaxException e) {
                    Utils.LogVPrint("URISyntaxException : " + e.getMessage());
                    e.printStackTrace();
                }
                startActivity(intent);
            } else if (Utils.isInstalled(NewMapLineActivity.this, "com.autonavi.minimap")) {
                intent.setData(Uri
                        .parse("androidamap://route?" +
                                "sourceApplication=softname" +
                                "&slat=" + startLatLng.getLocation().latitude +
                                "&slon=" + startLatLng.getLocation().longitude +
                                "&dlat=" + endLatLng.getLocation().latitude +
                                "&dlon=" +endLatLng.getLocation().longitude+
                                "&dname=" + endPointStr +
                                "&dev=0" +
                                "&m=0" +
                                "&t=2"));
                startActivity(intent);
            } else {
                ToastUtils.showLongTime(NewMapLineActivity.this, "请先下载百度地图或高德地图");
            }
        }else{
            ToastUtils.show(NewMapLineActivity.this, "导航失败");
        }
    }

    /**
     * 重新选择线路
     */
    private void lineChange() {
        mapLineChoice();
    }

    BaseAdapter LineAdapter = new BaseAdapter() {
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(NewMapLineActivity.this, R.layout.layout_newmapline_item, null);
                holder = new ViewHolder();
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            RouteStep step = listData.get(position);
            final LatLng location;
            final String title;
            switch (lineType) {
                case 1:
                    location = ((WalkingRouteLine.WalkingStep) step).getEntrance().getLocation();
                    title = ((WalkingRouteLine.WalkingStep) step).getInstructions();
                    break;
                case 2:
                    location = ((TransitRouteLine.TransitStep) step).getEntrance().getLocation();
                    title = ((TransitRouteLine.TransitStep) step).getInstructions();
                    break;
                case 3:
                    location = ((DrivingRouteLine.DrivingStep) step).getEntrance().getLocation();
                    title = ((DrivingRouteLine.DrivingStep) step).getInstructions();
                    break;
                default:
                    location = null;
                    title = "";
                    break;
            }
            holder.tvContent.setText(title);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nodeShow(location, title);
                    mScrollLayout.scrollToExit();
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tvContent;
        }
    };

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
     * 节点浏览示例
     */
    public void nodeShow(LatLng nodeLocation, String nodeTitle) {
        // 移动节点至中心
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
        // show popup
        popupText = new TextView(NewMapLineActivity.this);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setTextColor(0xFF000000);
        popupText.setText(nodeTitle);
        popupText.setGravity(Gravity.CENTER);
        mBaiduMap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));
    }

}
