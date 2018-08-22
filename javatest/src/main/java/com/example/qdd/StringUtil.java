package com.example.qdd;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 
 * 字符串工具类，用于MD5加密解密
 * 
 */
public class StringUtil {

//	/**
//	 * 格式化数据，保留指定的位数
//	 *
//	 * @param flt
//	 * @param count
//	 *            小数点后位数
//	 * @return
//	 */
//	public static String formatFloatStr(String flt, int count) {
//		if (TextUtils.isEmpty(flt)) {
//			if (count <= 0) {
//				return "0";
//			}
//			StringBuffer sb = new StringBuffer("0.");
//			for (int i = 0; i < count; i++) {
//				sb.append("0");
//			}
//			return sb.toString();
//		}
//		StringBuffer sb = new StringBuffer("0.00000");
//		LogProxy.v("format", sb.toString() + "----" + flt);
//		DecimalFormat df = new DecimalFormat(sb.toString()); // " "内写格式的模式
//																// 如保留2位就用"0.00"即可
//		String format = df.format(Float.parseFloat(flt));
//
//		return format.substring(0, format.indexOf(".") + count + 1);
//	}

	/**
	 * 格式化钱币，每三位加一逗号
	 *
	 * @param money
	 * @return
	 */
	public static String formatMoney(String money, int count) {
		// setMaximumFractionDigits(int) 设置数值的小数部分允许的最大位数。
		// setMaximumIntegerDigits(int) 设置数值的整数部分允许的最大位数。
		// setMinimumFractionDigits(int) 设置数值的小数部分允许的最小位数。
		// setMinimumIntegerDigits(int) 设置数值的整数部分允许的最小位数.
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(count);
		format.setMaximumFractionDigits(count);
		format.setMaximumIntegerDigits(20);
		format.setMinimumIntegerDigits(1);
		return format.format(new BigDecimal(money));
		// return format.format(Float.parseFloat(money));
	}

//	public static void addColorAndSize(final TextView textView, final int start, final int end, int color, int fontSize){
//		String text = textView.getText().toString().trim();
//		if(TextUtils.isEmpty(text) || start > end){
//			return;
//		}
//		SpannableStringBuilder builder = new SpannableStringBuilder(text);
//		if(color != 0) {
//			builder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		}
//		if(fontSize > 0) {
//			builder.setSpan(new AbsoluteSizeSpan(fontSize, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		}
//		textView.setText(builder);
//	}
}
