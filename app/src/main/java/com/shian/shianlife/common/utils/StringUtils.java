package com.shian.shianlife.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zm
 */
public class StringUtils {
    public static final String Separate_Tag = ",split,";
    public static final String Key_Value_Sp = ".split.";

    public static String getStringFromMap(Map<String, String> data) {
        StringBuffer buffer = new StringBuffer();
        if (data == null)
            return buffer.toString();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (buffer.length() != 0) {
                buffer.append(Separate_Tag);
            }
            String tempItem = key + Key_Value_Sp + value;
            buffer.append(tempItem);
        }
        return buffer.toString();
    }

    public static Map<String, String> getMapFormString(String data) {
        Map<String, String> buffer = new HashMap<>();
        String[] tempData = data.split(Separate_Tag);
        if (tempData == null || tempData.length == 0)
            return buffer;

        for (String itemData : tempData) {
            String[] itemKeyValue = itemData.split(Key_Value_Sp);
            if (itemKeyValue.length >= 2) {
                buffer.put(itemKeyValue[0], itemKeyValue[1]);
            }
        }
        return buffer;
    }

    /**
     * 替换指定字符到自定字符的string
     *
     * @param data
     * @param start
     * @param end
     * @return
     */
    public static String replaceString(String data, String start, String end) {
        int startNumber = data.indexOf(start);
        int endNumber = data.indexOf(end) + 1;
        if (startNumber != -1) {
            StringBuffer stringBuffer = new StringBuffer(data);
            stringBuffer.replace(startNumber, endNumber, "");
            data = replaceString(stringBuffer.toString(), start, end);
        }
        return data;
    }


}
