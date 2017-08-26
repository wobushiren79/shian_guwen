package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsClassAttrView extends BaseMVPView {
    void getGoodsClassAttrDataSuccess(List<GoodsClassAttrResultBean> listData);

    void getGoodsClassAttrDataFail(String msg);
}
