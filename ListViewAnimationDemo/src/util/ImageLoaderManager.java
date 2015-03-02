package util;

import android.graphics.Bitmap;

import com.example.listviewanimationdemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**********************************************************
 * @文件名称：ImageLoaderManager.java
 * @文件作者：renzhiqiang
 * @创建时间：2015年2月15日 上午11:41:34
 * @文件描述：为ImageLoader提供默认加载图,采用ImageLoader去加载网络或本地图资源
 * @修改历史：2015年2月15日创建初始版本
 **********************************************************/
public class ImageLoaderManager {
	private static ImageLoader imageLoader;

	static {
		imageLoader = ImageLoader.getInstance();
	}

	public static ImageLoader getImageLoader() {
		return imageLoader;
	}

	public static DisplayImageOptions getRecommendImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.mic_recommend_product_loading)
				.cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(1000)).build();
		return options;
	}
}
