package com.myframe.www.widget.customview;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


public class Utils {

	public static String dataFormatString(Date date) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String dateStr = f.format(date);
		return dateStr;
	}

	public static String dataFormatString(String dateStr) {
		Date date = string2Date(dateStr);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		return f.format(date);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		String dateStr = f.format(date) + " ";
		return dateStr;
	}

	public static String dateSecFormat(Date date) {
		DateFormat f = new SimpleDateFormat("HH:mm:ss:SSS", Locale.CHINA);
		String dateStr = f.format(date) + " ";
		return dateStr;
	}

	/**
	 * yyyy年MM月dd日 HH:mm
	 * 
	 * @param longTime
	 * @return
	 */
	public static String dateFormat(long longTime) {
		Date date = new Date(longTime);
		DateFormat f = new SimpleDateFormat("yyyy年MM月dd日HH:mm", Locale.CHINA);
		String dateStr = f.format(date) + " ";
		return dateStr;
	}

	/**
	 * yyyy-MM-dd HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat2(Date date) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
		String dateStr = f.format(date) + " ";
		return dateStr;
	}

	public static String getFilterDate(int num) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, num);
		Date date = gc.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		return formatter.format(date);
	}

	public static String formatResourceText(String str, int number) {
		return Html.fromHtml(String.format(str, number)).toString();
	}

	public static String formatResourceText(String resStr, String fillStr) {
		return Html.fromHtml(String.format(resStr, fillStr)).toString();
	}

	public static boolean checkStr(String str) {
		return str != null && str.trim().length() > 0;
	}

	public static boolean checkIlleChar(String str) {
		Pattern p = Pattern.compile("[.,\"\\?!:'#$%&()*+,-./:;<=>@^_`{|}~]");
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 对当前bitmap进行指定大小的缩放
	 * 
	 * @param oldBitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap oldBitmap, int width, int height) {
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / oldBitmap.getWidth());
		float scaleHeight = ((float) height / oldBitmap.getHeight());
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(oldBitmap, 0, 0,
				oldBitmap.getWidth(), oldBitmap.getHeight(), matrix, true);
		return newbmp;
	}

	/**
	 * 对图片进行缩放
	 * 
	 * @param size
	 * @param picfile
	 * @throws IOException
	 */
	public static void revitionImageSize(int size, File picfile)
			throws IOException {
		FileInputStream input = new FileInputStream(picfile);
		final BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(input, null, opts);
		input.close();
		int rate = 0;
		for (int i = 0;; i++) {
			if ((opts.outWidth >> i <= size) && (opts.outHeight >> i <= size)) {
				rate = i;
				break;
			}
		}
		input = new FileInputStream(picfile);
		opts.inSampleSize = (int) Math.pow(2, rate);
		opts.inJustDecodeBounds = false;
		Bitmap temp = null;
		try {
			temp = BitmapFactory.decodeStream(input, null, opts);
		} catch (OutOfMemoryError e) {
			opts.inSampleSize *= 2;
			temp = BitmapFactory.decodeStream(input, null, opts);
		}
		if (input != null) {
			input.close();
		}
		if (temp == null) {
			throw new IOException("Bitmap decode error!");
		}

		final FileOutputStream output = new FileOutputStream(picfile);
		if (opts != null && opts.outMimeType != null
				&& opts.outMimeType.contains("png")) {
			temp.compress(Bitmap.CompressFormat.PNG, 100, output);
		} else {
			temp.compress(Bitmap.CompressFormat.JPEG, 75, output);
		}
		if (output != null) {
			output.close();
		}
		temp.recycle();
	}

	/**
	 * 获取密度
	 * 
	 * @param acitivity
	 * @return
	 */
	public static float getDensity(Activity acitivity) {
		DisplayMetrics dm = new DisplayMetrics();
		Display d = acitivity.getWindowManager().getDefaultDisplay();
		d.getMetrics(dm);
		return dm.density;
	}

	/**
	 * 获取屏幕宽度和高度，即分辨率 返回数组：角标0为宽度，角标1为高度
	 * 
	 * @author luxu
	 */
	public static int[] getWH(Context context) {
		int[] whs = new int[2];
		DisplayMetrics dm = new DisplayMetrics();

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		whs[0] = dm.widthPixels;
		whs[1] = dm.heightPixels;

		return whs;
	}

	public static String formatFileSize(long size) {
		String result = "";
		int count = 0;
		while (size > 1024) {
			count++;
			size = size / 1024;
		}
		switch (count) {
		case 0:
			result = size + "B";
			break;
		case 1:
			result = size + "KB";
			break;
		case 2:
			result = size + "MB";
			break;
		case 3:
			result = size + "GB";
			break;
		}
		return result;
	}

	/**
	 * 分转换成元
	 * 
	 * @param price
	 * @return
	 */
	public static float converYuan(float price) {
		return price / 100;
	}

	/**
	 * 分转换成元
	 * 
	 * @param price
	 * @return
	 */
	public static String converYuanTwoDecimals(float price) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(converYuan(price));
	}

	public static boolean compareLong(long a, long b) {
		return a >= b;
	}

	/**
	 * 转换成原图的url
	 * 
	 * @param cover
	 * @return
	 */
	public static String converCoverSize(String cover) {
		if (cover == null || cover.length() == 0 || cover.equals("null")) {
			return cover;
		}
		try {
			int index = cover.lastIndexOf("_");
			String s1 = cover.substring(0, index);
			String s2 = cover.substring(index, cover.length());
			// System.out.println(s2);
			s2 = s2.replaceFirst("l", "o");
			return s1 + s2;
		} catch (Exception e) {
			e.printStackTrace();
			return cover;
		}
	}

	public static boolean isNumeric(String str) {
		if (TextUtils.isEmpty(str)) {
			return false;
		}

		Pattern pattern = Pattern.compile("-?[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static String removeEbook(String name) {

		String afterE = "(电子书)";
		if (name != null && name.contains(afterE)) {
			int lastIndex = name.lastIndexOf(afterE);
			if (lastIndex > 0) {
				name = name.substring(0, lastIndex);
			}

		}
		return name;
	}

	// ---合并 reader 阅读器 util 方法 start
	public static String getCurrentTime() {
		DateFormat f = new SimpleDateFormat("HH:mm", Locale.CHINA);
		String dateStr = f.format(new Date());
		return dateStr;
	}

	public static String dateFormatLong(Date date) {
		DateFormat f = new SimpleDateFormat("yyyyMMddHHMMSS", Locale.CHINA);
		String dateStr = f.format(date);
		return dateStr;
	}

	public static String stringDateFormatLong(String date) {
		Date datee = string2Date(date);
		DateFormat f = new SimpleDateFormat("yyyyMMddHHMMSS", Locale.CHINA);
		String dateStr = f.format(datee);
		return dateStr;
	}

	public static Date string2Date(String date) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Date datee = null;
		try {
			datee = f.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datee;
	}

	public static String long2DateString(long ldate) {
		String sdate = "";
		sdate = android.text.format.DateFormat
				.format("yyyy-MM-dd kk:mm", ldate).toString();
		return sdate;
	}

	/**
	 * 
	 * @param 要转换的毫秒数
	 * @return 该毫秒数转换为 * days * hours 后的格式
	 * @author liupengcheng
	 */
	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		if (days > 0) {
//			不显示小时
			if (hours > 12)
//				return days + "天" + hours + "小时";
				return (days + 1) + "天";
			else
				return days + "天";
		} else if (hours > 0)
			return hours + "小时";
		else if (minutes > 0)
			return minutes + "分钟";
		else
			return seconds + "秒";

	}

	// ---合并 reader 阅读器 util 方法 end

	public static boolean isStringEmpty(String v) {
		if (v == null || v.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static float retainDecimal(float srcf, int num) {
		BigDecimal b = new BigDecimal(srcf);
		return b.setScale(num, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 根据字节截取 字符串
	 * 
	 * @param str
	 *            待截取字符串
	 * @param len
	 *            字节长度
	 * @return
	 */
	public static String getStringByBytes(String str, int len) {

		String ret = "";
		byte[] bt = null;
		try {
			bt = str.getBytes("GBK");

			if (len == 0)
				return "";
			if (len == 1) {
				if (bt[0] > 0) {
					return str.substring(0, 1);
				} else {
					return "";
				}
			}

			if (bt.length <= len) {
				ret = str;
			} else {

				if (bt[len - 1] > 0) {
					ret = new String(bt, 0, len);
				} else {
					int count = 0;
					for (int i = len - 1; i >= 0; i--) {
						if (bt[i] >= 0) {
							break;
						}
						count++;
					}
					if (count % 2 == 0) {
						ret = new String(bt, 0, len, "GBK");
					} else {
						ret = new String(bt, 0, len - 1, "GBK");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * 根据字节截取 字符串
	 * 
	 * @param str
	 *            待截取字符串
	 * @param len
	 *            字节长度
	 * @param strEndFlag
	 *            字符串结尾标记
	 * @return
	 */
	public static String getStringByBytes(String str, int len, String strEndFlag) {

		String ret = "";
		byte[] bt = null;
		try {
			bt = str.getBytes("GBK");

			if (len == 0) {
				return "";
			}

			if (len == 1) {
				if (bt[0] > 0) {
					return str.substring(0, 1);
				} else {
					return "";
				}
			}

			if (bt.length <= len) {
				ret = str;
			} else {
				if (bt[len - 1] > 0) {
					ret = new String(bt, 0, len);
				} else {
					int count = 0;
					for (int i = len - 1; i >= 0; i--) {
						if (bt[i] >= 0) {
							break;
						}
						count++;
					}
					if (count % 2 == 0) {
						ret = new String(bt, 0, len, "GBK");
					} else {
						ret = new String(bt, 0, len - 1, "GBK");
					}
				}
				ret = ret + strEndFlag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}


	/**
	 * 数据是否为空
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean isArrEmpty(Object[] arr) {
		return arr == null || arr.length == 0;
	}

	/**
	 * 集合是否为空
	 * 
	 * @param colls
	 * @return
	 */
	public static boolean isCollEmpty(Collection<?> colls) {
		return colls == null || colls.size() == 0;
	}

	/**
	 * 转换毫秒数
	 * 
	 * @param ms
	 *            毫秒
	 * @return 距离当前时间1天内，返回xx小时前，大于一天显示具体时间xx-xx-xx
	 */
	public static String formatMilliSecond(long ms) {
		String str = null;
		long currentTimeMillis = System.currentTimeMillis();
		long deltaMs = currentTimeMillis - ms;
		long day = deltaMs / (24 * 60 * 60 * 1000);
		if (day == 0) {
			int hours = (int) (deltaMs / (60 * 60 * 1000));
			if (!(deltaMs % (60 * 60 * 1000) == 0)) {
				hours += 1;
			}
			str = hours + "小时前";
		} else {
			Date date = new Date(ms);
			DateFormat f = new SimpleDateFormat("yy-MM-dd", Locale.CHINA);
			str = f.format(date);
		}
		return str;
	}
	
	public static void showSoftInput(final View view){
		if(view != null){
			view.requestFocus();
			Timer timer = new Timer();
		    timer.schedule(new TimerTask(){
		         public void run() {
		             InputMethodManager inputManager =(InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		             inputManager.showSoftInput(view, 0);
		         }
		     }, 200);
		}
	}
	
	public static void hideSoftInput(View view){
		if(view != null){
			InputMethodManager inputManager =(InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(view.getWindowToken(),0);
		}
	}

	public static void hideInput(Context context) {
		try {
			InputMethodManager imm = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm.isActive())
				imm.hideSoftInputFromWindow(((Activity) context)
						.getCurrentFocus().getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	
    /**
     * @param input
     * @return把全角字符转换成半角
     */
    public static String ToDBC(String input) {  
        char[] c = input.toCharArray();  
        for (int i = 0; i< c.length; i++) {  
            if (c[i] == 12288) {  
              c[i] = (char) 32;  
              continue;  
            }if (c[i]> 65280&& c[i]< 65375)  
               c[i] = (char) (c[i] - 65248);  
            }  
        return new String(c);  
     }  

	
}
