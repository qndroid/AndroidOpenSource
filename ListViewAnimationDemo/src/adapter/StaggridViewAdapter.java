package adapter;

import java.util.ArrayList;

import util.LruCacheManager;
import util.Util;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.listviewanimationdemo.R;
import com.focustech.common.widget.staggridview.util.DynamicHeightImageView;

import constant.Constant;

public class StaggridViewAdapter extends BaseAdapter {

	private Activity context;
	private ArrayList<Integer> mList = new ArrayList<Integer>();
	private ViewHolder holder;

	public StaggridViewAdapter(Activity context, ArrayList<Integer> mList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mList = mList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.my_grid_item, null);
			holder.mImageView = (DynamicHeightImageView) convertView
					.findViewById(R.id.my_image_view);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mImageView.setHeightRatio(getRandomHeightRatio(position));
		setImageView(holder, position);
		return convertView;
	}

	class ViewHolder {

		private DynamicHeightImageView mImageView;
	}

	private double getRandomHeightRatio(int pos) {
		if (pos % 2 == 0) {
			return (1.0); // height will be 1.0 -
		} else {
			return 1.5;
		}
	}

	public void addData(ArrayList<Integer> list) {
		mList.addAll(list);
		notifyDataSetChanged();
	}

	private void setImageView(ViewHolder viewHolder, int position) {
		int imageResId = position % 15;
		Bitmap bitmap = LruCacheManager.getBitmapFromMemCache(imageResId);
		if (bitmap == null) {
			bitmap = Util.decodeSampledBitmapFromResource(
					context.getResources(), Constant.nature_images[imageResId],
					Util.dip2px(160, context), Util.dip2px(130, context));
			LruCacheManager.addMemoryCache(imageResId, bitmap);
		}
		viewHolder.mImageView.setImageBitmap(bitmap);
	}
}
