package com.shian.shianlife.common.utils;

import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;

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
    public static List<GoodsItemPerform> ShoppingCartToGoodsData(List<GoodsShoppingCartListChildBean> listShoppingCart) {
        List<GoodsItemPerform> listGoods = new ArrayList<>();
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
            listGoods.add(goodsData);
        }
        return listGoods;
    }
}
