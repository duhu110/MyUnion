package com.qhduhu.myunion.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ygjf.db";  
    private static final int DATABASE_VERSION = 1;  

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS jf" +  
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, yg_name VARCHAR,"
                + " jf_descrp VARCHAR,jf_type VARCHAR,jf_typedescrp VARCHAR,"
                + "jf_pic1 BLOB,jf_pic2 BLOB,jf INTEGER)");
		String sql="CREATE TABLE IF NOT EXISTS txl (id INTEGER PRIMARY KEY "
				+ "AUTOINCREMENT,txl_name VARCHAR,txl_tel VARCHAR,txl_dept "
				+ "VARCHAR,txl_mail VARCHAR) ";
		Log.d("dbhelper", "create Database------------->");
		db.execSQL(sql);
		Log.d("dbhelper", "create Database success------------->");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("ALTER TABLE person ADD COLUMN other STRING");  
	}

}
