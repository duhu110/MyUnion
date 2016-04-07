package com.qhduhu.myunion.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qhduhu.myunion.activity.AddNewJfActivity;
import com.qhduhu.myunion.entity.JfEntity;

import android.util.Base64;
import android.util.Log;

public class JfServiceIMPL implements JfService{

	@Override
	public boolean updateJf(JfEntity entity) throws Exception {
		System.out.println(entity.jf);
		HttpClient client = new DefaultHttpClient();
		String uri = "http://101.200.183.19/MyUnion/savejf.php";
		HttpPost post = new HttpPost(uri);
		JSONObject object = new JSONObject(); 
		object.put("name", entity.getYg_name());
		byte[] bs = entity.getJf_pic1();
		String string = Base64.encodeToString(bs, 0,bs.length, Base64.DEFAULT);
		Log.d("stringimg1lenth", String.valueOf(string.length()));
		object.put("img1", string);
		object.put("type", entity.getJf_type());
		object.put("typedesc", entity.getJf_typedescrp());
		object.put("desc", entity.getJf_descrp());
		object.put("img2", Base64.encodeToString(entity.getJf_pic2(),Base64.DEFAULT));
		object.put("jf", entity.getJf());
		System.out.println(object.toString());
		NameValuePair nameValuePair = new BasicNameValuePair("data",
				object.toString());
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(nameValuePair);
		post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse response = client.execute(post);
		System.out.println(response);
		int statuscode = response.getStatusLine().getStatusCode();
		if (statuscode != HttpStatus.SC_OK) {
			throw new ServiceRulesException(AddNewJfActivity.MSG_HTTP_FAIL);
		}
		String result = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
		if (result.equals("success")) {
			return true;
		}else {
			throw new ServiceRulesException(AddNewJfActivity.MSG_SAVE_FAIL);
		}
				
	}

	@Override
	public List<JfEntity> getJfs(int lastid) throws Exception {
		List<JfEntity> entities = new ArrayList<JfEntity>();
		HttpClient client = new DefaultHttpClient();
		String uri = "http://101.200.183.19/MyUnion/getJfData.php?lastid="+lastid;
		Log.d("lastiddxdasdasdfasdfasdfddddd", uri);
		HttpGet get = new HttpGet(uri);
		HttpResponse response = client.execute(get);
		int statuscode = response.getStatusLine().getStatusCode();
		if (statuscode != HttpStatus.SC_OK) {
			throw new ServiceRulesException(AddNewJfActivity.MSG_HTTP_FAIL);
		}
		String result = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
		if (result == null) {
			return null;
		}
		JSONArray jsonArray = new JSONArray(result);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			JfEntity entity = new JfEntity();
			entity.setYg_name(object.getString("name"));
			Log.d("getimg1stringlenth", String.valueOf((object.getString("img1").length())));			
			entity.setJf_pic1(this.stringtoimg(object.getString("img1")));
			entity.setJf_pic2(this.stringtoimg(object.getString("img2")));
			entity.setJf_type(object.getString("type"));
			entity.setJf_typedescrp(object.getString("typedesc"));
			entity.setJf_descrp(object.getString("desc"));
			entity.setJf(object.getInt("jf"));
			entity.setYg_name(object.getString("name"));
			entity.setYg_name(object.getString("name"));
			entities.add(entity);
		}
		
			return entities;
		
	}
	public byte[] stringtoimg(String string) {
		if (string == null) {
			return null;
		}
		return Base64.decode(string, Base64.DEFAULT);
	}
}
