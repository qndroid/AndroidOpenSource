package activity.gridview;

import java.util.ArrayList;

import util.Util;
import view.SampleListFragment;
import view.SampleListFragment.ItemClick;
import adapter.StaggridViewAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.example.listviewanimationdemo.R;
import com.focustech.common.widget.slidingmenu.SlidingMenu;
import com.focustech.common.widget.slidingmenu.app.SlidingFragmentActivity;
import com.focustech.common.widget.staggridview.StaggeredGridView;
import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;

import constant.Constant;

public class StaggridViewActivity extends SlidingFragmentActivity implements
		ItemClick {

	private StaggeredGridView mGridView;
	private StaggridViewAdapter mAdapter;
	private ArrayList<Integer> mData;
	protected boolean mHasRequestedMode;
	private AnimationAdapter animationAdapter;

	private SampleListFragment fragment;
	private SlidingMenu sm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initMenu();

		setContentView(R.layout.activity_sgv);
		setTitle("StaggridView");
		initData();
		mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
		mAdapter = new StaggridViewAdapter(this, mData);

		animationAdapter = new SwingBottomInAnimationAdapter(mAdapter);
		animationAdapter.setAbsListView(mGridView);
		mGridView.setAdapter(animationAdapter);
		mGridView.setOnScrollListener(scrollListener);
	}

	/**
	 * 初始化SldingMenu
	 */
	private void initMenu() {

		setBehindContentView(R.layout.menu_frame);
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		fragment = new SampleListFragment(this);
		t.replace(R.id.menu_frame, fragment);
		t.commit();

		sm = getSlidingMenu();
		sm.setShadowWidth(Util.dip2px(15, this));
		sm.setMode(SlidingMenu.RIGHT);
		sm.setShadowDrawable(R.drawable.drawer_shadow);
		sm.setBehindOffset(Util.dip2px(120, this));
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

	}

	private OnScrollListener scrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

			if (!mHasRequestedMode) {
				int lastInScreen = firstVisibleItem + visibleItemCount;
				if (lastInScreen >= totalItemCount) {

					mHasRequestedMode = true; // 用来控制滑动是否加载标志位
					onLoadMoreItems();
				}
			}
		}

		private void onLoadMoreItems() {
			// TODO Auto-generated method stub
			mAdapter.addData(mData);
			mHasRequestedMode = false;
		}
	};

	private void initData() {

		mData = new ArrayList<Integer>();
		for (int i = 0; i < Constant.nature_images.length; i++) {
			mData.add(Constant.nature_images[i]);
		}
	}

	@Override
	public void onClick(int position) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			animationAdapter = new SwingBottomInAnimationAdapter(mAdapter);
			break;
		case 1:
			animationAdapter = new SwingLeftInAnimationAdapter(mAdapter);
			break;
		case 2:
			animationAdapter = new SwingRightInAnimationAdapter(mAdapter);
			break;
		case 3:
			animationAdapter = new ScaleInAnimationAdapter(mAdapter);
			break;
		}
		animationAdapter.setAbsListView(mGridView);
		mGridView.setAdapter(animationAdapter);
		sm.toggle();
	}

}
