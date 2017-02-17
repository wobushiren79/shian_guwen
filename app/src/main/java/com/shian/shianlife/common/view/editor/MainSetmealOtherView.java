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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.model.SetmealModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.view.ScrollListView;
import com.shian.shianlife.view.dialog.EditOrderSetmealChangeDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 */

public class MainSetmealOtherView extends LinearLayout {
    View layout;
    TextView mTVTitle;
    TextView mTVSetmealName;

    Button mBTChangeSetMeal;

    ScrollListView mListViewData;

    private List<SetmealModel> mainSetmeals = new ArrayList<>();// 系统主套餐
    private List<CtgItemModel> ctgItems = new ArrayList<>();
    private List<CtgItemModel> detailsCtgItems = new ArrayList<>();
    private List<String> listTitle = new ArrayList<>();

    private HrGetOrderDetailResult result;

    private String titleName;
    private String SetmealName;
    private int SetmealPosition = 0;
    private int mainID;

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
            mainID = mainSetmeals.get(0).getId();
            mTVSetmealName.setText(mainSetmeals.get(0).getName());
            SetmealName = mainSetmeals.get(0).getName();
            ctgItems.addAll(mainSetmeals.get(0).getCtgItems());
            setInitDetailsCtgItems();
        }
        theAdapter.notifyDataSetChanged();


    }

    public void setInitData(String name, List<SetmealModel> mainSetmeals, HrGetOrderDetailResult result) {
        this.result = result;
        setInitData(name, mainSetmeals);
        for (int i = 0; i < mainSetmeals.size(); i++) {
            SetmealModel setmealData = mainSetmeals.get(i);
            if (result.getBoard().getSetmealMainId() == setmealData.getId()) {
                setSelectSetmeal(mainSetmeals.get(i).getName(), i);
                break;
            }
        }


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
            return detailsCtgItems.size();
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
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, MainSetmealOtherView.this.getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_48dp));
            theViewHolder.tvTitle.setLayoutParams(layoutparams);
            final CtgItemModel data = detailsCtgItems.get(position);
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
                        itemViewHolder = new ItemViewHolder();
                        itemViewHolder.ivDetail = (ImageView) convertView.findViewById(R.id.iv_detail);
                        itemViewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                        itemViewHolder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
                        itemViewHolder.tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
                        convertView.setTag(itemViewHolder);
                    } else {
                        itemViewHolder = (ItemViewHolder) convertView.getTag();
                    }
                    ProductItemModel dataItem = data.getProductItems().get(position);
                    itemViewHolder.tvName.setText(dataItem.getName() + "(" + dataItem.getSpecification() + ")");
                    itemViewHolder.tvMoney.setText("￥：" + dataItem.getPrice() * dataItem.getCount());
                    itemViewHolder.tvNum.setText("x " + dataItem.getCount() + dataItem.getUnit());
                    return convertView;
                }

                class ItemViewHolder {
                    TextView tvName;
                    TextView tvNum;
                    TextView tvMoney;
                    ImageView ivDetail;
                }
            });
            theViewHolder.itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.getSKUDetails(getContext(), data.getProductItems().get(position).getId());
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
        mainID = mainSetmeals.get(position).getId();
        ctgItems.addAll(mainSetmeals.get(position).getCtgItems());
        setInitDetailsCtgItems();
        mTVSetmealName.setText(name);
        SetmealName = name;
        theAdapter.notifyDataSetChanged();
    }

    private void setInitDetailsCtgItems() {
        detailsCtgItems.clear();
        for (CtgItemModel CtgData : ctgItems) {
            List<ProductItemModel> listTempProduct = new ArrayList<>();
            for (ProductItemModel ProductData : CtgData.getProductItems()) {
                if (ProductData.getCount() != 0) {
                    listTempProduct.add(ProductData);
                }
            }
            if (listTempProduct.size() != 0) {
                CtgItemModel tempCtgData = new CtgItemModel();
                tempCtgData.setId(CtgData.getId());
                tempCtgData.setName(CtgData.getName());
                tempCtgData.setProductItems(listTempProduct);
                detailsCtgItems.add(tempCtgData);
            }
        }
    }

    public List<CreateOrderProductItemModel> getProductItemModels() {
        List<CreateOrderProductItemModel> newList = new ArrayList<CreateOrderProductItemModel>();
        if (result == null) {
            createOrderProduct(newList);
            return newList;
        } else {
               if(mainID!=result.getBoard().getSetmealMainId()){
                   addCreateOrderProduct(newList,2);
                   createOrderProduct(newList);
               }else{
                   addCreateOrderProduct(newList,1);
               }


            return newList;
        }
    }

    private void createOrderProduct(List<CreateOrderProductItemModel> newList) {
        for (CtgItemModel CtgData : detailsCtgItems) {
            for (ProductItemModel ProductData : CtgData.getProductItems()) {
                CreateOrderProductItemModel item = new CreateOrderProductItemModel();
                item.setCategoryId(CtgData.getId());
                item.setProjectId(1);
                item.setNumber(ProductData.getCount());
                item.setPrice(ProductData.getPrice());
                item.setSkuId(ProductData.getId());
                item.setTotalPrice(ProductData.getPrice() * ProductData.getCount());
                item.setStatusFlag(1);
                item.setChange(false);
                newList.add(item);
            }
        }
    }

    /**
     *
     * @param newList
     * @param isDelete  1为不删除 2为删除
     */
    private void addCreateOrderProduct(List<CreateOrderProductItemModel> newList,int isDelete) {
        for (ProjectItemModel projectItemData : result.getProjectItems()) {
            if (projectItemData.getId() == 1) {
                for (OrderCtgItemModel orderCtgItemData : projectItemData.getCtgItems()) {
                    for (OrderProductItemModel orderProductItemData : orderCtgItemData.getProductItems()) {
                        CreateOrderProductItemModel item = new CreateOrderProductItemModel();
                        item.setId(orderProductItemData.getId());
                        item.setCategoryId(orderCtgItemData.getId());
                        item.setProjectId(1);
                        item.setNumber(orderProductItemData.getNumber());
                        item.setPrice(orderProductItemData.getPrice());
                        item.setSkuId(orderProductItemData.getSkuId());
                        item.setTotalPrice(orderProductItemData.getTotalPrice());
                        item.setStatusFlag(isDelete);
                        item.setChange(false);
                        newList.add(item);
                    }
                }
                break;
            }
        }
    }

    public int getMainID() {
        return mainID;
    }

}



