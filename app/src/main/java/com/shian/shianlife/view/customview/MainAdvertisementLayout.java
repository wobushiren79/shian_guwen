package com.shian.shianlife.view.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.WebActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpresult.PHPHrGetLoginAdvertisement;

/**
 * Created by Administrator on 2017/3/5.
 */

public class MainAdvertisementLayout extends LinearLayout {
    View view;
    ImageView mIVConent;
    Button mBTCancel;

    private CallBack callBack;
    private PHPHrGetLoginAdvertisement result;
    public MainAdvertisementLayout(Context context) {
        this(context, null);
    }

    public MainAdvertisementLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_mainadvertisement_layout, this);
        initView();
        getData();
    }

    private void initView() {
        mBTCancel = (Button) view.findViewById(R.id.bt_cancel);
        mIVConent = (ImageView) view.findViewById(R.id.iv_content);
    }

    /**
     * 获取数据
     */
    private void getData() {
        MHttpManagerFactory.getPHPManager().mainAdvertisement(getContext(), new HttpResponseHandler<PHPHrGetLoginAdvertisement>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(PHPHrGetLoginAdvertisement result) {
                MainAdvertisementLayout.this.result=result;
                mBTCancel.setOnClickListener(onClickListener);
                mIVConent.setOnClickListener(onClickListener);
                ImageLoader.getInstance().displayImage(AppContansts.PhpURL + result.getBanner(), mIVConent, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            callBack.loadingComplete();
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTCancel) {
                callBack.cancelPic();
            } else if (v == mIVConent) {
                if(result!=null){
                    Intent intent=new Intent(getContext(), WebActivity.class);
                    intent.putExtra("url",result.getUrl());
                    getContext().startActivity(intent);
                }
            }
        }
    };

    public interface CallBack {
        void loadingComplete();
        void cancelPic();
    }
}
