package com.myframe.www.retrofit.xywy;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.myframe.www.R;
import com.myframe.www.retrofit.xywy.interfaces.mine.ILoginContract;
import com.myframe.www.retrofit.xywy.presenter.mine.LoginPresenter;
import com.myframe.www.retrofit.xywy.ui.BaseActivity;
import com.myframe.www.utils.ToastUtils;

import java.io.File;

public class RetrofitXywyActivity extends BaseActivity implements View.OnClickListener, ILoginContract.View {

    private Button btn01;
    private Button btn02;
    private Button btn03;

    private LoginPresenter mPresenter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RetrofitXywyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setLayoutId() {
        setContentView(R.layout.activity_retrofit_xywy);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initView() {

        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        btn03 = (Button) findViewById(R.id.btn03);

        setListener();
    }

    @Override
    protected void getDate() {

    }

    private void setListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01://login
                mPresenter.login("13661090741","123456");
                break;
            case R.id.btn02://getCode
                mPresenter.getCode("13661090741","dynamic_password");
                break;
            case R.id.btn03://uploadImg
                File file = new File(Environment.getExternalStorageDirectory()+File.separator+
                "Download"+File.separator+"apple.jpeg");
                mPresenter.uploadImg(file);
                break;
        }
    }

    @Override
    public void loginSuccess() {
        ToastUtils.showShort(this,"登录成功");
    }

    @Override
    public void loginFail() {
        ToastUtils.showShort(this,"登录失败");
    }

    @Override
    public void getCodeSuccess() {
        ToastUtils.showShort(this,"获取验证码成功");
    }

    @Override
    public void getCodeFail() {
        ToastUtils.showShort(this,"获取验证码失败");
    }

    @Override
    public void uploadImgSuccess() {
        ToastUtils.showShort(this,"上传图片成功");
    }

    @Override
    public void uploadImgFail() {
        ToastUtils.showShort(this,"上传图片失败");
    }
}
