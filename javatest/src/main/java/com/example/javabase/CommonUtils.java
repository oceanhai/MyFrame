package com.example.javabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhai on 2017/02/13 19:45.
 * 描述：
 */

public class CommonUtils {

    /**
     *  判断字符串是否是 double类型
     * @param num
     * @return
     */
    public static boolean isDouble(String num){
        try{
            Double.valueOf(num);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param list
     * @param len
     * @return
     */
    public static List<List<?>> splitList(List<?> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<?>> result = new ArrayList<List<?>>();

        int size = list.size();
        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {
            List<?> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

}
