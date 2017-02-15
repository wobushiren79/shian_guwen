package com.shian.shianlife.common.view.editor;

import java.util.List;

import com.daimajia.swipe.SwipeLayout;
import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

@SuppressLint("InflateParams")
public class SetmealProductItemView extends FrameLayout implements
		OnItemSelectedListener {

	public SetmealProductItemView(Context context,
			List<ProductItemModel> productItems,
			CreateOrderProductItemModel model) {
		super(context);
		this.productItems = productItems;
		this.model = model;
		init();
	}

	@InjectView(R.id.sp_one)
	Spinner sp;
	@InjectView(R.id.tv_one_dj)
	TextView tvDJ;
	@InjectView(R.id.tv_one_xj)
	TextView tvXJ;
	@InjectView(R.id.tv_add)
	TextView tvAdd;
	@InjectView(R.id.tv_sub)
	TextView tvSub;
	@InjectView(R.id.et_num)
	EditText etNum;

	@InjectView(R.id.btn_delete)
	Button btn_delete;

	@InjectView(R.id.sl_swipe)
	SwipeLayout sl_swipe;

	List<ProductItemModel> productItems;

	int num = 1;

	float price;

	CreateOrderProductItemModel model;

	private void init() {
		View order = LayoutInflater.from(getContext()).inflate(
				R.layout.view_one, null);
		addView(order);
		ButterKnife.inject(this);
		ArrayAdapter<ProductItemModel> province_adapter = new ArrayAdapter<ProductItemModel>(
				getContext(), android.R.layout.simple_spinner_item,
				productItems);
		province_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setOnItemSelectedListener(this);
		sl_swipe.addDrag(SwipeLayout.DragEdge.Right, R.id.bottom);
		sl_swipe.setShowMode(SwipeLayout.ShowMode.LayDown);
		sl_swipe.setDragDistance(getContext().getResources()
				.getDimensionPixelSize(R.dimen.dimen_150dp));
		for (int i = 0; i < productItems.size(); i++) {
			ProductItemModel productItemModel = productItems.get(i);
			if (productItemModel.getId() == model.getSkuId()) {
				num = model.getNumber();
				price = model.getPrice();
				sp.setSelection(i);
				break;
			}
		}

		etNum.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				num=Integer.valueOf(s.toString());
				tvXJ.setText(price * num + "");
				change(false);
			}
		});

	}

	private boolean isEdit = true;

	public void setEnableEdit(boolean is) {
		isEdit = is;
		if (!isEdit) {
			findViewById(R.id.ll_one).setBackgroundColor(
					getResources().getColor(R.color.gray));
		}
	}

	@OnClick(R.id.btn_delete)
	void delete() {
		if (!isEdit) {
			ToastUtils.show(getContext(), "该订单已被接单，不能编辑");
			return;
		}
		if (onDeleteListener != null) {
			onDeleteListener.onDelete(model);
		}
	}

	@OnClick(R.id.tv_add)
	void addNum() {
		if (!isEdit) {
			ToastUtils.show(getContext(), "该订单已被接单，不能编辑");
			return;
		}
		if (num < 999) {
			num++;
			etNum.setText(num + "");
			tvXJ.setText(price * num + "");
			change(false);
		}
	}

	@OnClick(R.id.tv_sub)
	void subNum() {
		if (!isEdit) {
			ToastUtils.show(getContext(), "该订单已被接单，不能编辑");
			return;
		}
		if (num > 0) {
			num--;
			etNum.setText(num + "");
			tvXJ.setText(price * num + "");
			change(false);
		}
	}

	private void change(boolean isFirst) {
		if (onProductItemChangeListener != null) {
			model.setTotalPrice(price * num);
			model.setNumber(num);
			onProductItemChangeListener.onProductItemChange(isFirst);
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		ProductItemModel productItemModel = productItems.get(position);
		price = productItemModel.getPrice();
		model.setSkuId(productItemModel.getId());
		model.setCategoryId(productItemModel.getCategoryId());
		model.setPrice(price);
		model.setNumber(num);
		model.setTotalPrice(model.getPrice() * model.getNumber());
		tvDJ.setText(price + "");
		change(true);
		tvXJ.setText(num * price + "");
		etNum.setText(num + "");
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	OnProductItemChangeListener onProductItemChangeListener;
	OnDeleteListener onDeleteListener;

	public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
		this.onDeleteListener = onDeleteListener;
	}

	public void setOnProductItemChangeListener(
			OnProductItemChangeListener onProductItemChangeListener) {
		this.onProductItemChangeListener = onProductItemChangeListener;
	}

	public interface OnProductItemChangeListener {
		public void onProductItemChange(boolean isFirst);
	}

	public interface OnDeleteListener {
		public void onDelete(CreateOrderProductItemModel model);
	}

	public void setCantSub(){
		tvSub.setClickable(false);
	}
}
