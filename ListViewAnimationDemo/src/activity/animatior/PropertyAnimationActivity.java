package activity.animatior;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.listviewanimationdemo.R;

/**
 * @author renzhiqiang 属性动画效果列表
 */
@EActivity
public class PropertyAnimationActivity extends Activity implements
		OnClickListener {

	@ViewById(R.id.property_normal)
	protected TextView propertyNormalTextView;
	@ViewById(R.id.property_flakes)
	protected TextView propertyFlakes;
	@ViewById(R.id.container)
	protected LinearLayout container;
	@ViewById(R.id.layout_transation)
	protected TextView layoutAnimBtn;
	@ViewById(R.id.secret_textview)
	protected TextView secretTextView;
	@ViewById(R.id.click_imageview)
	protected TextView clickImageView;
	@ViewById(R.id.auto_scrollview)
	protected TextView autoScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.property_animation_layout);
		propertyNormalTextView.setOnClickListener(this);
		{
			propertyFlakes.setOnClickListener(this);
			layoutAnimBtn.setOnClickListener(this);
			secretTextView.setOnClickListener(this);
			clickImageView.setOnClickListener(this);
			autoScrollView.setOnClickListener(this);
		}

		Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
		LayoutAnimationController mAnimationControll = new LayoutAnimationController(
				animation);
		container.setLayoutAnimation(mAnimationControll);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.property_normal:
			startActivity(new Intent(this, ToggleActivity_.class));
			break;
		case R.id.property_flakes:
			startActivity(new Intent(this, DroidFlakesActivity.class));
			break;
		case R.id.layout_transation:
			startActivity(new Intent(this,
					LayoutAnimationTransationActivity.class));
			break;
		case R.id.secret_textview:
			startActivity(new Intent(this, SecretTextViewActivity.class));
			break;
		case R.id.click_imageview:
			startActivity(new Intent(this, ClickImageActivity.class));
			break;
		case R.id.auto_scrollview:
			startActivity(new Intent(this, AutoScrollActivity.class));
			break;
		}
	}
}
