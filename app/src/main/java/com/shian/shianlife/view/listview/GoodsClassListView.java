package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.shian.shianlife.adapter.GoodsClassAdapter;
import com.shian.shianlife.common.contanst.AppContansts;
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
    private CallBack callBack;
    private Long goodsClassId;

    public GoodsClassListView(Context context) {
        this(context, null);
    }

    public GoodsClassListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        goodsClassId = -1L;
        classAdapter = new GoodsClassAdapter(context);
        classAdapter.setCallBack(this);

        goodsClassPresenter = new GoodsClassPresenterImpl(this);
        this.setAdapter(classAdapter);
        this.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setGoodsClassId(Long goodsClassId) {
        this.goodsClassId = goodsClassId;
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
        boolean isHasData = false;
        for (int i = 0; i < listData.size(); i++) {
            GoodsClassResultBean itemData = listData.get(i);
            if (itemData.getId() != null && goodsClassId == itemData.getId()) {
                classAdapter.setSelectItem(i);
                isHasData = true;
            }
        }
        if (!isHasData)
            classAdapter.setSelectItem(0);
    }

    @Override
    public void getGoodsClassDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public Integer getChannelId() {
        return AppContansts.goodsChannelId;
    }

    public void getData() {
        goodsClassPresenter.getGoodsClassData();
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
