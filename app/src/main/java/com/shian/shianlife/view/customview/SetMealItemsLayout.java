package com.shian.shianlife.view.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.AddedCtgModel;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.view.ScrollListView;
import com.shian.shianlife.view.dialog.AddedSetMealSelectDialog;
import com.shian.shianlife.view.dialog.FuneralSetMealSelectDialog;

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


    public SetMealItemsLayout(Context context, CtgItemModel ctgItem) {
        super(context);
        this.ctgItem = ctgItem;
        view = View.inflate(context, R.layout.view_setmeal_items_layout, this);
        initView();
        initData();
    }

    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mIVAdd = (ImageView) view.findViewById(R.id.iv_add);
        mListView = (ScrollListView) view.findViewById(R.id.listview);
    }

    private void initData() {
        mTVTitle.setText(ctgItem.getName());

        mIVAdd.setOnClickListener(onClickListener);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVAdd) {
                FuneralSetMealSelectDialog dialog = new FuneralSetMealSelectDialog(getContext(), ctgItem.getProductItems());
                dialog.setCallback(new FuneralSetMealSelectDialog.CallBack() {
                    @Override
                    public void submit(List<ProductItemModel> listData) {
                        for (ProductItemModel data : listData) {

                        }
                    }
                });
                dialog.show();
            }
        }
    };

}
