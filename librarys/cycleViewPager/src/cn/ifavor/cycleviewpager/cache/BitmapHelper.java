package cn.ifavor.cycleviewpager.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import cn.ifavor.cycleviewpager.cache.bitmap.FileCacheUtils;
import cn.ifavor.cycleviewpager.cache.bitmap.MemCacheUtils;
import cn.ifavor.cycleviewpager.cache.bitmap.NetCacheUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;


public class BitmapHelper {
	private static BitmapHelper instance;
	private MemCacheUtils memCacheUtils;
	private FileCacheUtils fileCacheUtils;
	private NetCacheUtils netCacheUtils;

	private BitmapHelper(Context context, Handler handler) {

		memCacheUtils = new MemCacheUtils();
		fileCacheUtils = new FileCacheUtils(context,memCacheUtils);
		netCacheUtils = new NetCacheUtils(context, handler, fileCacheUtils);
	}

	public static BitmapHelper getInstance(Context context, Handler handler) {
		
		
		if (instance == null) {
			synchronized (BitmapHelper.class) {
				if (instance == null) {
					instance = new BitmapHelper(context, handler);
				}
			}
		}
		return instance;
	}

	public void display(ImageView imageView, String url) {
		
		Bitmap bitmap = null;

		if (imageView == null 
				|| imageView.getTag() != null
				&& imageView.getTag().equals(url)) {
			
//
			return;
		}


		bitmap = memCacheUtils.loadBitmap(url);
		if (bitmap != null) {

			imageView.setImageBitmap(bitmap);
			imageView.setTag(url);
			return;
		}

		bitmap = fileCacheUtils.loadBitmap(url);
		if (bitmap != null) {

			imageView.setImageBitmap(bitmap);
			imageView.setTag(url);
			return;
		}

		netCacheUtils.loadBitmap(imageView, url);
	}


	private void watchMem(LinkedHashMap<String, Bitmap> mBitmaps) {
		Set<Entry<String, Bitmap>> entrySet = mBitmaps.entrySet();
		Iterator<Entry<String, Bitmap>> iterator = entrySet.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Entry<String, Bitmap> next = iterator.next();
			String key = next.getKey();
			Bitmap value = next.getValue();
			System.out.println(i + ": " + key + "...." + value);
			i++;
		}

	}
}
