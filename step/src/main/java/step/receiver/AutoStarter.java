package step.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import step.StepManager;
import step.StepService;

public class AutoStarter extends BroadcastReceiver {

    private static final String TAG = "AutoStarterLog";
    private static final String ACTIVITY_PREF_KEY = "auto_start_activity_version";
    SharedPreferences prefs;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "@onReceive");
        Intent i = new Intent();
        i.setClass(StepManager.getmContext(), StepService.class);
        StepManager.getmContext().startService(i);

        //PollingUtils.getIns().startPollingService(StepManager.getmContext(), 5, StepService.class, StepService.ACTION);


    }


}