package com.shian.shianlife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.shian.shianlife.R;

public class BaiduMapActivity extends AppCompatActivity {

    public static int MAP_TYPE = 0;//地图类型
    public static Boolean MAP_TAFFIC=false;//交通图

    private MapView mMapView;
    private Button mTypeButton;
    private Button mTrafficButton;

    private BaiduMap mBaiduMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
        setTitle("地图");
        //初始化控件
        initView();
        //初始化地图
        initMap();
    }

    private void initView() {

        mMapView = (MapView) findViewById(R.id.bmapView);

        mTypeButton = (Button) findViewById(R.id.maptest_type);
        mTrafficButton= (Button) findViewById(R.id.maptest_traffic);

        mTypeButton.setOnClickListener(onClickListener);
        mTrafficButton.setOnClickListener(onClickListener);
    }

    private void initMap() {
        mBaiduMap = mMapView.getMap();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
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
            }else if(view==mTrafficButton){
                //交通图
                if(MAP_TAFFIC){
                    mBaiduMap.setTrafficEnabled(false);
                    MAP_TAFFIC=false;
                }else{
                    mBaiduMap.setTrafficEnabled(true);
                    MAP_TAFFIC=true;
                }
            }
        }
    };

}
