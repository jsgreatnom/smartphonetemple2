package com.example.smartphonetemple2.data;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class CommonData {  // 공통 데이터
	private static final String TAG = CommonData.class.getSimpleName();

	public static final String PREFERENCE_NAME = "smartphonetemple";
	public static final String STR_DB_IP = "db_ip";
	public static final String STR_DB_port = "db_port";

	public static final String STR_TOTAL_SENSOR_URL = "total_sensor_url";
	public static final String STR_MEASURE_SENSOR_URL = "measure_sensor_url";
	public static final String STR_FIRE_SENSOR_URL = "fire_sensor_url";

	public static final int REQUEST_SERVER = 10000;
	public static final int UI_UPDATE = 10001;
	public static final int REQUEST_FAIL = 10002;
	public static final int REQUEST_STOP = 10003;

	public static boolean dbFlag = false;  // Local DB 세팅 여부  
	
	public static String DB_IP;  // 관제 센터 IP
	public static String DB_PORT;   // 관제 센터 ORT
	public static String PhoneNumber = ""; // 단말기 번호
	public static boolean verify = false; // 관제 센터 DB에 이름 저장 여부
	
	public static final String SENDER_ID = "1068552764636";  // gcm sender_id
	
	public static String filePath;  
	
	
	public static void dirCheck(){ // wav파일 저장 폴더  없으면 생성
		String ext = Environment.getExternalStorageState();
		
		if(ext.equals(Environment.MEDIA_MOUNTED)){ // sdcard 있을경우
			Log.d(TAG, "SDCARD_MOUNTED!!!" );
			filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartPhoneTemple/";
		}else{										// sdcard 없을경우
			Log.d(TAG, "SDCARD_UN_MOUNTED!!!" );
			filePath = Environment.getRootDirectory() + "/SmartPhoneTemple/";
		}
		
		Log.d(TAG, "filePath - " + filePath );
		
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
	}
}
