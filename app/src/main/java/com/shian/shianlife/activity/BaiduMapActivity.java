package com.shian.shianlife.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.shian.shianlife.R;

import java.util.List;

public class BaiduMapActivity extends Activity {

    public static int MAP_TYPE = 0;//地图类型
    public static Boolean MAP_TAFFIC = false;//交通图

    private MapView mMapView;
    private Button mTypeButton;
    private Button mTrafficButton;

    private BaiduMap mBaiduMap;
    private final static double mapCenterlatitude = 30.6634450000;
    private final static double mapCenterlongitude = 104.0722210000;
    PoiSearch poiSearch;
    String locationPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
        String location = getIntent().getStringExtra("pdrLocation");
        locationPoint = location.replace("-", "");
        setTitle("地图");
        //初始化控件
        initView();
        //初始化地图
        initMap();
//去掉百度图标
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }

    }

    private void initView() {

        mMapView = (MapView) findViewById(R.id.bmapView);

        mTypeButton = (Button) findViewById(R.id.maptest_type);
        mTrafficButton = (Button) findViewById(R.id.maptest_traffic);

        mTypeButton.setOnClickListener(onClickListener);
        mTrafficButton.setOnClickListener(onClickListener);
    }

    private void initMap() {
        mBaiduMap = mMapView.getMap();
        setCenter(mapCenterlatitude, mapCenterlongitude);
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiListener);
        poiSearch.searchInCity((new PoiCitySearchOption())
                .city("成都")
                .keyword(locationPoint));
    }

    private void setCenter(double latitude, double longitude) {
        //设定中心点坐标
        LatLng cenpt = new LatLng(latitude, longitude);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
        public void onGetPoiResult(PoiResult result) {
            //获取POI检索结果
            List<PoiInfo> poiInfos = result.getAllPoi();
            mBaiduMap.clear();
            if (poiInfos != null) {
                for (PoiInfo poiInfo : poiInfos) {
                    Log.v("this", "poiInfo:" + poiInfo.name);
                    LatLng point = poiInfo.location;
                    //构建Marker图标
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.drawable.icon_marka);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option = new MarkerOptions()
                            .position(point)
                            .icon(bitmap);
                    //在地图上添加Marker，并显示
                    mBaiduMap.addOverlay(option);

                }
                setCenter(poiInfos.get(0).location.latitude, poiInfos.get(0).location.longitude);
            } else {
                Toast.makeText(BaiduMapActivity.this, "没有找到派单人地址", Toast.LENGTH_LONG).show();
                finish();
            }

        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        poiSearch.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTypeButton) {
                //地图类型
                MAP_TYPE++;
                if (MAP_TYPE > 2) {
                    MAP_TYPE = BaiduMap.MAP_TYPE_NORMAL;
                }
                switch (MAP_TYPE) {
                    case (BaiduMap.MAP_TYPE_NORMAL):
                        //普通地图
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;

                    case (BaiduMap.MAP_TYPE_SATELLITE):
                        //卫星地图
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;

                    case (BaiduMap.MAP_TYPE_NONE):
                        //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;
                }
            } else if (view == mTrafficButton) {
                //交通图
                if (MAP_TAFFIC) {
                    mBaiduMap.setTrafficEnabled(false);
                    MAP_TAFFIC = false;
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    MAP_TAFFIC = true;
                }
            }
        }
    };

}
