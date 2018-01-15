package com.shian.shianlife.common.view.customer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectViews;
import butterknife.OnClick;
import butterknife.OnLongClick;
import okhttp3.Request;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.ImagePreviewActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.base.BaseActivity.OnPhotoPickerListener;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.params.HpSaveCustomerContract.AddAddition;
import com.shian.shianlife.provide.params.HpSaveCustomerContract.RemoveAddition;
import com.shian.shianlife.provide.result.HrGetCustomerContract;
import com.shian.shianlife.provide.result.HrUploadFile;
import com.shian.shianlife.provide.result.HrGetCustomerContract.OrderContractAddition;

public class CustomerHTView extends BaseCustomerView {
	private View v;
	@InjectViews({ R.id.et_ht_0, R.id.et_ht_1, R.id.et_ht_2 })
	List<EditText> etList;
	@InjectViews({ R.id.btn_ht_pic_0, R.id.btn_ht_pic_1, R.id.btn_ht_pic_2,
			R.id.btn_ht_pic_3 })
	List<PicImageView> btnList;
	@InjectViews({ R.id.pb_ht_pic_0, R.id.pb_ht_pic_1, R.id.pb_ht_pic_2,
			R.id.pb_ht_pic_3 })
	List<ProgressBar> pbList;
	private long orderId;

	public CustomerHTView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_customer_ht,
				this);
		ButterKnife.inject(this, v);

	}

	public CustomerHTView(Context context) {
		this(context, null);
	}

	private List<OrderContractAddition> orderContractAdditions;

	public void initData() {
		orderId = ((Activity) getContext()).getIntent().getLongExtra("orderId",
				0);
		HpOrderIdParams params = new HpOrderIdParams();
		params.setOrderId(orderId);
		MHttpManagerFactory.getFuneralManager().getCustomerContract(
				getContext(), params,
				new HttpResponseHandler<HrGetCustomerContract>() {

					@Override
					public void onStart(Request request, int id) {

					}

					@Override
					public void onSuccess(HrGetCustomerContract result) {
						// TODO Auto-generated method stub
						etList.get(0).setText(
								result.getOrderContract().getContractNo());
						etList.get(1).setText(
								result.getOrderContract().getContractAmount());
						orderContractAdditions = result
								.getOrderContractAdditions();
						for (int i = 0; i < result.getOrderContractAdditions()
								.size(); i++) {
							PicassoUD
									.loadImage(
											getContext(),
											AppContansts.OSSURL
													+ result.getOrderContractAdditions()
															.get(i)
															.getFileUrl(),
											btnList.get(i));
							btnList.get(i).setAddItem(
									result.getOrderContractAdditions().get(i));
						}
						// etList.get(0).setText(result.getOrderContract().get);
						etList.get(2).setText(
								result.getOrderContract().getNote());
					}


					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}

	@OnClick({ R.id.btn_ht_pic_0, R.id.btn_ht_pic_1, R.id.btn_ht_pic_2,
			R.id.btn_ht_pic_3 })
	void btnPicClick(final PicImageView v) {
		if (v.getAddItem() != null) {
			Intent in = new Intent(getContext(), ImagePreviewActivity.class);
			in.putExtra("url", AppContansts.OSSURL
					+ v.getAddItem().getFileUrl());
			getContext().startActivity(in);
		}else if(v.getNewItem() != null){
			Intent in = new Intent(getContext(), ImagePreviewActivity.class);
			in.putExtra("url", AppContansts.OSSURL
					+ v.getNewItem().getFileUrl());
			getContext().startActivity(in);
		} else {
			((BaseActivity) getContext()).showPhotoPicker();
			((BaseActivity) getContext()).setOnPhotoPickerListener(new OnPhotoPickerListener() {

						@Override
						public void onPhoto(ArrayList<String> paths) {
							uploadFile(v, v.getTag().toString(), paths.get(0));
							ImageLoader.getInstance().displayImage(
									"file://" + paths.get(0), v);
						}
					});
		}

	}

	@OnLongClick({ R.id.btn_ht_pic_0, R.id.btn_ht_pic_1, R.id.btn_ht_pic_2,
			R.id.btn_ht_pic_3 })
	boolean btnPicLongclick(final PicImageView v) {
		if (v.getAddItem() == null && v.getNewItem() == null) {
			return true;
		}
		AlertDialog.Builder dialog = new Builder(getContext());
		dialog.setItems(new String[] { "修改", "删除" },
				new DialogInterface.OnClickListener() {

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
							((BaseActivity) getContext())
									.setOnPhotoPickerListener(new OnPhotoPickerListener() {

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

	private void uploadFile(final PicImageView ib, final String file,
			String path) {
		final ProgressBar pbVIew = (ProgressBar) ((ViewGroup) ib.getParent())
				.getChildAt(1);
		MHttpManagerFactory.getFileManager().upLoadFile(getContext(), file,
				path, new FileHttpResponseHandler<HrUploadFile>() {

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
					public void onError(String message) {

					}

					@Override
					public void onProgress(long total, float progress) {
						pbVIew.setVisibility(View.VISIBLE);
						pbVIew.setProgress((int) (progress * 100));
					}
				});
	}

	HpSaveCustomerContract params = new HpSaveCustomerContract();
	List<AddAddition> addList = new ArrayList<AddAddition>();
	List<RemoveAddition> removeList = new ArrayList<RemoveAddition>();

	@OnClick(R.id.tv_editorder)
	void saveData() {

		for (PicImageView picView : btnList) {
			if (picView.getNewItem() != null)
				addList.add(picView.getNewItem());
			if (picView.getDelItem() != null)
				removeList.add(picView.getDelItem());
		}

		if (addList.size() == 0
				&& (orderContractAdditions == null || orderContractAdditions
						.size() == 0)) {
			ToastUtils.show(getContext(), "请提交合同附件");
			return;
		}
		String no = etList.get(0).getText().toString();
		String amount = etList.get(1).getText().toString();
		String note = etList.get(2).getText().toString();
		if (TextUtils.isEmpty(no)) {
			ToastUtils.show(getContext(), "订单编号不能为空");
			return;
		}
		if (TextUtils.isEmpty(amount)) {
			ToastUtils.show(getContext(), "订单金额不能为空");
			return;
		}

		params.setContractAmount(amount);
		params.setContractNo(no);
		params.setOrderId(orderId);
		params.setAddAdditions(addList);
		params.setRemoveAdditions(removeList);
		params.setNote(note);
		MHttpManagerFactory.getFuneralManager().saveCustomerContract(
				getContext(), params, new HttpResponseHandler<Object>() {

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
