package com.shian.shianlife.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

import com.shian.shianlife.adapter.GoodsFiltrateAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;

import java.util.List;
import java.util.Map;

/**
 * Created by zm.
 */

public class GoodsQueryDrawerExpandableListView extends ExpandableListView implements ExpandableListView.OnGroupExpandListener, ExpandableListView.OnChildClickListener {
    private GoodsFiltrateAdapter filtrateAdapter;
    private CallBack callBack;

    public GoodsQueryDrawerExpandableListView(Context context) {
        this(context, null);
    }

    public GoodsQueryDrawerExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        filtrateAdapter = new GoodsFiltrateAdapter(getContext());

        this.setAdapter(filtrateAdapter);
        this.setOnGroupExpandListener(this);
        this.setOnChildClickListener(this);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onGroupExpand(int groupPosition) {
        if (this.isGroupExpanded(groupPosition)) {
            filtrateAdapter.getClassAttrData(groupPosition);
        }
    }

    public void setData(Map<GoodsClassResultBean, List<GoodsClassAttrResultBean>> data) {
        filtrateAdapter.setData(data);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        GoodsClassAttrResultBean data = filtrateAdapter.getChildItemData(groupPosition, childPosition);
        if (callBack != null)
            callBack.classAttrChange(data.getApec_id(), data.getId());
        return true;
    }

    public interface CallBack {
        void classAttrChange(Long classId, Long classAttrId);
    }
}
