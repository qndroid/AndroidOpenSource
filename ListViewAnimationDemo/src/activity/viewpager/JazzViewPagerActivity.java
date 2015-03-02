package activity.viewpager;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import util.Util;
import adapter.JazzPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.listviewanimationdemo.R;
import com.focustech.common.widget.residemenu.ResideMenu;
import com.focustech.common.widget.residemenu.ResideMenuItem;
import com.focustech.common.widget.viewpager.jazzviewpager.JazzyViewPager;
import com.focustech.common.widget.viewpager.jazzviewpager.JazzyViewPager.TransitionEffect;

@EActivity
public class JazzViewPagerActivity extends FragmentActivity implements
		OnClickListener {

	@ViewById(R.id.jazzy_pager)
	protected JazzyViewPager mJazzViewPager;

	private ResideMenu resideMenu;
	private ResideMenuItem stackItem, zoomInItem, tabletItem, rotateDownItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jazzy_main);
		setupJazziness(TransitionEffect.Stack);
		setUpMenu();
	}

	/**
	 * 构建ResideMenu
	 */
	private void setUpMenu() {
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.7f);
		resideMenu.setViewPager(mJazzViewPager);
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		// create menu items;
		stackItem = new ResideMenuItem(this, R.drawable.icon_settings, "ZoomIn");
		stackItem.setOnClickListener(this);
		zoomInItem = new ResideMenuItem(this, R.drawable.icon_settings, "Stack");
		zoomInItem.setOnClickListener(this);
		tabletItem = new ResideMenuItem(this, R.drawable.icon_settings,
				"Tablet");
		tabletItem.setOnClickListener(this);

		rotateDownItem = new ResideMenuItem(this, R.drawable.icon_settings,
				"RotateDown");
		rotateDownItem.setOnClickListener(this);
		resideMenu.addMenuItem(stackItem, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(zoomInItem, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(tabletItem, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(rotateDownItem, ResideMenu.DIRECTION_LEFT);
	}

	private void setupJazziness(TransitionEffect effact) {

		mJazzViewPager.setTransitionEffect(effact);
		mJazzViewPager.setAdapter(new JazzPagerAdapter(this, mJazzViewPager));
		mJazzViewPager.setPageMargin(Util.dip2px(15, this));
	}

	/**
	 * 优先将事件交给 ResideMenu去处理
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View v) {

		if (v == stackItem) {
			setupJazziness(TransitionEffect.ZoomIn);
		}
		if (v == zoomInItem) {

			setupJazziness(TransitionEffect.Stack);
		}
		if (v == tabletItem) {

			setupJazziness(TransitionEffect.Tablet);
		}
		if (v == rotateDownItem) {

			setupJazziness(TransitionEffect.RotateDown);
		}
		resideMenu.closeMenu();
	}
}
