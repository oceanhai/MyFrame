package www.wuhai.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}
