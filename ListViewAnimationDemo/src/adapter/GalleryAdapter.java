package adapter;

import util.ImageUtils;
import util.Util;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

/**
 * 高仿效果�?3D图片浏览的�?�配�?
 * 
 * @author Administrator
 *
 */
public class GalleryAdapter extends BaseAdapter {

	private Activity context;
	private int[] imageResIDs;

	public GalleryAdapter(Activity context, int[] imageResIDs) {
		this.context = context;
		this.imageResIDs = imageResIDs;
	}

	@Override
	public int getCount() {
		return imageResIDs.length;
	}

	@Override
	public Object getItem(int position) {
		return imageResIDs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView iv = null;
		if (convertView == null) {
			iv = new ImageView(context);
		} else {
			iv = (ImageView) convertView;
		}

		Bitmap bitmap = ImageUtils.getBitmap(context, imageResIDs[position],
				position);
		BitmapDrawable bd = new BitmapDrawable(context.getResources(), bitmap);
		bd.setAntiAlias(true);
		iv.setImageDrawable(bd);
		iv.setLayoutParams(new LayoutParams(Util.dip2px(160, context), (Util
				.dip2px(240, context))));
		return iv;
	}
}
