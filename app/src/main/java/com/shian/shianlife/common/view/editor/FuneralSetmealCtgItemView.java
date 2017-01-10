package com.shian.shianlife.common.view.editor;

import java.util.ArrayList;
import java.util.List;

import com.shian.shianlife.R;
import com.shian.shianlife.common.view.editor.SetmealProductItemView.OnDeleteListener;
import com.shian.shianlife.common.view.editor.SetmealProductItemView.OnProductItemChangeListener;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FuneralSetmealCtgItemView extends FrameLayout {
	private View v;

	public FuneralSetmealCtgItemView(Context context, List<CreateOrderProductItemModel> mProductItemModels) {
		super(context);
		v = LayoutInflater.from(context).inflate(R.layout.view_oneorder, null);
		addView(v);
		initView();
		this.mProductItemModels = mProductItemModels;
	}

	private TextView tvTitle;
	private LinearLayout llOneOrder;
	private Button btnAddOrder;
	private List<ProductItemModel> productItems;
	List<CreateOrderProductItemModel> mProductItemModels;
	long orderId;
	private CtgItemModel mCtgItemModel;

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	private void initView() {
		tvTitle = (TextView) v.findViewById(R.id.tv_one_title);
		llOneOrder = (LinearLayout) v.findViewById(R.id.ll_oneorder);
		btnAddOrder = (Button) v.findViewById(R.id.btn_addorder);
		btnAddOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addProductItem();
			}
		});

	}

	//新添加：设置不能删减列表
	List<SetmealProductItemView> listSetmealProductItemView = new ArrayList<>();

	public void setCantSub() {
		for (SetmealProductItemView itemView : listSetmealProductItemView) {
			itemView.setCantSub();
		}
	}
	//新添加：设置不能删减列表

	protected void addProductItem() {
		CreateOrderProductItemModel model = new CreateOrderProductItemModel();
		model.setProjectId(2);
		mProductItemModels.add(model);
		final SetmealProductItemView mProductItemView = new SetmealProductItemView(getContext(), productItems, model);

		//新添加：设置不能删减列表
		listSetmealProductItemView.add(mProductItemView);
		//新添加：设置不能删减列表

		mProductItemView.setOnProductItemChangeListener(new OnProductItemChangeListener() {

			@Override
			public void onProductItemChange(boolean isFirst) {
				if (onFuneralCtgChangeListener != null) {
					onFuneralCtgChangeListener.onFuneralCtgChange();
				}
			}
		});
		mProductItemView.setOnDeleteListener(new OnDeleteListener() {

			@Override
			public void onDelete(CreateOrderProductItemModel model) {
				mProductItemModels.remove(model);
				llOneOrder.removeView(mProductItemView);
				if (onFuneralCtgChangeListener != null) {
					onFuneralCtgChangeListener.onFuneralCtgChange();
				}
			}
		});
		llOneOrder.addView(mProductItemView);
	}

	private void addProductItem(OrderProductItemModel selectProductItem) {
		final CreateOrderProductItemModel model = new CreateOrderProductItemModel();
		model.setProjectId(2);
		model.setCategoryId(mCtgItemModel.getId());
		model.setNumber(selectProductItem.getNumber());
		model.setPrice(selectProductItem.getPrice());
		model.setSkuId(selectProductItem.getSkuId());
		model.setTotalPrice(selectProductItem.getTotalPrice());
		model.setId(selectProductItem.getId());
		model.setStatusFlag(1);
		model.setChange(true);
		mProductItemModels.add(model);
		final SetmealProductItemView mProductItemView = new SetmealProductItemView(getContext(), productItems, model);


		//新添加：设置不能删减列表
		listSetmealProductItemView.add(mProductItemView);
		//新添加：设置不能删减列表

		mProductItemView.setEnableEdit(selectProductItem.isCanEdit());
		mProductItemView.setOnProductItemChangeListener(new OnProductItemChangeListener() {

			@Override
			public void onProductItemChange(boolean isFirst) {
				if(!isFirst){
					model.setChange(false);
				}
				if (onFuneralCtgChangeListener != null) {
					onFuneralCtgChangeListener.onFuneralCtgChange();
				}
			}
		});
		mProductItemView.setOnDeleteListener(new OnDeleteListener() {

			@Override
			public void onDelete(CreateOrderProductItemModel model) {
//				mProductItemModels.remove(model);
				model.setChange(false);
				model.setStatusFlag(2);
				llOneOrder.removeView(mProductItemView);
				if (onFuneralCtgChangeListener != null) {
					onFuneralCtgChangeListener.onFuneralCtgChange();
				}
			}
		});
		llOneOrder.addView(mProductItemView);
	}

	public void setCtgDate(CtgItemModel ctgItemModel, List<ProductItemModel> productItems) {
		this.productItems = productItems;
		tvTitle.setText(ctgItemModel.getName());
		mCtgItemModel = ctgItemModel;
	}

	public void setCtgDate(CtgItemModel ctgItemModel, List<ProductItemModel> productItems,
			List<OrderProductItemModel> productItems2) {
		this.productItems = productItems;
		tvTitle.setText(ctgItemModel.getName());
		mCtgItemModel = ctgItemModel;
		for (OrderProductItemModel selectProductItem : productItems2) {
			addProductItem(selectProductItem);
		}
	}

	OnFuneralCtgChangeListener onFuneralCtgChangeListener;

	public void setOnFuneralCtgChangeListener(OnFuneralCtgChangeListener onFuneralCtgChangeListener) {
		this.onFuneralCtgChangeListener = onFuneralCtgChangeListener;
	}

	public interface OnFuneralCtgChangeListener {
		public void onFuneralCtgChange();
	}

}
