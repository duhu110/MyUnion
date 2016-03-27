package com.qhduhu.myunion.fragment;

import java.util.List;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.adapter.JfListAdapter;
import com.qhduhu.myunion.db.DBManager;
import com.qhduhu.myunion.entity.JfEntity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PYQFragment extends Fragment implements OnRefreshListener {
	private ListView listView;
	private JfListAdapter adapter;
	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private DBManager db;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_COMPLETE:
				getData();
				adapter.notifyDataSetChanged();
				mSwipeLayout.setRefreshing(false);
				break;

			}
		};
	};


	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pyq, null);
		listView = (ListView) view.findViewById(R.id.pyq_listview);
		
		getData();
		listView.setAdapter(adapter);
		return view;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSwipeLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.id_swipe_ly);
		mSwipeLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		mSwipeLayout.setOnRefreshListener(this);
	}

	private void getData() {
		List<JfEntity> list;
		db = new DBManager(getActivity());
		list = db.query();
		db.closeDB();
		adapter = new JfListAdapter(getActivity(), list);
	}


	public void onRefresh() {
		// Log.e("xxx", Thread.currentThread().getName());
		// UI Thread
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

	}
}