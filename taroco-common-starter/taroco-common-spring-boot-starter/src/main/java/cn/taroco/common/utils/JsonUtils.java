package cn.taroco.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON 工具类
 *
 * @author liuht
 * @date 2018/8/20 16:36
 */
public class JsonUtils {

    /**
     * Object 转JSON string
     *
     * @param obj 参数
     * @return string
     */
    public static String toJsonString (Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.SkipTransientField);
    }
}
