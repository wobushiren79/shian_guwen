package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsInvoiceDetailsItem;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsResultBean;


import java.util.List;
import java.util.Map;

/**
 * Created by zm.
 */

public interface IStoreOrderDetailsView extends BaseMVPView{


    void getStoreOrderDetailsSuccess(StoreOrderDetailsResultBean resultBean);

    void getStoreOrderDetailsFail(String msg);

    Long getOrderId();


    /**
     * 设置客户姓名
     *
     * @param name
     */
    void setCustomerName(String name);

    /**
     * 设置客户电话
     *
     * @param phone
     */
    void setCustomerPhone(String phone);

    /**
     * 设置服务地址
     *
     * @param location
     */
    void setServiceLocation(String location);

    /**
     * 设置服务时间
     *
     * @param time
     */
    void setServiceTime(String time);

    /**
     * 设置订单编号
     *
     * @param number
     */
    void setGoodsItemNumber(String number);

    /**
     * 设置商品数据
     *
     * @param listData
     */
    void setGoodsListData(Map<String, List<GoodsItemPerform>> listData);

    /**
     * 设置是否需要发票
     *
     * @param content
     */
    void setIsNeedInvoice(String content);

    /**
     * 设置发票数据
     *
     * @param listData
     */
    void setInvoiceData(List<GoodsInvoiceDetailsItem> listData);

    /**
     * 设置备注
     *
     * @param remark
     */
    void setRemark(String remark);

    /**
     * 设置客户总价
     *
     * @param money
     */
    void setCustomerMoney(String money);

    /**
     * 设置顾问总价
     *
     * @param money
     */
    void setCounselorMoney(String money);

    /**
     * 设置订单编号
     * @param number
     */
    void setOrderNumber(String number);

    /**
     * 设置订单时间
     * @param time
     */
    void setOrderTime(String time);

    /**
     * 设置付款时间
     * @param time
     */
    void setOrderPayTime(String time);

    /**
     * 设置付款流水
     * @param payNumber
     */
    void setOrderPayNumber(String payNumber);

}
