package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsClassView extends BaseMVPView {

    void getGoodsClassDataSuccess(List<GoodsClassResultBean> listData);

    void getGoodsClassDataFail(String msg);

    Integer getChannelId();
}
