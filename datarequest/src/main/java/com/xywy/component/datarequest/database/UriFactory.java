package com.xywy.component.datarequest.database;

import android.net.Uri;

public class UriFactory {

	private static final String HOST = "content://";

	private static final String SPECIAL_INSERT_TAG = "===";

	public static final int NO_INSERT_CONFIG_TAG = -100000000;

	public static Uri getUri(Class<? extends BaseContentProvider> providerType,
			Class<? extends BaseTableMode> mode) {
		Uri uri = Uri.parse(HOST + getProviderAuth(providerType) + "/"
				+ mode.getName());
		return uri;
	}

	public static Uri getSpecialTagUri(
			Class<? extends BaseContentProvider> providerType, String specialTag) {
		Uri uri = Uri.parse(HOST + getProviderAuth(providerType) + "/"
				+ specialTag);
		return uri;
	}

	public static Uri getSpecialInsertUri(
			Class<? extends BaseContentProvider> providerType,
			Class<? extends BaseTableMode> mode, int specialConfig) {
		StringBuffer sb = new StringBuffer();
		sb.append(SPECIAL_INSERT_TAG);
		sb.append(specialConfig);
		Uri uri = Uri.withAppendedPath(getUri(providerType, mode),
				sb.toString());
		return uri;
	}

	public static int parserSpecialInsertUriConfig(Uri uri) {
		String path = uri.getPath();
		int start = path.indexOf(SPECIAL_INSERT_TAG);
		if (start == -1) {
			return NO_INSERT_CONFIG_TAG;
		}
		start = start + SPECIAL_INSERT_TAG.length();
		path = path.substring(start);
		return Integer.valueOf(path);
	}

	public static Uri getHostUri(
			Class<? extends BaseContentProvider> providerType) {
		return Uri.parse(HOST + getProviderAuth(providerType));
	}

	public static String getProviderAuth(
			Class<? extends BaseContentProvider> providerType) {
		BaseContentProviderNode node = providerType
				.getAnnotation(BaseContentProviderNode.class);
		return node.authorities();
	}
}
