package activity.swipelayout;

import view.AnimationLinearLayout;
import activity.animation.AnimationListActivity_;
import activity.animatior.PropertyAnimationActivity_;
import activity.gridview.StaggridViewActivity;
import activity.gridview.SwipeGridViewActivity;
import activity.listviewanimation.DrawerLayoutActivity;
import activity.listviewanimation.DrogSortListViewActivity;
import activity.viewpager.JazzViewPagerActivity_;
import activity.viewpager.ViewPagerActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.listviewanimationdemo.R;
import com.focustech.common.widget.swipelayout.SwipeLayout;

public class SwipeActivity extends Activity implements OnClickListener {

	private SwipeLayout sample1, sample2, sample3;
	private AnimationLinearLayout viewAnimationLayout, propertyAnimationLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// sample1
		sample1 = (SwipeLayout) findViewById(R.id.sample1);
		sample1.setShowMode(SwipeLayout.ShowMode.LayDown);
		sample1.setDragEdge(SwipeLayout.DragEdge.Left);
		sample1.findViewById(R.id.animation).setOnClickListener(this);
		sample1.findViewById(R.id.drag).setOnClickListener(this);

		// sample2
		sample2 = (SwipeLayout) findViewById(R.id.sample2);
		sample2.setShowMode(SwipeLayout.ShowMode.LayDown);
		sample2.findViewById(R.id.star).setOnClickListener(this);
		sample2.findViewById(R.id.magnifier).setOnClickListener(this);
		// sample3
		sample3 = (SwipeLayout) findViewById(R.id.sample3);
		sample3.setShowMode(SwipeLayout.ShowMode.LayDown);
		sample3.setDragEdge(SwipeLayout.DragEdge.Left);
		sample3.findViewById(R.id.view_pager_color).setOnClickListener(this);
		sample3.findViewById(R.id.view_pager_scroll).setOnClickListener(this);

		// 属性动画布局
		propertyAnimationLayout = (AnimationLinearLayout) findViewById(R.id.animation_property);
		propertyAnimationLayout.setOnClickListener(this);
		// View动画布局
		viewAnimationLayout = (AnimationLinearLayout) findViewById(R.id.animation_view);
		viewAnimationLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.animation:
			startActivity(new Intent(SwipeActivity.this,
					DrawerLayoutActivity.class));
			break;
		case R.id.drag:
			startActivity(new Intent(SwipeActivity.this,
					DrogSortListViewActivity.class));
			break;
		case R.id.star:
			startActivity(new Intent(SwipeActivity.this,
					StaggridViewActivity.class));
			break;
		case R.id.magnifier:
			startActivity(new Intent(SwipeActivity.this,
					SwipeGridViewActivity.class));
			break;
		case R.id.view_pager_color:
			startActivity(new Intent(SwipeActivity.this,
					ViewPagerActivity.class));
			break;
		case R.id.view_pager_scroll:
			startActivity(new Intent(SwipeActivity.this,
					JazzViewPagerActivity_.class));
		case R.id.animation_property:
			startActivity(new Intent(SwipeActivity.this,
					PropertyAnimationActivity_.class));
			break;
		case R.id.animation_view:
			startActivity(new Intent(SwipeActivity.this,
					AnimationListActivity_.class));
			/**
			 * 为两个Activity添加出入场动画
			 */
			overridePendingTransition(R.anim.in_translate_top,
					R.anim.out_translate_top);
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		}
		return super.onKeyDown(keyCode, event);
	}
}
