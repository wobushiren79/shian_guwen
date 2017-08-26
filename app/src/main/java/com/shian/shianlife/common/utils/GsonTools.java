package com.shian.shianlife.common.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zm.
 */

public class GsonTools {
    private static Gson gson = new Gson();

    public GsonTools() {
    }

    public static <T> T getObjectByMapKey(String key, Map map, T t) throws Exception {
        if (!StringUtils.isEmpty(key) && map.containsKey(key)) {
            String jsonObjStr = gson.toJson(map.get(key));
            return (T) gson.fromJson(jsonObjStr, t.getClass());
        } else {
            return null;
        }
    }

    public static <T> List<T> getListByMapKey(String key, Map map, T t) throws Exception {
        if (!map.containsKey(key)) {
            throw new Exception("key值无效");
        } else {
            List lists = (List) map.get(key);
            if (lists != null && !lists.isEmpty()) {
                ArrayList newList = new ArrayList();
                Iterator i$ = lists.iterator();

                while (i$.hasNext()) {
                    Map mapObj = (Map) i$.next();
                    String valueMap = gson.toJson(mapObj);
                    Object tt = gson.fromJson(valueMap, t.getClass());
                    newList.add(tt);
                }

                return newList;
            } else {
                return null;
            }
        }
    }

    public static <T> Map<String, T> getMapByMapKey(String key, Map map, T t) throws Exception {
        if (!map.containsKey(key)) {
            throw new Exception("key值无效");
        } else {
            Map mapTmp = (Map) map.get(key);
            if (mapTmp.isEmpty()) {
                throw new Exception("key值无效");
            } else {
                HashMap newMap = new HashMap();
                Iterator i$ = mapTmp.keySet().iterator();

                while (i$.hasNext()) {
                    String keyMap = (String) i$.next();
                    String valueMap = gson.toJson(mapTmp.get(keyMap));
                    Object tt = gson.fromJson(valueMap, t.getClass());
                    newMap.put(keyMap, tt);
                }

                return newMap;
            }
        }
    }

    public static Gson getGson() {
        return gson;
    }
}
