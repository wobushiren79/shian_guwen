package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsLabelDetailsView extends BaseMVPView {
    void getGoodsLabelDetailsSuccess(List<GoodsLabelDetailsResultBean> resultBean);

    void getGoodsLabelDetailsFail(String msg);

    Long getLabelId();
}
