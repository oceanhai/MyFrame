package com.wuhai.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.picasso.Picasso;
import com.wuhai.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn01;
    private EditText name;
    private EditText addr;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setStu(new Student("路飞","海贼王","https://www.baidu.com/img/bd_logo1.png"));//model和布局绑定

        //取布局id 方式1
//        View view = binding.getRoot();
//        btn01 = (Button) view.findViewById(R.id.btn01);
//        name = (EditText) view.findViewById(R.id.name);
//        addr = (EditText) view.findViewById(R.id.addr);

        //取布局id 方式2
        btn01 = binding.btn01;
        name = binding.name;
        addr = binding.addr;

        btn01.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                String nameStr = name.getText().toString().trim();
                String addrStr = addr.getText().toString().trim();

                //示例 不做效果展示了，这里只是重新绑定model而已
//                binding.setStu(new Student(nameStr,addrStr));

                //示例2
                Student student = binding.getStu();
                student.setName(nameStr);
                student.setAddr(addrStr);
                break;
        }
    }
}
