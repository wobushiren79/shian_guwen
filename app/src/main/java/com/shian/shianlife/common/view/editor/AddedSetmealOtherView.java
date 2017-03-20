package com.shian.shianlife.common.view.editor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.ProductManagerImpl;
import com.shian.shianlife.provide.model.AddedCtgModel;
import com.shian.shianlife.provide.params.HpGetAddedCtgListParams;
import com.shian.shianlife.provide.params.HpGetGoodsListParams;
import com.shian.shianlife.provide.result.HrGetAddedCtgListResult;
import com.shian.shianlife.provide.result.HrGetGoodsListResult;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class AddedSetmealOtherView extends LinearLayout {
    View view;

    TextView mTVTitleName;//标题名字
    TextView mTVSetMealName;
    Button mBTAdd;//添加

    LinearLayout mLLAddSetMeal;


    /**
     * 所有的增值服务产品列表
     */
    private List<AddedCtgModel> mAddedCtgModels = null;

    public AddedSetmealOtherView(Context context) {
        this(context, null);
    }

    public AddedSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_addedsetmealother, this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        mTVTitleName = (TextView) findViewById(R.id.tv_title);
        mTVSetMealName = (TextView) findViewById(R.id.tv_setmealFuneralName);
        mBTAdd = (Button) findViewById(R.id.bt_add);
        mLLAddSetMeal = (LinearLayout) findViewById(R.id.ll_addsetmeal);

        mBTAdd.setOnClickListener(onClickListener);
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
//                            addAddedCtgView(result.getProductItems(), addedCtgModel);
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


}
