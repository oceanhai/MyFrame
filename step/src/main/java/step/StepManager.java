package step;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import step.db.DateBaseHelper;

/**
 * Created by yuwentao on 16/6/29.
 */
public class StepManager {

    public static Context mContext;

    public static String uid;

    private StepManager mStepManager;

    private StepManager() {

    }

    public static void init(Context c, String Userid) {
        mContext = c;
        uid = Userid;
        DateBaseHelper.getInstance(c);

        Intent i = new Intent();
        i.setClass(StepManager.getmContext(), StepService.class);
        StepManager.getmContext().startService(i);

    }

    public static Context getmContext() {
        return mContext;
    }

    public static String getUID() {
        return uid;
    }

    public static boolean isLogined() {
        //user_id = all代表手机记录数据

        if (TextUtils.isEmpty(uid)) {
            return false;
        }

        return true;
    }

    public StepManager getIns() {
        if (mStepManager == null) {
            mStepManager = new StepManager();
        }

        return mStepManager;
    }

}
