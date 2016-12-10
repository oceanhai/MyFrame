package com.xywy.component.uimodules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.xywy.component.R;
import com.xywy.component.uimodules.photoPicker.PhotoSelectActivity;
import com.xywy.component.uimodules.photoPicker.model.PhotoInfo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoPickerActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.question_pictures_gridview)
    GridView questionPicturesGridview;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.question_pictures_gridview2)
    GridView questionPicturesGridview2;

    private QuestionPictureAdapter adapter;
    private QuestionPictureAdapter adapter2;

    private int max = 3;//获取图片的最大张数
    private ArrayList<PhotoInfo> list = new ArrayList<PhotoInfo>();//被选中的图片集合
    private ArrayList<PhotoInfo> list2 = new ArrayList<PhotoInfo>();//被选中的图片集合

    private String fangan = "";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PhotoPickerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);

        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);

        questionPicturesGridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消"点击时候背景色，黄色背景=透明"
        adapter = new QuestionPictureAdapter(this);
        questionPicturesGridview.setAdapter(adapter);

        questionPicturesGridview2.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消"点击时候背景色，黄色背景=透明"
        adapter2 = new QuestionPictureAdapter(this);
        questionPicturesGridview2.setAdapter(adapter2);

    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, PhotoSelectActivity.class);
        switch (v.getId()) {
            case R.id.btn01:
                fangan = "fangan1";
                if (list.size() > 0) {
                    /**
                     * 方案1 每次把被选中的图片集合传过去，从新选择再从新返回集合
                     * ※这里对PhotoInfo 重写了equals和hashCode方法（不含isSelected字段）
                     */
                    intent.putExtra("list", list);

                }
                break;
            case R.id.btn02:
                fangan = "fangan2";
                if (list2.size() > 0) {
                    /**
                     * 方案2 每次把还可以再选多少张图片值传过去，选择再返回集合，并追加到被选中结合中
                     * ※这里对PhotoInfo 重写了equals和hashCode方法（不含isSelected字段）
                     */
                    intent.putExtra(PhotoSelectActivity.NUM, max - list2.size());

                }

                break;
        }
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (fangan) {
                case "fangan1":
                    /**
                     * 方案1 每次把被选中的图片集合传过去，从新选择再从新返回集合
                     * ※这里对PhotoInfo 重写了equals和hashCode方法（不含isSelected字段）
                     */
                    list = (ArrayList<PhotoInfo>) data.getSerializableExtra(PhotoSelectActivity.LIST);
                    adapter.setDatas(list);
                    adapter.notifyDataSetChanged();
                    break;
                case "fangan2":
                    /**
                     * 方案2 每次把还可以再选多少张图片值传过去，选择再返回集合，并追加到被选中结合中
                     * ※这里对PhotoInfo 重写了equals和hashCode方法（不含isSelected字段）
                     */
                    list2.addAll((ArrayList<PhotoInfo>) data.getSerializableExtra(PhotoSelectActivity.LIST));
                    adapter2.setDatas(list2);
                    adapter2.notifyDataSetChanged();
                    break;
            }
        }
    }
}
