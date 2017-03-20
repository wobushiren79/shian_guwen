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

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.daimajia.swipe.util.Attributes;
import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
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
    private List<CreateOrderProductItemModel>  deleteProudctItems=new ArrayList<>();

    private HrGetOrderDetailResult result;

    private String titleName;
    private String SetmealName;
    private int SetmealPosition = 0;
    private int mainID;


    SetmealOtherViewChangeListener changeListener;

    public MainSetmealOtherView(Context context) {
        this(context, null);
    }

    public MainSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setChangeListener(SetmealOtherViewChangeListener changeListener) {
        Log.v("this","setChangeListener");
        this.changeListener = changeListener;
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
        changeListener.changeTotalPrice();

    }

    public void setInitData(String name, List<SetmealModel> mainSetmeals, HrGetOrderDetailResult result) {
        this.result = result;
        setInitData(name, mainSetmeals);
        //设置选择的套餐

        for (int i = 0; i < mainSetmeals.size(); i++) {
            SetmealModel setmealData = mainSetmeals.get(i);
            if (result.getBoard().getSetmealMainId() == setmealData.getId()) {
                setSelectSetmeal(mainSetmeals.get(i).getName(), i);
                break;
            }
        }
        setListData(result);

    }

    private void setListData(HrGetOrderDetailResult result) {
        //设置详细的套餐数据
        detailsCtgItems.clear();
        for (ProjectItemModel prjectItem : result.getProjectItems()) {
            if (prjectItem.getId() == 1) {
                for (OrderCtgItemModel orderCtgItemModel : prjectItem.getCtgItems()) {
                    CtgItemModel ctgItemModel = new CtgItemModel();
                    ctgItemModel.setName(orderCtgItemModel.getName());
                    ctgItemModel.setId(orderCtgItemModel.getId());
                    List<ProductItemModel> productItems = new ArrayList<>();
                    for (OrderProductItemModel orderProductItemModel : orderCtgItemModel.getProductItems()) {
                        ProductItemModel productItemModel=new ProductItemModel();
                        productItemModel.setOrderId(orderProductItemModel.getId());
                        productItemModel.setName(orderProductItemModel.getName());
                        productItemModel.setCategoryId(orderProductItemModel.getCategoryId());
                        productItemModel.setSpecification(orderProductItemModel.getSpecification());
                        productItemModel.setUnit(orderProductItemModel.getUnit());
                        productItemModel.setId(orderProductItemModel.getSkuId());
                        productItemModel.setCount(orderProductItemModel.getNumber());
                        productItemModel.setCanEdit(orderProductItemModel.isCanEdit());
                        productItemModel.setPrice(orderProductItemModel.getPrice());
                        productItemModel.setTotalPrice(orderProductItemModel.getPrice()*orderProductItemModel.getNumber());
                        productItems.add(productItemModel);

                        //如果不能编辑 那么不能更改套餐
                        if(!orderProductItemModel.isCanEdit()){
                            mBTChangeSetMeal.setVisibility(GONE);
                        }
                    }
                    ctgItemModel.setProductItems(productItems);
                    detailsCtgItems.add(ctgItemModel);
                }
                theAdapter.notifyDataSetChanged();
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
        public View getView(final int positionfather, View convertView, ViewGroup parent) {
            TheViewHolder theViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_mainsetmealother_listview_items, null);
                theViewHolder = new TheViewHolder();
                theViewHolder.itemListView = (ScrollListView) convertView.findViewById(R.id.listview_data);
                theViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(theViewHolder);
            } else {
                theViewHolder = (TheViewHolder) convertView.getTag();
            }
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, MainSetmealOtherView.this.getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_80dp));
            theViewHolder.tvTitle.setLayoutParams(layoutparams);
            final CtgItemModel data = detailsCtgItems.get(positionfather);
            theViewHolder.tvTitle.setText(data.getName());
            BaseSwipeAdapter baseSwipeAdapter = new BaseSwipeAdapter() {
                @Override
                public int getSwipeLayoutResourceId(int position) {
                    return R.id.swipelayout;
                }

                @Override
                public View generateView(int position, ViewGroup parent) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.view_mainsetmealother_listview_items_items, null);
                    return view;
                }

                @Override
                public void fillValues(final int positionchilden, View convertView) {

                    TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
                    TextView tvNum = (TextView) convertView.findViewById(R.id.tv_num);
                    TextView tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
                    final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipelayout);

                    final ProductItemModel dataItem = data.getProductItems().get(positionchilden);
                    tvName.setText(dataItem.getName() + "(" + dataItem.getSpecification() + ")");
                    tvMoney.setText("￥：" + dataItem.getPrice() * dataItem.getCount());
                    tvNum.setText("x " + dataItem.getCount() + dataItem.getUnit());

                    swipeLayout.addDrag(SwipeLayout.DragEdge.Right, R.id.ll_bottom);
                    swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
                    TextView tvDelete = (TextView) convertView.findViewById(R.id.tv_delete);
                    TextView tvDetails = (TextView) convertView.findViewById(R.id.tv_detail);

                    tvDelete.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!dataItem.isCanEdit()){
                                ToastUtils.show(getContext(),"该项已经被接单，不能删除");
                                return;
                            }
                            CreateOrderProductItemModel item=new CreateOrderProductItemModel();
                            item.setCategoryId(data.getId());
                            item.setProjectId(1);
                            item.setNumber(dataItem.getCount());
                            item.setPrice(dataItem.getPrice());
                            item.setSkuId(dataItem.getId());
                            item.setTotalPrice(dataItem.getPrice() * dataItem.getCount());
                            item.setStatusFlag(2);
                            item.setChange(false);
                            if(dataItem.getOrderId()!=0){
                                item.setId(dataItem.getOrderId());
                            }
                            deleteProudctItems.add(item);
                            detailsCtgItems.get(positionfather).getProductItems().remove(positionchilden);
                            theAdapter.notifyDataSetChanged();
                            changeListener.changeTotalPrice();
                        }
                    });
                    tvDetails.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Utils.getSKUDetails(getContext(), data.getProductItems().get(positionchilden).getId());
                        }
                    });

                    swipeLayout.getSurfaceView().setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            SwipeLayout.Status status = swipeLayout.getOpenStatus();
                            if (status == SwipeLayout.Status.Close) {
                                swipeLayout.open();
                            } else if (status == SwipeLayout.Status.Open) {
                                swipeLayout.close();
                            } else {

                            }
                        }
                    });
                }

                @Override
                public int getCount() {
                    return data.getProductItems().size();
                }

                @Override
                public Object getItem(int i) {
                    return null;
                }

                @Override
                public long getItemId(int i) {
                    return i;
                }
            };

            baseSwipeAdapter.setMode(Attributes.Mode.Single);
            theViewHolder.itemListView.setAdapter(baseSwipeAdapter);
//            theViewHolder.itemListView.setAdapter(new BaseAdapter() {
//                @Override
//                public int getCount() {
//                    return data.getProductItems().size();
//                }
//
//                @Override
//                public Object getItem(int position) {
//                    return null;
//                }
//
//                @Override
//                public long getItemId(int position) {
//                    return 0;
//                }
//
//                @Override
//                public View getView(int position, View convertView, ViewGroup parent) {
//                    ItemViewHolder itemViewHolder;
//                    if (convertView == null) {
//                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_mainsetmealother_listview_items_items, null);
//                        itemViewHolder = new ItemViewHolder();
//                        itemViewHolder.ivDetail = (ImageView) convertView.findViewById(R.id.iv_detail);
//                        itemViewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
//                        itemViewHolder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
//                        itemViewHolder.tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
//                        convertView.setTag(itemViewHolder);
//                    } else {
//                        itemViewHolder = (ItemViewHolder) convertView.getTag();
//                    }
//                    ProductItemModel dataItem = data.getProductItems().get(position);
//                    itemViewHolder.tvName.setText(dataItem.getName() + "(" + dataItem.getSpecification() + ")");
//                    itemViewHolder.tvMoney.setText("￥：" + dataItem.getPrice() * dataItem.getCount());
//                    itemViewHolder.tvNum.setText("x " + dataItem.getCount() + dataItem.getUnit());
//                    return convertView;
//                }
//
//                class ItemViewHolder {
//                    TextView tvName;
//                    TextView tvNum;
//                    TextView tvMoney;
//                    ImageView ivDetail;
//                }
//            });

            return convertView;
        }

        class TheViewHolder {
            TextView tvTitle;
            ScrollListView itemListView;
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
        if(result!=null&&mainID==result.getBoard().getSetmealMainId() ){
            setListData(result);
        }
        changeListener.changeTotalPrice();
    }

    private void setInitDetailsCtgItems() {
        detailsCtgItems.clear();
        deleteProudctItems.clear();
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
            if (mainID != result.getBoard().getSetmealMainId()) {
                addCreateOrderProduct(newList, 2);
                createOrderProduct(newList);
            } else {
                newList.addAll(deleteProudctItems);
                createOrderProduct(newList);
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
                if(ProductData.getOrderId()!=0){
                    item.setId(ProductData.getOrderId());
                }
                newList.add(item);
            }
        }
    }

    /**
     * @param newList
     * @param isDelete 1为不删除 2为删除
     */
    private void addCreateOrderProduct(List<CreateOrderProductItemModel> newList, int isDelete) {
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

    public interface SetmealOtherViewChangeListener {
        void changeTotalPrice();
    }

}



