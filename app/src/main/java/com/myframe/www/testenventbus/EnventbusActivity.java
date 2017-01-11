package com.myframe.www.testenventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myframe.www.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

public class EnventbusActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = EnventbusActivity.class.getSimpleName();

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.textview01)
    TextView textview01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, EnventbusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enventbus);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Subscribe
    public void showEvnent1(BaseEvent baseEvent){
        L.e(TAG, "showEvnent1");
        String event = baseEvent.getEventStr();
        switch (event){
            case "event1":
                L.e(TAG, "收到event1事件");
                textview01.setText(event);
                break;
            case "event2":
                L.e(TAG, "收到event2事件");
                textview01.setText(event);
                break;
            case "event3":
                L.e(TAG, "收到event3事件");
                textview01.setText(event);
                break;
        }

    }

    @Subscribe
    public void showEvent2(BaseEvent baseEvent){
        L.e(TAG, "showEvnent2");
        String event = baseEvent.getEventStr();
        switch (event){
            case "listevent":
                L.e(TAG, "收到listevent事件");
                ListEvent listEvent = (ListEvent) baseEvent;
                StringBuilder stringBuilder = new StringBuilder();
                List<String> mdata = listEvent.getmData();
                for(int x=0;x<mdata.size();x++){
                    stringBuilder.append(mdata.get(x)).append(" ");
                }
                textview01.setText(stringBuilder.toString());
                break;
        }
    }

    @Subscribe
    public void showEvent3(Event1 event1){
        L.e(TAG, "非继承base event");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                Enventbus1Activity.startActivity(this);
                break;
            case R.id.btn02:
                Enventbus2Activity.startActivity(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
