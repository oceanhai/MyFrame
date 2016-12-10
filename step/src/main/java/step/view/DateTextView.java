package step.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.largeimg.stepapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by DongJr on 2016/5/27.
 */
public class DateTextView extends FrameLayout {

    TextView tvDay;
    TextView tvWeek;

    public DateTextView(Context context) {
        super(context);
        init(context);
    }

    public DateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_date_textview, this);
        tvDay = (TextView) view.findViewById(R.id.tv_day);
        tvWeek = (TextView) view.findViewById(R.id.tv_week);
    }

    public void setDate(String strDate, String week) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(strDate);
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
            String day = df.format(date);
            tvDay.setText(day);
            tvWeek.setText(week);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setTextSize(int textSize) {
        tvDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvWeek.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setTextColor(int color) {
        tvDay.setTextColor(color);
        tvWeek.setTextColor(color);
    }

}
