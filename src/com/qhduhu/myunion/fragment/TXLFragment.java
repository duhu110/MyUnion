package com.qhduhu.myunion.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.db.DBManager;
import com.qhduhu.myunion.entity.TXLEntity;
import com.qhduhu.myunion.sortlist.CharacterParser;
import com.qhduhu.myunion.sortlist.ClearEditText;
import com.qhduhu.myunion.sortlist.PinyinComparator;
import com.qhduhu.myunion.sortlist.SideBar;
import com.qhduhu.myunion.sortlist.SideBar.OnTouchingLetterChangedListener;
import com.qhduhu.myunion.sortlist.SortAdapter;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class TXLFragment extends Fragment {
	private DBManager dbManager;
	private String TAG = "TXLFragment---TAG";
	private ListView listView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	
	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<TXLEntity> SourceDateList;

	/**
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;



	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_txl, null);

		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		dbManager = new DBManager(getActivity());

		sideBar = (SideBar) view.findViewById(R.id.sidrbar);
		dialog = (TextView) view.findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// ����ĸ�״γ��ֵ�λ��
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					listView.setSelection(position);
				}

			}
		});
		listView = (ListView) view.findViewById(R.id.country_lvcountry);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Toast.makeText(getActivity(), "�볤���б���", Toast.LENGTH_SHORT).show();	
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
					
					@Override
					public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
						menu.setHeaderTitle("ѡ�����");
						menu.add(0, 1, Menu.NONE, "����绰");
						menu.add(0, 2, Menu.NONE, "���Ͷ���");
						menu.add(0, 3, Menu.NONE, "���Ƶ����а�");
						menu.add(0, 4, Menu.NONE, "�����ʼ�");
						menu.add(0, 5, Menu.NONE, "ȡ��");
						
					}
				});
				return false;
			}
		});
		
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				// ����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
//				// ((SortModel)adapter.getItem(position)).getName()
//				//Log.d(TAG, "��ť�ɹ�");
//				//Toast.makeText(getActivity(), adapter.getItem(position).toString(), Toast.LENGTH_SHORT);
//				//	TXLEntity entity = (TXLEntity) adapter.getItem(position);
//				//Log.d(TAG,entity.txl_name);
//				listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
//					@Override
//					public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//						menu.setHeaderTitle("ѡ�����");
//						menu.add(0, 1, Menu.NONE, "����绰");
//						menu.add(0, 2, Menu.NONE, "���Ͷ���");
//						menu.add(0, 3, Menu.NONE, "���Ƶ����а�");
//						menu.add(0, 4, Menu.NONE, "�����ʼ�");
//						menu.add(0, 5, Menu.NONE, "ȡ��");
//					}
//				});
//			}
//		});

		List<TXLEntity> list = new ArrayList<TXLEntity>();
		list = dbManager.querytxl();
		dbManager.closeDB();
		//Log.d("tagggggggggggg", list.get(3).getTxl_dept());

		
		SourceDateList = filledData(list);
		// ����a-z��������Դ����
		Collections.sort(this.SourceDateList, pinyinComparator);

		adapter = new SortAdapter(getActivity(), SourceDateList);
		listView.setAdapter(adapter);
		mClearEditText = (ClearEditText) view.findViewById(R.id.filter_edit);
		// �������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// ������������ֵΪ�գ�����Ϊԭ�����б�������Ϊ���������б�
				filterData(s.toString());
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		return view;
	}

	@SuppressLint("DefaultLocale")
	private List<TXLEntity> filledData(List<TXLEntity> list) {

		List<TXLEntity> mList = new ArrayList<TXLEntity>();

		for (int j = 0; j < list.size(); j++) {
			TXLEntity entity3 = new TXLEntity();
			entity3.setTxl_name(list.get(j).getTxl_name());
			entity3.setTxl_dept(list.get(j).getTxl_dept());
			entity3.setTxl_tel(list.get(j).getTxl_tel());
			entity3.setTxl_mail(list.get(j).getTxl_mail());
			String pinyin = characterParser.getSelling(list.get(j).getTxl_name());
			String sortString = pinyin.substring(0, 1).toUpperCase();
			// �������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				entity3.setSortLetters(sortString.toUpperCase());
			} else {
				entity3.setSortLetters("#");
			}

			mList.add(entity3);
		}
		return mList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		TXLEntity entity = new TXLEntity();
		entity = (TXLEntity) this.adapter.getItem(info.position);
		Log.d(TAG, entity.toString());
		String name = entity.txl_name;
		String phone_number = entity.txl_tel.trim();
		// ɾ���ַ����ײ���β���Ŀո�
		String dept = entity.txl_dept;
		String mail = entity.txl_mail;
		switch (item.getItemId()) {
		case 1:
			if (phone_number != null && !phone_number.equals("")) {
				// ����ϵͳ�Ĳ��ŷ���ʵ�ֵ绰������
				// ��װһ������绰��intent�����ҽ��绰�����װ��һ��Uri������
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_number));
				getActivity().startActivity(intent);// �ڲ���
			}
			break;
		case 2:
			if (phone_number != null && !phone_number.equals("")) {
				// ����ϵͳ�Ĳ��ŷ���ʵ�ֵ绰������
				// ��װһ������绰��intent�����ҽ��绰�����װ��һ��Uri������
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone_number));
				getActivity().startActivity(intent);// �ڲ���
			}
			break;
		case 3:
			String string = "����:" + name + ";�绰:" + phone_number + ";����:" + dept + "������Ϣ���ԡ��ҵĹ��ᡱ���뾡�찲װ��";
			ClipboardManager clipboardManager = (ClipboardManager) getActivity()
					.getSystemService(Context.CLIPBOARD_SERVICE);
			clipboardManager.setText(string);
			Toast.makeText(getActivity(), name + "��Ϣ���Ƴɹ���", Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Toast.makeText(getActivity(), mail + "���ڿ���", Toast.LENGTH_SHORT).show();
			break;
		case 5:
			break;
		default:
			break;
		}
		
		
		
		//return super.onContextItemSelected(item);
		return true;
	}

	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<TXLEntity> filterDateList = new ArrayList<TXLEntity>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(TXLEntity entity : SourceDateList){
				String name = entity.getTxl_name();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(entity);
				}
			}
		}
		
		// ����a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
	
}