package com.qhduhu.myunion.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtils {

	public static final int REQUEST_CODE_FROM_CAMERA = 5001;
	public static final int REQUEST_CODE_FROM_ALBUM = 5002;
	public static Uri imageUriFromCamera;


	public static void ShowImagePickDialog(final Activity activity) {
		String title = "选择图片来源";
		String[] item = new String[] { "拍照", "从相册选择" };
		new AlertDialog.Builder(activity).setTitle(title).setItems(item, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					PickImageFromeCamera(activity);
					break;
				case 1:
					PickImageFromeAlbum(activity);
					break;
				default:
					break;
				}
			}

		}).show();
	}
	public static void PickImageFromeCamera(Activity activity) {
		imageUriFromCamera = CreateImageUri(activity);
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
		activity.startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
	}
	
	private static Uri CreateImageUri(Context context) {
		String name = "boreWbImg" + System.currentTimeMillis();
		ContentValues v = new ContentValues();
		v.put(MediaStore.Images.Media.TITLE, name);
		v.put(MediaStore.Images.Media.DISPLAY_NAME, name+".jpeg");
		v.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, v);
		
		return uri;
	}
	public static void PickImageFromeAlbum(Activity activity) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);
	}
	public static void PickImageFromeAlbum2(Activity activity) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);
	}
	public static void DeleteImageUri(Context context, Uri uri) {
		context.getContentResolver().delete(imageUriFromCamera, null, null);
	}
}
