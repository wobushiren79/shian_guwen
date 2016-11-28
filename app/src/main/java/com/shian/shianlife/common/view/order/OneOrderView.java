package com.shian.shianlife.common.view.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import butterknife.OnClick;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.annotation.Annotation;
import java.util.List;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.ProductItemModel;

@SuppressLint("InflateParams")
public class OneOrderView extends FrameLayout {
	private View v;

	public OneOrderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_oneorder, null);
		addView(v);
		initView();
	}

	public OneOrderView(Context context) {
		this(context, null);
	}

	private TextView tvTitle;
	private LinearLayout llOneOrder;
	private Button btnAddOrder;

	private void initView() {
		tvTitle = (TextView) v.findViewById(R.id.tv_one_title);
		llOneOrder = (LinearLayout) v.findViewById(R.id.ll_oneorder);
		btnAddOrder = (Button) v.findViewById(R.id.btn_addorder);
		btnAddOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addOrder();
			}
		});

	}

	protected void addOrder() {
		View order = LayoutInflater.from(getContext()).inflate(R.layout.view_one, null);
		llOneOrder.addView(order);
		Spinner sp = (Spinner) order.findViewById(R.id.sp_one);
		final TextView tvDJ = (TextView) order.findViewById(R.id.tv_one_dj);
		final TextView tvXJ = (TextView) order.findViewById(R.id.tv_one_xj);
		TextView tvAdd = (TextView) order.findViewById(R.id.tv_add);
		TextView tvSub = (TextView) order.findViewById(R.id.tv_sub);
		final EditText etNum = (EditText) order.findViewById(R.id.et_num);
		ArrayAdapter<ProductItemModel> province_adapter = new ArrayAdapter<ProductItemModel>(getContext(),
				android.R.layout.simple_spinner_item, productItems);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				ProductItemModel productItemModel = productItems.get(position);
				tvDJ.setText(productItemModel.getPrice() + "");
				tvXJ.setText(Integer.parseInt(etNum.getText().toString()) * productItemModel.getPrice() + "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		tvAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int num = Integer.parseInt(etNum.getText().toString());
				float price = Float.parseFloat(tvDJ.getText().toString());
				if (num < 999) {
					num++;
					etNum.setText(num + "");
					tvXJ.setText(num * price + "");
				}
			}
		});
		tvSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int num = Integer.parseInt(etNum.getText().toString());
				float price = Float.parseFloat(tvDJ.getText().toString());
				if (num > 0) {
					num--;
					etNum.setText(num + "");
					tvXJ.setText(num * price + "");
				}

			}
		});

	}

	private String mDj;
	private String mXj;
	private String[] mArr;
	private List<ProductItemModel> productItems;

	public void setOrderDate(String title, String dj, String xj, String[] arr) {
		mDj = dj;
		mXj = xj;
		mArr = arr;
		tvTitle.setText(title);
		addOrder(dj, xj, arr);
	}

	public void addOrder(String dj, String xj, String[] arr) {
		View order = LayoutInflater.from(getContext()).inflate(R.layout.view_one, null);
		llOneOrder.addView(order);
		Spinner sp = (Spinner) order.findViewById(R.id.sp_one);
		TextView tvDJ = (TextView) order.findViewById(R.id.tv_one_dj);
		TextView tvXJ = (TextView) order.findViewById(R.id.tv_one_xj);
		TextView tvAdd = (TextView) order.findViewById(R.id.tv_add);
		TextView tvSub = (TextView) order.findViewById(R.id.tv_sub);
		final EditText etNum = (EditText) order.findViewById(R.id.et_num);
		tvDJ.setText(dj);
		tvXJ.setText(xj);
		tvSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int count = Integer.valueOf(etNum.getText().toString());
				if (count > 0) {
					etNum.setText(count - 1 + "");
				}
			}
		});
		tvAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int count = Integer.valueOf(etNum.getText().toString());
				etNum.setText(count + 1 + "");
			}
		});
		ArrayAdapter<CharSequence> province_adapter = new ArrayAdapter<CharSequence>(getContext(),
				android.R.layout.simple_spinner_item, arr);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);

	}

	public void setOrderDate(String name, List<ProductItemModel> productItems) {
		this.productItems = productItems;
		tvTitle.setText(name);
	}
}
