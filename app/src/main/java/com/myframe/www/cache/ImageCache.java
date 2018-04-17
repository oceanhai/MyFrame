package com.myframe.www.cache;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.util.LruCache;
import com.myframe.www.app.MyApplication;

public class ImageCache {
	public static String APP_PATH_NAME = "ChaBaiKe";
	public static String EXTERNAL_STORAGE_PATH = Environment
			.getExternalStorageDirectory() + File.separator + APP_PATH_NAME;

	private static final int LRU_MAX_SIZE = 1024 * 1024;
	private static final long DISK_MAX_SIZE = 5 * 1024 * 1024;
	private static ImageCache cache = new ImageCache();

	Map<String, SoftReference<Bitmap>> softCache;
	private LruCache<String, Bitmap> lruCache;
	private DiskLruCache diskLruCache;

	private ImageCache() {
		lruCache = new LruCache<String, Bitmap>(LRU_MAX_SIZE) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}

			@Override
			protected void entryRemoved(boolean evicted, String key,
					Bitmap oldValue, Bitmap newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
				if (evicted) {
					System.out.println("删除了");
					remove(key);
					SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(
							oldValue);
					softCache.put(key, softReference);
				}
			}
		};
		softCache = new HashMap<String, SoftReference<Bitmap>>();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			diskLruCache = DiskLruCache.openCache(MyApplication.getContext(), new File(
					EXTERNAL_STORAGE_PATH), DISK_MAX_SIZE);
		}

	}

	public static ImageCache getInstance() {
		return cache;
	}

	public void putCache(String key, Bitmap value) {
		lruCache.put(key, value);
		if (diskLruCache != null) {
			diskLruCache.put(key, value);
		}
	}

	public Bitmap getCache(String key) {
		Bitmap bitmap = lruCache.get(key);
		if (bitmap == null) {
			SoftReference<Bitmap> softReference = softCache.get(key);
			if (softReference != null) {
				bitmap = softReference.get();
				if (bitmap == null) {
					if (diskLruCache != null) {
						bitmap = diskLruCache.get(key);
					} else {
						System.out.println("从diskLrucache中取出");
						return bitmap;
					}
				} else {
					System.out.println("从软引用中取出");
					return bitmap;
				}
			}

		} else {
			System.out.println("从lrucache中取出");
			return bitmap;
		}
		return bitmap;
	}

}
