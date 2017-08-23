package com.shian.shianlife.common.view.customer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import butterknife.OnLongClick;
import okhttp3.Request;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.ImagePreviewActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.base.BaseActivity.OnFilePickerListener;
import com.shian.shianlife.base.BaseActivity.OnPhotoPickerListener;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.params.HpSaveCustomerContract.AddAddition;
import com.shian.shianlife.provide.params.HpSaveCustomerContract.RemoveAddition;
import com.shian.shianlife.provide.result.HrGetCustomerPreready;
import com.shian.shianlife.provide.result.HrUploadFile;

public class CustomerYBView extends BaseCustomerView {
    private View v;
    private long consultId;
    private long orderId;
    @InjectView(R.id.et_yb_0)
    EditText et0;
    @InjectViews({R.id.btn_yb_pic_0, R.id.btn_yb_pic_1, R.id.btn_yb_pic_2, R.id.btn_yb_pic_3})
    List<PicImageView> btnList;

    public CustomerYBView(Context context, AttributeSet attrs) {
        super(context, attrs);
        v = LayoutInflater.from(context).inflate(R.layout.view_customer_yb, this);
        ButterKnife.inject(this, v);
        consultId = ((Activity) getContext()).getIntent().getLongExtra("consultId", 0);
        orderId = ((Activity) getContext()).getIntent().getLongExtra("orderId", 0);
    }

    public CustomerYBView(Context context) {
        this(context, null);
    }

    public void initData() {
        super.initData();
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getFuneralManager().getCustomerPreready(getContext(), params,
                new HttpResponseHandler<HrGetCustomerPreready>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrGetCustomerPreready result) {
                        // TODO Auto-generated method stub
                        for (int i = 0; i < result.getConsultPrereadies().size(); i++) {
                            String fileUrl = AppContansts.OSSURL
                                    + result.getConsultPrereadies().get(i).getFileUrl();
                            PicassoUD.loadImage(getContext(), fileUrl, btnList.get(i));
                            btnList.get(i).setAdd1Item(result.getConsultPrereadies().get(i));
                        }
                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    @OnClick(R.id.btn_yb_0)
    void btnFileClick(final Button v) {
        ((BaseActivity) getContext()).showFileChooser();
        ((BaseActivity) getContext()).setOnFilePickerListener(new OnFilePickerListener() {
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
            Intent in = new Intent(getContext(), ImagePreviewActivity.class);
            if (v.getNewItem() != null) {
                in.putExtra("url", AppContansts.OSSURL + v.getNewItem().getFileUrl());
            } else {
                in.putExtra("url", AppContansts.OSSURL + v.getAdd1Item().getFileUrl());
            }
            getContext().startActivity(in);
        } else {
            ((BaseActivity) getContext()).showPhotoPicker();
            ((BaseActivity) getContext()).setOnPhotoPickerListener(new OnPhotoPickerListener() {

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
        AlertDialog.Builder dialog = new Builder(getContext());
        dialog.setItems(new String[]{"修改", "删除"}, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (which == 0) {
                    if (v.getAddItem() != null) {
                        RemoveAddition delItem = new RemoveAddition();
                        delItem.setId(v.getAddItem().getId());
                        v.setDelItem(delItem);
                    }
                    ((BaseActivity) getContext()).showPhotoPicker();
                    ((BaseActivity) getContext()).setOnPhotoPickerListener(new OnPhotoPickerListener() {

                        @Override
                        public void onPhoto(ArrayList<String> paths) {
                            uploadFile(v, v.getTag().toString(), paths.get(0));
                            ImageLoader.getInstance().displayImage("file://" + paths.get(0), v);
                        }
                    });
                } else {
                    if (v.getAddItem() != null) {
                        RemoveAddition delItem = new RemoveAddition();
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
        MHttpManagerFactory.getFileManager().upLoadFile(getContext(), file, path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        ToastUtils.show(getContext(), "上传成功");
                        AddAddition add = new HpSaveCustomerContract.AddAddition();
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
        MHttpManagerFactory.getFileManager().upLoadFile(getContext(), file, path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        ToastUtils.show(getContext(), "上传成功");
                        AddAddition add = new HpSaveCustomerContract.AddAddition();
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

    List<AddAddition> addList = new ArrayList<AddAddition>();
    HpSaveCustomerContract params = new HpSaveCustomerContract();
    List<RemoveAddition> removeList = new ArrayList<RemoveAddition>();

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
        MHttpManagerFactory.getFuneralManager().saveCustomerPreready(getContext(), params,
                new HttpResponseHandler<Object>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(getContext(), "保存成功");
                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }
}
