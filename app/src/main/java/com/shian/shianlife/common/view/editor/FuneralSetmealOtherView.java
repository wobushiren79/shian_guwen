package com.shian.shianlife.common.view.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.CtgItemModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.model.SetmealItemModel;
import com.shian.shianlife.provide.model.SetmealModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.view.customview.SetMealItemsLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class FuneralSetmealOtherView extends LinearLayout {
    View view;

    TextView mTVTitleName;
    Spinner mSPSetMealTitleName;
    Button mBTChangeSetMeal;
    LinearLayout mLLFueralSetMeal;


    List<SetmealModel> mSetmealModels;
    List<CreateOrderProductItemModel> mProductItemModels;
    List<CreateOrderProductItemModel> tempList = new ArrayList<>();
    private HrGetOrderDetailResult result;
    private ProjectItemModel projectItemModel;
    int funeralID;

    public FuneralSetmealOtherView(Context context) {
        this(context, null);
    }

    public FuneralSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_funeralsetmealother, this);
        initView();
    }

    public List<CreateOrderProductItemModel> getProductItemModels() {
        List<CreateOrderProductItemModel> newList = new ArrayList<CreateOrderProductItemModel>();
        if (mProductItemModels != null)
            for (CreateOrderProductItemModel m : mProductItemModels) {
                if (!m.isChange()) {
                    newList.add(m);
                }
            }
        newList.addAll(tempList);

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

    public int getFuneralID() {
        return funeralID;
    }

    /**
     * 初始化数据
     *
     * @param title
     * @param funeralSetmeals
     */
    public void setCtgItems(String title, List<SetmealModel> funeralSetmeals) {
        mSetmealModels = funeralSetmeals;
        mTVTitleName.setText(title);
        ArrayAdapter<SetmealModel> province_adapter = new ArrayAdapter<SetmealModel>(getContext(),
                R.layout.textview_spinner_2, funeralSetmeals);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPSetMealTitleName.setAdapter(province_adapter);
        mSPSetMealTitleName.setOnItemSelectedListener(onItemSelectedListener);
    }


    /**
     * 初始化数据
     *
     * @param title
     * @param funeralSetmeals
     * @param result
     */
    public void setCtgItems(String title, List<SetmealModel> funeralSetmeals, HrGetOrderDetailResult result) {
        mSetmealModels = funeralSetmeals;
        this.result = result;
        mTVTitleName.setText(title);
        ArrayAdapter<SetmealModel> province_adapter = new ArrayAdapter<SetmealModel>(getContext(),
                R.layout.textview_spinner_2, funeralSetmeals);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPSetMealTitleName.setAdapter(province_adapter);
        mSPSetMealTitleName.setOnItemSelectedListener(onItemSelectedListener);

        for (int i = 0; i < mSetmealModels.size(); i++) {
            SetmealModel setmealModel = mSetmealModels.get(i);
            if (setmealModel.getId() == result.getBoard().getSetmealFuneralId()) {
                mSPSetMealTitleName.setSelection(i);
                break;
            }
        }
        for (ProjectItemModel projectItemModel : result.getProjectItems()) {
            if ("殡仪馆".equals(projectItemModel.getName())) {
                this.projectItemModel = projectItemModel;
                break;
            }
        }
    }


    /**
     * 选择
     */
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (mLLFueralSetMeal.getChildCount() > 0) {
                mLLFueralSetMeal.removeAllViews();
            }
            SetmealModel setmealModel = mSetmealModels.get(position);
            mProductItemModels = new ArrayList<>();
            funeralID = setmealModel.getId();
            for (CtgItemModel showCtgItem : setmealModel.getCtgItems()) {
                SetMealItemsLayout setMealItemsLayout = new SetMealItemsLayout(getContext(), showCtgItem);
                setMealItemsLayout.setCallBack(new SetMealItemsLayout.CallBack() {
                    @Override
                    public void dataChange(List<CreateOrderProductItemModel> newProductItemModels, List<CreateOrderProductItemModel> oldProductItemModels) {
                        mProductItemModels.removeAll(oldProductItemModels);
                        mProductItemModels.addAll(newProductItemModels);
                        onFuneralChangeListener.onFuneralChange();
                    }

                    @Override
                    public void dataDelete(CreateOrderProductItemModel data) {
                        mProductItemModels.remove(data);
                        onFuneralChangeListener.onFuneralChange();
                    }


                });
                //是否第一次创建
                if (projectItemModel != null) {
                    List<OrderCtgItemModel> ctgItems = projectItemModel.getCtgItems();
                    List<OrderProductItemModel> productItems = null;
                    for (OrderCtgItemModel model : ctgItems) {
                        if (model.getId() == showCtgItem.getId()) {
                            productItems = model.getProductItems();
                            break;
                        }
                    }
                    if (productItems != null) {
                        tempList = new ArrayList<>();
                        if (mSetmealModels.get(position).getId() == result.getBoard().getSetmealFuneralId()) {
                            addCtgItemView(showCtgItem,productItems, setMealItemsLayout);
                        } else {
                            for (OrderProductItemModel data : productItems) {
                                CreateOrderProductItemModel model = new CreateOrderProductItemModel();
                                model.setProjectId(1);
                                model.setCategoryId(showCtgItem.getId());
                                model.setNumber(data.getNumber());
                                model.setPrice(data.getPrice());
                                model.setSkuId(data.getSkuId());
                                model.setTotalPrice(data.getTotalPrice());
                                model.setId(data.getId());
                                model.setStatusFlag(2);
                                model.setChange(false);
                                tempList.add(model);
                                if (!data.isCanEdit()) {
                                    mSPSetMealTitleName.setSelected(false);
                                    mSPSetMealTitleName.setEnabled(false);
                                    mBTChangeSetMeal.setVisibility(GONE);
                                }
                            }
                            addCtgItemView(setMealItemsLayout);
                        }
                    }
                } else {
                    addCtgItemView(setMealItemsLayout);
                }
                mLLFueralSetMeal.addView(setMealItemsLayout);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void addCtgItemView(CtgItemModel showCtgItem,List<OrderProductItemModel> productItems, SetMealItemsLayout setMealItemsLayout) {
        setMealItemsLayout.setData(showCtgItem,productItems);
    }

    private void addCtgItemView(SetMealItemsLayout setMealItemsLayout) {

    }

    private void initView() {
        mTVTitleName = (TextView) view.findViewById(R.id.tv_title);
        mSPSetMealTitleName = (Spinner) view.findViewById(R.id.tv_setmealFuneralName);
        mBTChangeSetMeal = (Button) view.findViewById(R.id.bt_changesetmeal);
        mLLFueralSetMeal = (LinearLayout) view.findViewById(R.id.ll_funeralsetmeal);

        mBTChangeSetMeal.setOnClickListener(onClickListener);

    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTChangeSetMeal) {
                mSPSetMealTitleName.performClick();
            }
        }
    };

    FuneralSetmealView.OnFuneralChangeListener onFuneralChangeListener;

    public void setOnFuneralChangeListener(FuneralSetmealView.OnFuneralChangeListener onFuneralChangeListener) {
        this.onFuneralChangeListener = onFuneralChangeListener;
    }

    public interface OnFuneralChangeListener {
        public void onFuneralChange();
    }
}
