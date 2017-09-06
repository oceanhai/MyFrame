package com.example.lctusb;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wuhai on 2017/08/16 15:51.
 * 描述：
 */

public class GetLongTest {

    public static void main(String[] args){
        String str = "{\"batch_id\":\"470556213534542362\"}";
        try {
            JSONObject jsonObject = new JSONObject(str);
            long batch_id = jsonObject.getLong("batch_id");
            System.out.println("1="+batch_id);

            String numStr = "470556213534542362";
            System.out.println("2="+Long.valueOf(numStr));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
