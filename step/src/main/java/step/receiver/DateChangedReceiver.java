package step.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import step.StepCountManager;
import step.StepManager;
import step.db.DateBaseHelper;
import step.model.StepModel;


/**
 * Created by yuwentao on 16/5/24.
 */
public class DateChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d("StepDetector", "DateChangedReceiver         ");

        //将在内存中的昨天的数据清零
        StepCountManager.getIns().onlyClearData();

        //创建当天步数数据,保证数据的连续性
        createStepData();
    }

    private void createStepData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));// 中国北京时间，东八区

                //写入的时候，判断表里面是否已经存在这条记录，如果不存在则插入
                String select_sql = "SELECT " + StepModel.stepCount + " FROM " + StepModel.table_name + " WHERE " +
                        StepModel.date + " = " + "'" + format.format(new Date(System.currentTimeMillis())) + "'" + " " +
                        "AND " + StepModel.userid + " = '" + StepManager.getUID() + "'";

                Cursor cursor = DateBaseHelper.getInstance(StepManager.getmContext())
                        .rawQuery(select_sql, new String[]{});

                //判断数据库中是否已经含有这条数据
                boolean isHasItem = false;
                while (cursor.moveToNext()) {
                    isHasItem = true;
                }
                cursor.close();

                if (!isHasItem) {
                    ContentValues values = new ContentValues();
                    values.put(StepModel.userid, StepManager.getUID());
                    values.put(StepModel.date, format.format(new Date(System.currentTimeMillis())));
                    values.put(StepModel.isUpload, 0);
                    values.put(StepModel.isUploadPoints, 0);
                    values.put(StepModel.stepCount, 0);

                    DateBaseHelper.getInstance(StepManager.getmContext())
                            .getwDB()
                            .insert(StepModel.table_name, null, values);
                }
            }
        }
        ).start();
    }
}
