package www.wuhai.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import www.wuhai.common.utils.L;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(LeftFragment.TAG, "MainActivity - onCreate");
        setContentView(R.layout.activity_main);
        //获取FragmentManager
        FragmentManager manager = getFragmentManager();
        //开启Fragment事务
        FragmentTransaction beginTransaction = manager.beginTransaction();
        //将两个Fragment创建出来
        LeftFragment leftFragment=new LeftFragment();
        RightFragment rightFragment=new RightFragment();
        //将两个Fragment加入到Fragment事务中
        beginTransaction.add(leftFragment, "left");
        beginTransaction.add(rightFragment, "right");
        //提交事务
        beginTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e(LeftFragment.TAG, "MainActivity - onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.e(LeftFragment.TAG, "MainActivity - onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e(LeftFragment.TAG, "MainActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.e(LeftFragment.TAG, "MainActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.e(LeftFragment.TAG, "MainActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e(LeftFragment.TAG, "MainActivity - onDestroy");
    }
}
