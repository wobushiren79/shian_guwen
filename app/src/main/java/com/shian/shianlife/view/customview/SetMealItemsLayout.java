package com.shian.shianlife.view.customview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.SetMealProductAdapter;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.view.ScrollListView;
import com.shian.shianlife.view.dialog.FuneralSetMealSelectDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class SetMealItemsLayout extends LinearLayout {
    View view;

    TextView mTVTitle;
    ImageView mIVAdd;
    ScrollListView mListView;
    CtgItemModel ctgItem;

    SetMealProductAdapter adapter;
    List<CreateOrderProductItemModel> productItemModels;
    List<CreateOrderProductItemModel> oldProductItemModels = new ArrayList<>();
    List<OrderProductItemModel> productItems;//已有的商品
    CallBack callBack;

    public SetMealItemsLayout(Context context, CtgItemModel ctgItem) {
        super(context);
        this.ctgItem = ctgItem;
        view = View.inflate(context, R.layout.view_setmeal_items_layout, this);
        initView();
        initData();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mIVAdd = (ImageView) view.findViewById(R.id.iv_add);
        mListView = (ScrollListView) view.findViewById(R.id.listview);
    }

    private void initData() {
        mTVTitle.setText(ctgItem.getName());
        mIVAdd.setOnClickListener(onClickListener);
        productItemModels = new ArrayList<>();
        adapter = new SetMealProductAdapter(getContext(), productItemModels);
        adapter.setCallBack(new SetMealProductAdapter.AdapterCallBack() {
            @Override
            public void dataChange() {

            }

            @Override
            public void dataDelete(CreateOrderProductItemModel data) {
                callBack.dataDelete(data);
            }


        });
        mListView.setAdapter(adapter);
        adapter.isEdit(false);
    }

    /**
     * 有初始数据
     */
    public void setData(CtgItemModel showCtgItem, List<OrderProductItemModel> productItems) {
        this.productItems = productItems;
        for (OrderProductItemModel data : productItems) {
            CreateOrderProductItemModel item = new CreateOrderProductItemModel();
            item.setName(data.getName());
            item.setNumber(data.getNumber());
            item.setStatusFlag(1);
            item.setTotalPrice(data.getNumber() * data.getPrice());
            item.setCategoryId(showCtgItem.getId());
            item.setChange(false);
            item.setProjectId(2);
            item.setId(data.getId());
            item.setSkuId(data.getSkuId());
            productItemModels.add(item);
            adapter.notifyDataSetChanged();
        }
        oldProductItemModels.clear();
        oldProductItemModels.addAll(productItemModels);
        if (callBack != null)
            callBack.dataChange(productItemModels, oldProductItemModels);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVAdd) {
                FuneralSetMealSelectDialog dialog = new FuneralSetMealSelectDialog(getContext(), ctgItem.getProductItems());
                dialog.setCallback(new FuneralSetMealSelectDialog.CallBack() {
                    @Override
                    public void submit(List<ProductItemModel> listData) {
                        oldProductItemModels.clear();
                        oldProductItemModels.addAll(productItemModels);
                        for (ProductItemModel data : listData) {
                            CreateOrderProductItemModel item = new CreateOrderProductItemModel();


                            item.setSkuId(data.getId());
                            item.setCategoryId(data.getCategoryId());
                            item.setPrice(data.getPrice());
                            item.setNumber(data.getCount());
                            item.setTotalPrice(data.getCount() * data.getPrice());
                            item.setName(data.getName());
//                            item.setStatusFlag(1);
                            item.setChange(false);
                            item.setProjectId(2);


                            productItemModels.add(item);
                            adapter.notifyDataSetChanged();
                        }
                        if (callBack != null)
                            callBack.dataChange(productItemModels, oldProductItemModels);
                    }
                });
                dialog.show();
            }
        }
    };

    public interface CallBack {
        void dataChange(List<CreateOrderProductItemModel> newProductItemModels, List<CreateOrderProductItemModel> oldProductItemModels);
        void dataDelete(CreateOrderProductItemModel data);
    }
}
