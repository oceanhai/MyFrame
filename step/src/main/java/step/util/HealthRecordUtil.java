package step.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 画折线图的时候，需要的一些工具函数
 */
public class HealthRecordUtil {


    public static void getWeekDate(ArrayList<String> weeklist, ArrayList<String> dateList) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");//天 例：28
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");//星期几 例：周一

        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 31; i++) {
            Date d = new Date(currentTime - i * (1000L * 60 * 60 * 24));

            weeklist.add(dateFormat.format(d));
            dateList.add(sdf.format(d));
            //Log.d("yu", dateFormat.format(d) + "  " + sdf.format(d));
        }

        Collections.reverse(weeklist);
        Collections.reverse(dateList);
    }

    public static LinkedHashMap<String, Float> getWeekDateHashMap() {

        LinkedHashMap<String, Float> map = new LinkedHashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 31; i++) {
            Date d = new Date(currentTime - i * (1000L * 60 * 60 * 24));

            map.put(dateFormat.format(d), (float) 0);
            //Log.d("yu", dateFormat.format(d) );
        }

        return map;
    }

    public static String getTomorrowTimeStamp() {

        HashMap<String, Float> map = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long currentTime = System.currentTimeMillis();
        Date d = new Date(currentTime + (1000L * 60 * 60 * 24));

        //Log.d("yu", dateFormat.format(d) );

        return dateFormat.format(d) + " 00:00:00";
    }

    //获取一个月之前的时间戳
    public static String getMonthAgoTimeStamp() {

        HashMap<String, Float> map = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long currentTime = System.currentTimeMillis();
        Date d = new Date(currentTime - 30 * (1000L * 60 * 60 * 24));

        //Log.d("yu", dateFormat.format(d) );

        return dateFormat.format(d) + " 00:00:00";
    }
}
