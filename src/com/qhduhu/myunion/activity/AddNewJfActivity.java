package com.qhduhu.myunion.activity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import com.qhduhu.myunion.R;
import com.qhduhu.myunion.adapter.ImageUtils;
import com.qhduhu.myunion.entity.JfEntity;
import com.qhduhu.myunion.service.JfService;
import com.qhduhu.myunion.service.JfServiceIMPL;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewJfActivity extends Activity implements OnClickListener {
	private EditText descrp, ettype;
	private TextView tvtype;
	private int typeCode = 0;
	private Button photo, submit;
	private ImageView img1, img2;
//	private DBManager db;
//	private static final int FLAG_LOGIN_SUCCESS = 1;
//	private static final int FLAG_BAOCUN_SUCCESS = 2;
//	private static final String FLAG_LOAD_ERROR = "加载出错";
//	private static final String FLAG_BAOCUN_ERROR = "保存出错";
	public static final String MSG_SAVE_FAIL = "保存失败";
	public static final String MSG_LOAD_FAIL = "加载失败";
	public static final String MSG_HTTP_FAIL = "連接服務器失败";
	JfService jfser = new JfServiceIMPL();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_jf);
		Intent intent = getIntent();
		typeCode = intent.getIntExtra("typeCode", 0);
		initactiongbar();
		initview();
		photo.setOnClickListener(this);
		submit.setOnClickListener(this);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
	}

	private void initview() {
		descrp = (EditText) findViewById(R.id.addjf_etwhat);
		photo = (Button) findViewById(R.id.add_photo);
		submit = (Button) findViewById(R.id.add_submit);
		tvtype = (TextView) findViewById(R.id.add_tvtype);
		ettype = (EditText) findViewById(R.id.add_ettype);
		img1 = (ImageView) findViewById(R.id.addnew_img1);
		img2 = (ImageView) findViewById(R.id.addnew_img2);
		switch (typeCode) {
		case 1:
			tvtype.setText("读的书名");
			break;
		case 2:
			tvtype.setText("运动类型");
			break;
		case 3:
			tvtype.setText("棋牌类型");
			break;
		case 4:
			tvtype.setText("程序名称");
			break;

		default:
			break;
		}
	}

	private void initactiongbar() {
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_photo:
			ImageUtils.ShowImagePickDialog(this);
			break;
		case R.id.add_submit:
//			db = new DBManager(this);
			byte[] pic1, pic2;
			JfEntity entity = new JfEntity();
			if (img1.getDrawable() != null) {
				Bitmap bitmap1 = getBitmapFromIv(img1);
				pic1 = Bitmap2Bytes(bitmap1);
				entity.setJf_pic1(pic1);
			}
			if (img2.getDrawable() != null) {
				Bitmap bitmap2 = getBitmapFromIv(img2);
				pic2 = Bitmap2Bytes(bitmap2);
				entity.setJf_pic2(pic2);
			}
			entity.setYg_name("DUHU");
			if (descrp.getText().toString() != null) {
				entity.setJf_descrp(descrp.getText().toString());
			}
			if (tvtype.getText().toString() != null) {
				
				entity.setJf_type(tvtype.getText().toString());
			}
			if (ettype.getText().toString() != null) {
				
				entity.setJf_typedescrp(ettype.getText().toString());
			}
			entity.setJf(3);
			savejf(entity);
			//db.add(entity);
			//db.closeDB();
			break;

		default:
			break;
		}
	}
	private void savejf(final JfEntity entity) {
		new Thread(new Runnable() {
			public void run() {
				
			try {
				jfser.updateJf(entity);
				Log.d("asdasdads", "sucessssssssssssssssssssssssssssss");
			} catch (Exception e) {
				e.printStackTrace();
			}	
			}
		}).start();
	}
	private Bitmap getBitmapFromIv(ImageView imageView){
		View drawingView = imageView;
		drawingView.buildDrawingCache(true);
		Bitmap bitmap = drawingView.getDrawingCache(true).copy(Config.ARGB_8888, false);
		drawingView.destroyDrawingCache();
		return bitmap;
	}
	private byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ImageUtils.REQUEST_CODE_FROM_ALBUM:
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			ContentResolver resolver = getContentResolver();
			Uri imageUri = data.getData();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;// 图片大小，设置越大，图片越不清晰，占用空间越小
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(resolver.openInputStream(imageUri), null, options);
				updateImgs(bitmap);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case ImageUtils.REQUEST_CODE_FROM_CAMERA:
			if (resultCode == RESULT_CANCELED) {
				ImageUtils.DeleteImageUri(this, ImageUtils.imageUriFromCamera);
			} else {
				Uri imageUriCamera = ImageUtils.imageUriFromCamera;
				ContentResolver resolver2 = getContentResolver();
				BitmapFactory.Options options2 = new BitmapFactory.Options();
				options2.inSampleSize = 1;// 图片大小，设置越大，图片越不清晰，占用空间越小
				try {
					Bitmap bitmap2 = BitmapFactory.decodeStream(resolver2.openInputStream(imageUriCamera), null,
							options2);
					updateImgs(bitmap2);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			break;
		}
	}

	private void updateImgs(Bitmap bitmap) {
		if (img1.getDrawable() == null) {
			img1.setImageBitmap(bitmap);
		} else {
			img2.setImageBitmap(bitmap);
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.add_new_jf, menu);
//		return true;
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
		default:
			return super.onOptionsItemSelected(item);}
	}
}
