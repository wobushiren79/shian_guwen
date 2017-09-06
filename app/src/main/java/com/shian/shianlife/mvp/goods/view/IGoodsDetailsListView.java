package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsDetailsListView extends BaseMVPView {

    void getGoodsDetailsListDataSuccess(List<GoodsDetailsListResultBean> resultBeen);

    void getGoodsDetailsListDataFail(String msg);

    List<Long> getGoodsIds();

    List<Integer> getChannelIds();

    List<Long> getGoodsSpecIds();

}
