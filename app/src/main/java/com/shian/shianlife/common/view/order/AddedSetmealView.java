package com.shian.shianlife.common.view.order;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.order.AddedItemView.OnChangeListener;
import com.shian.shianlife.common.view.order.AddedItemView.OnDeleteListener;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.ProductManagerImpl;
import com.shian.shianlife.provide.model.AddedCtgModel;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.GoodsModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.params.HpGetAddedCtgListParams;
import com.shian.shianlife.provide.params.HpGetGoodsListParams;
import com.shian.shianlife.provide.result.HrGetAddedCtgListResult;
import com.shian.shianlife.provide.result.HrGetGoodsListResult;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 增值服务
 * 
 * @author w9433
 *
 */
@SuppressLint("InflateParams")
public class AddedSetmealView extends FrameLayout {

	@InjectView(R.id.tv_additem)
	TextView tv_additem;

	/**
	 * 所有的增值服务产品列表
	 */
	List<AddedCtgModel> mAddedCtgModels;

	ProjectItemModel mProjectItemModel;

	int addedNum;

	private LinearLayout mLinearLayout;

	List<CreateOrderProductItemModel> mProductItemModels;

	public AddedSetmealView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public AddedSetmealView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AddedSetmealView(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		mLinearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.view_addedsetmeal, null);
		addView(mLinearLayout);
		ButterKnife.inject(this);
		mProductItemModels = new ArrayList<CreateOrderProductItemModel>();
	}

	@OnClick(R.id.tv_additem)
	void addAddedCtg() {
		if (mAddedCtgModels == null) {
			getAddedCtgList();
		} else {
			showSelectAddedCtg();
		}
	}

	/**
	 * 获取增值产品分类
	 */
	private void getAddedCtgList() {
		HpGetAddedCtgListParams params = new HpGetAddedCtgListParams();
		params.setProjectId(4);
		ProductManagerImpl.getInstance().getAddedCtgList(getContext(), params,
				new HttpResponseHandler<HrGetAddedCtgListResult>() {

					@Override
					public void onSuccess(HrGetAddedCtgListResult result) {
						if (result != null && result.getCtgItems() != null && result.getCtgItems().size() > 0) {
							mAddedCtgModels = result.getCtgItems();
							showSelectAddedCtg();
						} else {
							ToastUtils.show(getContext(), "暂无增值服务产品");
						}
					}

					@Override
					public void onStart() {
					}

					@Override
					public void onError(String message) {
					}
				});
	}

	/**
	 * 选择增值服务产品
	 */
	protected void showSelectAddedCtg() {
		ArrayAdapter<AddedCtgModel> adapter = new ArrayAdapter<AddedCtgModel>(getContext(),
				android.R.layout.simple_list_item_1, mAddedCtgModels);
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("选择增值服务产品");
		builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				getGoodsList(mAddedCtgModels.get(which));
			}
		});
		builder.show();
	}

	/**
	 * 根据产品获取商品列表
	 * 
	 * @param addedCtgModel
	 */
	protected void getGoodsList(final AddedCtgModel addedCtgModel) {
		HpGetGoodsListParams params = new HpGetGoodsListParams();
		params.setCtgId(addedCtgModel.getId());
		ProductManagerImpl.getInstance().getGoodsList(getContext(), params,
				new HttpResponseHandler<HrGetGoodsListResult>() {

					@Override
					public void onSuccess(HrGetGoodsListResult result) {
						if (result != null && result.getProductItems() != null && result.getProductItems().size() > 0) {
							addAddedCtgView(result.getProductItems(), addedCtgModel);
						}
					}

					@Override
					public void onStart() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}

	/**
	 * 添加一款产品
	 * 
	 * @param productItems
	 * @param addedCtgModel
	 */
	protected void addAddedCtgView(List<GoodsModel> productItems, AddedCtgModel addedCtgModel) {
		final CreateOrderProductItemModel model = new CreateOrderProductItemModel();
		model.setCategoryId(addedCtgModel.getId());
		model.setProjectId(4);
		mProductItemModels.add(model);
		final AddedItemView addedItemView = new AddedItemView(getContext(), productItems, addedCtgModel, model);
		addedItemView.setOnChangeListener(new OnChangeListener() {

			@Override
			public void onChange(boolean isFirst) {
				if (onAddedChangeListener != null) {
					onAddedChangeListener.onChange();
				}
			}
		});
		addedItemView.setOnDeleteListener(new OnDeleteListener() {

			@Override
			public void onDelete(CreateOrderProductItemModel model) {
				mProductItemModels.remove(model);
				mLinearLayout.removeView(addedItemView);
				if (onAddedChangeListener != null) {
					onAddedChangeListener.onChange();
				}
			}
		});
		mLinearLayout.addView(addedItemView, mLinearLayout.getChildCount() - 1);
	}

	public List<CreateOrderProductItemModel> getProductItemModels() {
		List<CreateOrderProductItemModel> newList=new ArrayList<CreateOrderProductItemModel>();
		if(mProductItemModels!=null)
		for(CreateOrderProductItemModel m:mProductItemModels){
			if(!m.isChange()){
				newList.add(m);
			}
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
	OnAddedChangeListener onAddedChangeListener;

	public void setOnAddedChangeListener(OnAddedChangeListener onAddedChangeListener) {
		this.onAddedChangeListener = onAddedChangeListener;
	}

	public interface OnAddedChangeListener {
		public void onChange();
	}

	public void setCtgItems(HrGetOrderDetailResult result) {
		for (ProjectItemModel mProjectItemModel : result.getProjectItems()) {
			if ("增值项目".equals(mProjectItemModel.getName())) {
				this.mProjectItemModel = mProjectItemModel;
				for (OrderCtgItemModel mOrderCtgItemModel : mProjectItemModel.getCtgItems()) {
					getGoodsList(mOrderCtgItemModel);
				}
				break;
			}
		}

	}

	private void getGoodsList(final OrderCtgItemModel mOrderCtgItemModel) {
		HpGetGoodsListParams params = new HpGetGoodsListParams();
		params.setCtgId(mOrderCtgItemModel.getId());
		ProductManagerImpl.getInstance().getGoodsList(getContext(), params,
				new HttpResponseHandler<HrGetGoodsListResult>() {

					@Override
					public void onSuccess(HrGetGoodsListResult result) {
						if (result != null && result.getProductItems() != null && result.getProductItems().size() > 0) {
							for (OrderProductItemModel mOrderProductItemModel : mOrderCtgItemModel.getProductItems()) {
								addAddedCtgView(result.getProductItems(), mOrderCtgItemModel, mOrderProductItemModel);
							}
						}
					}

					@Override
					public void onStart() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});

	}

	protected void addAddedCtgView(List<GoodsModel> productItems, OrderCtgItemModel mOrderCtgItemModel,
			OrderProductItemModel mOrderProductItemModel) {
		final CreateOrderProductItemModel model = new CreateOrderProductItemModel();
		model.setProjectId(4);
		model.setCategoryId(mOrderCtgItemModel.getId());
		model.setNumber(mOrderProductItemModel.getNumber());
		model.setPrice(mOrderProductItemModel.getPrice());
		model.setSkuId(mOrderProductItemModel.getSkuId());
		model.setTotalPrice(mOrderProductItemModel.getTotalPrice());
		model.setId(mOrderProductItemModel.getId());
		model.setStatusFlag(1);
		model.setChange(true);
		mProductItemModels.add(model);
		final AddedItemView addedItemView = new AddedItemView(getContext(), productItems, mOrderCtgItemModel, model);
		addedItemView.setEnableEdit(mOrderProductItemModel.isCanEdit());
		addedItemView.setOnChangeListener(new OnChangeListener() {

			@Override
			public void onChange(boolean isFirst) {
				if (!isFirst) {
					model.setChange(false);
				}
				if (onAddedChangeListener != null) {
					onAddedChangeListener.onChange();
				}
			}
		});
		addedItemView.setOnDeleteListener(new OnDeleteListener() {

			@Override
			public void onDelete(CreateOrderProductItemModel model) {
				// mProductItemModels.remove(model);
				model.setChange(false);
				model.setStatusFlag(2);
				mLinearLayout.removeView(addedItemView);
				if (onAddedChangeListener != null) {
					onAddedChangeListener.onChange();
				}
			}
		});
		mLinearLayout.addView(addedItemView, mLinearLayout.getChildCount() - 1);

	}

}
