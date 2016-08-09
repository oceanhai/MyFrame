package com.myframe.www.utils;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myframe.www.R;


/**
 * Toast工具类
 * 
 */
public class ToastUtils {
	private static int GRAVITY = Gravity.CENTER;

	public static void showLong(Context context, String message) {
		show(context, message, Toast.LENGTH_LONG);
	}

	public static void showShort(Context context, String message) {
		show(context, message, Toast.LENGTH_SHORT);
	}

	public static void showLong(Context context, int textId) {
		show(context, textId, Toast.LENGTH_LONG);
	}

	public static void showShort(Context context, int textId) {
		show(context, textId, Toast.LENGTH_SHORT);
	}
	

	public static void show(Context context, String text, int duration) {
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	public static void show(Context context, int textId, int duration) {
		Toast toast = Toast.makeText(context, textId, duration);
		toast.setGravity(GRAVITY, 0, 0);
 		toast.show();
	}
	public static void showCenter(Context context, String text, int imageId) {
		Toast toast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_toast,null);
		ImageView image =(ImageView) view.findViewById(R.id.img);
		TextView tv = (TextView)view.findViewById(R.id.tipTextView);
		tv.setText(text);
		image.setImageResource(imageId);
		toast.setView(view);
		toast.setGravity(GRAVITY, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}
	
}