package com.shian.shianlife.view.goods;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsLabelDetailsAdapter;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsResultBean;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsLabelDetailsPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsLabelDetailsView;
import com.shian.shianlife.view.ScrollRecyclerView;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsLabelDetailsLayout extends BaseLayout implements IGoodsLabelDetailsView {
    @InjectView(R.id.recyclerview)
    ScrollRecyclerView recyclerview;

    private Long labelId;
    private CallBack callBack;

    private GoodsLabelDetailsPresenter goodsLabelDetailsPresenter;
    private GoodsLabelDetailsAdapter detailsAdapter;

    public GoodsLabelDetailsLayout(Context context) {
        this(context, null);
    }

    public GoodsLabelDetailsLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_label_details, attrs);

    }

    @Override
    protected void initView() {

    }

    /**
     * 开始查询数据
     */
    public void startFindData() {
        goodsLabelDetailsPresenter.getGoodsLabelDetails();
    }

    @Override
    protected void initData() {
        detailsAdapter = new GoodsLabelDetailsAdapter(getContext());
        goodsLabelDetailsPresenter = new GoodsLabelDetailsPresenter(this);

        recyclerview.setAdapter(detailsAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsLabelDetailsSuccess(List<GoodsLabelDetailsResultBean> resultBean) {
        detailsAdapter.setData(resultBean);
        if (callBack != null)
            callBack.findDataSuccess(this);
    }

    @Override
    public void getGoodsLabelDetailsFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public Long getLabelId() {
        return labelId;
    }


    /**
     * 获取所有子控件高度
     *
     * @return
     */
    public Integer getAllItemHeight() {
        int dp208 = getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_208dp);
        if (detailsAdapter.getData() == null)
            return 0;
        else
            return dp208 * detailsAdapter.getData().size();
    }

    public interface CallBack {
        void findDataSuccess(View view);
    }
}
