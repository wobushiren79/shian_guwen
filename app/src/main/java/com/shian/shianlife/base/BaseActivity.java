package com.shian.shianlife.base;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.MessageDetailActviity;
import com.shian.shianlife.activity.MessageListActivity;
import com.shian.shianlife.activity.NewMessageListActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.FilePathUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.view.drawerlayout.GoodsQueryDrawerLayout;
import com.shian.shianlife.view.drawerlayout.MainDrawerLayout;
import com.shian.shianlife.view.drawerlayout.MainDrawerLayoutListener;
import com.yongchun.library.view.ImageSelectorActivity;

public class BaseActivity extends FragmentActivity {


    FrameLayout flContent;

    protected View v;

    protected MainDrawerLayout mainDrawerLayout;

    protected ImageView ivHeadTitle;
    protected Context mContext = this;
    private PushReciver mPushReciver;
    protected boolean LOGFLAG = SaBaseApplication.LOGFLAG;

    public LinearLayout mMain;
    protected DrawerLayout drawerLayout;

    public static DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_base);
        mMain = (LinearLayout) findViewById(R.id.mainll);
        flContent = (FrameLayout) findViewById(R.id.fl_base);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        ((SaBaseApplication) getApplicationContext()).addActivity(this);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Utils.setWindowStatusBarColor(this, R.color.zhy_title_color_1);//设置状态栏颜色
    }

    @Override
    public void setContentView(int layoutResID) {
        View v = LayoutInflater.from(this).inflate(layoutResID, null);
        flContent.addView(v, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view) {
        // TODO Auto-generated method stub
        flContent.addView(view, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if ((this instanceof NewMessageListActivity)
                || (this instanceof MessageDetailActviity))
            return;
        if (AppContansts.MessageCount == 0) {
            setMessageIconVisible(View.GONE);
        } else {
            setMessageIconVisible(View.VISIBLE);
        }
    }

    /**
     * 增加主界面個人資料抽屉布局
     */
    public void addMainDrawerLayout() {
        mainDrawerLayout = new MainDrawerLayout(this);
        int dp120 = this.getResources().getDimensionPixelOffset(R.dimen.dimen_120dp);
        DrawerLayout.LayoutParams layoutPrams = new DrawerLayout.LayoutParams(metrics.widthPixels - dp120, LayoutParams.MATCH_PARENT, Gravity.START);
        mainDrawerLayout.setLayoutParams(layoutPrams);
        drawerLayout.addView(mainDrawerLayout);
        drawerLayout.addDrawerListener(new MainDrawerLayoutListener(this, drawerLayout, metrics.widthPixels, metrics.heightPixels));
        drawerLayout.setScrimColor(Color.TRANSPARENT);
    }

    public void setMessageVisible(int visible) {
        View tvHeadRight = findViewById(R.id.tv_head_right);
        tvHeadRight.setVisibility(visible);
        setMessageIconVisible(visible);
    }

    public void setMessageIconVisible(int visible) {
        View iv = findViewById(R.id.iv_msg);
        iv.setVisibility(visible);
    }

    public void setTitleLocation(String titleLocation) {
        TextView tvHeadLocation = (TextView) findViewById(R.id.title_location);
        tvHeadLocation.setText(titleLocation);
    }

    public void setTitle(String title) {
        findViewById(R.id.rl_head).setVisibility(View.VISIBLE);
//        TextView tvHeadLeft = (TextView) findViewById(R.id.tv_head_left);
        TextView tvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
        TextView back = (TextView) findViewById(R.id.back);
        ivHeadTitle = (ImageView) findViewById(R.id.iv_head_title);
        TextView tvHeadLocation = (TextView) findViewById(R.id.title_location);
//        ImageView logo= (ImageView) findViewById(R.id.logo);
        View tvHeadRight = findViewById(R.id.tv_head_right);
        if (!title.equals("title")) {
            tvHeadLocation.setVisibility(View.GONE);
            ivHeadTitle.setVisibility(View.GONE);
//            logo.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);
//            tvHeadLeft.setVisibility(View.GONE);

            tvHeadTitle.setVisibility(View.VISIBLE);
            tvHeadTitle.setText(title);
            tvHeadTitle.setBackgroundColor(getResources().getColor(
                    R.color.transparent));

//            Drawable drawable = getResources().getDrawable(
//                    R.drawable.ic_back_default);
//            // / 这一步必须要做,否则不会显示.
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
//                    drawable.getMinimumHeight());
//            tvHeadLeft.setCompoundDrawables(null, drawable, null, null);
//            // tvHeadLeft.setBackgroundResource(R.drawable.ic_back_default);
//            tvHeadLeft.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
            back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            setMessageVisible(View.GONE);
        } else {
            back.setVisibility(View.GONE);
//            logo.setVisibility(View.VISIBLE);
            ivHeadTitle.setVisibility(View.VISIBLE);
            tvHeadTitle.setVisibility(View.GONE);
            ivHeadTitle.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawerLayout != null && mainDrawerLayout != null) {
                        if (drawerLayout.isDrawerOpen(mainDrawerLayout)) {
                            drawerLayout.closeDrawers();
                        } else {
                            drawerLayout.openDrawer(mainDrawerLayout);
                        }
                    }
                }
            });
        }
        tvHeadRight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in = new Intent(getBaseContext(), NewMessageListActivity.class);
                startActivity(in);
            }
        });
    }

    /**
     * 记录发请求的quest，在activity销毁的时候关闭请求。
     */
    private List<AsyncHttpClient> httpClientList = new ArrayList<AsyncHttpClient>();

    public void addHttpClient(AsyncHttpClient client) {
        httpClientList.add(client);
    }

    @Override
    protected void onDestroy() {

        ImageLoader.getInstance().clearMemoryCache();
        super.onDestroy();
        ((SaBaseApplication) getApplicationContext()).removeActivity(this);
        if (httpClientList != null && httpClientList.size() > 0) {
            for (AsyncHttpClient client : httpClientList) {
                client.cancelAllRequests(true);
            }
            httpClientList = null;
        }
        if (mPushReciver != null)
            unregisterReceiver(mPushReciver);
    }

    private static final int PICK_PHOTO = 101;
    private static final int FILE_SELECT_CODE = 102;

    public void showPhotoPicker() {
//		Intent intent = new Intent(this, PhotoPickerActivity.class);
//		intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, true);
//		intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE,
//				PhotoPickerActivity.MODE_SINGLE);
//		intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, 1);
//		startActivityForResult(intent, PICK_PHOTO);

        Intent intent = new Intent(this, ImageSelectorActivity.class);
        intent.putExtra(ImageSelectorActivity.EXTRA_MAX_SELECT_NUM, 1);
        intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, 2);
        intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_PREVIEW, true);
        intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_CROP, false);
        startActivityForResult(intent, PICK_PHOTO);
    }

    /**
     * 调用文件选择软件来选择文件
     **/
    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data
                        .getStringArrayListExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                if (mOnPhotoPickerListener != null) {
                    mOnPhotoPickerListener.onPhoto(result);
                }
            }
        } else if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String path = FilePathUtils.getPath(this, uri);
                if (mOnFilePickerListener != null) {
                    mOnFilePickerListener.onFile(path);
                }
            }
        }
    }

    public class PushReciver extends BroadcastReceiver {
        public static final String C_sPushAction = "com.shianlife.action";

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (intent.getAction().equals(C_sPushAction)) {
                if (mOnPushListener != null) {
                    mOnPushListener.onPush(intent.getStringExtra("channelId"));
                }
            }
        }

    }

    private OnPushListener mOnPushListener;

    public void setOnPushListener(OnPushListener l) {
        mPushReciver = new PushReciver();
        IntentFilter filter = new IntentFilter(PushReciver.C_sPushAction);
        registerReceiver(mPushReciver, filter);
        mOnPushListener = l;
    }

    public interface OnPushListener {
        public void onPush(String channelId);
    }

    private OnPhotoPickerListener mOnPhotoPickerListener;

    public void setOnPhotoPickerListener(OnPhotoPickerListener listener) {
        mOnPhotoPickerListener = listener;
    }

    public interface OnPhotoPickerListener {
        public void onPhoto(ArrayList<String> paths);
    }

    private OnFilePickerListener mOnFilePickerListener;

    public void setOnFilePickerListener(OnFilePickerListener listener) {
        mOnFilePickerListener = listener;
    }

    public interface OnFilePickerListener {
        public void onFile(String paths);
    }
}
