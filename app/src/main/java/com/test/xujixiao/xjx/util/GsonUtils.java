package com.test.xujixiao.xjx.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * gson解析的工具类
 */
public class GsonUtils {

    public static Gson gson = new Gson();

    public static <T> T from(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
        /*使用fastjson解析*/
//        return JSON.parseObject(json, classOfT);
    }

    public static <T> T from(JsonElement json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
        /*使用fastjson解析*/
//        return JSON.parseObject(json.toString(), classOfT);
    }

    public static String toJson(JsonElement jsonElement) {
        return gson.toJson(jsonElement);
    }

    public static <T> T from(JsonElement json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static <T> List<T> parseArray(String json, Class<T> tClass) {
        return JSON.parseArray(json, tClass);
    }

    public static <T, K> List<Map<T, K>> parseListMap(String json) {
        return JSON.parseObject(json, new TypeReference<List<Map<T, K>>>() {
        });
    }
}
