package com.myframe.www.getui;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.myframe.www.FloatViewActivity;
import com.myframe.www.MainActivity;
import com.myframe.www.R;
import com.myframe.www.ShowEdittextActivity;
import com.myframe.www.base.BaseActivity;
import com.myframe.www.utils.NotifycationManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PushReceiver extends BroadcastReceiver {

//    { type=1,"ticker": "我是一个通知","title": "寻医问药","content": "点我打开应用" }

    /**
     * ticker:状态栏的点击文本，这个就是那个从屏幕最上方往下弹的一个提示性通知，的文字内容
     * title：通知标题
     * content：通知内容
     */

    @Override
    public void onReceive(final Context context, Intent intent) {


        Bundle bundle = intent.getExtras();
        Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));

        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            // 获取透传信息
            case PushConsts.GET_MSG_DATA:

                    byte[] payload = bundle.getByteArray("payload");
                    String taskid = bundle.getString("taskid");
                    String messageid = bundle.getString("messageid");

                    boolean result = PushManager.getInstance().sendFeedbackMessage(
                            context, taskid, messageid, 90001);

                    if (payload != null) {
                        String data = new String(payload);

                        Log.d("GetuiSdkDemo", "receiver payload : " + data);
//                        if (isApplicationBroughtToBackground(context)) {
//                            //非当前应用
//                            try {
//                                JSONObject json = new JSONObject(data);
//                                String ticker = json.getString("ticker");
//                                String title = json.getString("title");
//                                String content = json.getString("content");
//                                NotificationManager manager = (NotificationManager) context
//                                        .getSystemService(Context.NOTIFICATION_SERVICE);
//                                Notification notification = new Notification(
//                                        R.mipmap.ic_launcher, ticker,
//                                        System.currentTimeMillis());
//                                notification.flags = Notification.FLAG_AUTO_CANCEL;
//                                notification.defaults = Notification.DEFAULT_ALL;
//                                Intent notificationIntent = new Intent(context,
//                                        MainActivity.class);
//                                PendingIntent pendingIntent = PendingIntent
//                                        .getActivity(context, 0,
//                                                notificationIntent,
//                                                PendingIntent.FLAG_UPDATE_CURRENT);
//                                notification.setLatestEventInfo(context, title,
//                                        content, pendingIntent);
//                                manager.notify(0, notification);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        } else {
                            //当前应用 TODO 按需求进行操作
//                            Intent intent1 = new Intent(context,FloatViewActivity.class);
//                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            context.startActivity(intent1);
//                            if(BaseActivity.getActivitySize() > 1){
                                try {
                                    JSONObject json = new JSONObject(data);
                                    String type = json.getString("type");
                                    String ticker = json.getString("ticker");
                                    String title = json.getString("title");
                                    String content = json.getString("content");
                                    String parameter = json.getString("parameter");
                                    if(TextUtils.isEmpty(type)){
                                        NotifycationManager.getInstance().sendNotifycation(context, ticker, title, content);
                                    }else if("0".equals(type)){
//                                        if(isAppAlive(context, "com.myframe.www")){//应用是否启动
//                                            NotifycationManager.getInstance().sendNotifycation(context, ticker, title, content, ShowEdittextActivity.class, parameter);
//                                        }else{
                                            NotifycationManager.getInstance().sendNotifycation(context, ticker, title, content, parameter);
//                                        }
                                    }else if("1".equals(type)){

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                            }

//                        }
                    }
                break;

            case PushConsts.GET_CLIENTID:
                String cid = bundle.getString("clientid");
                break;

            case PushConsts.THIRDPART_FEEDBACK:
			/*
			 * String appid = bundle.getString("appid"); String taskid =
			 * bundle.getString("taskid"); String actionid =
			 * bundle.getString("actionid"); String result =
			 * bundle.getString("result"); long timestamp =
			 * bundle.getLong("timestamp");
			 *
			 * Log.d("GetuiSdkDemo", "appid = " + appid); Log.d("GetuiSdkDemo",
			 * "taskid = " + taskid); Log.d("GetuiSdkDemo", "actionid = " +
			 * actionid); Log.d("GetuiSdkDemo", "result = " + result);
			 * Log.d("GetuiSdkDemo", "timestamp = " + timestamp);
			 */
                break;

            default:
                break;
        }
    }

    /**
     * 判断程序是否 处于后台
     * @return true 程序被置为后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * //TODO 这个方法不靠谱
     * 判断应用是否已经启动
     * @param context 一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName){
        ActivityManager activityManager =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for(int i = 0; i < processInfos.size(); i++){
            if(processInfos.get(i).processName.equals(packageName)){
                Log.i("NotificationLaunch",
                        String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }

}
