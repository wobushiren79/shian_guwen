![这是预览图](https://github.com/heshiweij/CycleViewPager/blob/master/sample.png?raw=true)

## 特点

CycleViewPager 是一个轻量级的无限轮播图展示控件

1. 支持自由选择加载网络图片和本地图片
2. 优化了 bitmap 的显示，防止 oom 
3. 三级缓存优化网络图片，节省流量
4. 支持各种属性的设置，可定制
5. Activity 生命周期联动
6. 使用线程池请求网络



## 使用方法

### 权限

```
<uses-permission  android:name="android.permission.INTERNET"/>
<uses-permission  android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission  android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

### 布局

```xml
<cn.ifavor.cycleviewpager.view.CycleViewPager
  android:id="@+id/cvp_main"
  android:layout_width="match_parent"
  android:layout_height="150dip" />
```

### 代码

**设置图片 ResId 方式**

```
mCycleViewPager = (CycleViewPager) findViewById(R.id.cvp_main);
		
Map<String, Integer> map = new TreeMap<String, Integer>();
LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
map.put("1", R.drawable.a);
map.put("2", R.drawable.b);
map.put("3", R.drawable.c);
map.put("4", R.drawable.d);
map.put("5", R.drawable.e);

mCycleViewPager.setResIdMap(map).setDuration(1000).start();

```

**设置图片 URL 方式**

```
mCycleViewPager = (CycleViewPager) findViewById(R.id.cvp_main);
		
LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
map.put("1", "http://www.2cto.com/meinv/uploads/131124/1-1311242143022C.jpg");
map.put("2", "http://www.2cto.com/meinv/uploads/131124/1-131124214242c7.jpg");
map.put("3", "http://www.2cto.com/meinv/uploads/131124/1-131124214135a6.jpg");
map.put("4", "http://www.2cto.com/meinv/uploads/131124/1-1311242141135E.jpg");
map.put("5", "http://www.2cto.com/meinv/uploads/131124/1-13112421404R17.jpg");

mCycleViewPager.setURLMap(map).setDuration(1000).start();
```

**方法汇总**
 

| 方法  | 含义 |建议/默认 |
| ------------- | ------------- |------------- |
| setBottomBackgroundColor  | 设置底部背景颜色  | Color.argb(54, 0, 0, 0)  |
|setIndicatorRadius  | 设置指示器的圆角值  |8(px)  |
|setUnSelectedColor  | 设置指示器未选中的颜色  |Color.rgb(61, 59, 59)  |
|setSelectedColor  | 设置指示器选中的颜色  |Color.rgb(255, 0, 0)  |
|setDuration  | 设置轮播器执行时间间隔  |3000(ms)  |
|setIndicatorDirection  | 设置指示器显示位置  |IndicatorDirection.CENTER  |
|setIndicatorPointSize  | 设置指示器点的尺寸  |16(px)  |
|setIndicatorPointMargin  | 设置指示器间隔距离  |8(px)  |
|setShowTitle  | 设置是否显示标题  |true  |
|setTitleTextSize  | 设置标题字体大小  |12(px)  |
|setTitleTextColor  | 设置标题字体颜色  |Color.rgb(255,0,0)  |
|setHandler  | 设置 handler  |建议传入全局唯一 handler,否则新建  |

**注意**

1. 建议使用全局 handler，如：
   setHandler(BaseApplication.getHanlder())

2. 建议在 onDestory 取消轮播，如：

```java
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mCycleViewPager != null){
			// 取消轮播定时器
			mCycleViewPager.cancel();
		}
	}
```