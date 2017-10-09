package com.shian.shianlife.common.utils;

import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsInvoice;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderItem;
import com.shian.shianlife.mvp.goods.bean.GoodsServiceInfo;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfoBean;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zm.
 */

public class DataUtils {
    /**
     * 将list<GoodsItemPerform> 转换为map形式  条件为分类名称
     *
     * @param goodsItemPerforms
     * @return
     */
    public static Map<String, List<GoodsItemPerform>> getMapForGoodsItemPerform(List<GoodsItemPerform> goodsItemPerforms) {
        Map<String, List<GoodsItemPerform>> goodsListData = new HashMap<>();
        try {
            List<String> classList = new LinkedList<>();
            for (GoodsItemPerform item : goodsItemPerforms) {
                classList.add(item.getSpecOrderedAttr());
            }
            List<String> newClassList = new ArrayList(new HashSet(classList));
            for (String className : newClassList) {
                List<GoodsItemPerform> listItemData = new ArrayList<>();
                for (GoodsItemPerform item : goodsItemPerforms) {
                    if (item.getSpecOrderedAttr().equals(className)) {
                        listItemData.add(item);
                    }
                }
                goodsListData.put(className, listItemData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodsListData;
    }


    /**
     * 将购物车数据转为流程数据
     *
     * @param listShoppingCart
     * @return
     */
    public static ArrayList<GoodsItemPerform> shoppingCartToGoodsData(List<GoodsShoppingCartListChildBean> listShoppingCart) {
        ArrayList<GoodsItemPerform> listGoods = new ArrayList<>();
        if (listShoppingCart == null) {
            return listGoods;
        }
        for (GoodsShoppingCartListChildBean item : listShoppingCart) {
            GoodsDetailsListResultBean shoppingData = item.getResultBean();
            if (shoppingData == null) {
                continue;
            }
            GoodsItemPerform goodsData = new GoodsItemPerform();
            //个数
            goodsData.setSpecOrderedNum(shoppingData.getShoppingCartNumber());
            //价钱
            goodsData.setAdviserPrice((int) (shoppingData.getAdviser_price() * 100));
            goodsData.setSpecOrderedPrice((int) (shoppingData.getSpec_price() * 100));
            goodsData.setEmentPrice((int) (shoppingData.getEment_price() * 100));
            //规格分类
            goodsData.setSpecAlias(shoppingData.getSpec_alias());
            //规格名称
            goodsData.setSpecName(shoppingData.getSpec_name());
            //规格商品名称
            goodsData.setSpecOrderedVolume(shoppingData.getGoods_name());
            //展示图片
            goodsData.setTitleImg(shoppingData.getTitle_img());
            //分类名称
            goodsData.setSpecOrderedAttr(shoppingData.getClass_name());
            //折扣率
            goodsData.setCurrentDiscount("1");

            goodsData.setClassifyAttrId(shoppingData.getClass_attr_id());

            goodsData.setSpecNumber(shoppingData.getGoods_number());
            goodsData.setUnit(shoppingData.getUnit());

            if (shoppingData.getIs_package() != null && shoppingData.getIs_package() == 0) {
                goodsData.setGoodsId(shoppingData.getGoods_id());
                goodsData.setGoodsSpecId(shoppingData.getSpec_id());
                goodsData.setClassifyId(shoppingData.getGoods_class_id());
            } else if (shoppingData.getIs_package() != null && shoppingData.getIs_package() == 1) {
                goodsData.setPackageId(shoppingData.getPackage_id());
                goodsData.setPackageSpecId(shoppingData.getSpec_id());
                goodsData.setClassifyId(shoppingData.getPackage_class_id());
                goodsData.setGoodsOrderItems(packageGoodsToGoodsData(shoppingData.getGoods()));
            }

            listGoods.add(goodsData);
        }
        return listGoods;
    }

    public static List<GoodsOrderItem> packageGoodsToGoodsData(List<GoodsDetailsResultBean.SpecGoods> packageGoods) {
        List<GoodsOrderItem> goodsData = new ArrayList<>();
        for (GoodsDetailsResultBean.SpecGoods item : packageGoods) {
            //价钱
            GoodsOrderItem goodsOrderItem = new GoodsItemPerform();
            goodsOrderItem.setGoodsId(item.getGoods_id());
            goodsOrderItem.setGoodsSpecId(item.getGoods_spec_id());
            goodsOrderItem.setSpecName(item.getSpec_name());
            goodsOrderItem.setSpecNumber(item.getGoods_number());
            goodsOrderItem.setCurrentDiscount("1");
            goodsOrderItem.setSpecAlias(item.getSpec_alias());
            goodsOrderItem.setSpecOrderedAttr(item.getSpec_name());
            goodsOrderItem.setSpecOrderedNum(item.getGoods_spec_number());
            goodsOrderItem.setSpecOrderedVolume(item.getName());
            goodsOrderItem.setSpecTotal((int) (item.getTotal() * 100));
            goodsOrderItem.setTitleImg(item.getTitle_img());
            goodsOrderItem.setUnit(item.getUnit());
            goodsData.add(goodsOrderItem);
        }
        return goodsData;
    }

    /**
     * 将订单详情数据转为流程数据
     *
     * @return
     */
    public static ArrayList<GoodsItemPerform> goodsDetailsToGoodsData(GoodsDetailsResultBean goodsDetails, GoodsDetailsResultBean.SpecpriceBean goodsSpec, int number) {
        ArrayList<GoodsItemPerform> listGoods = new ArrayList<>();
        if (goodsDetails == null || goodsSpec == null) {
            return listGoods;
        }
        GoodsItemPerform goodsData = new GoodsItemPerform();
        //个数
        goodsData.setSpecOrderedNum(number);
        //价钱
        goodsData.setAdviserPrice((int) (goodsSpec.getAdviser_price() * 100));
        goodsData.setSpecOrderedPrice((int) (goodsSpec.getSpec_price() * 100));
        goodsData.setEmentPrice((int) (goodsSpec.getEment_price() * 100));
        //规格分类
        goodsData.setSpecAlias(goodsSpec.getSpec_alias());
        //规格名称
        goodsData.setSpecName(goodsSpec.getSpec_name());
        //规格商品名称
        goodsData.setSpecOrderedVolume(goodsDetails.getName());
        //展示图片
        goodsData.setTitleImg(goodsDetails.getTitle_img());
        //分类名称
        goodsData.setSpecOrderedAttr(goodsDetails.getGoods_class_name());
        //折扣率
        goodsData.setCurrentDiscount("1");

        goodsData.setClassifyId(goodsDetails.getGoods_cate_id());
        goodsData.setClassifyAttrId(goodsDetails.getSpec_attr_id());
        goodsData.setGoodsId(goodsSpec.getGoods_id());
        goodsData.setGoodsSpecId(goodsSpec.getGoods_spec_id());
        goodsData.setSpecNumber(goodsSpec.getGoods_number());
        goodsData.setUnit(goodsDetails.getUnit());
        listGoods.add(goodsData);

        return listGoods;
    }

    public static GoodsInvoice SharedGoodsInvoiceInfoBeanToGoodsInvoice(SharedGoodsInvoiceInfoBean sharedData) {
        if (sharedData == null) {
            return null;
        }
        GoodsInvoice goodsInvoice = new GoodsInvoice();
        if (sharedData.getInvoiceType() != null)
            goodsInvoice.setTitleType(sharedData.getInvoiceType());

        if (!CheckUtils.isEmpty(sharedData.getCompanyName()))
            goodsInvoice.setTitle(sharedData.getCompanyName());

        if (!CheckUtils.isEmpty(sharedData.getCompanyTaxNumber()))
            goodsInvoice.setCompanyTaxId(sharedData.getCompanyTaxNumber());

        if (!CheckUtils.isEmpty(sharedData.getCompanyRemark()))
            goodsInvoice.setInvoiceRemark(sharedData.getCompanyRemark());

        if (!CheckUtils.isEmpty(sharedData.getReceiverName()))
            goodsInvoice.setReceiptName(sharedData.getReceiverName());

        if (!CheckUtils.isEmpty(sharedData.getReceiverPhone()))
            goodsInvoice.setReceiptPhone(sharedData.getReceiverPhone());

        if (!CheckUtils.isEmpty(sharedData.getReceiverLocation())
                && !CheckUtils.isEmpty(sharedData.getReceiverDetailsLocation()))
            goodsInvoice.setReceiptLocation(sharedData.getReceiverLocation() + sharedData.getReceiverDetailsLocation());

        return goodsInvoice;
    }

    public static GoodsServiceInfo SharedGoodsServiceInfoBeanToGoodsInvoice(SharedGoodsServiceInfoBean sharedData) {
        if (sharedData == null) {
            return null;
        }
        GoodsServiceInfo goodsServiceInfo = new GoodsServiceInfo();
        if (!CheckUtils.isEmpty(sharedData.getServiceWay()))
            goodsServiceInfo.setServiceWay(sharedData.getServiceWay());

        if (!CheckUtils.isEmpty(sharedData.getCustomerName()))
            goodsServiceInfo.setContact(sharedData.getCustomerName());

        if (!CheckUtils.isEmpty(sharedData.getCustomerPhone()))
            goodsServiceInfo.setContactPhone(sharedData.getCustomerPhone());

        if (!CheckUtils.isEmpty(sharedData.getServiceLocation()) && !CheckUtils.isEmpty(sharedData.getServiceDetailsLocation()))
            goodsServiceInfo.setServiceLocation(sharedData.getServiceLocation() + sharedData.getServiceDetailsLocation());

        if (!CheckUtils.isEmpty(sharedData.getServiceTime()))
            goodsServiceInfo.setBookTime(sharedData.getServiceTime() + ":00");

        return goodsServiceInfo;
    }
}
