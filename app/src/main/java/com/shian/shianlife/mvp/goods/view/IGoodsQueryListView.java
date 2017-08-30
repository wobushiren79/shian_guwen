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

    String getGoodsName();

    void getGoodsQueryListDataSuccess(List<GoodsQueryListResultBean> listData);

    void getGoodsQueryListDataFail(String msg);

}
