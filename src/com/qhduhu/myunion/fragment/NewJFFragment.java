package com.qhduhu.myunion.fragment;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.activity.AddNewJfActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class NewJFFragment extends Fragment implements OnClickListener {
	private int readTypeCode = 1;
	private int sportTypeCode = 2;
	private int chessTypeCode = 3;
	private int devTypeCode = 4;
	RelativeLayout read,sport,dev,chess;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_newjf, null);
		read=(RelativeLayout) view.findViewById(R.id.new_readbook);
		sport=(RelativeLayout) view.findViewById(R.id.new_sport);
		dev=(RelativeLayout) view.findViewById(R.id.new_dev);
		chess=(RelativeLayout) view.findViewById(R.id.new_chess);
		read.setOnClickListener(this);
		sport.setOnClickListener(this);
		dev.setOnClickListener(this);
		chess.setOnClickListener(this);
		

		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_readbook:
			startActivity(new Intent(getActivity(), AddNewJfActivity.class).putExtra("typeCode", readTypeCode));
			break;
		case R.id.new_sport:
			startActivity(new Intent(getActivity(), AddNewJfActivity.class).putExtra("typeCode", sportTypeCode));
			break;
		case R.id.new_dev:
				startActivity(new Intent(getActivity(), AddNewJfActivity.class).putExtra("typeCode", devTypeCode));
		
		break;
		case R.id.new_chess:
			startActivity(new Intent(getActivity(), AddNewJfActivity.class).putExtra("typeCode", chessTypeCode));
			break;
		}
		
	}


}
