/*
 * Name        : DialogManager.java
 * Classes     : DialogManager
 * Version     : 1.0
 * Date        :
 *
 * Copyright 2013 CMCC.  All rights reserved.
 * This material, including documentation and any related computer
 * programs, is protected by copyright controlled by CMCC.  All
 * rights are reserved.  Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the
 * prior written consent of CMCC.  This material also contains
 * confidential information which may not be disclosed to others
 * without the prior written consent of CMCC.
 */
package com.myframe.www.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
* AlertDialog和ProgressDialog 进行封装
* @author wuhai
* create at 2015/12/18 13:29
*/
public class DialogManager {

	private DialogManager() {
	}

	//--------------------------AlertDialog--------------------------------
	/**
	 * Show dialog with single button.
	 * 
	 * @param context
	 * @param title_rid
	 * @param msg_rid
	 * @param ok_rid
	 * @param click
	 */
	public static AlertDialog showSingleButton(Context context, int title_rid,
			int msg_rid, int ok_rid, OnClickListener click) {
		return showSingleButton(context, title_rid, msg_rid, ok_rid, false,
				click);
	}

	/**
	 * Show dialog with single button.
	 * 
	 * @param context
	 * @param title_rid
	 * @param msg
	 * @param ok_rid
	 * @param click
	 */
	public static AlertDialog showSingleButton(Context context, int title_rid,
			String msg, int ok_rid, OnClickListener click) {
		return showSingleButton(context, title_rid, msg, ok_rid, false, click);
	}

	/**
	 * Show dialog with single button.
	 * 
	 * @param context
	 * @param title_rid
	 * @param msg_rid
	 * @param ok_rid
	 * @param cancelable
	 *            Is cancelable
	 * @param click
	 */
	public static AlertDialog showSingleButton(Context context, int title_rid,
			int msg_rid, int ok_rid, boolean cancelable, OnClickListener click) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title_rid);
		builder.setMessage(msg_rid);
		builder.setPositiveButton(ok_rid, click);
		AlertDialog dialog = builder.create();
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		return dialog;
	}

	/**
	 * Show dialog with single button.
	 * 
	 * @param context
	 * @param title_rid
	 * @param msg
	 * @param ok_rid
	 * @param cancelable
	 *            Is cancelable
	 * @param click
	 */
	public static AlertDialog showSingleButton(Context context, int title_rid,
			String msg, int ok_rid, boolean cancelable, OnClickListener click) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title_rid);
		builder.setMessage(msg);
		builder.setPositiveButton(ok_rid, click);
		AlertDialog dialog = builder.create();
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();

		return dialog;
	}

	/**
	 * Show dialog with single button.
	 * 
	 * @param context
	 * @param title_rid
	 * @param items_rid
	 * @param checkId
	 * @param okResId
	 * @param cancelResId
	 * @param click
	 */
	public static AlertDialog showSingleChoiceDialog(Context context,
			int title_rid, String[] items_rid, int checkId, int okResId,
			int cancelResId, boolean cancelable, OnClickListener click) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title_rid);
		builder.setSingleChoiceItems(items_rid, checkId, click);
		builder.setPositiveButton(okResId, click);
		builder.setNegativeButton(cancelResId, click);
		AlertDialog dialog = builder.create();
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		return dialog;
	}

	/**
	 * Show dialog with single button.
	 * 
	 * @param context
	 * @param title_rid
	 * @param items
	 * @param checkId
	 * @param okResId
	 * @param cancelResId
	 */
	public static AlertDialog showSingleChoiceDialog(Context context,
			int title_rid, String[] items, int checkId, int okResId,
			int cancelResId, boolean cancelable,
			OnClickListener itemChoiceListener,
			OnClickListener poisitiveListener) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title_rid);
		builder.setSingleChoiceItems(items, checkId, itemChoiceListener);
		builder.setPositiveButton(okResId, poisitiveListener);
		builder.setNegativeButton(cancelResId, null);
		AlertDialog dialog = builder.create();
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		return dialog;
	}

	/**
	 * Show dialog with two buttons.
	 * 
	 * @param context
	 * @param title_rid
	 * @param msg_rid
	 * @param okResId
	 * @param cancelResId
	 * @param click
	 */
	public static AlertDialog showSelectDialog(Context context, int title_rid,
			String msg_rid, int okResId, int cancelResId, boolean cancelable,
			OnClickListener click) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if(title_rid != -1){
			builder.setTitle(title_rid);
		}
		builder.setMessage(msg_rid);
		builder.setPositiveButton(okResId, click);
		builder.setNegativeButton(cancelResId, click);
		AlertDialog dialog = builder.create();
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
		dialog.show();
		return dialog;
	}

	/**
	 * Show dialog with two buttons.
	 * 
	 * @param context
	 * @param title_rid
	 * @param msg_rid
	 * @param okResId
	 * @param cancelResId
	 * @param click
	 */
	public static AlertDialog showSelectDialog(Context context, int title_rid,
			int msg_rid, int okResId, int cancelResId, boolean cancelable,
			OnClickListener click) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if(title_rid != -1){
			builder.setTitle(title_rid);
		}
		builder.setMessage(msg_rid);
		builder.setPositiveButton(okResId, click);
		builder.setNegativeButton(cancelResId, click);
		AlertDialog dialog = builder.create();
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
		dialog.show();
		return dialog;
	}


	//------------------------ProgressDialog--------------------------------------------

	/**
	 * Show progress dialog.
	 * 
	 * @param context
	 *            The context
	 * @param msg_rid
	 *            The id of message
	 * 
	 * @return The progress dialog
	 */
	public static ProgressDialog showProgressDialog(Context context, int msg_rid) {
		return showProgressDialog(context, 0, msg_rid);
	}

	/**
	 * Show progress dialog.
	 * 
	 * @param context
	 *            The context
	 * @param msg_rid
	 *            The id of message
	 * @param isCancelable
	 *            Is cancelable
	 * @param cancelListener
	 *            The listener for cancel event
	 * 
	 * @return The progress dialog
	 */
	public static ProgressDialog showProgressDialog(Context context,
			int msg_rid, boolean isCancelable,
			DialogInterface.OnCancelListener cancelListener) {
		return showProgressDialog(context, 0, msg_rid, isCancelable,
				cancelListener);
	}

	/**
	 * Show progress dialog.
	 * 
	 * @param context
	 *            The context
	 * @param title_rid
	 *            The id of title.
	 * @param msg_rid
	 *            The id of message.
	 * @return The progress dialog
	 */
	public static ProgressDialog showProgressDialog(Context context,
			int title_rid, int msg_rid) {
		return showProgressDialog(context, title_rid,
				context.getString(msg_rid));
	}

	/**
	 * Show progress dialog.
	 * 
	 * @param context
	 *            The context
	 * @param title_rid
	 *            The id of title
	 * @param msg_rid
	 *            The id of message
	 * @param isCancelable
	 *            Is cancelable
	 * @param cancelListener
	 *            The listener for cancel event
	 * 
	 * @return The progress dialog
	 */
	public static ProgressDialog showProgressDialog(Context context,
			int title_rid, int msg_rid, boolean isCancelable,
			DialogInterface.OnCancelListener cancelListener) {
		return showProgressDialog(context, title_rid,
				context.getString(msg_rid), isCancelable, cancelListener);
	}

	/**
	 * Show progress dialog.
	 * 
	 * @param context
	 *            The context
	 * @param title_rid
	 *            The id of title
	 * @param msg
	 *            The message text
	 * 
	 * @return The progress dialog
	 */
	public static ProgressDialog showProgressDialog(Context context,
			int title_rid, String msg) {
		return showProgressDialog(context, title_rid, msg, false, null);
	}

	/**
	 * Show progress dialog.
	 * 
	 * @param context
	 *            The context
	 * @param title_rid
	 *            The id of title
	 * @param msg
	 *            The message text
	 * @param isCancelable
	 *            Is cancelable
	 * @param cancelListener
	 *            The listener for cancel event
	 * 
	 * @return The progress dialog
	 */
	public static ProgressDialog showProgressDialog(Context context,
			int title_rid, String msg, boolean isCancelable,
			DialogInterface.OnCancelListener cancelListener) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		if (title_rid != 0) {
			progressDialog.setTitle(title_rid);
		}
		progressDialog.setMessage(msg);
		progressDialog.setCancelable(isCancelable);
		progressDialog.setOnCancelListener(cancelListener);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();

		return progressDialog;
	}

	//-------------------------------自定义----------------------------------------------

}
