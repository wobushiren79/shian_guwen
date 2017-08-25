package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsClassAttrMainView extends BaseMVPView {
    void getGoodsClassAttrMainDataSuccess(List<GoodsClassAttrMainResultBean> data);

    void getGoodsClassAttrMainDataFail(String msg);
}
