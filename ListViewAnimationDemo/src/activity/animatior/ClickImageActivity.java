package activity.animatior;

import view.ClickImageView;
import view.ClickImageView.ClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.listviewanimationdemo.R;

public class ClickImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.click_image_layout);
		ClickImageView view = (ClickImageView) findViewById(R.id.image_view_1);
		view.setClickListener(new ClickListener() {
			@Override
			public void onClick() {
				Toast.makeText(ClickImageActivity.this, "ImageView±»µã»÷ÁË...",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
