package com.myframe.www;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.titlebar1.TitleBar;
import com.myframe.www.widget.autowrap.AutomaticWrapLayout;
import com.myframe.www.widget.autowrap.AutomaticWrapModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
* 可根据选项自动换行的单选/多选/排他选
* @author wuhai
* create at 2016/1/25 14:01
*/
public class AutoWrapActivity extends BaseActivity
        implements View.OnClickListener {

    @Bind(R.id.caseview1)
    AutomaticWrapLayout caseview1;
    @Bind(R.id.caseview2)
    AutomaticWrapLayout caseview2;
    @Bind(R.id.caseview3)
    AutomaticWrapLayout caseview3;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_wrap);
        ButterKnife.bind(this);

        titleBar.setBackOnClickListener(new View.OnClickListener() {//返回
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
    }


    private void initData() {

        List<AutomaticWrapModel> list1 = new ArrayList<>();
        AutomaticWrapModel model = new AutomaticWrapModel();
        model.setCaseText("还未确诊");
        list1.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("确诊为一型糖尿病");
        list1.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("确诊为二型糖尿病");
        list1.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("确诊，不知类型");
        model.setIsDefault(true);
        list1.add(model);
        caseview1.initView(list1, AutomaticWrapLayout.CaseViewType.CASE_VIEW_TYPE_SINGLE);

        List<AutomaticWrapModel> list2 = new ArrayList<>();
        model = new AutomaticWrapModel();
        model.setCaseText("眼睛");
        list2.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("足部");
        list2.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("皮肤");
        list2.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("肝脏");
        list2.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("心脑血管类");
        list2.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("无");
        model.setIsDefault(true);
        list2.add(model);
        caseview2.initView(list2, AutomaticWrapLayout.CaseViewType.CASE_VIEW_TYPE_MULTIPLE_NORMAL);

        List<AutomaticWrapModel> list3 = new ArrayList<>();
        model = new AutomaticWrapModel();
        model.setCaseText("眼睛");
        list3.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("足部");
        list3.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("皮肤");
        list3.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("肝脏");
        list3.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("心脑血管类");
        list3.add(model);

        model = new AutomaticWrapModel();
        model.setCaseText("无");
        model.setIsDefault(true);
        model.setIsExclusive(true);
        list3.add(model);
        caseview3.initView(list3, AutomaticWrapLayout.CaseViewType.CASE_VIEW_TYPE_MULTIPLE_EXCLUSIVE);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                String str1 = caseview1.getSingleStringResult("您是否检查过呢？");
                String result = caseview1.getSingleStringResult();
                String str2 = caseview2.getMultipleStringResult("直系亲属中有患糖尿病的吗？");
                String str3 = caseview3.getMultipleStringResult("近期体重情况是？");
                break;
        }
    }

}
