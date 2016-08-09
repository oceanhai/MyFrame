package com.xywy.component.datarequest.tool;

import com.xywy.component.datarequest.neworkWrapper.BaseData;
import com.xywy.component.datarequest.neworkWrapper.DataRequestWrapper;
import com.xywy.component.datarequest.neworkWrapper.IDataResponse;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 构造url的工具
 */
public class DataRequestTool {

    public static final String REQUEST_SOURCE = "xywyapp";
    public static final String REQUEST_OS = "android";
    public static final String REQUEST_PRO = "xywyf32l24WmcqquqqTdhXaEng";

    public static String getSig(String params, String secretKey) {
        StringBuffer sb = new StringBuffer(params);
        sb.append(secretKey);
        return MD5.KeyMD5(sb.toString());
    }

    /**
     * 根据Map请求参数计算sig值
     *
     * @param data
     * @param secretKey
     * @return
     */
    public static String getSigFromMap(final Map<String, String> data, String secretKey) {
        String param = buildStringFromBundle(data);
        String sig = getSig(param, secretKey);
        return sig;
    }

    public static String buildStringFromBundle(final Map<String, String> data) {
        StringBuffer sb = new StringBuffer();
        Iterator<String> keys = data.keySet().iterator();
        while (keys.hasNext()) {
            final String key = keys.next();
            String val = data.get(key);
            sb.append(key).append("=").append(val);
            sb.append("&");
        }
        int length = sb.length();
        if(length>0 ) {
            sb.deleteCharAt(length-1);
        }
        return sb.toString();
    }

    public static Map<String, String> buildCommonBundle() {
        Map<String, String> bundle = new TreeMap<>();
        bundle.put("source", REQUEST_SOURCE);
        bundle.put("os", REQUEST_OS);
        bundle.put("pro", REQUEST_PRO);
        return bundle;
    }



    public static DataRequestWrapper get(String url, String method, final Map<String, String> bundle, final IDataResponse iHttpResponse,
                                         Type type, String flag) {
//
//        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.GET,
//                builder.toString(), iHttpResponse)
//                .tag(flag)
//                .build();

        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.GET, url, iHttpResponse)
                .urlMethod(method)
                .header(bundle)
                .type(type)
                .tag(flag)
                .cache(true)
                .build();
        return wrapper;
    }

    public static DataRequestWrapper get(String url, final IDataResponse iHttpResponse, Type type, String flag){
        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.GET, url, iHttpResponse)
                .type(type)
                .tag(flag)
                .build();
        return wrapper;
    }

    public static DataRequestWrapper post(String url, String method, final Map<String, String> bundle, final IDataResponse iHttpResponse,
                                         Type type, String flag) {
//
//        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.GET,
//                builder.toString(), iHttpResponse)
//                .tag(flag)
//                .build();

        DataRequestWrapper wrapper = new DataRequestWrapper.Builder(DataRequestWrapper.DataRequestMethod.POST, url, iHttpResponse)
                .urlMethod(method)
                .param(bundle)
                .type(type)
                .tag(flag)
                .build();
        return wrapper;
    }

    /**
     * 对于基本错误的统一处理
     * @param res
     * @return
     */
    public static boolean noError(BaseData res) {
        //// TODO: 15/12/20 增加错误对应表

        boolean correct = false;
        if(res!=null) {
            if(res.getCode() >= 0) {
                correct = true;
            }
        }

        if(!correct) {
            //todo 统一的错误处理
        }

        return correct;
    }
}
