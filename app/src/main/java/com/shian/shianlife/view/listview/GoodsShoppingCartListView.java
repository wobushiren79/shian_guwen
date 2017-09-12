package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsShoppingCartListAdapter;
import com.shian.shianlife.base.BasePtrExpandableListView;
import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.bean.GoodsShoppingCartListGroupBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zm.
 */

public class GoodsShoppingCartListView extends BasePtrExpandableListView implements GoodsShoppingCartListAdapter.CallBack {
    private GoodsShoppingCartListAdapter listAdapter;

    private List<GoodsDetailsListResultBean> data;

    private CallBack callBack;

    public GoodsShoppingCartListView(Context context) {
        super(context, null);
    }

    public GoodsShoppingCartListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        listAdapter = new GoodsShoppingCartListAdapter(context);
        listAdapter.setCallBack(this);
        this.setPtrHandler2(handler2);
        this.setAdapter(listAdapter);

        expandableListView.setDivider(context.getResources().getDrawable(R.color.zhy_backgroud_1));
        expandableListView.setDividerHeight(context.getResources().getDimensionPixelOffset(R.dimen.dimen_2dp));
        expandableListView.setChildDivider(context.getResources().getDrawable(R.color.zhy_backgroud_1));
    }

    public void setData(List<GoodsDetailsListResultBean> data) {
        this.data = data;
        Map<GoodsShoppingCartListGroupBean, List<GoodsShoppingCartListChildBean>> mapData = getMapData(data);
        listAdapter.setData(mapData);
        listAdapter.setAllChecked(false);


        //展开所有
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    PtrDefaultHandler2 handler2 = new PtrDefaultHandler2() {
        @Override
        public void onLoadMoreBegin(PtrFrameLayout frame) {
            GoodsShoppingCartListView.this.setRefreshComplete();
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            GoodsShoppingCartListView.this.setRefreshComplete();
        }
    };

    /**
     * 获取Map
     *
     * @param data
     * @return
     */
    private Map<GoodsShoppingCartListGroupBean, List<GoodsShoppingCartListChildBean>> getMapData(List<GoodsDetailsListResultBean> data) {
        Map<GoodsShoppingCartListGroupBean, List<GoodsShoppingCartListChildBean>> mapData = new HashMap<>();
        if (data == null)
            return mapData;
        List<String> titleName = new ArrayList<>();
        for (GoodsDetailsListResultBean item : data) {
            if (item.getClass_name() != null)
                titleName.add(item.getClass_name());
        }
        List<String> tempTitleName = new ArrayList(new HashSet(titleName));
        for (String itemTitle : tempTitleName) {
            GoodsShoppingCartListGroupBean groupData = new GoodsShoppingCartListGroupBean();
            List<GoodsShoppingCartListChildBean> childDataList = new ArrayList<>();
            for (GoodsDetailsListResultBean item : data) {
                if (item.getClass_name().equals(itemTitle)) {
                    GoodsShoppingCartListChildBean childData = new GoodsShoppingCartListChildBean();
                    childData.setResultBean(item);
                    childDataList.add(childData);
                }
            }
            groupData.setClassName(itemTitle);
            mapData.put(groupData, childDataList);
        }
        return mapData;
    }

    /**
     * 設置全部點擊
     *
     * @param isAllCheck
     */
    public void setAllCheck(boolean isAllCheck) {
        listAdapter.setAllChecked(isAllCheck);
    }

    @Override
    public void isAllCheck(boolean isAllCheck) {
        if (callBack != null)
            callBack.getIsAllCheck(isAllCheck);
    }

    @Override
    public void getSelectGoods(ArrayList<GoodsShoppingCartListChildBean> selectGoods) {
        if (callBack != null)
            callBack.getSelectGoods(selectGoods);
    }


    public interface CallBack {
        void getIsAllCheck(boolean isAllCheck);

        void getSelectGoods(ArrayList<GoodsShoppingCartListChildBean> selectGoods);
    }
}
