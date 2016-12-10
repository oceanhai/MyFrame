package com.xywy.component.uimodules.photoPicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xywy.component.R;
import com.xywy.component.datarequest.tool.SerializeTools;
import com.xywy.component.uimodules.photoPicker.adapter.PhotoPreviewAdapter;
import com.xywy.component.uimodules.photoPicker.model.PhotoInfo;
import com.xywy.component.uimodules.photoPicker.view.PPViewPager;

import java.util.ArrayList;

/**
 * 图片预览页面
 */
public class PhotoPreviewActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public static final String SELECT_PHOTO_LIST = "select_photo_list";
    public static final String SHOW_PHOTO_LIST = "show_photo_list";
    public static final String FILE_NAME = "file_name";
    public static final String POSITION = "position";
    public static final String FINISH_OR_BACK = "finish_or_back";
    public static final String FINISH = "finish";
    public static final String BACK = "back";

    private RelativeLayout mTitleBar;
    private ImageView mIvBack;
    private TextView mTvSelect;
    private TextView mTvTitle;
    private TextView mOK;

    private PPViewPager mVpPager;
    private ArrayList<PhotoInfo> mShowPhotoList;
    private ArrayList<PhotoInfo> mNewSelectPhotoList = new ArrayList<PhotoInfo>();//新的被选择图片集合
    private PhotoPreviewAdapter mPhotoPreviewAdapter;

    private int mNum;//可选图片上限
    private int mPostion;//PhotoSelectActivity中被点击item的位置
    private String mFileName;//文件存储路径
    private int mCurPosition = 0;//当前page的postion


    /**
     * 当前相册所有图片/被选中图片   预览
     *
     * @param context
     * @param selectPhotoList 被选中图片集合
     * @param showPhotoList   当前相册 showPhotoList；预览：showPhotoList=selectPhotoList
     * @param canSelectNum
     * @param position        当前相册 position；预览：null
     * @param fileName        fileName 当相册图片过多时候，通过文件传递
     */
    public static void startActivity(Context context, ArrayList<PhotoInfo> selectPhotoList, ArrayList<PhotoInfo> showPhotoList, int canSelectNum, int position, String fileName) {
        Intent intent = new Intent(context, PhotoPreviewActivity.class);
        intent.putExtra(SELECT_PHOTO_LIST, selectPhotoList);
        intent.putExtra(SHOW_PHOTO_LIST, showPhotoList);
        intent.putExtra(PhotoSelectActivity.NUM, canSelectNum);
        intent.putExtra(POSITION, position);
        intent.putExtra(FILE_NAME, fileName);
        ((Activity) context).startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pp_activity_photo_preview);
        parseIntent();
        initView();
        initListener();

        mPhotoPreviewAdapter = new PhotoPreviewAdapter(this, mShowPhotoList);
        mVpPager.setAdapter(mPhotoPreviewAdapter);
        mVpPager.setCurrentItem(mPostion);
        selectChanged(mPostion);

        isShowOkBtn();//初始化显示数据
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            mNewSelectPhotoList.addAll((ArrayList<PhotoInfo>) intent.getSerializableExtra(SELECT_PHOTO_LIST));//创建一个新的被选中图片集合
            mNum = intent.getIntExtra(PhotoSelectActivity.NUM, 3);
            mPostion = intent.getIntExtra(POSITION, 0);
            mFileName = intent.getStringExtra(FILE_NAME);
            mShowPhotoList = (ArrayList<PhotoInfo>) intent.getSerializableExtra(SHOW_PHOTO_LIST);
            if (mShowPhotoList == null && mFileName != null) {
                mShowPhotoList = (ArrayList<PhotoInfo>) SerializeTools.deserialization(mFileName);
            }
        }

    }

    private void initView() {
        mTitleBar = (RelativeLayout) findViewById(R.id.titlebar);//上栏
        mIvBack = (ImageView) findViewById(R.id.pp_iv_back);//返回箭头
        mTvTitle = (TextView) findViewById(R.id.tv_title);//XX/XX
        mOK = (TextView) findViewById(R.id.pp_tv_indicator);//完成
        mTvSelect = (TextView) findViewById(R.id.pp_cb_select);//选择
        mVpPager = (PPViewPager) findViewById(R.id.vp_pager);
    }

    private void initListener() {
        mVpPager.addOnPageChangeListener(this);
        mIvBack.setOnClickListener(this);
        mTvSelect.setOnClickListener(this);
        mOK.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurPosition = position;
        selectChanged(position);
    }

    /**
     * 根据position，刷新显示
     *
     * @param position
     */
    private void selectChanged(int position) {
        Drawable deselected = getResources().getDrawable(R.drawable.select_photo_deselected);
        Drawable selected = getResources().getDrawable(R.drawable.select_photo_selected);
        deselected.setBounds(0, 0, deselected.getMinimumWidth(), deselected.getMinimumHeight());
        selected.setBounds(0, 0, selected.getMinimumWidth(), selected.getMinimumHeight());

        mTvTitle.setText((position + 1) + "/" + mShowPhotoList.size());

        PhotoInfo photoInfo = mShowPhotoList.get(position);
        if (photoInfo.isSelected()) {
            mTvSelect.setCompoundDrawables(selected, null, null, null);
        } else {
            mTvSelect.setCompoundDrawables(deselected, null, null, null);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 完成Btn 显示变化
     */
    private void isShowOkBtn() {
        if (mNewSelectPhotoList == null) {
            return;
        }
        refreshNum(mNewSelectPhotoList.size());
    }

    /**
     * 刷新完成 数据显示
     */
    private void refreshNum(int num) {
        String numString = num > 0 ? "(" + num + "/" + mNum + ")" : "";
        mOK.setText(getResources().getString(R.string.finish) + numString);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNewSelectPhotoList.clear();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.pp_iv_back) {
            retunData(BACK);

        } else if (i == R.id.pp_tv_indicator) {
            retunData(FINISH);

        } else if (i == R.id.pp_cb_select) {
            PhotoInfo photoInfo = mShowPhotoList.get(mCurPosition);
            if (!photoInfo.isSelected()) {
                mNewSelectPhotoList.add(photoInfo);
                photoInfo.setIsSelected(true);
            } else {
                mNewSelectPhotoList.remove(photoInfo);
                photoInfo.setIsSelected(false);
            }
            selectChanged(mCurPosition);
            isShowOkBtn();

        }
    }

    /**
     * 回传数据
     *
     * @param finishOrBack
     */
    private void retunData(String finishOrBack) {
        judgeData(finishOrBack);

        Intent intent = getIntent();
        intent.putExtra(PhotoSelectActivity.LIST, mNewSelectPhotoList);
        intent.putExtra(FINISH_OR_BACK, finishOrBack);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * finish 即点击了完成 mNewSelectPhotoList 为空的时候，把当前图片作为回传数据
     *
     * @param finishOrBack
     */
    private void judgeData(String finishOrBack) {
        if (finishOrBack.equals(FINISH)) {
            if (mNewSelectPhotoList.size() == 0) {
                mNewSelectPhotoList.add(getCurPhoto());
                isShowOkBtn();//完成 按钮显示变更
                selectChanged(mCurPosition);//当前图片 选中
            }
        }
    }

    /**
     * 当mNewSelectPhotoList=0时候，点击完成
     * 获取当前浏览的图片信息 作为选中图片使用
     *
     * @return
     */
    private PhotoInfo getCurPhoto() {
        PhotoInfo photoInfo = mShowPhotoList.get(mCurPosition);
        photoInfo.setIsSelected(true);//选中
        return photoInfo;
    }

    /**
     * 手机返回键
     */
    @Override
    public void onBackPressed() {
        retunData(BACK);
    }

}
