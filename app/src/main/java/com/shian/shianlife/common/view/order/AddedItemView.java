package com.shian.shianlife.common.view.order;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.daimajia.swipe.SwipeLayout;
import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.model.AddedCtgModel;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.GoodsModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 增值商品
 * 
 * @author w9433
 *
 */
@SuppressLint("InflateParams")
public class AddedItemView extends FrameLayout implements OnItemSelectedListener {
	@InjectView(R.id.tv_addedname)
	TextView tv_addedname;
	@InjectView(R.id.sp_addedtype)
	Spinner sp_addedtype;
	@InjectView(R.id.tv_one_dj)
	TextView tv_one_dj;
	@InjectView(R.id.tv_sub)
	TextView tv_sub;
	@InjectView(R.id.tv_add)
	TextView tv_add;
	@InjectView(R.id.et_num)
	EditText et_num;
	@InjectView(R.id.tv_one_xj)
	TextView tv_one_xj;

	@InjectView(R.id.btn_delete)
	Button btn_delete;

	@InjectView(R.id.sl_swipe)
	SwipeLayout sl_swipe;

	List<GoodsModel> productItems;
	GoodsModel goodsModel;
	CreateOrderProductItemModel model;

	int num = 1;

	public AddedItemView(Context context, List<GoodsModel> productItems, AddedCtgModel addedCtgModel,
			CreateOrderProductItemModel model) {
		super(context);
		this.productItems = productItems;
		this.model = model;
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_addeditem, null);
		addView(view);
		ButterKnife.inject(this);
		ArrayAdapter<GoodsModel> adapter = new ArrayAdapter<GoodsModel>(getContext(),
				android.R.layout.simple_spinner_item, productItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_addedtype.setAdapter(adapter);
		sp_addedtype.setOnItemSelectedListener(this);
		tv_addedname.setText(addedCtgModel.getName());
		sl_swipe.addDrag(SwipeLayout.DragEdge.Right, R.id.bottom);
		sl_swipe.setShowMode(SwipeLayout.ShowMode.LayDown);
		et_num.setText(num+"");

	}

	public AddedItemView(Context context, List<GoodsModel> productItems, OrderCtgItemModel mOrderCtgItemModel,
			CreateOrderProductItemModel model) {
		super(context);
		this.productItems = productItems;
		this.model = model;
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_addeditem, null);
		addView(view);
		ButterKnife.inject(this);
		ArrayAdapter<GoodsModel> adapter = new ArrayAdapter<GoodsModel>(getContext(),
				android.R.layout.simple_spinner_item, productItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_addedtype.setAdapter(adapter);
		sp_addedtype.setOnItemSelectedListener(this);
		tv_addedname.setText(mOrderCtgItemModel.getName());
		sl_swipe.addDrag(SwipeLayout.DragEdge.Right, R.id.bottom);
		sl_swipe.setShowMode(SwipeLayout.ShowMode.LayDown);
		for (int i = 0; i < productItems.size(); i++) {
			if (productItems.get(i).getId()==model.getSkuId()) {
				sp_addedtype.setSelection(i);
				num = model.getNumber();
			}
		}
		et_num.setText(num+"");
	}

	private boolean isEdit = true;

	public void setEnableEdit(boolean is) {
		isEdit = is;
		if (!isEdit) {
			findViewById(R.id.ll_one).setBackgroundColor(
					getResources().getColor(R.color.gray));
		}
	}

	@OnClick(R.id.tv_add)
	void addNum() {
		if (num < 999) {
			num++;
			et_num.setText(num + "");
			tv_one_xj.setText(goodsModel.getPrice() * num + "");
			change(false);
		}
	}

	@OnClick(R.id.tv_sub)
	void subNum() {
		if (num > 0) {
			num--;
			et_num.setText(num + "");
			tv_one_xj.setText(goodsModel.getPrice() * num + "");
			change(false);
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

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		goodsModel = productItems.get(position);
		tv_one_dj.setText(goodsModel.getPrice() + "");
		tv_one_xj.setText(goodsModel.getPrice() * num + "");
		model.setSkuId(goodsModel.getId());
		change(true);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	private void change(boolean isFirst) {
		model.setNumber(num);
		model.setPrice(goodsModel.getPrice());
		model.setTotalPrice(num * model.getPrice());
		model.setSkuId(goodsModel.getId());
		if (onChangeListener != null) {
			onChangeListener.onChange(isFirst);
		}
	}

	OnChangeListener onChangeListener;

	OnDeleteListener onDeleteListener;

	public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
		this.onDeleteListener = onDeleteListener;
	}

	public void setOnChangeListener(OnChangeListener onChangeListener) {
		this.onChangeListener = onChangeListener;
	}

	public interface OnChangeListener {

		public void onChange(boolean isFirst);
	}

	public interface OnDeleteListener {
		public void onDelete(CreateOrderProductItemModel model);
	}

}
