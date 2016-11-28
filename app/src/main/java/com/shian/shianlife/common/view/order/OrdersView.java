package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.provide.model.SetmealModel;

public class OrdersView extends FrameLayout implements OnItemSelectedListener {

	private View v;
	private LinearLayout ll_add;
	private Spinner sp;
	private TextView tvTitle;

	List<SetmealModel> mSetmealModels;

	public OrdersView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_orders, null);
		addView(v);
		ll_add = (LinearLayout) v.findViewById(R.id.ll_add);
		sp = (Spinner) v.findViewById(R.id.sp_orders);
		tvTitle = (TextView) v.findViewById(R.id.tv_orders_title);
	}

	public void setOrders(String title, List<SetmealModel> mainSetmeals) {
		mSetmealModels = mainSetmeals;
		tvTitle.setText(title);
		ArrayAdapter<SetmealModel> province_adapter = new ArrayAdapter<SetmealModel>(getContext(),
				android.R.layout.simple_spinner_item, mainSetmeals);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setOnItemSelectedListener(this);
	}

	public void addOrder(String title, String dj, String xj, String[] arr) {
		OneOrderView oneOrder = new OneOrderView(getContext());
		oneOrder.setOrderDate(title, dj, xj, arr);
		ll_add.addView(oneOrder);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (ll_add.getChildCount()>0) {
			ll_add.removeAllViews();
		}
		SetmealModel setmealModel = mSetmealModels.get(position);
		for (CtgItemModel ctgItemModel : setmealModel.getCtgItems()) {
			addOrder(ctgItemModel.getName()+position, ctgItemModel.getProductItems());
		}
	}

	private void addOrder(String name, List<ProductItemModel> productItems) {
		OneOrderView oneOrderView = new OneOrderView(getContext());
		oneOrderView.setOrderDate(name, productItems);
		ll_add.addView(oneOrderView);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

}
