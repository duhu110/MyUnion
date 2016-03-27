package com.qhduhu.myunion.activity;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.fragment.MoreFragment;
import com.qhduhu.myunion.fragment.NewJFFragment;
import com.qhduhu.myunion.fragment.PYQFragment;
import com.qhduhu.myunion.fragment.TXLFragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements TabListener {
	private TXLFragment txlfragmt = new TXLFragment();
	private PYQFragment pyqfragmt = new PYQFragment();
	private NewJFFragment newjffragmt = new NewJFFragment();
	private MoreFragment morefragmt = new MoreFragment();
	private static final int TAB_INDEX_COUNT = 4;
	private static final int TAB_INDEX_ONE = 0;
	private static final int TAB_INDEX_TWO = 1;
	private static final int TAB_INDEX_THREE = 2;
	private static final int TAB_INDEX_FOUR = 3;
	private ViewPager mViewPager;
	private ViewPagerAdapter mViewPagerAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpActionBar();
		 setUpViewPager();
		 setUpTabs();
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
    	
    	mViewPager = (ViewPager)findViewById(R.id.pager);
    	mViewPager.setAdapter(mViewPagerAdapter);
    	mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
    		@Override
    		public void onPageSelected(int position) {
    			final ActionBar actionBar = getActionBar();
    			actionBar.setSelectedNavigationItem(position);
    		}
    		
    		@Override
    		public void onPageScrollStateChanged(int state) {
    			switch(state) {
    				case ViewPager.SCROLL_STATE_IDLE:
    					//TODO
    					break;
    				case ViewPager.SCROLL_STATE_DRAGGING:
    					//TODO
    					break;
    				case ViewPager.SCROLL_STATE_SETTLING:
    					//TODO
    					break;
    				default:
    					//TODO
    					break;
    			}
    		}
    	});
	}
	private void setUpTabs() {
		ActionBar actionBar = getActionBar();
    	for (int i = 0; i < mViewPagerAdapter.getCount(); ++i) {
    		actionBar.addTab(actionBar.newTab()
    				.setText(mViewPagerAdapter.getPageTitle(i))
    				.setTabListener(this));
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
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}
}
