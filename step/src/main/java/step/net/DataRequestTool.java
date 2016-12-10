package step.net;


import android.graphics.Bitmap;

import com.xywy.component.datarequest.neworkWrapper.BaseData;
import com.xywy.component.datarequest.neworkWrapper.DataRequestWrapper;
import com.xywy.component.datarequest.neworkWrapper.IDataResponse;
import com.xywy.component.datarequest.tool.MD5;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 构造url的工具
 */
public class DataRequestTool {

    public final static int REQUEST_SUCC = 10000;

    public final static int REQUEST_NO_DATA = 50000;

    /**
     * 计算sig,针对中间层    get形式
     * 30002
     *
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
        //带汉字的请求加密用
        sign = MD5.generateSign(sign + secretKey);
        return sign;
    }


    /**
     * 计算sig,针对中间层    post multipart 形式
     *
     * @param getParams
     * @param postParams
     * @param secretKey
     * @return
     */
    public static String getSig(ApiParams getParams, ApiParams postParams, String secretKey) {
        ApiParams newGetParams = new ApiParams();
        newGetParams.putAll(getParams);
        if (postParams != null) newGetParams.putAll(postParams);
        return getSig(newGetParams, secretKey);
    }

    /**
     * @param url           基础URL
     * @param method        中间URL
     * @param bundle        GET 参数
     * @param iHttpResponse 接口回调结果
     * @param type          回调结果entity.class 返回json对应的
     * @param flag          请求标签
     * @param haveCache     是否有缓存
     * @return
     */
    public static DataRequestWrapper get(String url, String method, final Map<String, String> bundle, final IDataResponse iHttpResponse,
                                         Type type, Object flag, boolean haveCache) {

        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.GET, url, iHttpResponse)
                .urlMethod(method)
                .header(bundle)
                .type(type)
                .tag(flag)
                .cache(haveCache)
                .build();
        return wrapper;
    }


    /**
     * @param url           基础URL
     * @param method        中间URL
     * @param getParms      GET 参数(就是用来拼接URL用的 例如version等)
     * @param postParms     POST 参数
     * @param files         图片文件
     * @param iHttpResponse 接口回调结果
     * @param type          回调结果entity.class 返回json对应的
     * @param flag          请求标签
     * @param haveCache     是否有缓存
     * @return
     */
    public static DataRequestWrapper multipart(String url, String method, Map<String, String> getParms, Map<String, String> postParms, Map<String, File> files, final IDataResponse iHttpResponse,
                                               Type type, Object flag, boolean haveCache) {

        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.MULTIPART, url, iHttpResponse)
                .urlMethod(method)
                .header(getParms)
                .param(postParms)
                .file(files)
                .type(type)
                .tag(flag)
                .cache(haveCache)
                .build();
        return wrapper;
    }

    /**
     * @param url           基础URL
     * @param method        中间URL
     * @param getParms      GET 参数(就是用来拼接URL用的 例如version等)
     * @param postParms     POST 参数
     * @param files         图片文件
     * @param iHttpResponse 接口回调结果
     * @param type          回调结果entity.class 返回json对应的
     * @param flag          请求标签
     * @param haveCache     是否有缓存
     * @return
     */
    public static DataRequestWrapper multipart(String url, String method, Map<String, String> getParms, Map<String, String> postParms, Map<String, File> files, Map<String, Bitmap> bitmap, final IDataResponse iHttpResponse,
                                               Type type, Object flag, boolean haveCache) {

        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.MULTIPART, url, iHttpResponse)
                .urlMethod(method)
                .header(getParms)
                .param(postParms)
                .file(files)
                .type(type)
                .tag(flag)
                .bitmap(bitmap)
                .cache(haveCache)
                .build();
        return wrapper;
    }


    /**
     * @param url           基础URL
     * @param method        中间URL
     * @param getParms      GET 参数(就是用来拼接URL用的 例如version等)
     * @param postParms     POST 参数
     * @param iHttpResponse 接口回调结果
     * @param type          回调结果entity.class 返回json对应的
     * @param flag          请求标签
     * @param haveCache     是否有缓存
     * @return
     */
    public static DataRequestWrapper post(String url, String method, Map<String, String> getParms, Map<String, String> postParms, IDataResponse iHttpResponse,
                                          Type type, Object flag, boolean haveCache) {

        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.POST, url, iHttpResponse)
                .urlMethod(method)
                .header(getParms)
                .param(postParms)
                .type(type)
                .tag(flag)
                .cache(haveCache)
                .build();
        return wrapper;
    }


    /**
     * 对于基本错误的统一处理
     *
     * @param res
     * @return
     */
    public static boolean noError(BaseData res) {
        boolean correct = false;
        if (res != null) {
            if (res.getCode() >= 0 && res.getCode() <= REQUEST_SUCC) {
                correct = true;
            }
            if (res.getMsg().equals("network error")) {
                res.setMsg("网络异常");
            }
        }

//
//        if(!correct && isToast) {//TODO 错误处理 根据实际需求在设定
//            if(!TextUtils.isEmpty(res.getMsg())){
//                if(res.getMsg().equals("network error")){
//                    Toast.makeText(context, "数据加载失败，请稍后再试", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(context, res.getMsg(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }

        return correct;
    }


}
