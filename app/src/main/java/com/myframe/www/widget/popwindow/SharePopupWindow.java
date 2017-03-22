package com.myframe.www.widget.popwindow;


import android.app.ActivityGroup;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

//TODO 待定
public class SharePopupWindow {
//
//	/**
//	 * view
//	 */
//	private View mParent;
//	private View popupWindowMenuView;
//	private PopupWindow mPopupWindowBg = null;
//	private PopupWindow mPopupWindow = null;
//
//	/**
//	 * data
//	 */
//	private Context mContext;
//	private boolean mIsShowingShareMenu = false;							// 分享弹出框是否正在显示
//
//	public SharePopupWindow(Context context,View parent) {
//		mContext = context;
//		mParent = parent;
//	}
//
//	public SharePopupWindow(ActivityGroup context,View parent) {
//		mContext = context;
//		mParent = parent;
//	}
//
//
//	/**
//	 * 显示或者隐藏分享菜单
//	 */
//	public void showOrHideShareMenu() {
//		if (mIsShowingShareMenu) {
//			mIsShowingShareMenu = false;
//			if(mPopupWindow != null && mPopupWindowBg != null){
//				mPopupWindowBg.dismiss();
//				mPopupWindow.dismiss();
//			}
//		} else {
//			mIsShowingShareMenu = true;
//			// 半透明背景
//			if (mPopupWindowBg == null) {
//				View popupWindowMenuViewBg = LayoutInflater.from(mContext).inflate(R.layout.share_popup_menu_background, null);;
//				mPopupWindowBg = new PopupWindow(popupWindowMenuViewBg, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//				mPopupWindowBg.setTouchable(true);
//				mPopupWindowBg.setOutsideTouchable(true);
//				popupWindowMenuViewBg.setOnTouchListener(new OnTouchListener() {
//					//点击屏幕取消菜单
//					@Override
//					public boolean onTouch(View v, MotionEvent event) {
//						int action = event.getAction();
//						if(action == MotionEvent.ACTION_DOWN){
//							if(mPopupWindow.isShowing()){
//								mPopupWindowBg.dismiss();
//								mPopupWindow.dismiss();
//								mIsShowingShareMenu = false;
//							}
//							return true;
//						}
//						return false;
//					}
//				});
//
//			}
//			mPopupWindowBg.showAtLocation(mParent, Gravity.NO_GRAVITY, 0, 0);
//
//			// 底部菜单
//			if (mPopupWindow == null) {
//				popupWindowMenuView = LayoutInflater.from(mContext).inflate(R.layout.share_popup_menu, null);
//				mPopupWindow = new PopupWindow(popupWindowMenuView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//				bindShareMenuOnClickListener();
//			}
//			if(mPopupWindow != null){
//				mPopupWindow.setAnimationStyle(R.style.mypopwindow_up_anim_style);
//				mPopupWindow.showAtLocation(mParent, Gravity.BOTTOM, 0, 0);
//			}
//		}
//	}
//
//	public boolean isShowShareMenu(){
//		return mIsShowingShareMenu;
//	}
//
//	public void setShowShareMenuStatus(boolean status){
//		 mIsShowingShareMenu = status;
//	}
//
//	private OnClickListener mShareMenuOnClickLisnter = new OnClickListener() {
//		@Override
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.share_to_sina:
//				shareToSinaWeiBo();
//				break;
//			case R.id.share_to_tencent:
//				shareToTencentWeibo();
//				break;
//			case R.id.share_to_qq:
//				shareToQQFriends();
//				break;
//			case R.id.share_to_qzone:
//				shareToQzone();
//				break;
//			case R.id.share_to_wx:
//				shareToWX();
//				break;
//			case R.id.share_to_wx_friends:
//				shareToWXFriends();
//				break;
//			case R.id.share_to_other:
//				shareToOther();
//				break;
//			case R.id.share_cancel:
//				break;
//			default:
//				break;
//			}
//			if(mPopupWindow != null && mPopupWindowBg != null){
//				mPopupWindowBg.dismiss();
//				mPopupWindow.dismiss();
//			}
//			if (mContext instanceof BookNoteActivity)
//				((BookNoteActivity) mContext).finish();
//			mIsShowingShareMenu = false;
//		}
//	};
//
//
//	private boolean isNeedStatistics(){
//		return mDDStatisticsData != null;
//	}
//
//	private void bindShareMenuOnClickListener() {
//		popupWindowMenuView.findViewById(R.id.share_to_sina).setOnClickListener(mShareMenuOnClickLisnter);
//		popupWindowMenuView.findViewById(R.id.share_to_tencent).setOnClickListener(mShareMenuOnClickLisnter);
//		popupWindowMenuView.findViewById(R.id.share_to_qq).setOnClickListener(mShareMenuOnClickLisnter);
//		popupWindowMenuView.findViewById(R.id.share_to_qzone).setOnClickListener(mShareMenuOnClickLisnter);
//		popupWindowMenuView.findViewById(R.id.share_to_wx).setOnClickListener(mShareMenuOnClickLisnter);
//		popupWindowMenuView.findViewById(R.id.share_to_wx_friends).setOnClickListener(mShareMenuOnClickLisnter);
//		popupWindowMenuView.findViewById(R.id.share_to_other).setOnClickListener(mShareMenuOnClickLisnter);
//		popupWindowMenuView.findViewById(R.id.share_cancel).setOnClickListener(mShareMenuOnClickLisnter);
//	}
}
