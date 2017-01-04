package com.myframe.www.testfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;
import www.wuhai.common.utils.ToastUtils;

/**
 * 参考
 * http://www.tuicool.com/articles/j22E3u
 */
public class FragmentSetArguments1Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = FragmentSetArguments1Activity.class.getSimpleName();

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.btn01)
    Button btn01;

    private String flag;
    private TestFragment2 fragment2;

    public static void startActivity(Context context, String flag) {
        Intent intent = new Intent(context, FragmentSetArguments1Activity.class);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(TAG, "Activity onCreate()");
        setContentView(R.layout.activity_fragment_set_arguments1);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
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
            if ("1".equals(flag)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content, new TestFragment1("构造传值")).commit();
            } else {
                fragment2 = TestFragment2.newInstance("setArguments");
//                String str = fragment2.getTvStr();//TODO err
//                L.e(TAG, "onCreate() err:" + str);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content, fragment2).commit();
            }
        }

        btn01.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e(TAG, "Activity onStart()");
        String str = fragment2.getTvStr();//TODO ok
        L.e(TAG, "onStart() err:" + str);
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e(TAG, "Activity onResume()");
//        String str = fragment2.getTvStr();//TODO ok
//        L.e(TAG, "onStart() err:" + str);
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.e(TAG, "Activity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.e(TAG, "Activity onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e(TAG, "Activity onDestroy()");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn01:
                ToastUtils.showShort(this, fragment2.getTvStr());

                break;
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
        public TestFragment1(String arg) {
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
        public TextView tv;//测试下fragment何时可以调用里面的控件

        public TestFragment2() {
            Log.i("INFO", "TestFragment non-parameter constructor");
        }

        public static TestFragment2 newInstance(String arg) {
            TestFragment2 fragment = new TestFragment2();
            Bundle bundle = new Bundle();
            bundle.putString(ARG, arg);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            L.e(TAG, "TestFragment2 onAttach()");
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            L.e(TAG, "TestFragment2 onCreate()");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            L.e(TAG, "TestFragment2 onCreateView()");
            View rootView = inflater.inflate(R.layout.fragment3, container,
                    false);
            tv = (TextView) rootView.findViewById(R.id.fragment3);
            tv.setText(getArguments().getString(ARG));
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            L.e(TAG, "TestFragment2 onViewCreated()");
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            L.e(TAG, "TestFragment2 onActivityCreated()");
        }

        @Override
        public void onStart() {
            super.onStart();
            L.e(TAG, "TestFragment2 onStart()");
        }

        @Override
        public void onResume() {
            super.onResume();
            L.e(TAG, "TestFragment2 onResume()");
        }

        @Override
        public void onPause() {
            super.onPause();
            L.e(TAG, "TestFragment2 onPause()");
        }

        @Override
        public void onStop() {
            super.onStop();
            L.e(TAG, "TestFragment2 onStop()");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            L.e(TAG, "TestFragment2 onDestroyView()");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            L.e(TAG, "TestFragment2 onDestroy()");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            L.e(TAG, "TestFragment2 onDetach()");
        }

        /**
         * TODO 创建完fragment，然后直接调取肯定会报空指针异常
         *
         * @return
         */
        public String getTvStr() {
            L.e(TAG, "getTvStr()");
            return tv.getText().toString();
        }
    }
}
