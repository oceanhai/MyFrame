package com.myframe.www.widget.autowrap;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.myframe.www.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自动换行的单选、多选控件
 */
public class AutomaticWrapLayout extends RelativeLayout {

    /**
     * 问题补充控件类型：单选、正常多选、有排他选项的多选
     */
    public enum CaseViewType {
        CASE_VIEW_TYPE_SINGLE,
        CASE_VIEW_TYPE_MULTIPLE_NORMAL,
        CASE_VIEW_TYPE_MULTIPLE_EXCLUSIVE
    }

    private List<AutomaticWrapModel> modelList = Collections.EMPTY_LIST;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private boolean isFirst = false;//多选第一个

    private CaseViewType caseType = CaseViewType.CASE_VIEW_TYPE_SINGLE;

    private AutomaticWrapViewGroup AutomaticWrapView;

    public AutomaticWrapLayout(Context context) {
        super(context);
    }

    public AutomaticWrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutomaticWrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(List<AutomaticWrapModel> list, CaseViewType type) {
        if(list!=null) {
            modelList = list;
        }
        caseType = type;
        init();
    }

    /**
     * 在checkBoxes被选中的CheckBox,对应的CaseModel集合
     * @return
     */
    public List<AutomaticWrapModel> getResult() {
        List<AutomaticWrapModel> model = new ArrayList<>();
        for(int i = 0; i<checkBoxes.size(); i++) {
            CheckBox box = checkBoxes.get(i);
            if(box!=null && box.isChecked()) {
                Object tag = box.getTag();
                if(box!=null && tag instanceof AutomaticWrapModel) {
                    model.add((AutomaticWrapModel) tag);
                }
            }
        }

        return model;
    }

    private void init() {
        inflateView();

        MarginLayoutParams lp = new MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 10;
        lp.rightMargin = 10;
        lp.topMargin = 10;
        lp.bottomMargin = 10;
        for(int i = 0; i < modelList.size(); i ++){
            CheckBox view = new CheckBox(getContext());

            view.setCompoundDrawables(null, null, null, null);
            view.setPadding(40, 20, 40, 20);
            view.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            view.setBackground(getResources().getDrawable(R.drawable.widget_automatic_wrap_checkbox_selector));
            ColorStateList mTextColor =  getResources().getColorStateList(R.color.widget_automatic_wrap_textview_selector);
            if(mTextColor!=null){
                view.setTextColor(mTextColor);
            }

            AutomaticWrapModel model = modelList.get(i);
            if(model!=null) {
                view.setTag(model);
                view.setOnClickListener(new CheckBoxClistListener());
                view.setText(model.getCaseText());
                checkBoxes.add(view);
                AutomaticWrapView.addView(view, lp);

                if (model.isDefault()) {
                    view.setChecked(true);
                }
            }
        }
    }

    private void inflateView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.widget_automatic_wrap_layout, this, true);

        AutomaticWrapView = (AutomaticWrapViewGroup) findViewById(R.id.autowrap);
    }

    public class CheckBoxClistListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d("checkbox", "v clicked");
            Object tag = v.getTag();
            if(tag instanceof AutomaticWrapModel) {
                AutomaticWrapModel model = (AutomaticWrapModel) tag;

                boolean hasDone = checkBox((CheckBox) v);
                if(hasDone) {
                    return;
                }
                Log.d("checkbox", "v clicked === " + model.getCaseText());
                switch (caseType) {
                    case CASE_VIEW_TYPE_SINGLE:
                        dealWithSingleView(model);
                        break;
                    case CASE_VIEW_TYPE_MULTIPLE_NORMAL:
                        break;
                    case CASE_VIEW_TYPE_MULTIPLE_EXCLUSIVE:
                        dealWithMultipleExclusiveView(model);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void dealWithSingleView(AutomaticWrapModel model) {
        if(model == null || modelList == null) {
            return;
        }
        for(int i = 0; i<checkBoxes.size(); i++) {
            CheckBox box = checkBoxes.get(i);
            AutomaticWrapModel itemModel = modelList.get(i);
            if(itemModel!=null && itemModel!=model && box!=null && box.isChecked()) {
                box.setChecked(false);
            }
        }
    }

    private void dealWithMultipleExclusiveView(AutomaticWrapModel model) {
        if(model == null || modelList == null) {
            return;
        }

        //找到排他box和当前box
        CheckBox exclusiveBox = null;
        CheckBox currentBox = null;
        for(int i = 0; i<checkBoxes.size(); i++) {
            AutomaticWrapModel itemModel = modelList.get(i);
            if(itemModel!=null && itemModel.isExclusive()) {
                exclusiveBox = checkBoxes.get(i);
            }
            if (itemModel == model) {
                currentBox = checkBoxes.get(i);
            }
        }


        //对排他box操作，当排他box选中的时候，其它box都要非选中
        if(model.isExclusive() && currentBox!=null && currentBox.isChecked()) {
            for(int i = 0; i<checkBoxes.size(); i++) {
                AutomaticWrapModel itemModel = modelList.get(i);
                CheckBox box = checkBoxes.get(i);
                if(itemModel!=null && !itemModel.isExclusive()) {
                    box.setChecked(false);
                }

            }
        } else if(!model.isExclusive() && checkBoxes!=null && currentBox.isChecked()) {
            //对非排他box操作，变成选中的时候，排他box要变成非选中
            exclusiveBox.setChecked(false);
        }
    }

    public void clear() {
        if(checkBoxes!=null) {
            for(int i = 0; i<checkBoxes.size(); i++) {
                CheckBox check =  checkBoxes.get(i);
                if(check!=null) {
                    check.setOnClickListener(null);
                }
            }
            checkBoxes.clear();
            checkBoxes = null;
        }
        if(modelList!=null) {
            modelList.clear();
            modelList = null;
        }

        AutomaticWrapView.removeAllViews();
    }

    /**
     * 最后一个box不能被选不中
     * @return 是否可以下一步操作
     */
    private boolean checkBox(CheckBox box) {
        boolean hasDone = false;

        if(box == null) {
            return true;
        }

        if(box.isChecked()) {//选中
            return hasDone;
        } else {//未选中
            int count = selectionCount();
            //1.没有选项->选择当前点击的选项；2.单选，点击选中项之后因为count==0，又再次把这个选项置为true
            //其实就是保证选项中至少被选择一个
            if(count == 0) {
                box.setChecked(true);
                hasDone = true;
            }
        }

        return hasDone;
    }

    /**
     * checkBoxes中选中的个数
     * @return
     */
    private int selectionCount() {
        int count = 0;
        for(int i = 0; i<checkBoxes.size(); i++) {
            CheckBox box = checkBoxes.get(i);
            if(box!=null && box.isChecked()) {
                count ++;
            }
        }
        return count;
    }

    /**
     * 单选  问题1::单选答案1
     * @return
     */
    public String getSingleStringResult(String question) {
        List<AutomaticWrapModel> list = getResult();
        String content = list.get(0).getCaseText();//单选内容
        return question+ "::" +content;
    }

    /**
     * 单选  单选答案1
     * @return
     */
    public String getSingleStringResult() {
        return getResult().get(0).getCaseText();
    }

    /**
     * 多选
     * @return
     */
    public String getMultipleStringResult(String question) {
        String result = "";
        List<AutomaticWrapModel> list = getResult();
        for (int i = 0; i < list.size(); i++) {
            AutomaticWrapModel itemModel = list.get(i);
            if (!isFirst) {
                result = question + "::" + itemModel.getCaseText();//多选第一个
                isFirst = true;
            }
            else {
                result = result + "__" + itemModel.getCaseText();//多选其他选项
            }
        }
        isFirst = false;
        return result;
    }

    /**
     * 判断是否有并发症 A-3-3  TODO 可删 根据需求自行追加
     * @return true 有；false 无
     */
    public boolean getQtype() {
        List<AutomaticWrapModel> list = getResult();
        if(list.size()!=1){
            return true;
        }else{
            if("无".equals(list.get(0).getCaseText())){
                return false;
            }else{
                return true;
            }
        }
    }
}
