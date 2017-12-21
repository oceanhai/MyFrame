package www.wuhai.logoupdate;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 当是使用默认的主入口时，app可以正常升级。当是使用B子入口时，
 * app就无法正常升级了，如果想升级，在升级之前要把入口切换会主入口，不然直接升级失败
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn01;
    private Button btn02;
    private Button btn03;

    private ComponentName mDefault;
    private ComponentName mDouble11;
    private ComponentName mDouble12;
    private PackageManager mPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = (Button) findViewById(R.id.bt01);
        btn02 = (Button) findViewById(R.id.bt02);
        btn03 = (Button) findViewById(R.id.bt03);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);

//        mDefault = getComponentName();
        mDefault = new ComponentName(
                getBaseContext(),
                "www.wuhai.logoupdate.MainActivity");
        mDouble11 = new ComponentName(
                getBaseContext(),
                "www.wuhai.logoupdate.Test11");
        mDouble12 = new ComponentName(
                getBaseContext(),
                "www.wuhai.logoupdate.Test12");
        mPm = getApplicationContext().getPackageManager();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt01:
                disableComponent(mDefault);
                disableComponent(mDouble12);
                enableComponent(mDouble11);
                break;
            case R.id.bt02:
                disableComponent(mDefault);
                disableComponent(mDouble11);
                enableComponent(mDouble12);
                break;
            case R.id.bt03:
                enableComponent(mDefault);
                disableComponent(mDouble11);
                disableComponent(mDouble12);
                break;
        }
    }

    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
