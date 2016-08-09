package com.myframe.www.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.myframe.www.MainActivity;
import com.myframe.www.R;

/** 
* 通知管理类
* @author wuhai
* create at 2016/4/28 16:06
*/  
public class NotifycationManager {

    private static NotifycationManager inst;

    private NotifycationManager() {

    }

    public static NotifycationManager getInstance() {
        if (inst == null) {
            inst = new NotifycationManager();
        }
        return inst;

    }

    /**
     * 默认发送的通知，跳转方式（没有办法通过Intent传递参数）
     * 1 应用在前台，点击通知，直接执行跳转操作；
     * 2 应用在后台，将后台进程唤起到前台，执行跳转操作；
     * 3 应用没有启动，启动应用，执行跳转操作；
     * 4 应用在后台被回收，恢复应用，然执行跳转操作
     * @param context
     * @param ticker
     * @param title
     * @param content
     */
    public void sendNotifycation(Context context, String ticker, String title, String content) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(
                R.mipmap.ic_launcher, ticker,
                System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL;//点击后删除
        notification.defaults = Notification.DEFAULT_ALL;//所有默认值

        /**
         *  实现关键Intent属性值
         */
        Intent notificationIntent = new Intent(context,
                MainActivity.class);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        PendingIntent pendingIntent = PendingIntent
                .getActivity(context, 0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(context, title,
                content, pendingIntent);
        manager.notify(0, notification);
    }

    public void sendNotifycation(Context context, String ticker, String title, String content, String parameter) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.mipmap.ic_launcher, ticker, System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL;//点击后删除
        notification.defaults = Notification.DEFAULT_ALL;//所有默认值

        Intent notificationIntent = new Intent(context,
                MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //TODO 跳转到界面的必要参数
        notificationIntent.putExtra("parameter", parameter);

        PendingIntent pendingIntent = PendingIntent
                .getActivity(context, 0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(context, title,
                content, pendingIntent);
        manager.notify(1, notification);
    }

    public void sendNotifycation(Context context, String ticker, String title, String content, Class classz, String parameter) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.mipmap.ic_launcher, ticker, System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL;//点击后删除
        notification.defaults = Notification.DEFAULT_ALL;//所有默认值

        Intent notificationIntent = new Intent(context,
                classz);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //TODO 跳转到界面的必要参数
        notificationIntent.putExtra("parameter", parameter);

        PendingIntent pendingIntent = PendingIntent
                .getActivity(context, 0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(context, title,
                content, pendingIntent);
        manager.notify(1, notification);
    }
}
