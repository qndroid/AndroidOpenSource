package activity.animation;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.listviewanimationdemo.R;

public class GridViewAnimationActivity extends Activity {

	private List<ResolveInfo> mApps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		loadApps();
		setContentView(R.layout.gridview_animation_layout);
		GridView grid = (GridView) findViewById(R.id.grid);
		grid.setAdapter(new AppsAdapter());
	}

	private void loadApps() {
		// TODO Auto-generated method stub
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
	}

	public class AppsAdapter extends BaseAdapter {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView = new ImageView(GridViewAnimationActivity.this);
			ResolveInfo info = mApps.get(position % mApps.size());
			imageView.setImageDrawable(info.activityInfo
					.loadIcon(getPackageManager()));
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			final int w = (int) (45 * getResources().getDisplayMetrics().density + 0.5f);
			imageView.setLayoutParams(new GridView.LayoutParams(w, w));
			return imageView;
		}

		public final int getCount() {
			return Math.min(32, mApps.size());
		}

		public final Object getItem(int position) {
			return mApps.get(position % mApps.size());
		}

		public final long getItemId(int position) {
			return position;
		}
	}
}
