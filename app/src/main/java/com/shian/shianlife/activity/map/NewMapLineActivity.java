package com.shian.shianlife.activity.map;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;

public class NewMapLineActivity extends Activity {
    Button mMapBack;
    Button mMapMyLocation;

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

    boolean useDefaultIcon = true;//是否使用默认图标
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    int lineType = -1;//选择地图的类型 1.为步行 2为公交车 3为驾车
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

        SearchResult result = getIntent().getParcelableExtra("MapLine");
        if (result instanceof WalkingRouteResult) {
            lineType = 1;
            walkResult = (WalkingRouteResult) result;
            if (walkResult.getRouteLines().size() == 1) {
                WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(walkResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

                listData.addAll(walkResult.getRouteLines().get(0).getAllStep());
                int time = walkResult.getRouteLines().get(0).getDuration();
                int distance = walkResult.getRouteLines().get(0).getDistance();
                Utils.LogVPrint(" s"+walkResult.getRouteLines().get(0).getStarting().getTitle());
                setHeadInfo("", time, distance);
            } else if (walkResult.getRouteLines().size() > 1) {
                mapLineChoice();
            }
        } else if (result instanceof TransitRouteResult) {
            lineType = 2;
            transitResult = (TransitRouteResult) result;
            if (transitResult.getRouteLines().size() == 1) {
                TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(transitResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

                listData.addAll(transitResult.getRouteLines().get(0).getAllStep());
                int time = transitResult.getRouteLines().get(0).getDuration();
                int distance = transitResult.getRouteLines().get(0).getDistance();
                setHeadInfo("", time, distance);
            } else if (transitResult.getRouteLines().size() > 1) {
                mapLineChoice();
            }
        } else if (result instanceof DrivingRouteResult) {
            lineType = 3;
            drivingResult = (DrivingRouteResult) result;
            if (drivingResult.getRouteLines().size() == 1) {
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(drivingResult.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

                listData.addAll(drivingResult.getRouteLines().get(0).getAllStep());
                int time = drivingResult.getRouteLines().get(0).getDuration();
                int distance = drivingResult.getRouteLines().get(0).getDistance();
                setHeadInfo("", time, distance);
            } else if (drivingResult.getRouteLines().size() > 1) {
                mapLineChoice();
            }
        }

        //            if (result.getRouteLines().size() > 1) {
//                nowResultwalk = result;
//                Utils.LogVPrint("结果数>1");
//                MapLineChoiceDialog dialog = new MapLineChoiceDialog(NewRoutePlanActivity.this,
//                        result.getRouteLines(),
//                        RouteLineAdapter.Type.WALKING_ROUTE);
//                dialog.setCancelable(false);
//                dialog.show();
//            } else if (result.getRouteLines().size() == 1) {
//
//                Utils.LogVPrint("结果数=1");
//                route = result.getRouteLines().get(0);
//
//                WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
//                mBaiduMap.setOnMarkerClickListener(overlay);
//                routeOverlay = overlay;
//                overlay.setData(result.getRouteLines().get(0));
//                overlay.addToMap();
//                overlay.zoomToSpan();
//                // 直接显示
//            } else {
//                Utils.LogVPrint("结果数<0");
//                return;
//            }
        backMyLocation();
    }

    /**
     * 线路选择
     */
    private void mapLineChoice() {
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
                }
            });
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

        mTVTitle = (TextView) findViewById(R.id.tv_title);
        mTVTimeAndDistance = (TextView) findViewById(R.id.tv_timeanddistance);

        mMapView = (MapView) findViewById(R.id.map);
        mScrollLayout = (ScrollLayout) findViewById(R.id.scroll_down_layout);
        mLineListView = (ContentListView) findViewById(R.id.map_listview);
        mLLHead = (LinearLayout) findViewById(R.id.ll_head);

        mMapBack.setOnClickListener(onClickListener);
        mMapMyLocation.setOnClickListener(onClickListener);
        mLLHead.setOnClickListener(onClickListener);
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
            }
        }
    };

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
            return null;
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
//    /**
//     * 节点浏览示例
//     *
//     * @param v
//     */
//    public void nodeClick(View v) {
//        LatLng nodeLocation = null;
//        String nodeTitle = null;
//        Object step = null;
//        if (nowSearchType != 0 && nowSearchType != -1) {
//            // 非跨城综合交通
//            if (route == null || route.getAllStep() == null) {
//                return;
//            }
//            if (nodeIndex == -1 && v.getId() == R.id.pre) {
//                return;
//            }
//            // 设置节点索引
//            if (v.getId() == R.id.next) {
//                if (nodeIndex < route.getAllStep().size() - 1) {
//                    nodeIndex++;
//                } else {
//                    return;
//                }
//            } else if (v.getId() == R.id.pre) {
//                if (nodeIndex > 0) {
//                    nodeIndex--;
//                } else {
//                    return;
//                }
//            }
//            // 获取节结果信息
//            step = route.getAllStep().get(nodeIndex);
//            if (step instanceof DrivingRouteLine.DrivingStep) {
//                nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrance().getLocation();
//                nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
//            } else if (step instanceof WalkingRouteLine.WalkingStep) {
//                nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrance().getLocation();
//                nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
//            } else if (step instanceof TransitRouteLine.TransitStep) {
//                nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrance().getLocation();
//                nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
//            } else if (step instanceof BikingRouteLine.BikingStep) {
//                nodeLocation = ((BikingRouteLine.BikingStep) step).getEntrance().getLocation();
//                nodeTitle = ((BikingRouteLine.BikingStep) step).getInstructions();
//            }
//        } else if (nowSearchType == 0) {
//            // 跨城综合交通  综合跨城公交的结果判断方式不一样
//            if (massroute == null || massroute.getNewSteps() == null) {
//                return;
//            }
//            if (nodeIndex == -1 && v.getId() == R.id.pre) {
//                return;
//            }
//            boolean isSamecity = nowResultmass.getOrigin().getCityId() == nowResultmass.getDestination().getCityId();
//            int size = 0;
//            if (isSamecity) {
//                size = massroute.getNewSteps().size();
//            } else {
//                for (int i = 0; i < massroute.getNewSteps().size(); i++) {
//                    size += massroute.getNewSteps().get(i).size();
//                }
//            }
//
//            // 设置节点索引
//            if (v.getId() == R.id.next) {
//                if (nodeIndex < size - 1) {
//                    nodeIndex++;
//                } else {
//                    return;
//                }
//            } else if (v.getId() == R.id.pre) {
//                if (nodeIndex > 0) {
//                    nodeIndex--;
//                } else {
//                    return;
//                }
//            }
//            if (isSamecity) {
//                // 同城
//                step = massroute.getNewSteps().get(nodeIndex).get(0);
//            } else {
//                // 跨城
//                int num = 0;
//                for (int j = 0; j < massroute.getNewSteps().size(); j++) {
//                    num += massroute.getNewSteps().get(j).size();
//                    if (nodeIndex - num < 0) {
//                        int k = massroute.getNewSteps().get(j).size() + nodeIndex - num;
//                        step = massroute.getNewSteps().get(j).get(k);
//                        break;
//                    }
//                }
//            }
//
//            nodeLocation = ((MassTransitRouteLine.TransitStep) step).getStartLocation();
//            nodeTitle = ((MassTransitRouteLine.TransitStep) step).getInstructions();
//        }
//
//        if (nodeLocation == null || nodeTitle == null) {
//            return;
//        }
//
//        // 移动节点至中心
//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
//        // show popup
//        popupText = new TextView(NewRoutePlanActivity.this);
//        popupText.setBackgroundResource(R.drawable.popup);
//        popupText.setTextColor(0xFF000000);
//        popupText.setText(nodeTitle);
//        popupText.setGravity(Gravity.CENTER);
//        mBaiduMap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));
//    }
}
