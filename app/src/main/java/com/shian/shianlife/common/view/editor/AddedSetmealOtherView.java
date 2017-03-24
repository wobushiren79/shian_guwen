package com.shian.shianlife.common.view.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.SetMealProductAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
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
import com.shian.shianlife.view.ScrollListView;
import com.shian.shianlife.view.dialog.AddedSetMealSelectDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class AddedSetmealOtherView extends LinearLayout {
    View view;

    TextView mTVTitleName;//标题名字
    TextView mTVSetMealName;
    Button mBTAdd;//添加

    ScrollListView mListView;

    SetMealProductAdapter adapter;
    /**
     * 所有的增值服务产品列表
     */
    private List<AddedCtgModel> mAddedCtgModels = null;
    /**
     * 新建的订单列表
     */
    private List<CreateOrderProductItemModel> mProductItemModels;
    private ProjectItemModel mProjectItemModel;

    OnAddedChangeListener onAddedChangeListener;

    public AddedSetmealOtherView(Context context) {
        this(context, null);
    }

    public AddedSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_addedsetmealother, this);
        initData();
        initView();
    }

    public void setOnAddedChangeListener(OnAddedChangeListener onAddedChangeListener) {
        this.onAddedChangeListener = onAddedChangeListener;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mProductItemModels = new ArrayList<>();
        adapter = new SetMealProductAdapter(getContext(), mProductItemModels);
        adapter.setCallBack(callBack);
    }

    /**
     * 增删改操作
     */
    SetMealProductAdapter.AdapterCallBack callBack = new SetMealProductAdapter.AdapterCallBack() {
        @Override
        public void dataChange() {
            if (onAddedChangeListener != null) {
                onAddedChangeListener.onChange();
            }
        }
    };

    /**
     * 初始化控件
     */
    private void initView() {
        mTVTitleName = (TextView) findViewById(R.id.tv_title);
        mTVSetMealName = (TextView) findViewById(R.id.tv_setmealFuneralName);
        mBTAdd = (Button) findViewById(R.id.bt_add);
        mListView = (ScrollListView) findViewById(R.id.listview);

        mBTAdd.setOnClickListener(onClickListener);
        mListView.setAdapter(adapter);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTAdd) {
                addAddedCtg();
            }
        }
    };

    /**
     * 判断是否加载过产品列表
     */
    private void addAddedCtg() {
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
        AddedSetMealSelectDialog dialog = new AddedSetMealSelectDialog(getContext(), mAddedCtgModels);
        dialog.setCallback(new AddedSetMealSelectDialog.CallBack() {
            @Override
            public void submit(List<AddedCtgModel> listData) {
                for (AddedCtgModel data : listData) {
                    getGoodsList(data);
                }
            }
        });
        dialog.show();
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
        GoodsModel goodsModel = productItems.get(0);
        model.setCategoryId(addedCtgModel.getId());
        model.setName(goodsModel.getName());
        model.setSkuId(goodsModel.getId());
        model.setPrice(goodsModel.getPrice());
        model.setNumber(1);
        model.setTotalPrice(model.getNumber() * model.getPrice());
        model.setProjectId(4);
        model.setStatusFlag(1);
        model.setChange(false);
        mProductItemModels.add(model);
        adapter.notifyDataSetChanged();
        onAddedChangeListener.onChange();
    }

    public List<CreateOrderProductItemModel> getProductItemModels() {
        List<CreateOrderProductItemModel> newList = new ArrayList<CreateOrderProductItemModel>();
        if (mProductItemModels != null)
            for (CreateOrderProductItemModel m : mProductItemModels) {
                if (!m.isChange()) {
                    newList.add(m);
                }
            }
        return newList;
    }

    public List<CreateOrderProductItemModel> getProductItemModelsT() {
        List<CreateOrderProductItemModel> newList = new ArrayList<CreateOrderProductItemModel>();
        if (mProductItemModels != null)
            for (CreateOrderProductItemModel m : mProductItemModels) {
                if (m.getStatusFlag() != 2) {
                    newList.add(m);
                }
            }
        return newList;
    }

    /**
     * 第二次进入设置参数
     *
     * @param result
     */
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
        model.setName(mOrderCtgItemModel.getProductItems().get(0).getName());
        model.setCategoryId(mOrderCtgItemModel.getId());
        model.setNumber(mOrderProductItemModel.getNumber());
        model.setPrice(mOrderProductItemModel.getPrice());
        model.setSkuId(mOrderProductItemModel.getSkuId());
        model.setTotalPrice(mOrderProductItemModel.getTotalPrice());
        model.setId(mOrderProductItemModel.getId());
        model.setStatusFlag(1);
        model.setChange(false);
        mProductItemModels.add(model);
        adapter.notifyDataSetChanged();
        onAddedChangeListener.onChange();
    }

    public interface OnAddedChangeListener {
        void onChange();
    }
}
