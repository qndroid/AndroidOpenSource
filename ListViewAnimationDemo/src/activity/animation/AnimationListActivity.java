package activity.animation;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.listviewanimationdemo.R;

@EActivity
public class AnimationListActivity extends Activity implements OnClickListener {

	@ViewById(R.id.view_flip_animation)
	protected TextView viewFlipBtn;
	@ViewById(R.id.grid_view_animation)
	protected TextView gridViewBtn;
	@ViewById(R.id.gallery_view_animation)
	protected TextView galleryTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_list_layout);
		viewFlipBtn.setOnClickListener(this);
		gridViewBtn.setOnClickListener(this);
		galleryTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.view_flip_animation:
			startActivity(new Intent(this, ViewFlipActivity.class));
			break;
		case R.id.grid_view_animation:
			startActivity(new Intent(this, GridViewAnimationActivity.class));
			break;
		case R.id.gallery_view_animation:
			startActivity(new Intent(this, CustomGalleryActivity.class));
			break;
		}
	}
}
