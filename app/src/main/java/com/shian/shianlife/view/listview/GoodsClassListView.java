package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.shian.shianlife.adapter.GoodsClassAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsClassPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsClassView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassListView extends RecyclerView implements IGoodsClassView, GoodsClassAdapter.CallBack {

    private GoodsClassAdapter classAdapter;
    private IGoodsClassPresenter goodsClassPresenter;
    private Integer channerId;
    private CallBack callBack;


    public GoodsClassListView(Context context) {
        this(context, null);
    }

    public GoodsClassListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        classAdapter = new GoodsClassAdapter(context);
        classAdapter.setCallBack(this);

        goodsClassPresenter = new GoodsClassPresenterImpl(this);
        this.setAdapter(classAdapter);
        this.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setData(List<GoodsClassResultBean> listData) {
        classAdapter.setData(listData);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsClassDataSuccess(List<GoodsClassResultBean> listData) {
        classAdapter.setData(listData);
        classAdapter.setSelectItem(0);
    }

    @Override
    public void getGoodsClassDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public Integer getChannelId() {
        return channerId;
    }

    public void getData() {
        goodsClassPresenter.getGoodsClassData();
    }

    public void setChannerId(Integer channerId) {
        this.channerId = channerId;
    }

    @Override
    public void selectItem(int index, GoodsClassResultBean data) {
        if (callBack != null)
            callBack.selectItem(this, index, data);
    }

    public interface CallBack {
        void selectItem(View view, int index, GoodsClassResultBean data);
    }
}
