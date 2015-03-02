package activity.animatior;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;

import com.example.listviewanimationdemo.R;
import com.focustech.common.widget.floatbutton.FloatingActionButton;

public class LayoutAnimationTransationActivity extends Activity implements
		OnCheckedChangeListener {

	private GridLayout mGridLayout;
	private int mVal;
	private LayoutTransition mTransation;

	private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;
	private FloatingActionButton floatButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_animator);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub

		mAppear = (CheckBox) findViewById(R.id.id_appear);
		mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
		mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
		mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);

		mAppear.setOnCheckedChangeListener(this);
		mChangeAppear.setOnCheckedChangeListener(this);
		mDisAppear.setOnCheckedChangeListener(this);
		mChangeDisAppear.setOnCheckedChangeListener(this);

		mGridLayout = (GridLayout) findViewById(R.id.grid_layout);
		mTransation = new LayoutTransition();
		mTransation.setAnimator(LayoutTransition.APPEARING, (mAppear
				.isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1)
				: null));
		mGridLayout.setLayoutTransition(mTransation);

		floatButton = (FloatingActionButton) findViewById(R.id.float_btn);
		floatButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addBtn(v);
			}
		});
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub

		mTransation.setAnimator(LayoutTransition.APPEARING, (mAppear
				.isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1)
				: null));

		mTransation
				.setAnimator(
						LayoutTransition.CHANGE_APPEARING,
						(mChangeAppear.isChecked() ? mTransation
								.getAnimator(LayoutTransition.CHANGE_APPEARING)
								: null));
		mTransation.setAnimator(
				LayoutTransition.DISAPPEARING,
				(mDisAppear.isChecked() ? mTransation
						.getAnimator(LayoutTransition.DISAPPEARING) : null));
		mTransation.setAnimator(
				LayoutTransition.CHANGE_DISAPPEARING,
				(mChangeDisAppear.isChecked() ? mTransation
						.getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
						: null));

		mGridLayout.setLayoutTransition(mTransation);
	}

	/**
	 * Ìí¼Ó°´Å¥
	 * 
	 * @param view
	 */
	public void addBtn(View view) {
		final Button button = new Button(this);
		button.setText((++mVal) + "");
		mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mGridLayout.removeView(button);
			}
		});
	}

}
