package com.qhduhu.myunion.fragment;

import java.util.List;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.adapter.JfListAdapter;
import com.qhduhu.myunion.db.DBManager;
import com.qhduhu.myunion.entity.JfEntity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PYQFragment extends Fragment implements OnRefreshListener {
	private ListView listView;
	private JfListAdapter adapter;
	private SwipeRefreshLayout mSwipeLayout;
	private DBManager db;


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
		Log.d("getdata", list.get(1).jf_descrp);
		db.closeDB();
		adapter = new JfListAdapter(getActivity(), list);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
	}

	@Override
	public void onResume() {
		onRefresh();
		super.onResume();
	}

	public void onRefresh() {
		//getData();
		List<JfEntity> list;
		db = new DBManager(getActivity());
		list = db.query();
		Log.d("getdata", list.get(0).jf_descrp);
		db.closeDB();
		adapter = new JfListAdapter(getActivity(), list);
		//listView.invalidateViews();
		listView.setAdapter(adapter);
		//adapter.notifyDataSetChanged();
		Log.d("下拉熟悉", "数据更新成功");
		//listView.invalidateViews();
	//	Log.d("下拉熟悉", "数据更新成功");
		db.closeDB();
		mSwipeLayout.setRefreshing(false);

	}
}