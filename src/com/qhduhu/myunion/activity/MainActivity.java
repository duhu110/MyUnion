package com.qhduhu.myunion.activity;

import java.util.ArrayList;
import java.util.List;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.db.DBManager;
import com.qhduhu.myunion.entity.TXLEntity;
import com.qhduhu.myunion.fragment.MoreFragment;
import com.qhduhu.myunion.fragment.NewJFFragment;
import com.qhduhu.myunion.fragment.PYQFragment;
import com.qhduhu.myunion.fragment.TXLFragment;
import com.qhduhu.myunion.service.GetTXLListService;
import com.qhduhu.myunion.service.GetTXLListServiceIMPL;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity implements TabListener {
	private TXLFragment txlfragmt = new TXLFragment();
	private PYQFragment pyqfragmt = new PYQFragment();
	private GetTXLListService gettxllist = new GetTXLListServiceIMPL();
	private NewJFFragment newjffragmt = new NewJFFragment();
	private MoreFragment morefragmt = new MoreFragment();
	private static final int TAB_INDEX_COUNT = 4;
	private String TAG = "MainActivity++++++++TAG";
	private DBManager db;
	private static final int TAB_INDEX_ONE = 0;
	private static final int TAB_INDEX_TWO = 1;
	private static final int TAB_INDEX_THREE = 2;
	private static final int TAB_INDEX_FOUR = 3;
	private ViewPager mViewPager;
	private ViewPagerAdapter mViewPagerAdapter;
	ArrayList<Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpActionBar();
		setUpViewPager();
		setUpTabs();
		GettxlThread thread = new GettxlThread();
		thread.start();
	}

	class GettxlThread extends Thread {
		List<TXLEntity> list = new ArrayList<TXLEntity>();

		@Override
		public void run() {
			Log.d(TAG, "thread success");
			try {

				list = gettxllist.getTXLList();
				db = new DBManager(getApplicationContext());
			//	Log.d(TAG, list.get(3).txl_name);
				db.DeleteTXL();
				db.addtxl(list);
				db.closeDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void setUpActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
	}

	@SuppressWarnings("deprecation")
	private void setUpViewPager() {
		mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				final ActionBar actionBar = getActionBar();
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				switch (state) {
				case ViewPager.SCROLL_STATE_IDLE:
					// TODO
					break;
				case ViewPager.SCROLL_STATE_DRAGGING:
					// TODO
					break;
				case ViewPager.SCROLL_STATE_SETTLING:
					// TODO
					break;
				default:
					// TODO
					break;
				}
			}
		});
	}

	private void setUpTabs() {
		ActionBar actionBar = getActionBar();
		for (int i = 0; i < mViewPagerAdapter.getCount(); ++i) {
			actionBar.addTab(actionBar.newTab().setText(mViewPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	public class ViewPagerAdapter extends FragmentPagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case TAB_INDEX_ONE:
				return newjffragmt;
			case TAB_INDEX_TWO:
				return pyqfragmt;
			case TAB_INDEX_THREE:
				return txlfragmt;
			case TAB_INDEX_FOUR:
				return morefragmt;

			}
			throw new IllegalStateException("No fragment at position " + position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return TAB_INDEX_COUNT;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = (Fragment) super.instantiateItem(container, position);
			// String ftag = fragment.getTag();
			// FragmentTransaction ft =fm.beginTransaction();
			// //移除旧的fragment
			// ft.remove(fragment);
			// //换成新的fragment
			// fragment =fragments[position %fragments.length];
			// //添加新fragment时必须用前面获得的tag ❶
			// ft.add(container.getId(), fragment, fragmentTag);
			// ft.attach(fragment);
			// ft.commit();
			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String tabLabel = null;
			switch (position) {
			case TAB_INDEX_ONE:
				tabLabel = "新增积分";
				break;
			case TAB_INDEX_TWO:
				tabLabel = "工友圈";
				break;
			case TAB_INDEX_THREE:
				tabLabel = "通讯录";
				break;
			case TAB_INDEX_FOUR:
				tabLabel = "更多";
				break;
			}
			return tabLabel;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.menu_about:// 点击返回图标事件
			startActivity(new Intent(this, AboutActivity.class));
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
