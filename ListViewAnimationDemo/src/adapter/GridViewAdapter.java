package adapter;

import util.ImageLoaderManager;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.listviewanimationdemo.R;
import com.focustech.common.widget.swipelayout.adapters.BaseSwipeAdapter;

public class GridViewAdapter extends BaseSwipeAdapter {

	private Activity mContext;
	private String[] lists;

	public GridViewAdapter(Activity mContext, String[] lists) {
		this.mContext = mContext;
		this.lists = lists;
	}

	@Override
	public int getCount() {
		return lists.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public void fillValues(int position, View view) {

		ImageView imageView = (ImageView) view.findViewById(R.id.position);
		/**
		 * 可以手动把图压缩
		 */
		// imageView.setImageBitmap(lists[position]);
		ImageLoaderManager.getImageLoader().displayImage(lists[position],
				imageView, ImageLoaderManager.getRecommendImageOptions());
	}

	@Override
	public View generateView(int arg0, ViewGroup arg1) {
		return LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
	}

	@Override
	public int getSwipeLayoutResourceId(int arg0) {
		// TODO Auto-generated method stub
		return R.id.swipe;
	}

}
