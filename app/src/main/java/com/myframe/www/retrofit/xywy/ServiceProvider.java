package com.myframe.www.retrofit.xywy;

import com.myframe.www.request.Constants;
import com.myframe.www.request.RequestKey;
import com.myframe.www.retrofit.xywy.model.LoginEntity;
import com.myframe.www.retrofit.xywy.network.APIBaseService;
import com.myframe.www.retrofit.xywy.network.ApiParams;
import com.myframe.www.retrofit.xywy.network.XywyAPI;
import com.xywy.component.datarequest.tool.MD5;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import www.wuhai.common.utils.L;

/**
 * Created by wuhai on 2018/5/16.
 */

public class ServiceProvider extends APIBaseService implements IServiceProvider {

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

    /**
     * 计算sig,针对中间层    post multipart 形式
     * @param getParams
     * @param postParams
     * @param secretKey
     * @return
     */
    public static String getSig(ApiParams getParams, ApiParams postParams, String secretKey) {
        ApiParams newGetParams = new ApiParams();
        newGetParams.putAll(getParams);
        if (postParams != null){
            newGetParams.putAll(postParams);
        }
        return  getSig(newGetParams, secretKey);
    }

    @Override
    public void login(String name, String password, Callback<LoginEntity> callback) {
        ApiParams apiParams = new ApiParams().withNoToken(Constants.version_value1);
        apiParams.with(Constants.UserName, name);
        apiParams.with(Constants.PassWord, password);
        apiParams.with(Constants.api_key, Constants.api_value);
        apiParams.with(Constants.SIGN, getSig(apiParams, RequestKey.basekey));

        Call<LoginEntity> call = createService(XywyAPI.class).login(apiParams);
        call.enqueue(callback);
    }

    @Override
    public void getCode(String phone, String flag, Callback<ResponseBody> callback) {
        ApiParams getApiParams = new ApiParams().with(Constants.version_value2)
                .with(Constants.api_key,
                        Constants.api_code_value);
        ApiParams postApiParams = new ApiParams()
                .with(Constants.act_key, Constants.act_value)
                .with(Constants.phone_key, phone)
                .with(Constants.code_key, "");
        if("dynamic_password".equals(flag)){
            postApiParams.with(Constants.project_key, "APP_XYWYCJAPP_LOGIN");
        }else{
            postApiParams.with(Constants.project_key, Constants.project_value);
        }
        getApiParams.with(Constants.SIGN,getSig(getApiParams, postApiParams,
                RequestKey.basekey));

        Call<ResponseBody> call =  createService(XywyAPI.class).getCode(getApiParams,postApiParams);
        call.enqueue(callback);
    }

    @Override
    public void uploadImg(File file, Callback<ResponseBody> callback) {
        ApiParams getApiParams = new ApiParams()
                .withNoToken(Constants.version_value1)
                .with(Constants.api_key, "1248");

        ApiParams postApiParams = new ApiParams().with("thumb", "2");

        getApiParams.with(Constants.SIGN,getSig(getApiParams, postApiParams,
                        RequestKey.basekey));

        //创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        /**
         * 图文上传
         * thumb 2
         * mypic 文件 （※多个文件继续追加即可，只不过这要跟后台业务相对应）
         */
        Map<String, RequestBody> map = new HashMap<>();
        map.put("thumb",RequestBody.create(MediaType.parse("text/plain"),"2"));
        map.put("mypic\"; filename=\""+file.getName()+"\"",requestFile);

        createService(XywyAPI.class).uploadImg(getApiParams,map).enqueue(callback);
    }
}
