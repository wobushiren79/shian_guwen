package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsMainRecommendTitleResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsMainRecommendTitleView extends BaseMVPView {

    void getGoodsMainRecommendTitleSuccess(List<GoodsMainRecommendTitleResultBean> listData);

    void getGoodsMainRecommendTitleFail(String msg);


}
