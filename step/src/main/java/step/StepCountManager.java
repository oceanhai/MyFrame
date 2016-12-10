package step;

import android.content.ContentValues;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import step.data.StepDBManager;
import step.db.DateBaseHelper;
import step.model.StepModel;
import step.util.SPUtils;


/**
 * 加速传感器和计步传感器 获得的计步数都放在这个类，通过get  set进行操作
 */
public class StepCountManager {

    private static final String TAG = "StepCounterManager";
    private static StepCountManager mManager;
    private volatile int StepCountFromSensorAcc;//加速传感器得到的计步数据
    private volatile int StepCountFromSensorStep;//计步传感器得到的计步数据
    private volatile int StepCountFromDB = 0;//从数据库中读取出的数据
    private volatile int recordtepCountFromSensorStep = 0;//从传感器读取出的数据
    private String currentDate = "";
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private long currentTime = 0;


    private StepCountManager() {

    }

    public static StepCountManager getIns() {
        if (mManager == null) {
            mManager = new StepCountManager();
        }

        return mManager;
    }

    public int getStepCountFromSensorAccInMemory() {
        return StepCountFromSensorAcc;
    }

    public void setStepCountFromSensorAcc(final int stepCountFromSensorAcc) {
        StepCountFromSensorAcc = stepCountFromSensorAcc;
    }

    public int getStepCountFromSensorStepInMemory() {
        return StepCountFromSensorStep;
    }

    public void setStepCountFromSensorStep(final int stepCountFromSensorStep) {

        recordtepCountFromSensorStep = stepCountFromSensorStep;
        /**
         * 只有日期没有发生变化的时候 才会去sp里取x轴速度传感器 产生的计步数
         */
        if (StepManager.isLogined() && !dateChanged()) {
            int ignoreCount = (int) SPUtils.get(StepConstants.restartCount, 0);
            Log.d("StepCounterManager", " 锚点值 " + ignoreCount + "        DB返回的值: " + StepCountFromDB);

            //如果计步传感器返回的值，小于数据库中存储的值，说明手机重启了，需要把这两个值相加
            if (stepCountFromSensorStep < StepCountFromDB) {

                //如果sp中的锚点大于传感器返回的值，说明这个锚点是在重启手机之前写入的，没有意义，重置
                if (ignoreCount > stepCountFromSensorStep) {
                    Log.d("StepCounterManager", "设置sp   setStepCountFromSensorStep  " + 0);

                    SPUtils.put(StepConstants.restartCount, 0);
                    ignoreCount = 0;
                }

                //sp中存储的锚点值如果大于0.需要做差值，然后在相加，过滤掉未登录状态时，记录下来的那些数据
                if (ignoreCount > 0) {
                    Log.d("StepCounterManager", "3");
                    StepCountFromSensorStep = StepCountFromDB + stepCountFromSensorStep - ignoreCount;
                } else {
                    Log.d("StepCounterManager", "4");
                    StepCountFromSensorStep = StepCountFromDB + stepCountFromSensorStep;
                }

            } else {//手机没有重启

                //如果sp中的锚点大于传感器返回的值，说明这个锚点是在重启手机之前写入的，没有意义，重置
                if (ignoreCount > stepCountFromSensorStep) {
                    Log.d("StepCounterManager", "设置sp   setStepCountFromSensorStep  " + 0);

                    SPUtils.put(StepConstants.restartCount, 0);
                    ignoreCount = 0;
                }else{
                    //如果sp中的锚点小于传感器返回的值，并且两个值得差值很巨大，
                    //说明传感器返回的数据异常或者是app的数据被人为的清理过(目前先用5w 作为阀值),
                    // 将这个sensoe返回的值，作为新的锚点值，写入sp，重新计算
                    if(Math.abs(ignoreCount-stepCountFromSensorStep)>50000){
                        Log.d("StepCounterManager", "sensor 返回的数据与锚点值相差超过5w，被认定为数据异常  " + 0);
                        StepCountFromSensorStep=0;
                        ignoreCount=StepCountFromSensorStep;
                        SPUtils.put(StepConstants.restartCount, ignoreCount);

                    }else{
                        //sp中存储的锚点值如果大于0.需要做差值，然后在相加，过滤掉未登录状态时，记录下来的那些数据
                        if (ignoreCount > 0) {
                            Log.d("StepCounterManager", "1");
                            StepCountFromSensorStep = StepCountFromDB + stepCountFromSensorStep - ignoreCount;
                        } else {
                            Log.d("StepCounterManager", "2");

                            StepCountFromSensorStep = stepCountFromSensorStep;
                        }
                    }


                }



            }
        } else {
            Log.d("StepCounterManager", "设置sp   setStepCountFromSensorStep  else  " + stepCountFromSensorStep);
            setSpValue(recordtepCountFromSensorStep);
        }

    }

    //设置sp的锚点值
    private void setSpValue(int value) {
        if (value != 0) {
            SPUtils.put(StepConstants.restartCount, value);

        }

    }

    //将内存中的sensorStep  传感器返回的值写入sp中，下次步数只记录增加的部分，而不是从头计算，避免重复的取值
    public void writeSensorStepToSp() {

        Log.d("StepCounterManager", "设置sp   writeSensorStepToSp    " + recordtepCountFromSensorStep);
        setSpValue(recordtepCountFromSensorStep);

        StepCountFromDB = getStepCount();
        Log.d("StepCounterManager", "writeSensorStepToSp");

    }

    /**
     * 更新当前内存中的
     * StepCountFromSensorAcc，StepCountFromSensorStep，StepCountFromDB
     *
     * @param count
     */
    public void setStepCountFromDB(int count) {
        Log.d("StepCounterManager", "setStepCountFromDB");
        StepCountFromSensorAcc = count;
        StepCountFromSensorStep = count;
        StepCountFromDB = count;
    }


    public void clearStepCount() {

        onlyClearData();
        StepManager.uid = "";
    }

    //清除内存中的数据
    public void onlyClearData() {
        Log.d("StepCounterManager", "设置sp   clearStepCount  " + recordtepCountFromSensorStep);
        setSpValue(recordtepCountFromSensorStep);

        StepCountFromSensorStep = 0;
        StepCountFromSensorAcc = 0;

    }


    /**
     * 加速传感器变化（大于一定阈值） 调用
     * ※ 所以内存中的数据不一定会即使清楚，需要的地方要在单独调用一下清除方法
     */
    public void addStepCountForSensorAcc() {

        if (!StepManager.isLogined()) {
            return;
        }
        //如果手机日期发生变更，清空内存中的数据，重新计步
        if (!currentDate.equals("")) {
            if (dateChanged()) {//日期发生变化
                clearMemory();

                Log.d("StepCounterManager", "设置sp   addStepCountForSensorAcc  " + recordtepCountFromSensorStep);
                setSpValue(recordtepCountFromSensorStep);
                currentDate = format.format(new Date(System.currentTimeMillis()));

//                /**
//                 * 查看DB中，当前时间的步数
//                 */
//                int stepNum = DateBaseHelper.getInstance(StepManager.getmContext()).getStepNumByDate(currentDate);
//                if(stepNum > 0){
//                    setStepCountFromDB(stepNum);
//                }else{
//                    //插入一条 新的空数据 根据日期
//                    ContentValues values = new ContentValues();
//                    values.put(StepModel.userid, StepManager.getUID());
//                    values.put(StepModel.date, format.format(new Date(System.currentTimeMillis())));
//                    values.put(StepModel.isUpload, 0);
//                    values.put(StepModel.stepCount, StepCountManager.getIns().getStepCount());
//                    values.put(StepModel.isUploadPoints, 0);
//                    DateBaseHelper.getInstance(StepManager.getmContext()).insertDate(values);
//                }

                /**
                 * 查看DB中是否有数据
                 */
                if (StepDBManager.getIns().isHaveDataFromDB()) {
                    setStepCountFromDB(DateBaseHelper.getInstance(StepManager.getmContext()).getStepNumByDate(currentDate));
                } else {
                    //插入一条 新的空数据 根据日期
                    ContentValues values = new ContentValues();
                    values.put(StepModel.userid, StepManager.getUID());
                    values.put(StepModel.date, format.format(new Date(System.currentTimeMillis())));
                    values.put(StepModel.isUpload, 0);
                    values.put(StepModel.stepCount, StepCountManager.getIns().getStepCount());
                    values.put(StepModel.isUploadPoints, 0);
                    DateBaseHelper.getInstance(StepManager.getmContext()).insertDate(values);
                }
            }
        } else {
            currentDate = format.format(new Date(System.currentTimeMillis()));
        }

        //阀值比较灵敏,所以再加一个时间的判断,如果时间差距大于一秒,认为是走了一步
        if (currentTime != 0) {

            /**
             * 把差值做绝对值后进行判断，解决系统日期前调不能计数问题
             */
            if (Math.abs(System.currentTimeMillis() - currentTime) >= 500) {
                //Log.e(TAG, "============add==========");
                //Vibrator vibrator = (Vibrator) ServicePlatformApp.getAppContext()
                // .getSystemService(ServicePlatformApp.getAppContext().VIBRATOR_SERVICE);
                //                        vibrator.vibrate(200);

                this.StepCountFromSensorAcc = StepCountFromSensorAcc + 1;
                currentTime = System.currentTimeMillis();
            }
        } else {
            currentTime = System.currentTimeMillis();
        }
    }

    /**
     * 清除内存 记录信息
     */
    public void clearMemory() {
        this.StepCountFromSensorAcc = 0;
        this.StepCountFromSensorStep = 0;
        this.StepCountFromDB = 0;
        SPUtils.put(StepConstants.restartCount, 0);
    }

    //两个感应器，哪一个获取的值大 就用哪一个的值
    public int getStepCount() {
        if (StepCountFromSensorAcc == 0 && StepCountFromSensorStep == 0) {
            StepDBManager.getIns()
                    .getStepCountFromDB();
        }

        if (StepCountFromSensorAcc > StepCountFromSensorStep) {
            Log.d(TAG, "acc" + "   StepCountFromSensorAcc " + StepCountFromSensorAcc + "   StepCountFromSensorStep "
                    + StepCountFromSensorStep);
            return StepCountFromSensorAcc;
        }

        if (StepCountFromSensorAcc < StepCountFromSensorStep) {
            Log.d(TAG, "stepSen" + "   StepCountFromSensorAcc " + StepCountFromSensorAcc + "   " +
                    "StepCountFromSensorStep " + StepCountFromSensorStep);
            return StepCountFromSensorStep;
        }

        return StepCountFromSensorAcc;
    }

    /**
     * 获取 内存当前日期
     *
     * @return
     */
    public String getCurrentDate() {
        return currentDate;
    }

    /**
     * 当前系统日期 设定
     */
    public void setCurrentDate() {
        this.currentDate = format.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 日期发生变换
     *
     * @return
     */
    public boolean dateChanged() {
        return !getCurrentDate().equals(format.format(new Date(System.currentTimeMillis())));
    }
}


























