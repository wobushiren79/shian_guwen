package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;

import java.util.List;


/**
 * Created by zm.
 */

public interface IGoodsDetailsView extends BaseMVPView {

    void getGoodsDetailsSuccess(GoodsDetailsResultBean listData);

    void getGoodsDetailsFail(String msg);

    Long getGoodsId();

    /**
     * 设置轮播图
     * @param picList
     */
    void setCarouselPic(List<String> picList);

    /**
     * 设置商品名称
     * @param name
     */
    void setGoodsName(String name);
}
