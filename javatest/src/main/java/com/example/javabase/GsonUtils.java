package com.example.javabase;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuhai on 2017/2/18 0018 14:35.
 * 描述：Gson 工具类
 */
public final class GsonUtils {
    private volatile static GsonUtils instance = new GsonUtils();
    private static Gson gson = new Gson();

    public static GsonUtils getInstance() {
        return instance;
    }

    private GsonUtils() {

    }

    public Gson getGson(){
        return gson;
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public String toJson(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public <T> T fromJson(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     * @param gsonString
     * @param cls
     * @return
     */
    public <T> List<T> parseToList1(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list
     * 解决泛型问题
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> parseToList2(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public <T> List<Map<String, T>> parseToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public <T> Map<String, T> parseToMap(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 将json 字符串转换为 String List
     * 
     * @param listJsonStr
     * @return String list
     */
    public List<String> parsJson2StringList(String listJsonStr) {
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        List<String> list = gson.fromJson(listJsonStr, type);

        if (null != list) {
            return list;
        }
        return null;
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public <T> String mapToJson(Map<String, T> map) {
        return gson.toJson(map);
    }
}
