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
import com.shian.shianlife.common.view.editor.CemeterySetmealCtgItemView.OnCemeteryCtgChangeListener;
import com.shian.shianlife.provide.model.CemeteryModel;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("InflateParams")
public class CemeterySetmealView extends FrameLayout implements OnItemSelectedListener {

	private View v;
	private LinearLayout ll_add;
	private Spinner sp;
	private TextView tvTitle;

	List<CemeteryModel> cemeteryModels;

	List<ProductItemModel> selectProductItems;

	/**
	 * 公墓ID
	 */
	int cemeterID;
	/**
	 * 上传到服务器的公墓订单项目
	 */
	List<CreateOrderProductItemModel> mProductItemModels;
	private ProjectItemModel projectItemModel;
	long orderId;

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public CemeterySetmealView(Context context, AttributeSet attrs) {
		super(context, attrs);
		selectProductItems = new ArrayList<ProductItemModel>();
		v = LayoutInflater.from(context).inflate(R.layout.view_orders, null);
		addView(v);
		ll_add = (LinearLayout) v.findViewById(R.id.ll_add);
		sp = (Spinner) v.findViewById(R.id.sp_orders);
		tvTitle = (TextView) v.findViewById(R.id.tv_orders_title);

	}

	public void setCtgItems(String title, List<CemeteryModel> cemeteryModels) {
		this.cemeteryModels = cemeteryModels;
		tvTitle.setText(title);
		ArrayAdapter<CemeteryModel> province_adapter = new ArrayAdapter<CemeteryModel>(getContext(),
				android.R.layout.simple_spinner_item, cemeteryModels);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setOnItemSelectedListener(this);
	}

	public void setCtgItems(String title, List<CemeteryModel> cemeteryModels, HrGetOrderDetailResult result) {
		this.cemeteryModels = cemeteryModels;
		tvTitle.setText(title);
		ArrayAdapter<CemeteryModel> province_adapter = new ArrayAdapter<CemeteryModel>(getContext(),
				android.R.layout.simple_spinner_item, cemeteryModels);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setOnItemSelectedListener(this);
		for (int i = 0; i < cemeteryModels.size(); i++) {
			CemeteryModel cemeteryModel = cemeteryModels.get(i);
			if (cemeteryModel.getCemeteryId() == result.getBoard().getSetmealCemeteryId()) {
				sp.setSelection(i);
				break;
			}
		}
		for (ProjectItemModel projectItemModel : result.getProjectItems()) {
			if ("公墓".equals(projectItemModel.getName())) {
				this.projectItemModel=projectItemModel;
				break;
			}
		}
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

	public int getCemeterID() {
		return cemeterID;
	}
	private int isSp;
	private List<CreateOrderProductItemModel> initList = new ArrayList<CreateOrderProductItemModel>();
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (ll_add.getChildCount() > 0) {
			ll_add.removeAllViews();
		}
		CemeteryModel cemeteryModel = cemeteryModels.get(position);
		cemeterID = cemeteryModel.getCemeteryId();
		mProductItemModels = new ArrayList<CreateOrderProductItemModel>();
		for (CtgItemModel ctgItemModel : cemeteryModel.getCtgItems()) {
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
							sp.setSelected(false);sp.setEnabled(false);

						}
					}
					addCtgItemView(ctgItemModel, ctgItemModel.getProductItems(), productItems);
				}
			} else {
				addCtgItemView(ctgItemModel, ctgItemModel.getProductItems());
			}
		}
		if (onCemeteryChangeListener != null) {
			onCemeteryChangeListener.onCemeteryChange();
		}isSp++;
	}

	private void addCtgItemView(CtgItemModel ctgItemModel, List<ProductItemModel> productItems,
			List<OrderProductItemModel> productItems2) {
		CemeterySetmealCtgItemView mCtgItemView = new CemeterySetmealCtgItemView(getContext(), mProductItemModels);
		mCtgItemView.setOrderId(orderId);
		mCtgItemView.setCtgDate(ctgItemModel, productItems, productItems2);
		mCtgItemView.setOnCemeteryCtgChangeListener(new OnCemeteryCtgChangeListener() {

			@Override
			public void onCemeteryCtgChange() {
				if (onCemeteryChangeListener != null) {
					onCemeteryChangeListener.onCemeteryChange();
				}

			}
		});
		ll_add.addView(mCtgItemView);

	}

	private void addCtgItemView(CtgItemModel ctgItemModel, List<ProductItemModel> productItems) {
		CemeterySetmealCtgItemView mCtgItemView = new CemeterySetmealCtgItemView(getContext(), mProductItemModels);
		mCtgItemView.setCtgDate(ctgItemModel, productItems);
		mCtgItemView.setOnCemeteryCtgChangeListener(new OnCemeteryCtgChangeListener() {

			@Override
			public void onCemeteryCtgChange() {
				if (onCemeteryChangeListener != null) {
					onCemeteryChangeListener.onCemeteryChange();
				}

			}
		});
		ll_add.addView(mCtgItemView);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	OnCemeteryChangeListener onCemeteryChangeListener;

	public void setOnCemeteryChangeListener(OnCemeteryChangeListener onCemeteryChangeListener) {
		this.onCemeteryChangeListener = onCemeteryChangeListener;
	}

	public interface OnCemeteryChangeListener {
		public void onCemeteryChange();
	}

}
