package com.shian.shianlife.view.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.shian.shianlife.adapter.AddedSetMealSelectAdapter;
import com.shian.shianlife.adapter.FuneralSetMealSelectAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.provide.model.AddedCtgModel;
import com.shian.shianlife.provide.model.ProductItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class FuneralSetMealSelectDialog extends BaseDialog {
    ListView contentListView;
    FuneralSetMealSelectAdapter adapter;

    private FuneralSetMealSelectDialog.CallBack callback;

    private List<ProductItemModel> productItems;// 商品列表
    private List<ProductItemModel> mSelectProductList = new ArrayList<>();

    public FuneralSetMealSelectDialog(Context context, List<ProductItemModel> productItems) {
        super(context);
        this.productItems = productItems;
        initDialog();
    }

    private void initDialog() {
        initListView();
        initOtherView();
    }

    private void initOtherView() {
        setTitle("选择殡仪馆项目产品");

        mTVCancel.setOnClickListener(onClickListener);
        mTVSubmit.setOnClickListener(onClickListener);
    }


    public void setCallback(FuneralSetMealSelectDialog.CallBack callback) {
        this.callback = callback;
    }

    private void initListView() {
        contentListView = new ListView(getContext());
        contentListView.setDivider(null);
        contentListView.setDividerHeight(0);
        RelativeLayout.LayoutParams layoutParams;
        if (productItems.size() > 6) {
            layoutParams = new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (int) (BaseActivity.metrics.heightPixels * 0.6));
        } else {
            layoutParams = new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        contentListView.setLayoutParams(layoutParams);
        adapter = new FuneralSetMealSelectAdapter(getContext(), productItems);
        contentListView.setAdapter(adapter);
        setContent(contentListView);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVCancel) {
                FuneralSetMealSelectDialog.this.cancel();
            } else if (v == mTVSubmit) {
                saveData();
                FuneralSetMealSelectDialog.this.cancel();
            }
        }
    };

    private void saveData() {
        if (callback != null) {
            for (ProductItemModel data : productItems) {
                if (data.isCheck()) {
                    mSelectProductList.add(data);
                }
            }
            callback.submit(mSelectProductList);
        }
        for (ProductItemModel data : productItems) {
            data.setCheck(false);
        }
    }

    public interface CallBack {
        void submit(List<ProductItemModel> listData);
    }

}
