package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.common.utils.StringUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsChannelPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsChannelPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsChannelView;
import com.shian.shianlife.view.listview.GoodsClassAttrListView;
import com.shian.shianlife.view.listview.GoodsClassListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsClassAttrDetailsActivity extends BaseActivity implements  GoodsClassListView.CallBack {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.goods_class_list)
    GoodsClassListView goodsClassList;
    @InjectView(R.id.goods_class_attr_list)
    GoodsClassAttrListView goodsClassAttrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_class_attr_details);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("分类");
        goodsClassList.setCallBack(this);
    }

    private void initData() {
        goodsClassList.getData();
    }

    private void setClassTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void selectItem(View view, int index, GoodsClassResultBean data) {
        if (view == goodsClassList) {
            if (!CheckUtils.isEmpty(data.getName()))
                setClassTitle(data.getName());

            if (!CheckUtils.isEmpty(data.getId()))
                getClassAttrData(data.getId());
        }
    }

    private void getClassAttrData(Long classID) {
        goodsClassAttrList.setClassId(classID);
        goodsClassAttrList.getData();
    }
}
