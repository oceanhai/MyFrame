package step.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(strDate, new ParsePosition(0));
    }

    public static String getDateString(Date date, String separate) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) + separate + (c.get(Calendar.MONTH) + 1) + separate + c.get(Calendar.DAY_OF_MONTH);
    }

    public static String getWeek(String strDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(strDate);
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String week = sdf.format(date);
            return week;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static int getWeekIndex(String date) {
        //获取当前日期星期,周日为1,周一为2
        int week = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            week = calendar.get(Calendar.DAY_OF_WEEK);
            if (week == 1) {
                week = 6;
            } else {
                week = week - 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return week;
    }


}
