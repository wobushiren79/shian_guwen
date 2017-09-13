package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsOrderListView extends BaseMVPView {

    void getGoodsOrderListDataSuccess(GoodsOrderListResultBean resultBean);

    void getGoodsOrderListDataFail(String resultBean);

    List<Integer> getOrderStatus();

    Integer getPayStatus();

    int getPageSize();

    int getPageNumber();
}
