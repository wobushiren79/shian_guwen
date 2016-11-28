package cn.ifavor.cycleviewpager;

import java.util.LinkedHashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import cn.ifavor.cycleviewpager.view.CycleViewPager;
import cn.ifavor.cycleviewpager.view.CycleViewPager.IndicatorDirection;


public class MainActivity extends Activity {

    private CycleViewPager mCycleViewPager;;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

	private void init() {
		mCycleViewPager = (CycleViewPager) findViewById(R.id.cvp_main);
		
//		Map<String, Integer> map = new TreeMap<String, Integer>();
//		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
//		map.put("1", R.drawable.a);
//		map.put("2", R.drawable.b);
//		map.put("3", R.drawable.c);
//		map.put("4", R.drawable.d);
//		map.put("5", R.drawable.e);
//		mCycleViewPager.setResIdMap(map);
		
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("���Ǳ��� 1", "http://img3.imgtn.bdimg.com/it/u=2863599132,929299683&fm=21&gp=0.jpg");
		map.put("���Ǳ��� 2", "http://img5.imgtn.bdimg.com/it/u=1319787381,824476088&fm=21&gp=0.jpg");
		map.put("���Ǳ��� 3", "http://img3.imgtn.bdimg.com/it/u=2791706699,2937974108&fm=21&gp=0.jpg");
		map.put("���Ǳ��� 4", "http://img5.imgtn.bdimg.com/it/u=4244423416,3770856977&fm=23&gp=0.jpg");
		map.put("���Ǳ��� 5", "http://img3.imgtn.bdimg.com/it/u=3725619427,3480650754&fm=21&gp=0.jpg");
		map.put("���Ǳ��� 6", "http://img5.imgtn.bdimg.com/it/u=2225230626,2273977563&fm=21&gp=0.jpg");
		map.put("���Ǳ��� 7", "http://img4.imgtn.bdimg.com/it/u=2757297171,813906151&fm=21&gp=0.jpg");
		map.put("���Ǳ��� 8", "http://img5.imgtn.bdimg.com/it/u=501369453,1464539144&fm=21&gp=0.jpg");
		mCycleViewPager.setURLMap(map).setDuration(1000).start();
		
//
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mCycleViewPager != null){

			mCycleViewPager.cancel();
		}
	}
}
