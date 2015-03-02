package adapter;

import util.LruCacheManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewanimationdemo.R;

public class ListViewAdapter extends ArrayAdapter<Integer> {

	private Context mContext;

	public ListViewAdapter(Context context) {
		super(context, 0);
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.activity_listviews_googlecards_card, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.activity_googlecards_card_imageview);
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.activity_googlecards_card_textview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.textView.setText("This is card" + getItem(position) + 1);
		setImageView(viewHolder, position);
		return convertView;
	}

	private void setImageView(ViewHolder viewHolder, int position) {
		int imageResId;
		switch (getItem(position) % 6) {
		case 0:
			imageResId = R.drawable.girl_11;
			break;
		case 1:
			imageResId = R.drawable.girl_1;
			break;
		case 2:
			imageResId = R.drawable.girl_2;
			break;
		case 3:
			imageResId = R.drawable.girl_3;
			break;
		case 4:
			imageResId = R.drawable.girl_4;
		default:
			imageResId = R.drawable.girl_5;
		}

		Bitmap bitmap = LruCacheManager.getBitmapFromMemCache(imageResId);
		if (bitmap == null) {
			bitmap = BitmapFactory.decodeResource(mContext.getResources(),
					imageResId);
			LruCacheManager.addMemoryCache(imageResId, bitmap);
		}
		viewHolder.imageView.setImageBitmap(bitmap);
	}

	private static class ViewHolder {
		TextView textView;
		ImageView imageView;
	}
}
