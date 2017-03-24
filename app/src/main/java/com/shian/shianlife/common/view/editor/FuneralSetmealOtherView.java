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
    int funeralID;

    public FuneralSetmealOtherView(Context context) {
        this(context, null);
    }

    public FuneralSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_funeralsetmealother, this);
        initView();
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
        mTVTitleName.setText(title);
        ArrayAdapter<SetmealModel> province_adapter = new ArrayAdapter<SetmealModel>(getContext(),
                R.layout.textview_spinner_2, funeralSetmeals);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPSetMealTitleName.setAdapter(province_adapter);
        mSPSetMealTitleName.setOnItemSelectedListener(onItemSelectedListener);
//        for (int i = 0; i < mSetmealModels.size(); i++) {
//            SetmealModel setmealModel = mSetmealModels.get(i);
//            if (setmealModel.getId() == result.getBoard().getSetmealFuneralId()) {
//                sp.setSelection(i);
//                break;
//            }
//        }
//        for (ProjectItemModel projectItemModel : result.getProjectItems()) {
//            if ("殡仪馆".equals(projectItemModel.getName())) {
//                this.projectItemModel = projectItemModel;
//                break;
//            }
//        }
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
            for (CtgItemModel ctgItem : setmealModel.getCtgItems()) {
                SetMealItemsLayout setMealItemsLayout = new SetMealItemsLayout(getContext(),ctgItem);
                mLLFueralSetMeal.addView(setMealItemsLayout);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


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
}
