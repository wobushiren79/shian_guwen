package com.shian.shianlife.mvp.shared.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfoBean;

/**
 * Created by zm.
 */

public interface ISharedGoodsServiceInfoView extends BaseMVPView {

    void getSharedGoodsServiceInfoSuccess(SharedGoodsServiceInfoBean result);

    void getSharedGoodsServiceInfoFail(String msg);

    void setSharedGoodsServiceInfoSuccess(String msg);

    void setSharedGoodsServiceInfoFail(String msg);

    /**
     * 设置联系人姓名
     *
     * @param name
     */
    void setServiceInfoCustomerName(String name);

    /**
     * 设置联系人电话
     *
     * @param phone
     */
    void setServiceInfoCustomerPhone(String phone);

    /**
     * 设置服务方式
     *
     * @param serviceWay
     */
    void setServiceInfoServiceWay(Integer serviceWay);

    /**
     * 设置服务地址
     *
     * @param serviceLocation
     */
    void setServiceInfoServiceLocation(String serviceLocation);

    /**
     * 设置详细服务地址
     *
     * @param serviceDetailsLocation
     */
    void setServiceInfoServiceDetailsLocation(String serviceDetailsLocation);

    /**
     * 设置预约服务时间
     *
     * @param serviceTime
     */
    void setServiceInfoServiceTime(String serviceTime);



    /**
     * 获取联系人姓名
     *
     */
    String getServiceInfoCustomerName();

    /**
     * 获取联系人电话
     *
     */
    String getServiceInfoCustomerPhone();

    /**
     * 获取服务方式
     *
     */
    Integer getServiceInfoServiceWay();

    /**
     * 获取服务地址
     *
     */
    String getServiceInfoServiceLocation();

    /**
     * 获取详细服务地址
     *
     */
    String getServiceInfoServiceDetailsLocation();

    /**
     * 获取预约服务时间
     *
     */
    String getServiceInfoServiceTime();
}
