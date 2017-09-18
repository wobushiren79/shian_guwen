package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsLabelView extends BaseMVPView {

    void getGoodsMainRecommendTitleSuccess(List<GoodsLabelResultBean> listData);

    void getGoodsMainRecommendTitleFail(String msg);


}
