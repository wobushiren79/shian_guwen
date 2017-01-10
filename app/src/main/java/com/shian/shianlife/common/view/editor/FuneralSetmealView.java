package com.shian.shianlife.common.view.editor;

import android.annotation.SuppressLint;
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

import com.shian.shianlife.R;
import com.shian.shianlife.common.view.editor.FuneralSetmealCtgItemView.OnFuneralCtgChangeListener;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.model.SetmealModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("InflateParams")
public class FuneralSetmealView extends FrameLayout implements OnItemSelectedListener {

	private View v;
	private LinearLayout ll_add;
	private Spinner sp;
	private TextView tvTitle;

	List<SetmealModel> mSetmealModels;

	List<CreateOrderProductItemModel> mProductItemModels;
	int funeralID;
	private ProjectItemModel projectItemModel;
	long orderId;
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public FuneralSetmealView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_orders, null);
		addView(v);
		ll_add = (LinearLayout) v.findViewById(R.id.ll_add);
		sp = (Spinner) v.findViewById(R.id.sp_orders);
		tvTitle = (TextView) v.findViewById(R.id.tv_orders_title);

	}

	public List<CreateOrderProductItemModel> getProductItemModels() {
		List<CreateOrderProductItemModel> newList=new ArrayList<CreateOrderProductItemModel>();
		if(mProductItemModels!=null)
		for(CreateOrderProductItemModel m:mProductItemModels){
			if(!m.isChange()){
				newList.add(m);

			}
		}if(isSp>1){
			newList.addAll(initList);
		}
		return newList;
	}
	public List<CreateOrderProductItemModel> getProductItemModelsT() {
		List<CreateOrderProductItemModel> newList=new ArrayList<CreateOrderProductItemModel>();
		if(mProductItemModels!=null)
		for(CreateOrderProductItemModel m:mProductItemModels){
			if(m.getStatusFlag()!=2){
				newList.add(m);
			}
		}
		return newList;
	}
	public int getFuneralID() {
		return funeralID;
	}

	public void setCtgItems(String title, List<SetmealModel> mainSetmeals) {
		mSetmealModels = mainSetmeals;
		tvTitle.setText(title);
		ArrayAdapter<SetmealModel> province_adapter = new ArrayAdapter<SetmealModel>(getContext(),
				android.R.layout.simple_spinner_item, mainSetmeals);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setOnItemSelectedListener(this);
	}

	public void setCtgItems(String title, List<SetmealModel> funeralSetmeals, HrGetOrderDetailResult result) {
		mSetmealModels = funeralSetmeals;
		tvTitle.setText(title);
		ArrayAdapter<SetmealModel> province_adapter = new ArrayAdapter<SetmealModel>(getContext(),
				android.R.layout.simple_spinner_item, funeralSetmeals);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setOnItemSelectedListener(this);
		for (int i = 0; i < mSetmealModels.size(); i++) {
			SetmealModel setmealModel = mSetmealModels.get(i);
			if (setmealModel.getId() == result.getBoard().getSetmealFuneralId()) {
				sp.setSelection(i);
				break;
			}
		}
		for (ProjectItemModel projectItemModel : result.getProjectItems()) {
			if ("殡仪馆".equals(projectItemModel.getName())) {
				this.projectItemModel=projectItemModel;
				break;
			}
		}
	}
	private int isSp;
	private List<CreateOrderProductItemModel> initList = new ArrayList<CreateOrderProductItemModel>();
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (ll_add.getChildCount() > 0) {
			ll_add.removeAllViews();
		}
		SetmealModel setmealModel = mSetmealModels.get(position);
		mProductItemModels = new ArrayList<CreateOrderProductItemModel>();
		funeralID = setmealModel.getId();
		for (CtgItemModel ctgItemModel : setmealModel.getCtgItems()) {
			if (projectItemModel != null) {
				List<OrderCtgItemModel> ctgItems = projectItemModel.getCtgItems();
				List<OrderProductItemModel> productItems = null;
				for (OrderCtgItemModel model : ctgItems) {
					if (model.getId() == ctgItemModel.getId()) {
						productItems = model.getProductItems();
						break;
					}
				}
				if (productItems == null ||isSp>0) {
					addCtgItemView(ctgItemModel, ctgItemModel.getProductItems());
				} else {
					for(OrderProductItemModel orderProductItemModel:productItems){
						final CreateOrderProductItemModel model = new CreateOrderProductItemModel();
						model.setProjectId(1);
						model.setCategoryId(ctgItemModel.getId());
						model.setNumber(orderProductItemModel.getNumber());
						model.setPrice(orderProductItemModel.getPrice());
						model.setSkuId(orderProductItemModel.getSkuId());
						model.setTotalPrice(orderProductItemModel.getTotalPrice());
						model.setId(orderProductItemModel.getId());
						model.setStatusFlag(2);
						model.setChange(false);
						initList.add(model);
						if(!orderProductItemModel.isCanEdit()){
							sp.setSelected(false);
							sp.setEnabled(false);
						}
					}
					addCtgItemView(ctgItemModel, ctgItemModel.getProductItems(), productItems);
				}
			} else {
				addCtgItemView(ctgItemModel, ctgItemModel.getProductItems());
			}
		}
		if (onFuneralChangeListener != null) {
			onFuneralChangeListener.onFuneralChange();
		}isSp++;
	}
	//新添加：设置不能删减列表
	List<FuneralSetmealCtgItemView> listFuneralSetmealCtgItemView = new ArrayList<>();

	public void setCantSub() {
		for (FuneralSetmealCtgItemView itemView : listFuneralSetmealCtgItemView) {
			itemView.setCantSub();
		}
	}
	//新添加：设置不能删减列表

	private void addCtgItemView(CtgItemModel ctgItemModel, List<ProductItemModel> productItems,
			List<OrderProductItemModel> productItems2) {
		FuneralSetmealCtgItemView mCtgItemView = new FuneralSetmealCtgItemView(getContext(), mProductItemModels);

		//新添加：设置不能删减列表
		listFuneralSetmealCtgItemView.add(mCtgItemView);
		//新添加：设置不能删减列表

		mCtgItemView.setOrderId(orderId);
		mCtgItemView.setCtgDate(ctgItemModel, productItems,productItems2);
		mCtgItemView.setOnFuneralCtgChangeListener(new OnFuneralCtgChangeListener() {

			@Override
			public void onFuneralCtgChange() {
				if (onFuneralChangeListener != null) {
					onFuneralChangeListener.onFuneralChange();
				}
			}
		});
		ll_add.addView(mCtgItemView);
		
	}

	private void addCtgItemView(CtgItemModel ctgItemModel, List<ProductItemModel> productItems) {
		FuneralSetmealCtgItemView mCtgItemView = new FuneralSetmealCtgItemView(getContext(), mProductItemModels);

		//新添加：设置不能删减列表
		listFuneralSetmealCtgItemView.add(mCtgItemView);
		//新添加：设置不能删减列表

		mCtgItemView.setCtgDate(ctgItemModel, productItems);
		mCtgItemView.setOnFuneralCtgChangeListener(new OnFuneralCtgChangeListener() {

			@Override
			public void onFuneralCtgChange() {
				if (onFuneralChangeListener != null) {
					onFuneralChangeListener.onFuneralChange();
				}
			}
		});
		ll_add.addView(mCtgItemView);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	OnFuneralChangeListener onFuneralChangeListener;

	public void setOnFuneralChangeListener(OnFuneralChangeListener onFuneralChangeListener) {
		this.onFuneralChangeListener = onFuneralChangeListener;
	}

	public interface OnFuneralChangeListener {
		public void onFuneralChange();
	}

}
