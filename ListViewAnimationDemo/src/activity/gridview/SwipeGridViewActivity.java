package activity.gridview;

import util.Util;
import adapter.GridViewAdapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.listviewanimationdemo.R;
import com.focustech.common.widget.swipelayout.implments.SwipeItemMangerImpl;

import constant.Constant;

public class SwipeGridViewActivity extends Activity {

	private GridView gridView;
	private GridViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);

		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new GridViewAdapter(this, Constant.images);
		adapter.setMode(SwipeItemMangerImpl.Mode.Multiple);
		gridView.setAdapter(adapter);
		gridView.setSelected(false);
		gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("onItemLongClick", "onItemLongClick:" + position);
				return false;
			}
		});
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("onItemClick", "onItemClick:" + position);
			}
		});

		gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("onItemSelected", "onItemSelected:" + position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	private Bitmap[] initData() {
		Bitmap[] bitmaps = new Bitmap[Constant.nature_images.length];
		for (int i = 0; i < Constant.nature_images.length; i++) {
			bitmaps[i] = Util.decodeSampledBitmapFromResource(getResources(),
					Constant.nature_images[i], Util.dip2px(50, this),
					Util.dip2px(50, this));
		}
		return bitmaps;
	}
}
