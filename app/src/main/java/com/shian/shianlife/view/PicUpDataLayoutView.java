package com.shian.shianlife.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.ImagePreviewActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.customer.PicImageView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.result.HrGetCustomerContract;
import com.shian.shianlife.provide.result.HrUploadFile;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/22.
 */

public class PicUpDataLayoutView extends LinearLayout {
    PicImageView picIV;
    CheckBox checkBox;
    TextView name;

    public PicUpDataLayoutView(Context context) {
        super(context);
    }

    public PicUpDataLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_picupdata_view, null);

        name = (TextView) view.findViewById(R.id.tv_name);
        checkBox = (CheckBox) view.findViewById(R.id.cb_check);
        picIV = (PicImageView) view.findViewById(R.id.btn_ht_pic_0);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.pb_ht_pic_0);

        picIV.setOnClickListener(onClickListener);
        picIV.setOnLongClickListener(onLongClickListener);
        addView(view);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == picIV) {
                btnPicClick(picIV);
            }
        }
    };


    OnLongClickListener onLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            if (view == picIV) {
                btnPicLongclick(picIV);
            }
            return true;
        }
    };

    void btnPicClick(final PicImageView v) {
        if (v.getAddItem() != null) {
            Intent in = new Intent(getContext(), ImagePreviewActivity.class);
            in.putExtra("url", v.getAddItem().getFileUrl());
            getContext().startActivity(in);
        } else if (v.getNewItem() != null) {
            Intent in = new Intent(getContext(), ImagePreviewActivity.class);
            in.putExtra("url", v.getNewItem().getFileUrl());
            getContext().startActivity(in);
        } else {
            ((BaseActivity) getContext()).showPhotoPicker();
            ((BaseActivity) getContext()).setOnPhotoPickerListener(new BaseActivity.OnPhotoPickerListener() {

                @Override
                public void onPhoto(ArrayList<String> paths) {
                    uploadFile(v, v.getTag().toString(), paths.get(0));
                    ImageLoader.getInstance().displayImage(
                            "file://" + paths.get(0), v);
                }
            });
        }
    }

    boolean btnPicLongclick(final PicImageView v) {
        if (v.getAddItem() == null && v.getNewItem() == null) {
            return true;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setItems(new String[]{"修改", "删除"},
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        if (which == 0) {
                            if (v.getAddItem() != null) {
                                HpSaveCustomerContract.RemoveAddition delItem = new HpSaveCustomerContract.RemoveAddition();
                                delItem.setId(v.getAddItem().getId());
                                v.setDelItem(delItem);
                            }
                            ((BaseActivity) getContext()).showPhotoPicker();
                            ((BaseActivity) getContext())
                                    .setOnPhotoPickerListener(new BaseActivity.OnPhotoPickerListener() {

                                        @Override
                                        public void onPhoto(
                                                ArrayList<String> paths) {
                                            uploadFile(v,
                                                    v.getTag().toString(),
                                                    paths.get(0));
                                            ImageLoader
                                                    .getInstance()
                                                    .displayImage(
                                                            "file://"
                                                                    + paths.get(0),
                                                            v);
                                        }
                                    });
                        } else {
                            if (v.getAddItem() != null) {
                                HpSaveCustomerContract.RemoveAddition delItem = new HpSaveCustomerContract.RemoveAddition();
                                delItem.setId(v.getAddItem().getId());
                                v.setDelItem(delItem);
                            }
                            v.setImageResource(R.drawable.ic_ht_pic);
                            v.setAddItem(null);
                            v.setNewItem(null);
                        }
                    }
                });
        dialog.show();
        return true;
    }

    private void uploadFile(final PicImageView ib, final String file,
                            String path) {
        final ProgressBar pbVIew = (ProgressBar) ((ViewGroup) ib.getParent())
                .getChildAt(1);
        MHttpManagerFactory.getFileManager().upLoadFile(getContext(), file,
                path, new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        ToastUtils.show(getContext(), "上传成功");
                        HpSaveCustomerContract.AddAddition add = new HpSaveCustomerContract.AddAddition();
                        add.setFileName(file);
                        add.setFileUrl(AppContansts.OSSURL + t.getNameMap().get(file).toString());
                        ib.setNewItem(add);
                        pbVIew.setVisibility(View.GONE);

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(long total, long current,
                                           boolean isUploading) {
                        pbVIew.setVisibility(View.VISIBLE);
                        pbVIew.setProgress((int) (current / total * 100));
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }

    /**
     * @return 0没有图片 1有图片
     */
    public int getUpdataState() {
        if (picIV.getNewItem() == null) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * 查看点击状态
     *
     * @return
     */
    public boolean isCheckChecBox() {
        if (checkBox.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置名字
     *
     * @param name
     */
    public void setName(String name) {
        this.name.setText(name);
    }

    /**
     * 获取图片路劲
     *
     * @return
     */
    public String getPic() {
        return picIV.getNewItem().getFileUrl();
    }

    public void setPic(String url) {
        PicassoUD.loadImage(getContext(), url, picIV);
//        HrGetCustomerContract.OrderContractAddition orderContractAddition = new HrGetCustomerContract.OrderContractAddition();
//        orderContractAddition.setFileUrl(url);
//        picIV.setAddItem(orderContractAddition);
        HpSaveCustomerContract.AddAddition addAddition = new HpSaveCustomerContract.AddAddition();
        addAddition.setFileUrl(url);
        picIV.setNewItem(addAddition);
    }

    public void setCheck(boolean check) {
        checkBox.setChecked(check);
    }
}
