package com.qhduhu.myunion.activity;

import com.qhduhu.myunion.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	EditText username,password;
	Button loginbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().hide();
		username=(EditText) findViewById(R.id.login_username);
		password=(EditText) findViewById(R.id.login_password);
		loginbtn=(Button) findViewById(R.id.login_btn);
		loginbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=username.getText().toString();
				String pswd=password.getText().toString();
				Log.d("getedittext", name+pswd);
				if (pswd.equals("111")) {
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, MainActivity.class);
					intent.putExtra("username", name);
					startActivity(intent);
				} else {

					Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
					
				}
			}
		});
	}
}
