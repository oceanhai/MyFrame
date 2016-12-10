package com.xywy.component.uimodules.photoPicker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xywy.component.R;
import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;
import com.xywy.component.datarequest.tool.SerializeTools;
import com.xywy.component.uimodules.photoPicker.adapter.FolderListAdapter;
import com.xywy.component.uimodules.photoPicker.adapter.PhotoListAdapter;
import com.xywy.component.uimodules.photoPicker.model.PhotoFolderInfo;
import com.xywy.component.uimodules.photoPicker.model.PhotoInfo;
import com.xywy.component.uimodules.utils.ClickUtil;
import com.xywy.component.uimodules.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 图片选择页面
 */
public class PhotoSelectActivity extends Activity implements GetPhotoTask.AsyncTaskCallback, AdapterView.OnItemClickListener, PhotoListAdapter.OnSelectListener {

    public static final String NUM = "num";
    public static final String LIST = "list";
    //文件夹列表
    private List<PhotoFolderInfo> mAllPhotoFolderList;
    private FolderListAdapter mFolderListAdapter;
    //当前文件夹照片列表
    private ArrayList<PhotoInfo> mCurPhotoList;
    private PhotoListAdapter mPhotoListAdapter;
    private GetPhotoTask getPhotoTask;
    private GridView mGvPhotoList;
    private ListView mLvFolderList;
    private ArrayList<PhotoInfo> mSelectPhotoList = new ArrayList<PhotoInfo>();
    private ImageView mPhotoBack;
    private Button mOK;
    private View mAlbumPanel;
    private TextView mAlbumBtn;
    private TextView mPreviewBtn;
    private PPSelectState mState;
    private int canSelectNum = 3;//可选张数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pp_activity_photo_select);
        ButterKnife.bind(this);

        ImageLoaderUtils.getInstance().init(this);

        initView();

        Intent intent = getIntent();
        if (intent != null) {
            /**
             * 方案1 每次把被选中的图片集合传过去，从新选择再从新返回集合
             * ※这里对PhotoInfo 重写了equals和hashCode方法（不含isSelected字段）
             */
            ArrayList<PhotoInfo> list = (ArrayList<PhotoInfo>) intent.getSerializableExtra("list");
            if (list != null) {
                mSelectPhotoList.addAll(list);
                isShowOkAndPreviewBtn();
            }

            /**
             * 方案2 每次把还可以再选多少张图片值传过去，选择再返回集合，并追加到被选中结合中
             * ※这里对PhotoInfo 重写了equals和hashCode方法（不含isSelected字段）
             */
            canSelectNum = intent.getIntExtra(NUM, 3);
        }

        initData();
        initListener();

        isShowOkAndPreviewBtn();//初始化展示信息
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSelectPhotoList.clear();

        mCurPhotoList.clear();//清空

        if (getPhotoTask != null && !getPhotoTask.isCancelled()) {
            getPhotoTask.cancel(true);
        }
        getPhotoTask = null;
    }

    private void initView() {
        mGvPhotoList = (GridView) findViewById(R.id.pp_photo_grid);
        mLvFolderList = (ListView) findViewById(R.id.pp_lv_folder_list);

        mAlbumPanel = findViewById(R.id.pp_folder_panel);

        mPhotoBack = (ImageView) findViewById(R.id.pp_iv_back);
        mOK = (Button) findViewById(R.id.pp_tv_indicator);

        mAlbumBtn = (TextView) findViewById(R.id.pp_tv_album);
        mPreviewBtn = (TextView) findViewById(R.id.pp_tv_preview);

        //展示图片
        changeState(PPSelectState.PP_SELECT_STATE_PHOTO);
    }

    private void initData() {
        mAllPhotoFolderList = new ArrayList<>();//所有相册文件夹集合
        mFolderListAdapter = new FolderListAdapter(this, mAllPhotoFolderList);
        mLvFolderList.setAdapter(mFolderListAdapter);
        mCurPhotoList = new ArrayList<>();//当前相册文件夹的相片list集合
        getPhotoTask = new GetPhotoTask(this, this);

        DisplayMetrics dm = DeviceUtils.getScreenPix(this);
        mPhotoListAdapter = new PhotoListAdapter(this, mCurPhotoList, mSelectPhotoList, dm.widthPixels);
        mPhotoListAdapter.setOnSelectListener(this);
        mGvPhotoList.setAdapter(mPhotoListAdapter);

        getPhotos();
    }

    private void initListener() {
        /**
         * 返回箭头
         */
        mPhotoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mState == PPSelectState.PP_SELECT_STATE_ALBUM) {
                    changeState(PPSelectState.PP_SELECT_STATE_PHOTO);
                    return;
                }
                finish();
            }
        });

        /**
         * 完成
         */
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra(LIST, mSelectPhotoList);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        /**
         * 相册选择 弹框 TODO 这里应该用popwindow
         */
        mAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mState == PPSelectState.PP_SELECT_STATE_PHOTO) {
                    changeState(PPSelectState.PP_SELECT_STATE_ALBUM);
                } else {
                    changeState(PPSelectState.PP_SELECT_STATE_PHOTO);
                }
            }
        });

        /**
         * 预览
         */
        mPreviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPreviewActivity.startActivity(PhotoSelectActivity.this, mSelectPhotoList, mSelectPhotoList, canSelectNum, 0, null);
            }
        });

        /**
         * 选择相册  弹框的周边点击效果
         */
        mAlbumPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState(PPSelectState.PP_SELECT_STATE_PHOTO);
            }
        });

        mLvFolderList.setOnItemClickListener(this);
        mGvPhotoList.setOnItemClickListener(this);
    }

    /**
     * 图片选择/取消选择
     *
     * @param position
     * @param isSelected
     * @param mIvCheck
     */
    @Override
    public void onSelect(int position, boolean isSelected, ImageView mIvCheck) {
        PhotoInfo photoInfo = mCurPhotoList.get(position);
        // 选择图片数量 >= 本次选择最大张数
        if (mSelectPhotoList.size() >= canSelectNum) {
            if (!removeOneData(photoInfo, isSelected, mIvCheck)) {//非取消选中图片操作,提示"超出可选图片张数"
                Toast.makeText(PhotoSelectActivity.this, "超出可选图片张数", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (isSelected) {//选择图片
            mIvCheck.setImageResource(R.drawable.select_photo_selected);
            mSelectPhotoList.add(photoInfo);
        } else {//取消选择的图片
            mIvCheck.setImageResource(R.drawable.select_photo_deselected);
            mSelectPhotoList.remove(photoInfo);
        }
        photoInfo.setIsSelected(isSelected);
        refreshNum();
        isShowOkAndPreviewBtn();//完成btn和预览btn 显示效果
    }

    /**
     * 取消选中图片操作
     *
     * @param photoInfo
     * @return
     */
    private boolean removeOneData(PhotoInfo photoInfo, boolean isSelected, ImageView mIvCheck) {
        if (mSelectPhotoList.contains(photoInfo)) {
            mSelectPhotoList.remove(photoInfo);
            photoInfo.setIsSelected(isSelected);
            mIvCheck.setImageResource(R.drawable.select_photo_deselected);
            refreshNum();
            return true;
        }
        return false;
    }

    /**
     * 刷新完成和预览 数据显示
     */
    private void refreshNum() {
        mOK.setText(getResources().getString(R.string.finish) + "("
                + mSelectPhotoList.size() + "/" + canSelectNum + ")");
        mPreviewBtn.setText(getResources().getString(R.string.preview) + "("
                + mSelectPhotoList.size() + ")");
    }

    /**
     * 刷新完成和预览 无数据
     */
    private void refreshNumNomorl() {
        mOK.setText(getResources().getString(R.string.finish));
        mPreviewBtn.setText(getResources().getString(R.string.preview));
    }

    /**
     * 完成和预览  是否可以点击
     */
    private void isShowOkAndPreviewBtn() {
        if (mSelectPhotoList.size() > 0) {
            refreshNum();
            mPreviewBtn.setPressed(true);
            mOK.setPressed(true);
            mPreviewBtn.setClickable(true);
            mOK.setClickable(true);
            mOK.setTextColor(Color.WHITE);
            mPreviewBtn.setTextColor(Color.WHITE);
        } else {
            refreshNumNomorl();
            mPreviewBtn.setPressed(false);
            mPreviewBtn.setClickable(false);
            mOK.setPressed(false);
            mOK.setClickable(false);
            mOK.setTextColor(Color.parseColor("#E1E0DE"));
            mPreviewBtn.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }

    /**
     * gridview listview  item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /**
         * 因为相册图片过多的时候，是用本地文件的形式传过去的，会产生生成多个预览界面的情况
         * 所以这里防止快速点击
         */
        if (ClickUtil.isFastDoubleClick())
            return;

        int parentId = parent.getId();
        if (parentId == R.id.pp_lv_folder_list) {
            folderItemClick(position);
        } else {
            photoItemClick(view, position);
        }
    }

    /**
     * 相册文件夹listview 点击事件
     *
     * @param position
     */
    private void folderItemClick(int position) {

        changeState(PPSelectState.PP_SELECT_STATE_PHOTO);

        mCurPhotoList.clear();
        PhotoFolderInfo photoFolderInfo = mAllPhotoFolderList.get(position);
        mAlbumBtn.setText(photoFolderInfo.getFolderName());//相册文件夹名称

        if (photoFolderInfo.getPhotoList() != null) {
            mCurPhotoList.addAll(photoFolderInfo.getPhotoList());
        }
        mPhotoListAdapter.notifyDataSetChanged();

        mFolderListAdapter.setSelectFolder(photoFolderInfo);
        mFolderListAdapter.notifyDataSetChanged();

    }

    /**
     * 相册 的相片 gridview点击事件
     *
     * @param view
     * @param position
     */
    private void photoItemClick(View view, int position) {
        if (mCurPhotoList.size() > 1000) {
            String fileName = getApplicationContext().getFilesDir() + "photolist";
            SerializeTools.serialization(fileName, mCurPhotoList);
            PhotoPreviewActivity.startActivity(this, mSelectPhotoList, null, canSelectNum, position, fileName);
        } else {
            PhotoPreviewActivity.startActivity(this, mSelectPhotoList, mCurPhotoList, canSelectNum, position, null);
        }
    }

    /**
     * 异步 获取所有相册文件夹集合
     */
    private void getPhotos() {
        getPhotoTask.execute();
    }

    /**
     * 异步回传结果  相册文件夹list结合（所有图片相册为自己创建的）
     *
     * @param list
     */
    @Override
    public void onAsyncResult(List<PhotoFolderInfo> list) {
        if (list != null) {
            mAllPhotoFolderList.clear();
            mAllPhotoFolderList.addAll(list);
        }
        //默认展示"所有图片"相册
        refreshPhotoList(0);
    }

    /**
     * 展示相册中的图片 "所有图片"相册
     *
     * @param index
     */
    private void refreshPhotoList(int index) {
        mCurPhotoList.clear();
        if (mAllPhotoFolderList != null && mAllPhotoFolderList.size() > index) {
            PhotoFolderInfo info = mAllPhotoFolderList.get(index);
            if (info != null && info.getPhotoList() != null) {
                mCurPhotoList.addAll(info.getPhotoList());
            }
        }

        mPhotoListAdapter.notifyDataSetChanged();
    }

    /**
     * 切换状态：相册／照片
     */
    private void changeState(PPSelectState state) {
        mState = state;
        switch (mState) {
            case PP_SELECT_STATE_ALBUM://隐藏相册选择框
                mAlbumPanel.setVisibility(View.VISIBLE);
                break;
            case PP_SELECT_STATE_PHOTO://弹出相册选择框
                mAlbumPanel.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mSelectPhotoList.clear();
        if (data != null) {
            mSelectPhotoList.addAll((ArrayList<PhotoInfo>) data.getSerializableExtra(PhotoSelectActivity.LIST));
            if (data.getStringExtra(PhotoPreviewActivity.FINISH_OR_BACK).equals(PhotoPreviewActivity.FINISH)) {
                //完成
                Intent intent = getIntent();
                intent.putExtra(LIST, mSelectPhotoList);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                //返回箭头 或 手机返回键
                mPhotoListAdapter.updateSelectPhotoList(mSelectPhotoList);
                mPhotoListAdapter.notifyDataSetChanged();
                isShowOkAndPreviewBtn();
            }
        }
    }

    /**
     * 手机返回键
     */
    @Override
    public void onBackPressed() {
        if (mState == PPSelectState.PP_SELECT_STATE_ALBUM) {
            changeState(PPSelectState.PP_SELECT_STATE_PHOTO);
            return;
        }
        super.onBackPressed();
    }

    enum PPSelectState {
        PP_SELECT_STATE_PHOTO,
        PP_SELECT_STATE_ALBUM
    }

}
