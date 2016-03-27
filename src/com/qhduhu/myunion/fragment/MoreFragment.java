package com.qhduhu.myunion.fragment;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.activity.AboutActivity;
import com.qhduhu.myunion.activity.TodoActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MoreFragment extends Fragment implements OnClickListener {

	private RelativeLayout more1, more2, more3, more4;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_more, null);
		more1 = (RelativeLayout) view.findViewById(R.id.more_1);
		more2 = (RelativeLayout) view.findViewById(R.id.more_2);
		more3 = (RelativeLayout) view.findViewById(R.id.more_3);
		more4 = (RelativeLayout) view.findViewById(R.id.more_4);
		more1.setOnClickListener(this);
		more2.setOnClickListener(this);
		more3.setOnClickListener(this);
		more4.setOnClickListener(this);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more_1:
			startActivity(new Intent(getActivity(), TodoActivity.class));
			break;
		case R.id.more_2:
			startActivity(new Intent(getActivity(), TodoActivity.class));
			break;
		case R.id.more_3:
			startActivity(new Intent(getActivity(), TodoActivity.class));
			break;
		case R.id.more_4:
			startActivity(new Intent(getActivity(), AboutActivity.class));
			break;
		}

	}

}
