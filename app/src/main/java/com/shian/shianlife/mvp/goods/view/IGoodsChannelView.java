package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsChannelView extends BaseMVPView {
    void getGoodsChannelDataSuccess(List<GoodsChannelResultBean> listData);

    void getGoodsChannelDataFail(String msg);

    void setChannelId(Integer channelId);
}
