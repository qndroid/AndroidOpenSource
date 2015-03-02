package adapter;

import util.Util;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.focustech.common.widget.viewpager.jazzviewpager.JazzyViewPager;
import com.focustech.common.widget.viewpager.jazzviewpager.OutlineContainer;

import constant.Constant;

public class JazzPagerAdapter extends PagerAdapter {

	private Activity activity;
	private JazzyViewPager jazzPager;

	public JazzPagerAdapter(Activity activity, JazzyViewPager pager) {

		this.activity = activity;
		this.jazzPager = pager;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		ImageView iamgeView = new ImageView(activity);
		iamgeView.setPadding(Util.dip2px(10, activity),
				Util.dip2px(10, activity), Util.dip2px(10, activity),
				Util.dip2px(10, activity));
		int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
				(int) Math.floor(Math.random() * 128) + 64,
				(int) Math.floor(Math.random() * 128) + 64);
		iamgeView.setBackgroundColor(bg);
		iamgeView.setImageResource(Constant.nature_images[position]);
		container.addView(iamgeView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		jazzPager.setObjectForPosition(iamgeView, position);
		return iamgeView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object obj) {
		container.removeView((View) obj);
	}

	@Override
	public int getCount() {
		return Constant.nature_images.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		if (view instanceof OutlineContainer) {
			return ((OutlineContainer) view).getChildAt(0) == obj;
		} else {
			return view == obj;
		}
	}
}
