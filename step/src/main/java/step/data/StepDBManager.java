package step.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.xywy.component.datarequest.neworkWrapper.IDataResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import step.StepCountManager;
import step.StepManager;
import step.db.DateBaseHelper;
import step.model.StepDBModel;
import step.model.StepModel;

/**
 * Created by yuwentao on 16/5/23.
 */
public class StepDBManager {

    private static final String TAG = "StepService";

    static StepDBManager mStepDBManager;

    private volatile boolean mIsUploadPoints = false;

    private IDataResponse mIDataResponse;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private StepDBManager() {
        format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));// 中国北京时间，东八区
    }


    public static StepDBManager getIns() {
        if (mStepDBManager == null) {
            mStepDBManager = new StepDBManager();
        }

        return mStepDBManager;
    }

    public boolean isUploadPoints() {
        return mIsUploadPoints;
    }


    //将数据库中的步数，加载进内存
    public int getStepCountFromDB() {

        if (!StepManager.isLogined()) {
            return 0;
        }

        try {

            String sql = "SELECT " + StepModel.stepCount + "," + StepModel.isUploadPoints + " FROM " +
                    StepModel.table_name + " WHERE " +
                    StepModel.userid + " = '" + StepManager.getUID() + "' AND " + StepModel.date + " = " +
                    "'" + format.format(new Date(System.currentTimeMillis())) + "'";

            String[] obj = {};
            Cursor cursor = DateBaseHelper.getInstance(StepManager.getmContext())
                    .rawQuery(sql, obj);

            int count = 0;
            int isUploadPoints = 0;
            while (cursor.moveToNext()) {

                count = cursor.getInt(cursor.getColumnIndex(StepModel.stepCount));

                isUploadPoints = cursor.getInt(cursor.getColumnIndex(StepModel.isUploadPoints));

                //if (StepCountManager.getIns()
                //        .getStepCountFromSensorAccInMemory() == 0 && StepCountManager.getIns()
                //        .getStepCountFromSensorStepInMemory() == 0) {
                //
                //}
            }

            StepCountManager.getIns().setStepCountFromDB(count);

            mIsUploadPoints = isUploadPoints == 1;


            Log.d("StepDetector", count + "----------query");
            Log.d("StepCounterManager", "执行了---------getStepCountFromDB" + "  count " + count + "  uid: " + StepManager.getUID());
            cursor.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    //将内存中的步数写入数据库
    public void writeStepToDb() {
        new Thread() {
            @Override
            public void run() {
                if (!StepManager.isLogined()) {
                    return;
                }
                //                insertDate();

                StepCountManager.getIns().writeSensorStepToSp();


                String[] obj = {};

//                //写入的时候，判断表里面是否已经存在这条记录，如果不存在则插入，存在则修改
//                String select_sql = "SELECT " + StepModel.stepCount + " FROM " + StepModel.table_name + " WHERE " +
//                        StepModel.date + " = " + "'" + format.format(new Date(System.currentTimeMillis())) + "'" + " " +
//                        "AND " + StepModel.userid + " = '" + StepManager.getUID() + "'";
//
//                Cursor cursor = DateBaseHelper.getInstance(StepManager.getmContext())
//                        .rawQuery(select_sql, obj);
//
//                //判断数据库中是否已经含有这条数据
//                boolean isHasItem = false;
//                while (cursor.moveToNext()) {
//                    //这里更新的步数需要做判断,库中的值大于当前值,进行赋值操作
//                    //解决stepCount重新初始化,将原有数据清0继续入库的问题
//                    int dbStepCount = cursor.getInt(cursor.getColumnIndexOrThrow(StepModel.stepCount));
//
//                    if (dbStepCount >= StepCountManager.getIns().getStepCount()) {
//                        StepCountManager.getIns().setStepCountFromDB(dbStepCount);
//                    }
//                    isHasItem = true;
//                }
//                cursor.close();

                String sql;

                if (isHaveDataFromDB()) {

                    sql = "UPDATE " + StepModel.table_name + " SET " + StepModel.stepCount + " = " + StepCountManager.getIns().getStepCount() + " " +
                            " WHERE " + StepModel.userid + " = '" + StepManager.getUID() + "' AND " + StepModel.date
                            + " = " +
                            "'" + format.format(new Date(System.currentTimeMillis())) + "'";

                    //Log.d("StepDetector", StepCounterManager.getIns().getStepCount() + "----------write   " + sql);

                    DateBaseHelper.getInstance(StepManager.getmContext())
                            .execSQL(sql, obj);
                } else {
                    /**
                     * 每次插入数据前 都要对StepCountManager数据清0
                     * ※插入数据，肯定是因为日期放生变换
                     */
                    StepCountManager.getIns().clearMemory();

                    ContentValues values = new ContentValues();
                    values.put(StepModel.userid, StepManager.getUID());
                    values.put(StepModel.date, format.format(new Date(System.currentTimeMillis())));
                    values.put(StepModel.isUpload, 0);
                    values.put(StepModel.stepCount, StepCountManager.getIns().getStepCount());
                    values.put(StepModel.isUploadPoints, 0);
                    Long id = DateBaseHelper.getInstance(StepManager.getmContext())
                            .getwDB()
                            .insert(StepModel.table_name, null, values);

                    Log.d("StepDetector", StepCountManager.getIns().getStepCount() + "----------insert   " + id);
                }


            }
        }.start();
    }

    //得到最近一个月的数据
    public ArrayList<StepDBModel> getMonthData() {

        String todayDate = format.format(new Date(System.currentTimeMillis()));

        HashMap<String, Integer> map = new HashMap<>();
        String[] obj = {};
        String sql = "SELECT * FROM " + StepModel.table_name + " WHERE " + StepModel.userid + " = '" + StepManager
                .getUID() + "' AND " + StepModel.date + "<= '" + todayDate + "' order by" + " " + StepModel.date +
                " DESC " + " LIMIT 30";

        Cursor cursor = DateBaseHelper.getInstance(StepManager.getmContext())
                .rawQuery(sql, obj);
        ArrayList<StepDBModel> arrayList = new ArrayList<>();
        if (cursor.moveToLast()) {
            while (!cursor.isBeforeFirst()) {
                StepDBModel stepModel = new StepDBModel();
                stepModel.setIsupload(cursor.getInt(cursor.getColumnIndex(StepModel.isUpload)));
                stepModel.setDate(cursor.getString(cursor.getColumnIndex(StepModel.date)));
                stepModel.setStepCount(cursor.getInt(cursor.getColumnIndex(StepModel.stepCount)));
                stepModel.setUserid(cursor.getString(cursor.getColumnIndex(StepModel.userid)));

                map.put(stepModel.getDate(), stepModel.getStepCount());
                arrayList.add(stepModel);
                cursor.moveToPrevious();
            }
        }

        cursor.close();

        return arrayList;
    }

    public int getDateCount() {

        long dayCount = 0;
        try {
            String sql = "SELECT " + StepModel.date + " FROM " + StepModel.table_name + " WHERE " +
                    StepModel.userid + " = '" + StepManager.getUID() + "' order by " +
                    StepModel.date + " ASC " +
                    " LIMIT 1";
            Cursor cursor = DateBaseHelper.getInstance(StepManager.getmContext())
                    .rawQuery(sql, new String[]{});

            int dayTime = 1000 * 60 * 60 * 24;
            if (cursor.moveToFirst()) {
                String date = cursor.getString(cursor.getColumnIndexOrThrow(StepModel.date));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date preDate = null;
                preDate = dateFormat.parse(date);
                long preDateTime = preDate.getTime();
                dayCount = (System.currentTimeMillis() - preDateTime) / dayTime;
                dayCount = dayCount > 30 ? 30 : dayCount;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) dayCount;
    }

    /**
     * 以系统日期查询数据库 看是否存在
     *
     * @return
     */
    public boolean isHaveDataFromDB() {
        String[] obj = {};

        //写入的时候，判断表里面是否已经存在这条记录，如果不存在则插入，存在则修改
        String select_sql = "SELECT " + StepModel.stepCount + " FROM " + StepModel.table_name + " WHERE " +
                StepModel.date + " = " + "'" + format.format(new Date(System.currentTimeMillis())) + "'" + " " +
                "AND " + StepModel.userid + " = '" + StepManager.getUID() + "'";

        Cursor cursor = DateBaseHelper.getInstance(StepManager.getmContext())
                .rawQuery(select_sql, obj);

        //判断数据库中是否已经含有这条数据
        boolean isHasItem = false;
        while (cursor.moveToNext()) {
            //这里更新的步数需要做判断,库中的值大于当前值,进行赋值操作
            //解决stepCount重新初始化,将原有数据清0继续入库的问题
            int dbStepCount = cursor.getInt(cursor.getColumnIndexOrThrow(StepModel.stepCount));

            if (dbStepCount >= StepCountManager.getIns().getStepCount()) {
                StepCountManager.getIns().setStepCountFromDB(dbStepCount);
            }
            isHasItem = true;
        }
        cursor.close();

        return isHasItem;
    }

}
