package util;

import android.graphics.Bitmap;
import android.util.LruCache;

public class LruCacheManager {

	private static LruCache<Integer, Bitmap> mMemoryCache;

	static {
		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		int cacheSize = maxMemory;
		mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(Integer key, Bitmap value) {
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};
	}

	public static void addMemoryCache(int key, Bitmap bitmap) {

		if (getBitmapFromMemCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public static Bitmap getBitmapFromMemCache(int key) {
		return mMemoryCache.get(key);
	}
}
