package com.myframe.www.testfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *  参考
 *  http://www.tuicool.com/articles/j22E3u
 */
public class FragmentSetArguments1Activity extends AppCompatActivity {

    @Bind(R.id.content)
    FrameLayout content;

    private String flag;

    public static void startActivity(Context context, String flag) {
        Intent intent = new Intent(context, FragmentSetArguments1Activity.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_set_arguments1);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent!=null){
            flag = intent.getStringExtra("flag");
        }

        //TODO 屏幕切换时候savedInstanceState！=null，所以构造传值会调用无参构造方法
        /**
         * savedInstanceState判断
         * 构造传值
         *      竖：构造传值
         *      横：non-param
         * setArguments
         *      竖：setArguments
         *      横：setArguments
         *
         * savedInstanceState不判断
         * 构造传值
         *      竖：构造传值
         *      横：构造传值
         * setArguments
         *      竖：setArguments
         *      横：setArguments
         *
         */
        if (savedInstanceState == null) {
            if("1".equals(flag)){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content, new TestFragment1("构造传值")).commit();
            }else{
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content, TestFragment2.newInstance("setArguments")).commit();
            }
        }

    }

    @SuppressLint("ValidFragment")
    public static class TestFragment1 extends Fragment {

        private String mArg = "non-param";

        /**
         * java.lang.RuntimeException: Unable to start activity ComponentInfo
         * {com.myframe.www/com.myframe.www.testfragment.FragmentSetArguments1Activity}:
         * android.support.v4.app.Fragment$InstantiationException:
         * Unable to instantiate fragment
         * com.myframe.www.testfragment.FragmentSetArguments1Activity$TestFragment1:
         * make sure class name exists, is public, and has an empty constructor that is public
         * //TODO 在横竖屏切换时候必须有无参构造方法
         */
        public TestFragment1() {
            Log.i("INFO", "TestFragment non-parameter constructor");
        }

        @SuppressLint("ValidFragment")
        public TestFragment1(String arg){
            mArg = arg;
            Log.i("INFO", "TestFragment construct with parameter");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment3, container,
                    false);
            TextView tv = (TextView) rootView.findViewById(R.id.fragment3);
            tv.setText(mArg);
            return rootView;
        }
    }


    public static class TestFragment2 extends Fragment {

        private static final String ARG = "arg";

        public TestFragment2() {
            Log. i("INFO", "TestFragment non-parameter constructor");
        }

        public static Fragment newInstance(String arg){
            TestFragment2 fragment = new TestFragment2();
            Bundle bundle = new Bundle();
            bundle.putString( ARG, arg);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment3, container,
                    false);
            TextView tv = (TextView) rootView.findViewById(R.id.fragment3);
            tv.setText(getArguments().getString(ARG));
            return rootView;
        }
    }
}
