package com.qhduhu.myunion.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qhduhu.myunion.entity.TXLEntity;

import android.util.Log;

public class GetTXLListServiceIMPL implements GetTXLListService{

	@Override
	public List<TXLEntity> getTXLList(int lastid) throws Exception {
		System.out.println("excuse gettxllist metho");
		
		List<TXLEntity> txlEntities = new ArrayList<TXLEntity>();
		HttpClient client = new DefaultHttpClient();
		String uri = "http://101.200.183.19/MyUnion/getTXLData.php?lastid="+lastid;
		Log.d("gettxllllllllllllllllll", uri);
		HttpGet get = new HttpGet(uri);
		HttpResponse response = client.execute(get);
		int statuscode = response.getStatusLine().getStatusCode();
		Log.d("tagggggggggggg", "gettxllisthttpsatus:"+statuscode);
		if (statuscode != HttpStatus.SC_OK) {
			throw new ServiceRulesException("更新通讯录服务器连接失败");
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			JSONArray array = new JSONArray(result);
		
		for (int i = 0; i < array.length(); i++) {
			
			JSONObject jsontxllist = array.getJSONObject(i);
			String name = jsontxllist.getString("txl_name");
			String dept = jsontxllist.getString("txl_dept");
			String tel = jsontxllist.getString("txl_tel");
			String mail = jsontxllist.getString("txl_mail");
			txlEntities.add(new TXLEntity(name,dept,tel,mail));
		}
		
		return txlEntities;
	}

}
