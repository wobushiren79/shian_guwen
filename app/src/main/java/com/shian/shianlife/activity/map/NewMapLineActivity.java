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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
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
import com.shian.shianlife.mapapi.overlayutil.DrivingRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.OverlayManager;
import com.shian.shianlife.mapapi.overlayutil.TransitRouteOverlay;
import com.shian.shianlife.mapapi.overlayutil.WalkingRouteOverlay;

public class NewMapLineActivity extends Activity {
    Button mMapBack;
    MapView mMapView;
    BaiduMap mBaiduMap;
    ListView mLineListView;

    //线路绘制
    RouteLine route = null;
    OverlayManager routeOverlay = null;
    MassTransitRouteLine massroute = null;
    TextView popupText = null; // 泡泡view

    //返回的结果
    WalkingRouteResult nowResultwalk = null;
    BikingRouteResult nowResultbike = null;
    TransitRouteResult nowResultransit = null;
    DrivingRouteResult nowResultdrive = null;
    MassTransitRouteResult nowResultmass = null;

    boolean useDefaultIcon = false;//是否使用默认图标
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    int lineType = -1;//选择地图的类型 1.为步行 2为公交车 3为驾车

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
            Log.v("this", "WalkingRouteResult");
            WalkingRouteResult walkResult = (WalkingRouteResult) result;
            WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
            mBaiduMap.setOnMarkerClickListener(overlay);
            routeOverlay = overlay;
            overlay.setData(walkResult.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

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

    private void initView() {
        mMapBack = (Button) findViewById(R.id.map_back);
        mMapView = (MapView) findViewById(R.id.map);
        mLineListView = (ListView) findViewById(R.id.map_listview);

        mMapBack.setOnClickListener(onClickListener);

        mBaiduMap = mMapView.getMap();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mMapBack) {
                finish();
            }
        }
    };

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 0;
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
