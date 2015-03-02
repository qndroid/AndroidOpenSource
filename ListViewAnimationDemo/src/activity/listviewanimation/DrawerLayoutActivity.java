package activity.listviewanimation;

import java.util.ArrayList;

import adapter.ListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listviewanimationdemo.R;
import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;

import constant.Constant;

public class DrawerLayoutActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawer;
	private ActionBarDrawerToggle mDrawerToggle;

	private ListView contentListView;
	private ListViewAdapter bottonInAdapter;
	private AnimationAdapter animationAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.drawer_layout);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawer = (ListView) findViewById(R.id.start_drawer);
		contentListView = (ListView) findViewById(R.id.activity_googlecards_listview);

		mDrawerLayout.setDrawerListener(new DemoDrawerListener());
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawer.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_layout_item, Constant.TITLES));
		mDrawer.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close);

		bottonInAdapter = new ListViewAdapter(this);
		bottonInAdapter.addAll(getItems());
		animationAdapter = new SwingBottomInAnimationAdapter(bottonInAdapter);
		animationAdapter.setAbsListView(contentListView);
		contentListView.setAdapter(animationAdapter);
	}

	private ArrayList<Integer> getItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			items.add(i);
		}
		return items;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * This list item click listener implements very simple view switching by
	 * changing the primary content text. The drawer is closed when a selection
	 * is made.
	 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {
			case 0:
				animationAdapter = new SwingBottomInAnimationAdapter(
						bottonInAdapter);
				break;
			case 1:
				animationAdapter = new SwingLeftInAnimationAdapter(
						bottonInAdapter);
				break;
			case 2:
				animationAdapter = new SwingRightInAnimationAdapter(
						bottonInAdapter);
				break;
			case 3:
				animationAdapter = new ScaleInAnimationAdapter(bottonInAdapter);
				break;
			case 4:
				startActivity(new Intent(DrawerLayoutActivity.this,
						DrogSortListViewActivity.class));
				break;
			// case 5:
			// startActivity(new Intent(DrawerLayoutActivity.this,
			// ViewPagerActivity.class));
			// break;
			// case 6:
			// startActivity(new Intent(DrawerLayoutActivity.this,
			// StaggridViewActivity.class));
			// break;
			}
			animationAdapter.setAbsListView(contentListView);
			contentListView.setAdapter(animationAdapter);

			mDrawerLayout.closeDrawer(mDrawer);
		}
	}

	private class DemoDrawerListener implements DrawerLayout.DrawerListener {
		@Override
		public void onDrawerOpened(View drawerView) {
			mDrawerToggle.onDrawerOpened(drawerView);
		}

		@Override
		public void onDrawerClosed(View drawerView) {
			mDrawerToggle.onDrawerClosed(drawerView);
		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
		}

		@Override
		public void onDrawerStateChanged(int newState) {
			mDrawerToggle.onDrawerStateChanged(newState);
		}
	}

}
