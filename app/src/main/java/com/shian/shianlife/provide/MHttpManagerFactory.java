package com.shian.shianlife.provide;

import com.shian.shianlife.provide.imp.CarManager;
import com.shian.shianlife.provide.imp.CemeteryManager;
import com.shian.shianlife.provide.imp.FileManager;
import com.shian.shianlife.provide.imp.FuneralManager;
import com.shian.shianlife.provide.imp.FuneralOrderManager;
import com.shian.shianlife.provide.imp.GoodsManager;
import com.shian.shianlife.provide.imp.GoodsOrderManager;
import com.shian.shianlife.provide.imp.PHPManager;
import com.shian.shianlife.provide.imp.ProductManager;
import com.shian.shianlife.provide.imp.SystemManager;
import com.shian.shianlife.provide.imp.impl.CarManagerImpl;
import com.shian.shianlife.provide.imp.impl.CemeteryManagerImpl;
import com.shian.shianlife.provide.imp.impl.FileManagerImpl;
import com.shian.shianlife.provide.imp.impl.FuneralManagerImpl;
import com.shian.shianlife.provide.imp.impl.FuneralOrderManagerImpl;
import com.shian.shianlife.provide.imp.impl.GoodsManagerImpl;
import com.shian.shianlife.provide.imp.impl.GoodsOrderManagerImpl;
import com.shian.shianlife.provide.imp.impl.PHPManagerImpl;
import com.shian.shianlife.provide.imp.impl.ProductManagerImpl;
import com.shian.shianlife.provide.imp.impl.SystemManagerImpl;

/**
 * 接口工厂
 *
 * @author Administrator
 */
public class MHttpManagerFactory {
    /**
     * 获取账户接口manager
     *
     * @return
     */
    //殡仪
    public static FuneralManager getFuneralManager() {
        return FuneralManagerImpl.getInstance();
    }

    //殡仪订单
    public static FuneralOrderManager getFuneralOrderManager() {
        return FuneralOrderManagerImpl.getInstance();
    }

    //殡仪商品
    public static ProductManager getProductManager() {
        return ProductManagerImpl.getInstance();
    }


    //公墓
    public static CemeteryManager getCemeteryManager() {
        return CemeteryManagerImpl.getInstance();
    }

    //派車
    public static CarManager getCarManager() {
        return CarManagerImpl.getInstance();
    }

    //文件管理
    public static FileManager getFileManager() {
        return FileManagerImpl.getInstance();
    }

    //PHP管理
    public static PHPManager getPHPManager() {
        return PHPManagerImpl.getInstance();
    }

    //登陆
    public static SystemManager getSystemManager() {
        return SystemManagerImpl.getInstance();
    }

    //商品管理
    public static GoodsManager getGoodsManager() {
        return GoodsManagerImpl.getInstance();
    }

    //商品流程
    public static GoodsOrderManager getGoodsOrderManager() {
        return GoodsOrderManagerImpl.getInstance();
    }
}
