package com.myframe.www.retrofit.xywy;

import com.myframe.www.request.Constants;
import com.myframe.www.request.RequestKey;
import com.myframe.www.retrofit.xywy.model.LoginEntity;
import com.myframe.www.retrofit.xywy.network.ApiParams;
import com.xywy.component.datarequest.tool.MD5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import www.wuhai.common.utils.L;

/**
 * Created by wuhai on 2018/5/16.
 */

public class ServiceProvider implements IServiceProvider {

    /**
     * 计算sig,针对中间层    get形式
     * @param params
     * @param secretKey
     * @return
     */
    public static String getSig(ApiParams params, String secretKey) {

        String sign = "";
        List<Map.Entry<String, String>> list
                = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sign += list.get(i).getKey() + "=" + list.get(i).getValue();
            } else {
                sign += list.get(i).getKey() + "=" + list.get(i).getValue() +
                        "&";
            }
        }
        L.e("TAG_SUBMIT-sign", sign + secretKey);
        //带汉字的请求加密用
        sign = MD5.generateSign(sign + secretKey);
        return sign;
    }

    @Override
    public void login(String name, String password, Callback<LoginEntity> callback) {
        ApiParams apiParams = new ApiParams().withNoToken(Constants.version_value1);
        apiParams.with(Constants.UserName, name);
        apiParams.with(Constants.PassWord, password);
        apiParams.with(Constants.api_key, Constants.api_value);
        apiParams.with(Constants.SIGN, getSig(apiParams, RequestKey.basekey));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUrl.REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        XywyAPI xywyAPI = retrofit.create(XywyAPI.class);
        Call<LoginEntity> call = xywyAPI.login(apiParams);
        call.enqueue(callback);
    }
}
