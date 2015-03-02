package util;

import java.lang.ref.SoftReference;
import java.util.Hashtable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.util.Log;

public class ImageUtils {

	// 存储图片的集�?(存在内存�?)
	private static Hashtable<Integer, SoftReference<Bitmap>> mCacheBitmapTable = new Hashtable<Integer, SoftReference<Bitmap>>();

	/**
	 * 根据id获得bitmap对象, 注意: 采用了SoftReference缓存,
	 * 
	 * @param context
	 * @param imageID
	 * @return 先去集合中根据imageID去取相对应的图片, 如果�?, 直接返回 如果没有,
	 *         调用getInvertImage方法得到�?个对象返�?
	 */
	public static Bitmap getBitmap(Context context, int imageID, int position) {

		SoftReference<Bitmap> softReference = mCacheBitmapTable.get(imageID);
		if (softReference != null) {
			Bitmap bitmap = softReference.get();
			if (bitmap != null) {
				Log.i("ImageUtils", "从内存中�?: " + position);
				return bitmap;
			}
		}

		Log.i("ImageUtils", "重新加载: " + position);
		Bitmap invertImage = getInvertImage(context, imageID);

		// 取出来对应的图片之后, �?内存中存�?�?, 为了方便下次直接去内存中�?
		mCacheBitmapTable.put(imageID, new SoftReference<Bitmap>(invertImage));
		return invertImage;
	}

	/**
	 * 根据给定的id获取处理(倒影, 倒影渐变)过的bitmap
	 * 
	 * @param imageID
	 * @return
	 */
	private static Bitmap getInvertImage(Context context, int imageID) {
		// 获得原图
		Options opts = new Options();
		opts.inSampleSize = 2;
		Bitmap sourceBitmap = BitmapFactory.decodeResource(
				context.getResources(), imageID, opts);

		// 倒影图片
		Matrix matrix = new Matrix();
		// 设置图片的反转为, 垂直反转
		matrix.setScale(1.0f, -1.0f);
		// float[] values = {
		// 1.0f, 0f, 0f,
		// 0f, -1.0f, 0f,
		// 0f, 0f, 1.0f
		// };
		// 倒影图片
		Bitmap invertBitmap = Bitmap.createBitmap(sourceBitmap, 0,
				sourceBitmap.getHeight() / 2, sourceBitmap.getWidth(),
				sourceBitmap.getHeight() / 2, matrix, false);

		// 合成�?张图�?
		Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(),
				(int) (sourceBitmap.getHeight() * 1.5 + 5), Config.ARGB_8888);

		// 把原图添加到合成图片的左上角
		Canvas canvas = new Canvas(resultBitmap); // 指定画板画在合成图片�?
		canvas.drawBitmap(sourceBitmap, 0, 0, null); // 把原图绘制到合成图上

		// 把�?�影图片绘制到合成图片上
		canvas.drawBitmap(invertBitmap, 0, sourceBitmap.getHeight() + 5, null);

		Rect rect = new Rect(0, sourceBitmap.getHeight() + 5,
				resultBitmap.getWidth(), resultBitmap.getHeight());
		Paint paint = new Paint();

		/**
		 * TileMode.CLAMP 指定渲染边界以外的控件以�?后的那个颜色继续�?下渲�?
		 */
		LinearGradient shader = new LinearGradient(0,
				sourceBitmap.getHeight() + 5, 0, resultBitmap.getHeight(),
				0x70FFFFFF, 0x00FFFFFF, TileMode.CLAMP);

		// 设置为取交集模式
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// 指定渲染器为线�?�渲染器
		paint.setShader(shader);
		canvas.drawRect(rect, paint);

		return resultBitmap;
	}
}
