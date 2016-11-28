package com.shian.shianlife.common.utils;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author wangx
 * 
 * @date 2011-11-4
 * 
 * @version 1.0
 * 
 * @since SDK1.5
 */
public class ObjectMapperFactory
{
    private static ObjectMapper mapper = null;

    public static ObjectMapper getInstance()
    {
        if (mapper == null)
        {
            mapper = new ObjectMapper();
            // 获取解析配置
            DeserializationConfig config = mapper.getDeserializationConfig();
            // 设置解析时，如果遇到不能识别的属性，则直接忽略，不报错
            DeserializationConfig newConfig = config
                    .without(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            // 将新的设置设置到mapper中
            mapper.setDeserializationConfig(newConfig);
            mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS,
                    true);
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        }

        return mapper;
    }
}
