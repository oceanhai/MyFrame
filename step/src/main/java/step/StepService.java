/*
 *  Pedometer - Android App
 *  Copyright (C) 2009 Levente Bagi
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package step;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.largeimg.stepapp.R;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import step.data.StepDBManager;
import step.receiver.AutoStarter;


public class StepService extends Service {


    public static final String ACTION = "com.service.platform.StepService";
    private static final String TAG = "StepService";
    private final static int GRAY_SERVICE_ID = 10;
    private Sensor mSensor;
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private SensorManager mSensorManager;
    //间隔多久，向数据库中更新当前走步的数据
    private int timenterval = 60;//单位秒
    private StepDetector mStepDetector;

    private StepCounterDetector mStepDetector2;

    private PowerManager.WakeLock wakeLock;
    // BroadcastReceiver for handling ACTION_SCREEN_OFF.
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Check action just to be on the safe side.
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                // Unregisters the listener and registers it again.
                StepService.this.unregisterDetector();
                StepService.this.registerDetector();
                wakeLock.release();
                acquireWakeLock();
            }
        }
    };

    @Override
    public void onCreate() {
        Log.i(TAG, "[SERVICE] onCreate");
        super.onCreate();

        init();

    }

    private void init() {

        // Start detecting
        mStepDetector = StepDetector.getIns();
        mStepDetector2 = StepCounterDetector.getIns();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        registerDetector();

        // Register our receiver for the ACTION_SCREEN_OFF action. This will make our receiver
        // code be called whenever the phone enters standby mode.
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);

        acquireWakeLock();

        new Thread() {
            @Override
            public void run() {
                StepDBManager.getIns()
                        .getStepCountFromDB();
            }
        }.start();


        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        StepDBManager.getIns()
                                .writeStepToDb();
                        Thread.sleep(timenterval * 1000);
                        //                        Thread.sleep(1000);
                        //                        处理震动
                        //                                                Vibrator vibrator = (Vibrator)
                        // ServicePlatformApp.getAppContext()
                        //                         .getSystemService(ServicePlatformApp.getAppContext()
                        // .VIBRATOR_SERVICE);
                        //                                                vibrator.vibrate(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT < 18) {

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.step_launcher)
                            .setContentTitle("健康邢台")
                            .setContentText("健康邢台正在为您计步");

            Notification notification = mBuilder.build();

            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, StepCountActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

            notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
            mBuilder.setContentIntent(contentIntent);
            startForeground(GRAY_SERVICE_ID, notification);//API < 18 ，此方法能有效隐藏Notification上的图标
        } else {
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }

        return Service.START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "[SERVICE] onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "[SERVICE] onDestroy");

        // Unregister our receiver.
        unregisterDetector();

        // 添加自启动功能
        startAutoAlarm();

        wakeLock.release();

        super.onDestroy();


    }

    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    public void registerDetector() {
        if (mSensorManager != null && mStepDetector != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(mStepDetector, mSensor, SensorManager.SENSOR_DELAY_FASTEST);

            // Step Detector
            mSensorManager.registerListener(mStepDetector2, mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_UI);
        }

    }

    private void unregisterDetector() {
        if (mSensorManager != null && mStepDetector != null) {
            mSensorManager.unregisterListener(mStepDetector);
        }

        unregisterDetector2();
    }

    public void unregisterDetector2() {
        mSensorManager.unregisterListener(mStepDetector2);

        Log.d(TAG, "unregisterDetector2");
    }

    // 开启自启动闹钟
    private void startAutoAlarm() {
        //操作：发送一个广播, 启动AutoStarter
        Intent intent = new Intent(StepService.this, AutoStarter.class);
        intent.setAction("com.renren.mobile.android.ACTION_AUTOSTARTER");
        PendingIntent autoStart = PendingIntent.getBroadcast(StepService.this, 0, intent, 0);

        //设定一个五秒后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);

        AlarmManager alarmManager = (AlarmManager) StepManager.getmContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), autoStart);
    }

    private void acquireWakeLock() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        int wakeFlags;
        wakeFlags = PowerManager.PARTIAL_WAKE_LOCK;
        wakeLock = pm.newWakeLock(wakeFlags, TAG);
        wakeLock.acquire();
    }

    public static class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(final Intent intent) {
            return null;
        }

    }


}

