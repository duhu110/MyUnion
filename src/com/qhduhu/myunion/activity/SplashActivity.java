package com.qhduhu.myunion.activity;

import com.qhduhu.myunion.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {
	private Handler handler = new Handler();
	private ImageView img, img_small;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		getActionBar().hide();
		img = (ImageView) findViewById(R.id.img);
		img_small = (ImageView) findViewById(R.id.img_small);
		img.setVisibility(View.GONE);
		img_small.setVisibility(View.GONE);
		
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				
				img.setVisibility(View.VISIBLE);
				img.startAnimation(AnimationUtils.loadAnimation(
						SplashActivity.this, R.anim.anim_splash));
				

				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						
						img_small.setVisibility(View.VISIBLE);
						img_small.startAnimation(AnimationUtils.loadAnimation(
								SplashActivity.this, R.anim.anim_splash_small));
						
						handler.postDelayed(new Runnable() {
							
							@Override
							public void run() {
								Intent intent = new Intent(SplashActivity.this,
										LoginActivity.class);
								startActivity(intent);
								finish();
							}
						}, 1500);
						
					}
				}, 2200);

			}
		}, 1000);
	}

}

