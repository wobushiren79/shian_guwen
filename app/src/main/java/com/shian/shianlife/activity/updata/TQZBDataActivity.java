package com.shian.shianlife.activity.updata;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

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
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.result.HrGetCustomerPreready;
import com.shian.shianlife.provide.result.HrUploadFile;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class TQZBDataActivity extends BaseActivity {
    private View v;
    private long consultId;
    private long orderId;

    EditText et0;

    PicImageView btn_yb_pic_0;
    PicImageView btn_yb_pic_1;
    PicImageView btn_yb_pic_2;
    PicImageView btn_yb_pic_3;

    List<PicImageView> btnList;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        v = LayoutInflater.from(TQZBDataActivity.this).inflate(R.layout.activity_tqzbdata, null);
        setContentView(v);

        initView();
        setTitle("提前准备");
        consultId = getIntent().getLongExtra("consultId", 0);
        orderId = getIntent().getLongExtra("orderId", 0);
        initData();
    }

    private void initView() {
        btn_yb_pic_0 = (PicImageView) findViewById(R.id.btn_yb_pic_0);
        btn_yb_pic_1 = (PicImageView) findViewById(R.id.btn_yb_pic_1);
        btn_yb_pic_2 = (PicImageView) findViewById(R.id.btn_yb_pic_2);
        btn_yb_pic_3 = (PicImageView) findViewById(R.id.btn_yb_pic_3);

        et0 = (EditText) findViewById(R.id.et_yb_0);

        btnList = new ArrayList<>();
        btnList.add(btn_yb_pic_0);
        btnList.add(btn_yb_pic_1);
        btnList.add(btn_yb_pic_2);
        btnList.add(btn_yb_pic_3);
    }

    public void initData() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getCustomerPreready(TQZBDataActivity.this, params,
                new HttpResponseHandler<HrGetCustomerPreready>() {

                    @Override
                    public void onSuccess(HrGetCustomerPreready result) {
                        // TODO Auto-generated method stub
                        for (int i = 0; i < result.getConsultPrereadies().size(); i++) {
                            String fileUrl = AppContansts.OSSURL
                                    + result.getConsultPrereadies().get(i).getFileUrl();
                            PicassoUD.loadImage(TQZBDataActivity.this, fileUrl, btnList.get(i));
                            btnList.get(i).setAdd1Item(result.getConsultPrereadies().get(i));
                        }
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    @OnClick(R.id.btn_yb_0)
    void btnFileClick(final Button v) {
        (TQZBDataActivity.this).showFileChooser();
        (TQZBDataActivity.this).setOnFilePickerListener(new OnFilePickerListener() {
            @Override
            public void onFile(String paths) {
                if (paths == null) return;
                String filename = paths.substring(paths.lastIndexOf("/"));
                v.setText(filename);
                uploadFile(v, v.getTag().toString(), paths);
            }
        });
    }


    @OnClick({R.id.btn_yb_pic_0, R.id.btn_yb_pic_1, R.id.btn_yb_pic_2, R.id.btn_yb_pic_3})
    void btnPicClick(final PicImageView v) {
        if (v.getAdd1Item() != null || v.getNewItem() != null) {
            Intent in = new Intent(TQZBDataActivity.this, ImagePreviewActivity.class);
            if (v.getNewItem() != null) {
                in.putExtra("url", AppContansts.OSSURL + v.getNewItem().getFileUrl());
            } else {
                in.putExtra("url", AppContansts.OSSURL + v.getAdd1Item().getFileUrl());
            }
            TQZBDataActivity.this.startActivity(in);
        } else {
            (TQZBDataActivity.this).showPhotoPicker();
            (TQZBDataActivity.this).setOnPhotoPickerListener(new OnPhotoPickerListener() {

                @Override
                public void onPhoto(ArrayList<String> paths) {
                    uploadFile(v, v.getTag().toString(), paths.get(0));
                    ImageLoader.getInstance().displayImage("file://" + paths.get(0), v);
                }
            });
        }

    }

    @OnLongClick({R.id.btn_yb_pic_0, R.id.btn_yb_pic_1, R.id.btn_yb_pic_2, R.id.btn_yb_pic_3})
    boolean btnPicLongclick(final PicImageView v) {
        if (v.getAdd1Item() == null && v.getNewItem() == null) {
            return true;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(TQZBDataActivity.this);
        dialog.setItems(new String[]{"修改", "删除"}, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (which == 0) {
                    if (v.getAddItem() != null) {
                        HpSaveCustomerContract.RemoveAddition delItem = new HpSaveCustomerContract.RemoveAddition();
                        delItem.setId(v.getAddItem().getId());
                        v.setDelItem(delItem);
                    }
                    (TQZBDataActivity.this).showPhotoPicker();
                    (TQZBDataActivity.this).setOnPhotoPickerListener(new OnPhotoPickerListener() {

                        @Override
                        public void onPhoto(ArrayList<String> paths) {
                            uploadFile(v, v.getTag().toString(), paths.get(0));
                            ImageLoader.getInstance().displayImage("file://" + paths.get(0), v);
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

    private void uploadFile(final PicImageView ib, final String file, String path) {
        final ProgressBar pbVIew = (ProgressBar) ((ViewGroup) ib.getParent()).getChildAt(1);
        MHttpManagerFactory.getFileManager().upLoadFile(TQZBDataActivity.this, file, path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        ToastUtils.show(TQZBDataActivity.this, "上传成功");
                        HpSaveCustomerContract.AddAddition add = new HpSaveCustomerContract.AddAddition();
                        add.setFileName(file);
                        add.setFileUrl(t.getNameMap().get(file).toString());
                        ib.setNewItem(add);
                        // addList.add(add);
                        pbVIew.setVisibility(View.GONE);

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(long total, long current, boolean isUploading) {
                        pbVIew.setVisibility(View.VISIBLE);
                        pbVIew.setProgress((int) (current / total * 100));
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }

    private void uploadFile(final View ib, final String file, String path) {
        MHttpManagerFactory.getFileManager().upLoadFile(TQZBDataActivity.this, file, path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        ToastUtils.show(TQZBDataActivity.this, "上传成功");
                        HpSaveCustomerContract.AddAddition add = new HpSaveCustomerContract.AddAddition();
                        add.setFileName(file);
                        add.setFileUrl(t.getNameMap().get(file).toString());
                        addList.add(add);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(long total, long current, boolean isUploading) {
                        // ib.setText((int) (((float) (current / total)) * 100)
                        // + "%");
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }

    List<HpSaveCustomerContract.AddAddition> addList = new ArrayList<HpSaveCustomerContract.AddAddition>();
    HpSaveCustomerContract params = new HpSaveCustomerContract();
    List<HpSaveCustomerContract.RemoveAddition> removeList = new ArrayList<HpSaveCustomerContract.RemoveAddition>();


    @OnClick(R.id.tv_backback)
    void back() {
        finish();
    }

    @OnClick(R.id.tv_editorder)
    void saveData() {
        for (PicImageView picView : btnList) {
            if (picView.getNewItem() != null)
                addList.add(picView.getNewItem());
            if (picView.getDelItem() != null)
                removeList.add(picView.getDelItem());
        }
        params.setAddAdditions(addList);
        params.setRemoveAdditions(removeList);
        params.setOrderId(orderId);
        params.setConsultId(consultId);
        params.setContractNo(et0.getText().toString());
        MHttpManagerFactory.getAccountManager().saveCustomerPreready(TQZBDataActivity.this, params,
                new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(TQZBDataActivity.this, "保存成功");
                        finish();
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }
}
