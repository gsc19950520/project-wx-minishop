package com.shop.util.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.List;
import java.util.Map;

public class JSONUtil {
    public JSONUtil() {
    }

    public static String toJson(Object object) {
        ValueFilter filter = (obj, s, v) -> {
            return v == null ? "" : v;
        };
        return JSONObject.toJSONString(object, filter, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
    }

    public static <T> List<T> json2List(String jsonStr, Class<T> clazz) {
        return JSONObject.parseArray(jsonStr, clazz);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> clazz) {
        return JSONObject.parseObject(jsonStr, clazz);
    }

    public static <T> T json2Bean(JSONObject jsonObject, Class<T> clazz) {
        return jsonObject.toJavaObject(clazz);
    }

    public static List jsonToList(String jsonStr) {
        return JSONArray.parseArray(jsonStr);
    }

    public static List jsonToList(String jsonStr, Class clazz) {
        return JSONObject.parseArray(jsonStr, clazz);
    }

    public static Object jsonToBean(String jsonStr) {
        return JSONObject.parseObject(jsonStr);
    }

    public static Object jsonToBean(String jsonStr, Class clazz) {
        return JSONObject.parseObject(jsonStr, clazz);
    }

    public static Object jsonToBean(JSONObject jsonObject, Class clazz) {
        return jsonObject.toJavaObject(clazz);
    }

    public static Object jsonToBean(String jsonStr, String key, Class clazz) {
        return JSONObject.parseObject(JSONObject.parseObject(jsonStr).getString(key), clazz);
    }

    public static Map<String, String> jsonToMap(String jsonStr) {
        return (Map)JSONObject.parseObject(jsonStr, Map.class);
    }

    public static Map<String, Object> jsonToMap2(String jsonStr) {
        return (Map)JSONObject.parseObject(jsonStr, Map.class);
    }
}
