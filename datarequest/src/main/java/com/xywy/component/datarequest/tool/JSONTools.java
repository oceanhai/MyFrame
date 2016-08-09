
package com.xywy.component.datarequest.tool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONTools {

    public final static JSONObject parserJsonObject(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    /***
     * 
     * @param jsonString
     * @return
     * @deprecated 方法名和返回值类型不统一 建议修改
     */
    public final static JSONArray getJsonObject(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public final static JSONObject buildJsonObject(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public final static String getStringValue(JSONObject json, String key) {
        return getStringValue(json, key, null);
    }

    public final static String getStringValue(JSONObject json, String key, String defaultValue) {

        if (json == null) {
            return defaultValue;
        }

        try {
            return json.getString(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public final static long getLongValue(JSONObject json, String key) {
        return getLongValue(json, key, 0l);
    }

    public final static long getLongValue(JSONObject json, String key, long defaultValue) {
        if(json == null){
            return defaultValue;
        }
        try {
            return json.getLong(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public final static int getIntValue(JSONObject json, String key) {
        return getIntValue(json, key, 0);
    }

    public final static int getIntValue(JSONObject json, String key, int defaultValue) {
        if (json == null) {
            return defaultValue;
        }
        try {
            return json.getInt(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public final static boolean getBooleanValue(JSONObject json, String key) {
        return getBooleanValue(json, key, false);
    }

    public final static boolean getBooleanValue(JSONObject json, String key, boolean defaultValue) {
        if(json == null){
            return defaultValue;
        }
        
        try {
            return json.getBoolean(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public final static double getDoubleValue(JSONObject json, String key) {
        return getDoubleValue(json, key, 0d);
    }

    public final static double getDoubleValue(JSONObject json, String key, double defaultValue) {
        if(json == null){
            return defaultValue;
        }
        try {
            return json.getDouble(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public final static JSONObject getJsonObjValue(JSONObject json, String key) {
        if(json == null){
            return null;
        }
        return json.optJSONObject(key);

    }

    public final static JSONArray getJsonArrayValue(JSONObject json, String key) {
        if(json == null || !json.has(key)) {
            return null;
        }
        try {
            return json.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static String getStringFromJsonArray(JSONArray array, int index) {
        if(array == null){
            return null;
        }
        try {
            return array.getString(index);
        } catch (JSONException e) {
            return null;
        }
    }
    
    public final static long getLongFromJsonArray(JSONArray array, int index){
        if(array == null){
            return 0l;
        }
        try {
            return array.getLong(index);
        } catch (JSONException e) {
            return 0;
        }
    }

    public final static String[] getJsonKeys(JSONObject json) {
        if (json == null) {
            return null;
        }
        //        Log.v(TAG, "feng json getkeys whole json = " + json);

        String[] ret;

        JSONArray keys = json.names();
        int count = keys.length();
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            try {
                ret[i] = keys.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public final static JSONObject getJsonObject(JSONArray jsonArray, int index) {
        if(jsonArray == null){
            return null;
        }
        try {
            JSONObject obj = jsonArray.getJSONObject(index);
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
