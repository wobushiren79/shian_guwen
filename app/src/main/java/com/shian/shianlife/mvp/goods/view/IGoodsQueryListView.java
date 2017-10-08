package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsQueryListView extends BaseMVPView {

    Long getClassAttrId();

    Integer getChannelId();

    Integer getPageNumber();

    Integer getPageSize();

    /**
     * 获取排序规则
     *
     * @return
     */
    String getRankOrder();

    String getGoodsName();

    void getGoodsQueryListDataSuccess(List<GoodsQueryListResultBean> listData);

    void getGoodsQueryListDataFail(String msg);

}
