package www.wuhai.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gson 工具类
 */
public final class GsonUtils {
    private volatile static GsonUtils instance = new GsonUtils();
    private static Gson gson = new Gson();

    public static GsonUtils getInstance() {
        return instance;
    }

    private GsonUtils() {

    }

    public <T> T toObj(String json, Class<T> T) {
        return gson.fromJson(json, T);
    }

    public String toJson(Object obj) {
        return gson.toJson(obj);
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

    public Gson getGson(){
        return gson;
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /**
     * json 转 map
     * @param data
     * @return
     */
    public Map<String, String> parseToMap(String data){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }
}
