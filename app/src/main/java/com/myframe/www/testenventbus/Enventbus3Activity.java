package com.myframe.www.testenventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class Enventbus3Activity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Enventbus3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enventbus3);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                Enventbus2Activity.startActivity(this);
                break;
        }
    }

    @Subscribe
    public void showEvnent1(BaseEvent baseEvent){
        L.e(EnventbusActivity.TAG, "showEvnent1");
        String event = baseEvent.getEventStr();
        switch (event){
            case "event1":
                L.e(EnventbusActivity.TAG, "Enventbus3Activity收到event1事件");
                break;
            case "event2":
                L.e(EnventbusActivity.TAG, "Enventbus3Activity收到event2事件");
                break;
            case "event3":
                L.e(EnventbusActivity.TAG, "Enventbus3Activity收到event3事件");
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
