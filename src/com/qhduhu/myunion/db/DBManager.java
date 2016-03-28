package com.qhduhu.myunion.db;

import java.util.ArrayList;
import java.util.List;

import com.qhduhu.myunion.entity.JfEntity;
import com.qhduhu.myunion.entity.TXLEntity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	private DBHelper helper;
	private SQLiteDatabase db;

	public DBManager(Context context) {
		helper = new DBHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}

	public void add(JfEntity jf) {
		db.beginTransaction(); // 开始事务
		try {
			db.execSQL(
					"INSERT INTO jf VALUES(null, ?, ?, ?, ?, ?, ?, ?)",
					new Object[] { jf.getYg_name(), jf.getJf_descrp(),
							jf.getJf_type(), jf.getJf_typedescrp(),
							jf.getJf_pic1(), jf.getJf_pic2(), jf.getJf() });

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void inserttxl(String name, String tel, String dept, String mail) {
		db.beginTransaction();
		try {
			String sql = "INSERT INTO txl VALUES(null, " + name + "," + tel
					+ " ," + dept + " ," + mail + " )";
			Log.d("DBManager11111", sql);
			db.execSQL(sql);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void addtxl(List<TXLEntity> txlentities) {
		db.beginTransaction(); // 开始事务
		try {
			for (TXLEntity txlEntity : txlentities) {
				db.execSQL("INSERT INTO txl VALUES(null, ?, ?, ?, ?)",
						new Object[] { txlEntity.txl_name, txlEntity.txl_dept,
								txlEntity.txl_tel, txlEntity.txl_mail });
			}
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void DeleteTXL() {
		db.beginTransaction(); // 开始事务
		try {

			db.execSQL("DELETE FROM txl");

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	// public void updateAge(Person person) {
	// ContentValues cv = new ContentValues();
	// cv.put("age", person.age);
	// db.update("person", cv, "name = ?", new String[]{person.name});
	// }
	// public void deleteOldPerson(Person person) {
	// db.delete("person", "age >= ?", new
	// String[]{String.valueOf(person.age)});
	// }
	public List<JfEntity> query() {
		ArrayList<JfEntity> jfs = new ArrayList<JfEntity>();
		Cursor c = queryTheCursor();
			
		while (c.moveToNext()) {
			JfEntity jf = new JfEntity();
			jf.set_id(c.getInt(c.getColumnIndex("_id")));
			jf.setYg_name(c.getString(c.getColumnIndex("yg_name")));
			jf.setJf_descrp(c.getString(c.getColumnIndex("jf_descrp")));
			jf.setJf_type(c.getString(c.getColumnIndex("jf_type")));
			jf.setJf_typedescrp(c.getString(c.getColumnIndex("jf_typedescrp")));
			jf.setJf_pic1(c.getBlob(c.getColumnIndex("jf_pic1")));
			jf.setJf_pic2(c.getBlob(c.getColumnIndex("jf_pic2")));
			jf.setJf(c.getInt(c.getColumnIndex("jf")));
			jfs.add(jf);
		}
		c.close();
		return jfs;
	}

	public List<TXLEntity> querytxl() {
		ArrayList<TXLEntity> txlEntities = new ArrayList<TXLEntity>();
		Cursor c = queryTheCursorTXL();
		while (c.moveToNext()) {
			TXLEntity txlEntity = new TXLEntity();
			txlEntity.txl_name = c.getString(c.getColumnIndex("txl_name"));
			txlEntity.txl_tel = c.getString(c.getColumnIndex("txl_tel"));
			txlEntity.txl_dept = c.getString(c.getColumnIndex("txl_dept"));
			txlEntity.txl_mail = c.getString(c.getColumnIndex("txl_mail"));
			txlEntities.add(txlEntity);
		}
		c.close();
		return txlEntities;
	}

	public Cursor queryTheCursor() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("SELECT * FROM jf ORDER BY _id DESC", null);
		return c;
	}

	public Cursor queryTheCursorTXL() {
		Cursor c = db.rawQuery("SELECT * FROM txl", null);
		return c;
	}

	public void closeDB() {
		db.close();
	}

	public int queryJFlastId() {
		Cursor c = db.rawQuery("SELECT * FROM jf ORDER BY _id DESC", null);
		if (c.moveToNext()) {
			return c.getInt(c.getColumnIndex("_id"));
		}
		return 0;
	}

}
