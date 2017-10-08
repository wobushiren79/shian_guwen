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

    Integer getIsPackage();

    /**
     * 设置轮播图
     *
     * @param picList
     */
    void setCarouselPic(List<String> picList);

    /**
     * 设置商品名称
     *
     * @param name
     */
    void setGoodsName(String name);

    /**
     * 设置价格范围
     *
     * @param range
     */
    void setPriceRange(String range);

    /**
     * 设置原价
     *
     * @param original
     */
    void setPriceOriginal(String original);

    /**
     * 设置销售数量
     *
     * @param number
     */
    void setSaleNumber(String number);

    /**
     * 设置适用地区
     *
     * @param location
     */
    void setLocation(String location);

    /**
     * 设置规格商品数据
     *
     * @param data
     */
    void setGoodsSpecSelectData(List<GoodsDetailsResultBean.SpecpriceBean> data);

    /**
     * 设置产品详情
     *
     * @param html
     */
    void setGoodsDescribeDetails(String html);


    /**
     * 设置适用安葬礼仪
     *
     * @param applyBury
     */
    void setGoodsApplyBury(String applyBury);

    /**
     * 设置适用人群
     *
     * @param applyPerson
     */
    void setGoodsApplyPerson(String applyPerson);

    /**
     * 设置适用阶段
     *
     * @param applyPhase
     */
    void setGoodsApplyPhase(String applyPhase);

    /**
     * 设置适用年龄
     *
     * @param applyAge
     */
    void setGoodsApplyAge(String applyAge);

    /**
     * 设置适用地区
     *
     * @param location
     */
    void setGoodsApplyLocation(String location);

    /**
     * 设置分类ID
     *
     * @param classId
     */
    void setGoodsClassId(Long classId);

    /**
     * 设置分类属性ID
     *
     * @param classAttrId
     */
    void setGoodsClassAttrId(Long classAttrId);
}

