package com.example.smartphonetemple2.db;

import com.example.smartphonetemple2.data.CommonData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;


public class DataDB {    // local DB		
	
	private static final String TAG = DataDB.class.getSimpleName();
	
	public interface DataColumns extends BaseColumns{
		public static final String COLUMN_STR_DB_IP = "_db_ip";
		public static final String COLUMN_INT_DB_PORT = "_db_port";
		public static final String COLUMN_STR_DB_ADMIN	= "_db_id";
		public static final String COLUMN_STR_DB_PASSWORD = "_db_password";
		
		public static final String COLUMN_STR_CAMERA1_IP = "_camera1_ip";
		public static final String COLUMN_INT_CAMERA1_PORT = "_camera1_port";
		public static final String COLUMN_STR_CAMERA1_ADMIN	= "_camera1_id";
		public static final String COLUMN_STR_CAMERA1_PASSWORD = "_camera1_password";
		
		public static final String COLUMN_STR_CAMERA2_IP = "_camera2_ip";
		public static final String COLUMN_INT_CAMERA2_PORT = "_camera2_port";
		public static final String COLUMN_STR_CAMERA2_ADMIN	= "_camera2_id";
		public static final String COLUMN_STR_CAMERA2_PASSWORD = "_camera2_password";
		
		public static final String COLUMN_STR_CAMERA3_IP = "_camera3_ip";
		public static final String COLUMN_INT_CAMERA3_PORT = "_camera3_port";
		public static final String COLUMN_STR_CAMERA3_ADMIN	= "_camera3_id";
		public static final String COLUMN_STR_CAMERA3_PASSWORD = "_camera3_password";
		
		public static final String COLUMN_STR_CAMERA4_IP = "_camera4_ip";
		public static final String COLUMN_INT_CAMERA4_PORT = "_camera4_port";
		public static final String COLUMN_STR_CAMERA4_ADMIN	= "_camera4_id";
		public static final String COLUMN_STR_CAMERA4_PASSWORD = "_camera4_password";
		
		public static final String COLUMN_STR_CAMERA5_IP = "_camera5_ip";
		public static final String COLUMN_INT_CAMERA5_PORT = "_camera5_port";
		public static final String COLUMN_STR_CAMERA5_ADMIN	= "_camera5_id";
		public static final String COLUMN_STR_CAMERA5_PASSWORD = "_camera5_password";
		
		public static final String COLUMN_STR_CAMERA6_IP = "_camera6_ip";
		public static final String COLUMN_INT_CAMERA6_PORT = "_camera6_port";
		public static final String COLUMN_STR_CAMERA6_ADMIN	= "_camera6_id";
		public static final String COLUMN_STR_CAMERA6_PASSWORD = "_camera6_password";
		
		public static final String COLUMN_STR_CAMERA7_IP = "_camera7_ip";
		public static final String COLUMN_INT_CAMERA7_PORT = "_camera7_port";
		public static final String COLUMN_STR_CAMERA7_ADMIN	= "_camera7_id";
		public static final String COLUMN_STR_CAMERA7_PASSWORD = "_camera7_password";
		
		public static final String COLUMN_STR_CAMERA8_IP = "_camera8_ip";
		public static final String COLUMN_INT_CAMERA8_PORT = "_camera8_port";
		public static final String COLUMN_STR_CAMERA8_ADMIN	= "_camera8_id";
		public static final String COLUMN_STR_CAMERA8_PASSWORD = "_camera8_password";
	}
	
	private SQLiteDatabase DB;
	private static final String DATABASE_TABLE = "data_table";
	
	private static DataDB instance = null;
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		private static final String DATABASE_NAME = "data";
		private static final int DATABASE_VERSION = 2;
		private static final String DATABASE_CREATE = "create table "
				+ DATABASE_TABLE + " ("
				+ DataColumns.COLUMN_STR_DB_IP + " text, "
				+ DataColumns.COLUMN_INT_DB_PORT + " integer,"
				+ DataColumns.COLUMN_STR_DB_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_DB_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA1_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA1_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA1_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA1_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA2_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA2_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA2_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA2_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA3_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA3_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA3_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA3_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA4_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA4_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA4_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA4_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA5_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA5_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA5_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA5_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA6_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA6_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA6_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA6_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA7_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA7_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA7_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA7_PASSWORD + " text,"
				+ DataColumns.COLUMN_STR_CAMERA8_IP + " text, "
				+ DataColumns.COLUMN_INT_CAMERA8_PORT + " integer,"
				+ DataColumns.COLUMN_STR_CAMERA8_ADMIN + " text,"
				+ DataColumns.COLUMN_STR_CAMERA8_PASSWORD + " text"
				+ ");";
		


		public DatabaseHelper(Context context) {
			// TODO Auto-generated constructor stub
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.d(TAG, "DatabaseHelper");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onCreate");
			db.execSQL(DATABASE_CREATE);
			CommonData.dbFlag = true;
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.d(TAG, "Content provider database Upgrading database from version " + oldVersion
					+ " to " + newVersion + " which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);
		}
	}
	
	//객체 가져오기. DB는 Singleton 방식을 사용한다.
	public static DataDB getInstance(Context context){
		if(instance == null){
			instance = new DataDB(context);
		}
		
		return instance;
	}
	
	public int delete(long id, String selection, String[] selectionArgs){
		int count = 0;
		count = DB.delete(DATABASE_TABLE, DataColumns._ID
				+ " = "
				+ id
				+ (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')': ""), selectionArgs);
		return count;
	}
	
	public long insert(ContentValues values){
		long rowID = DB.insert(DATABASE_TABLE, "", values);
		
		if(rowID > 0)
		{
			return rowID;
		}
		throw new SQLException("Failed to insert row");
	}
	
	
	public DataDB(Context context){
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		DB = dbHelper.getWritableDatabase();
	}
	
	public Cursor getIpPort(String[] projection, String selection, String[] selectionArgs, String sortOrder){
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		sqlBuilder.setTables(DATABASE_TABLE);
		
		if(sortOrder == null || sortOrder == "")
			sortOrder = DataColumns.COLUMN_STR_DB_IP;
		
		Cursor c = sqlBuilder.query(DB, projection, selection, selectionArgs, null, null, sortOrder);
		
		return c;
	}
	
	public int update(ContentValues values){
		int count = 0;
		count = DB.update(DATABASE_TABLE, values, null, null);//DataColumns.DB_IP
		//		+ " = "
		//		+ id
//				+ (!TextUtils.isEmpty(selection) ? 
//				"AND (" + selection + ')': ""), selectionArgs);		
//		DB.execSQL(selection);
		return count;
	}
}
