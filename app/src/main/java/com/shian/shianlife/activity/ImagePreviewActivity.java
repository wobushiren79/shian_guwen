package com.shian.shianlife.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.FilePathUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.PinchImageView;
import com.squareup.picasso.Picasso;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ImagePreviewActivity extends BaseActivity implements OnLongClickListener {

    private String url;

    PinchImageView mImageView;

    Bitmap mBitmap;

    Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Log.v("this","1");
                    Log.v("this","1"+mBitmap.toString());
                    mImageView.setImageBitmap(mBitmap);
                    mImageView.setVisibility(View.VISIBLE);
                    break;
                case 1:

                    break;
            }
        }

        ;
    };

    HandlerThread mHandlerThread;

    Handler mThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageView = new PinchImageView(this);
     mImageView.setBackgroundResource(R.color.main_blackground);
//        mImageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
        mImageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mMain.setBackgroundColor(getResources().getColor(R.color.main_blackground));
        setContentView(mImageView);
        setTitle("查看图片");
        url = getIntent().getStringExtra("url");
        Log.v("this", "url:" + url);
        mHandlerThread = new HandlerThread(getClass().getSimpleName());
        mHandlerThread.start();
        mThreadHandler = new Handler(mHandlerThread.getLooper()) {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case 0:
                        getBitmap();
                        break;
                    case 1:
                        saveBitmap();
                        break;
                }
            }
        };
        mThreadHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(0);
        mThreadHandler.removeMessages(0);
        mThreadHandler.removeMessages(1);
        mHandlerThread.quit();
        if (mBitmap != null) {
//			mBitmap.recycle();
            mBitmap = null;
        }
        super.onDestroy();
    }

    protected void saveBitmap() {
        String fileName = url.substring(url.lastIndexOf("/"), url.length());
        File file = FilePathUtils.getDownloadFile("Pic", fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            ToastUtils.show(this, "保存成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ToastUtils.show(this, "无法保存图片！");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void getBitmap() {
        try {
            mBitmap = Picasso.with(this).load(url).get();
            mHandler.sendEmptyMessage(0);
            mImageView.setOnLongClickListener(this);

        } catch (IOException e) {
            ToastUtils.show(this, "获取图片失败！");
            e.printStackTrace();
        }

    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder = new AlertDialog.Builder(this);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Dialog);
        } else {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog);
        }
        builder.setMessage("是否保存图片?");
        builder.setNegativeButton("保存", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                mThreadHandler.sendEmptyMessage(1);
            }
        });
        builder.setPositiveButton("取消", null);
        builder.create().show();
        return true;
    }

}
