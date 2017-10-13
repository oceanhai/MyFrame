package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.myframe.www.utils.SilentInstallAndUninstall;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InstallAndUnloadActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.unload)
    Button unload;
    @Bind(R.id.activity_install_and_unload)
    RelativeLayout activityInstallAndUnload;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InstallAndUnloadActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_and_unload);
        ButterKnife.bind(this);

        unload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.unload:
//            //卸载应用程序
//            Intent uninstall_intent = new Intent();
//            uninstall_intent.setAction(Intent.ACTION_DELETE);
//            uninstall_intent.setData(Uri.parse("package:"+"com.lecuntao.home.pos"));
//            startActivity(uninstall_intent);
                SilentInstallAndUninstall.uninstall("com.lecuntao.home.pos");
            break;
        }
    }
}
