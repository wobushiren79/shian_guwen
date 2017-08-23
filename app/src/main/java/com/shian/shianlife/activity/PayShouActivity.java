package com.shian.shianlife.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.updata.ContractDataActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.FilePathUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.finger.DrawView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpOrderFeedback;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.result.HrCommentResult;
import com.shian.shianlife.provide.result.HrOrderFeedback;
import com.shian.shianlife.provide.result.HrUploadFile;
import com.shian.shianlife.view.dialog.SignDialog;

import org.support.v4.annotation.NonNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import okhttp3.Request;

public class PayShouActivity extends BaseActivity {
    @InjectView(R.id.iv_sign)
    ImageView ivSign;
    @InjectViews({R.id.sp_pay_00, R.id.sp_pay_01, R.id.sp_pay_10,
            R.id.sp_pay_11, R.id.sp_pay_20, R.id.sp_pay_21})
    List<Spinner> spList;
    @InjectViews({R.id.tv_pays_0, R.id.tv_pays_1, R.id.tv_pays_2, R.id.tv_pays_3})
    List<TextView> tvList;
    @InjectView(R.id.et_pay_opinion)
    EditText etOp;
    private long orderId;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_pay_shou);
        setTitle("收款");
        orderId = getIntent().getLongExtra("orderId", 0);
        initPermission();
        intiSpinner(new int[]{1, 1, 1, 1, 1, 1});
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMoney();
    }

    @TargetApi(23)
    private void initPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1002);
            } else {

            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1002) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }

    private String mAmount;

    private void getMoney() {
        HpOrderIdParams params = new HpOrderIdParams();
        params.setOrderId(getIntent().getLongExtra("orderId", 0));
        MHttpManagerFactory.getFuneralManager().getOrderFeedback(this, params,
                new HttpResponseHandler<HrOrderFeedback>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrOrderFeedback result) {
                        // TODO Auto-generated method stub
                        tvList.get(0).setText("￥" + result.getPrepayAmount());
                        tvList.get(1).setText(
                                "￥" + result.getTotalAmount());
                        tvList.get(2)
                                .setText("￥" + result.getRestAmount());
                        tvList.get(3)
                                .setText("￥" + result.getRefundAmount());
                        mAmount = result.getRestAmount();
                        etOp.setText(result.getPayFeedback().getOpinion());
                        intiSpinner(new int[]{
                                result.getPayFeedback().getSatTotal(),
                                result.getPayFeedback().getSatCeremony(),
                                result.getPayFeedback().getSatArrange(),
                                result.getPayFeedback().getSatFr(),
                                result.getPayFeedback().getSatLife(),
                                result.getPayFeedback().getSatSku()});
                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    private void intiSpinner(int[] sats) {
        for (int i = 0; i < spList.size(); i++) {
            final Spinner sp = spList.get(i);
            ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
                    .createFromResource(this, R.array.pay_my,
                            android.R.layout.simple_spinner_item);
            province_adapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(province_adapter);
            sp.setSelection(sats[i] - 1);
            sp.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    // params.setFrBelief(position + 1);
                    switch (sp.getId()) {
                        case R.id.sp_pay_00:
                            params.setSatTotal(position + 1);
                            break;
                        case R.id.sp_pay_01:
                            params.setSatCeremony(position + 1);
                            break;
                        case R.id.sp_pay_10:
                            params.setSatArrange(position + 1);
                            break;
                        case R.id.sp_pay_11:
                            params.setSatFr(position + 1);
                            break;
                        case R.id.sp_pay_20:
                            params.setSatLife(position + 1);
                            break;
                        case R.id.sp_pay_21:
                            params.setSatSku(position + 1);
                            break;

                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @OnClick(R.id.tv_orderDetail)
    void onOrderDetial(View v) {
        Intent in = new Intent(this, OrderDetailActivity.class);
        in.putExtra("orderId", getIntent().getLongExtra("orderId", 0));
        startActivity(in);
    }

    Bitmap bitmapName;

    /**
     * 签名
     */
    @OnClick(R.id.iv_sign)
    void reSign(View v) {
        SignDialog dialog = new SignDialog(PayShouActivity.this, R.style.CustomDialog);
        dialog.setCallBack(new SignDialog.CallBack() {
            @Override
            public void signComplete(Bitmap bitmapName) {
                ivSign.setImageBitmap(bitmapName);
                PayShouActivity.this.bitmapName = bitmapName;
            }
        });
        dialog.show();

    }

    HpOrderFeedback params = new HpOrderFeedback();
    boolean isfinfish = true;

    @OnClick(R.id.tv_pay_sure)
    void payClick(View v) {
        final File file = FilePathUtils.getDownloadFile("sign", "1.png");
//		Toast.makeText(getBaseContext(),"read pay",Toast.LENGTH_SHORT).show();
        try {
            if (!file.exists())
                file.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            // Bitmap bm=Bitmap.createBitmap(dvPay.getDrawingCache());
            // dvPay.setDrawingCacheEnabled(false);
            if (bitmapName == null) {
                ToastUtils.show(PayShouActivity.this, "还没有签名");
                return;
            }
            bitmapName.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            // Bitmap.createBitmap(dvPay.getDrawingCache());
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//		if(!isfinfish)return;
        isfinfish = false;
        MHttpManagerFactory.getFileManager().upLoadFile(this, "file",
                file.getAbsolutePath(),
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        // TODO Auto-generated method stub
                        restPay(t.getNameMap().get("file").toString());
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProgress(long total, long current,
                                           boolean isUploading) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getBaseContext(), "upload" + message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void restPay(final String path) {
        HpOrderIdParams params = new HpOrderIdParams();
        params.setOrderId(orderId);
        params.setPayAmount(mAmount);
        MHttpManagerFactory.getFuneralManager().creatRestPay(this, params,
                new HttpResponseHandler<HrCommentResult>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrCommentResult result) {
                        // TODO Auto-generated method stub
                        toPay(path, result.getPayId());
                    }



                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getBaseContext(), "restpay" + message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void toPay(String path, final long PayId) {
        params.setOpinion(etOp.getText().toString());
        params.setOrderId(orderId);
        params.setSignUrl(path);
        MHttpManagerFactory.getFuneralManager().saveOrderFeedback(this, params,
                new HttpResponseHandler<HrCommentResult>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrCommentResult result) {
                        // TODO Auto-generated method stub
                        isfinfish = true;
                        Intent in = new Intent(PayShouActivity.this,
                                PayActivity.class);
                        in.putExtra("orderId",
                                getIntent().getLongExtra("orderId", 0));
                        in.putExtra("payAmount", mAmount);
                        in.putExtra("ding", false);
                        in.putExtra("payId", PayId);
                        startActivity(in);
                        finish();
                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getBaseContext(), "topay" + message, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @OnClick(R.id.tv_pay_refund)
    void refundClick(View v) {
        Intent in = new Intent(this, RefundOrderActivity.class);
        in.putExtra("orderId", getIntent().getLongExtra("orderId", 0));
        in.putExtra("consultId", getIntent().getLongExtra("consultId", 0));
        startActivity(in);
    }
}
