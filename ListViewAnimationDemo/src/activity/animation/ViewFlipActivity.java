package activity.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import com.example.listviewanimationdemo.R;

public class ViewFlipActivity extends Activity implements
		AdapterView.OnItemSelectedListener {

	private ViewFlipper mFlipper;
	private String[] mStrings = { "Push up", "Push left", "Cross fade" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_flip_layout);
		mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
		mFlipper.startFlipping();

		Spinner s = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mStrings);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {

		case 0:
			mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_up_in));
			mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_up_out));
			break;
		case 1:
			mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_in));
			mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_out));
			break;
		case 2:
			mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_in));
			mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					android.R.anim.fade_out));
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
