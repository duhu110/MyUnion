package com.qhduhu.myunion.activity;

import com.qhduhu.myunion.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class TodoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
		default:
			return super.onOptionsItemSelected(item);}
	}
}
