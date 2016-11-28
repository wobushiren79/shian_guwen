/*
 * $Revision: $
 * $Date: $
 * $Id: $
 * ====================================================================
 * Copyright © 2012 Beijing seeyon software Co..Ltd..All rights reserved.
 *
 * This software is the proprietary information of Beijing seeyon software Co..Ltd.
 * Use is subject to license terms.
 */
package com.shian.shianlife.common.utils;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;

public class JSONUtil
{
//    private static ObjectMapper mapper = ObjectMapperFactory.getInstance();

//    /**
//     * 解析json 对象
//     * 
//     * @param result
//     * @param cls
//     * @return
//     * @throws M1Exception
//     */
//    public static <T> T parseJSONString(String result, Class<T> cls)
//            throws Exception
//    {
//        T t = mapper.readValue(result, cls);
//        return t;
//    }

    /**
     * 解析json 对象
     * 
     * @param result
     * @param cls
     * @return
     * @throws M1Exception
     */
//    public static <T> T parseJSONString(String result,
//            TypeReference<T> typeReference) throws Exception
//    {
//        T t = mapper.readValue(result, typeReference);
//        return t;
//    }

//    /**
//     * 对象 转化为json字符串
//     * 
//     * @param result
//     * @param cls
//     * @return
//     * @throws M1Exception
//     */
//    public static String writeEntityToJSONString(Object entity)
//            throws Exception
//    {
//        return mapper.writeValueAsString(entity);
//    }
//
//    public static <T> T parseJSONString2Obj(String result, Class<T> cls)
//    {
//        T t = null;
//        try
//        {
//            t = mapper.readValue(result, cls);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//        return t;
//    }
    
    

	private static GsonBuilder instance;

	private static synchronized GsonBuilder build() {
		if (instance == null) {
			instance = new GsonBuilder();
		}
		return instance;
	}
	
	private static GsonBuilder getInstance(){
		return build();
	}
	
	/**
	 * 将obj对象生产对应的JSON字符串
	 * 
	 * @param obj 要转换成JSON字符串的类实例
	 * @return String 根据obj生产的JSON字符串
	 */
	public static String writeEntityToJSONString(Object obj){
		return getInstance().create().toJson(obj);
	}
	
	/**
	 * 根据传入的JSON字符串创建相应类型的实例
	 * 
	 * @param json JSON格式的字符串
	 * @param clazz 对应的类的类型参数
	 * @return T JSON所对应的类实例
	 */
	public static <T> T parseJSONString(String json, Class<T> clazz){
		return getInstance().create().fromJson(json, clazz);
	}

	public static <T> T parseJSONString(String json, Type type){
		return getInstance().create().fromJson(json, type);
	}





}
