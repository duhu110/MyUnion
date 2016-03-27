package com.qhduhu.myunion.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.qhduhu.myunion.activity.AddNewJfActivity;
import com.qhduhu.myunion.entity.JfEntity;

public class JfServiceIMPL implements JfService{

	@Override
	public boolean updateJf(JfEntity entity) throws Exception {
		System.out.println(entity.jf);
		HttpClient client = new DefaultHttpClient();
		String uri = "http://192.168.31.211:81/myunion/savejf.php";
		HttpPost post = new HttpPost(uri);
		JSONObject object = new JSONObject(); 
		object.put("name", entity.getYg_name());
		object.put("img1", entity.getJf_pic1());
		object.put("type", entity.getJf_type());
		object.put("typedesc", entity.getJf_typedescrp());
		object.put("desc", entity.getJf_descrp());
		object.put("img2", entity.getJf_pic2());
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

}
