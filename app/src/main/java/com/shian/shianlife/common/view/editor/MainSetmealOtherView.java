package com.shian.shianlife.common.view.editor;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.provide.model.SetmealModel;
import com.shian.shianlife.view.ScrollListView;
import com.shian.shianlife.view.dialog.EditOrderSetmealChangeDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */

public class MainSetmealOtherView extends LinearLayout {
    View layout;
    TextView mTVTitle;
    TextView mTVSetmealName;

    Button mBTChangeSetMeal;

    ScrollListView mListViewData;

    private List<SetmealModel> mainSetmeals = new ArrayList<>();// 系统主套餐
    private List<CtgItemModel> ctgItems = new ArrayList<>();
    private List<String> listTitle = new ArrayList<>();

    private String titleName;
    private String SetmealName;
    private int SetmealPosition = 0;

    public MainSetmealOtherView(Context context) {
        this(context, null);
    }

    public MainSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setInitData(String name, List<SetmealModel> mainSetmeals) {
        this.mainSetmeals.clear();
        this.listTitle.clear();
        this.titleName = name;
        this.mainSetmeals = mainSetmeals;
        for (SetmealModel data : mainSetmeals) {
            listTitle.add(data.getName());
        }
        mTVTitle.setText(titleName);
        if (mainSetmeals != null && mainSetmeals.size() > 0) {
            mTVSetmealName.setText(mainSetmeals.get(0).getName());
            SetmealName = mainSetmeals.get(0).getName();
            ctgItems.addAll(mainSetmeals.get(0).getCtgItems());
        }
        theAdapter.notifyDataSetChanged();
    }


    private void initView() {
        layout = LayoutInflater.from(getContext()).inflate(R.layout.view_mainsetmealother, this);
        mTVTitle = (TextView) layout.findViewById(R.id.tv_title);
        mListViewData = (ScrollListView) layout.findViewById(R.id.listview_data);
        mTVSetmealName = (TextView) layout.findViewById(R.id.tv_setmeal);

        mBTChangeSetMeal = (Button) layout.findViewById(R.id.bt_changesetmeal);

        mBTChangeSetMeal.setOnClickListener(onClickListener);

        mListViewData.setAdapter(theAdapter);
        mTVTitle.setText(titleName);
    }


    BaseAdapter theAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            Log.v("this", "ctgItems.size():" + ctgItems.size());
            return ctgItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TheViewHolder theViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_mainsetmealother_listview_items, null);
                theViewHolder = new TheViewHolder();
                theViewHolder.itemListView = (ListView) convertView.findViewById(R.id.listview_data);
                theViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(theViewHolder);
            } else {
                theViewHolder = (TheViewHolder) convertView.getTag();
            }
            AbsListView.LayoutParams layoutparams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, MainSetmealOtherView.this.getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_48dp));
            convertView.setLayoutParams(layoutparams);
            final CtgItemModel data = ctgItems.get(position);
            theViewHolder.tvTitle.setText(data.getName());
            theViewHolder.itemListView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return data.getProductItems().size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    ItemViewHolder itemViewHolder;
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_mainsetmealother_listview_items_items, null);
                        itemViewHolder=new ItemViewHolder();
                        itemViewHolder.ivDetail = (ImageView) convertView.findViewById(R.id.iv_detail);
                        itemViewHolder.tvName= (TextView) convertView.findViewById(R.id.tv_name);
                        itemViewHolder.tvNum= (TextView) convertView.findViewById(R.id.tv_num);
                        itemViewHolder.tvMoney= (TextView) convertView.findViewById(R.id.tv_money);
                        convertView.setTag(itemViewHolder);
                    }else{
                        itemViewHolder= (ItemViewHolder) convertView.getTag();
                    }
                    ProductItemModel dataItem= data.getProductItems().get(position);
                    itemViewHolder.tvName.setText(dataItem.getName());
                    itemViewHolder.tvMoney.setText("￥："+dataItem.getPrice()*dataItem.getCount());
                    itemViewHolder.tvNum.setText("x "+dataItem.getCount()+dataItem.getUnit());
                    return convertView;
                }

                class ItemViewHolder {
                    TextView tvName;
                    TextView tvNum;
                    TextView tvMoney;
                    ImageView ivDetail;
                }
            });
            return convertView;
        }

        class TheViewHolder {
            TextView tvTitle;
            ListView itemListView;
        }
    };


    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTChangeSetMeal) {
                creatDialog();
            }
        }
    };

    private void creatDialog() {
        EditOrderSetmealChangeDialog dialog = new EditOrderSetmealChangeDialog(getContext(), R.style.CustomDialogBottom, new EditOrderSetmealChangeDialog.DialogCallBack() {
            @Override
            public void getSelectString(String name, int position) {
                setSelectSetmeal(name, position);
            }
        });
        dialog.setData(listTitle);
        dialog.show();
    }

    private void setSelectSetmeal(String name, int position) {
        ctgItems.clear();
        SetmealPosition = position;
        ctgItems.addAll(mainSetmeals.get(position).getCtgItems());
        mTVSetmealName.setText(name);
        SetmealName = name;
        theAdapter.notifyDataSetChanged();
    }


}
