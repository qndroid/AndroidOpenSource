package activity.viewpager;

import util.Util;
import view.ColorAnimationView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.listviewanimationdemo.R;

import constant.Constant;

public class ViewPagerActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_backgroundcolor_main);

		MyFragmentStatePager adpter = new MyFragmentStatePager(
				getSupportFragmentManager());
		ColorAnimationView colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setPageMargin(30);
		viewPager.setAdapter(adpter);

		colorAnimationView.setmViewPager(viewPager,
				Constant.nature_images.length);
		colorAnimationView
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {
						Log.e("TAG", "onPageScrolled");
					}

					@Override
					public void onPageSelected(int position) {
						Log.e("TAG", "onPageSelected");
					}

					@Override
					public void onPageScrollStateChanged(int state) {
						Log.e("TAG", "onPageScrollStateChanged");
					}
				});
	}

	private static class MyFragmentStatePager extends FragmentStatePagerAdapter {

		public MyFragmentStatePager(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new MyFragment(position);
		}

		@Override
		public int getCount() {
			return Constant.nature_images.length;
		}
	}

	@SuppressLint("ValidFragment")
	private static class MyFragment extends Fragment {
		private int position;

		public MyFragment(int position) {
			this.position = position;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setImageBitmap(Util.decodeSampledBitmapFromResource(
					getActivity().getResources(),
					Constant.nature_images[position],
					Util.dip2px(50, getActivity()),
					Util.dip2px(50, getActivity())));
			return imageView;
		}
	}
}
