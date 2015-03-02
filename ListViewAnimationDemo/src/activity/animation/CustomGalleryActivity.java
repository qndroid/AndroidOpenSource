package activity.animation;

import view.CustomGallery;
import adapter.GalleryAdapter;
import android.app.Activity;
import android.os.Bundle;

import com.example.listviewanimationdemo.R;

import constant.Constant;

public class CustomGalleryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imitate_gallery);
		CustomGallery mCustomGallery = (CustomGallery) findViewById(R.id.custom_gallery);

		int[] imageResIDs = Constant.same_images;

		GalleryAdapter mAdapter = new GalleryAdapter(this, imageResIDs);
		mCustomGallery.setAdapter(mAdapter);
	}
}
