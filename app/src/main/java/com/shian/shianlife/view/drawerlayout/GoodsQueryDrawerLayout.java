package com.shian.shianlife.view.drawerlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsFiltrateAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsChannelPresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsChannelPresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsClassPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsChannelView;
import com.shian.shianlife.mvp.goods.view.IGoodsClassView;
import com.shian.shianlife.view.listview.GoodsQueryDrawerExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsQueryDrawerLayout extends LinearLayout implements IGoodsClassView, IGoodsChannelView, GoodsQueryDrawerExpandableListView.CallBack {
    View mLayoutView;
    private GoodsQueryDrawerExpandableListView expandableListview;

    private IGoodsClassPresenter goodsClassPresenter;
    private IGoodsChannelPresenter goodsChannelPresenter;

    private Integer channelId;
    private CallBack callBack;

    public GoodsQueryDrawerLayout(Context context) {
        this(context, null);
    }

    public GoodsQueryDrawerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mLayoutView = View.inflate(getContext(), R.layout.layout_goods_query_drawer, this);
        initView();
        initData();
    }

    private void initView() {
        expandableListview = (GoodsQueryDrawerExpandableListView) findViewById(R.id.expandable_listview);
        expandableListview.setCallBack(this);
    }

    private void initData() {
        goodsClassPresenter = new GoodsClassPresenterImpl(this);
        goodsChannelPresenter = new GoodsChannelPresenterImpl(this);
        goodsChannelPresenter.getGoodsChannelData();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsClassDataSuccess(List<GoodsClassResultBean> listData) {
        Map<GoodsClassResultBean, List<GoodsClassAttrResultBean>> data = new HashMap<>();
        for (GoodsClassResultBean item : listData) {
            data.put(item, new ArrayList<GoodsClassAttrResultBean>());
        }
        expandableListview.setData(data);
    }

    @Override
    public void getGoodsClassDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public Integer getChannelId() {
        return channelId;
    }

    @Override
    public void getGoodsChannelDataSuccess(List<GoodsChannelResultBean> listData) {
        goodsClassPresenter.getGoodsClassData();
    }

    @Override
    public void getGoodsChannelDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Override
    public void classAttrChange(Long classId, Long classAttrId) {
        if (callBack != null)
            callBack.changeClassAttr(this, classId, classAttrId);
    }


    public interface CallBack {
        void changeClassAttr(View view, Long classId, Long classAttrId);
    }

}
