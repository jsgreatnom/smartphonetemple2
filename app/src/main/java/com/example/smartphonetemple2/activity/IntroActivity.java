package com.example.smartphonetemple2.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.CommonData;
import com.example.smartphonetemple2.db.DataDB;
import com.example.smartphonetemple2.db.DataDB.DataColumns;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import static com.example.smartphonetemple2.data.CommonData.SENDER_ID;

public class IntroActivity extends Activity {
	private static final String TAG = IntroActivity.class.getSimpleName();

	private final String PROPERTY_REG_ID = "PROPERTY_REG_ID";

	private Context mContext;
	private GoogleCloudMessaging gcm;
	private String regId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		mContext = this;

		initDefaultData();

		gcmRegistCheck();

		// 인트로 화면이 보여야 되므로 약 1초 후에 메인 화면으로 넘어간다.
		Handler hd = new Handler();
		hd.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(IntroActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}

		}, 1000);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		GCMRegistrar.onDestroy(mContext);
		super.onDestroy();
	}
	
	// 데이터 초기화
	private void initDefaultData() {
		
//		DataDB.getInstance(mContext);
//		Log.d(TAG, "CommonData.dbFlag - " + CommonData.dbFlag);
//		if (CommonData.dbFlag) {
//			insertValue();  // db의 값 초기화
//		}
		
		// 단말기 전화번호를 가져온다.
//		TelephonyManager systemService = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//		String PhoneNumber = systemService.getLine1Number();
//		if (PhoneNumber != null) CommonData.PhoneNumber = PhoneNumberUtils.formatNumber(PhoneNumber);

		SharedPreferences pref = getSharedPreferences(CommonData.PREFERENCE_NAME, Context.MODE_PRIVATE);
		CommonData.DB_IP = pref.getString(CommonData.STR_DB_IP, getString(R.string.database_ip));
		CommonData.DB_PORT = pref.getString(CommonData.STR_DB_port, getString(R.string.database_port));
      	
      	// DB에서 서버에 IP값과 PORT값을 받는다.
//      	Cursor c = DataDB.getInstance(mContext).getIpPort(null, null, null, DataColumns.COLUMN_STR_DB_IP + " desc");
//		if(c.moveToFirst()){
//			do{
//				CommonData.DB_IP = c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_DB_IP));
//				CommonData.DB_PORT = c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_DB_PORT));
//			}while(c.moveToNext());
//		}
	}
	
	// 관제서버 IP Default로 세팅
	public void insertValue() {
		
		ContentValues values = new ContentValues();
		values.put(DataColumns.COLUMN_STR_DB_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_DB_PORT, "3306");
		values.put(DataColumns.COLUMN_STR_DB_ADMIN, "root");
		values.put(DataColumns.COLUMN_STR_DB_PASSWORD, "SIBMS");
		// 183.106.182.225:380
		values.put(DataColumns.COLUMN_STR_CAMERA1_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA1_PORT, "3554");
		values.put(DataColumns.COLUMN_STR_CAMERA1_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA1_PASSWORD, "4321");

		values.put(DataColumns.COLUMN_STR_CAMERA2_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA2_PORT, "4554");
		values.put(DataColumns.COLUMN_STR_CAMERA2_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA2_PASSWORD, "4321");

		values.put(DataColumns.COLUMN_STR_CAMERA3_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA3_PORT, "5554");
		values.put(DataColumns.COLUMN_STR_CAMERA3_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA3_PASSWORD, "4321");

		values.put(DataColumns.COLUMN_STR_CAMERA4_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA4_PORT, "6554");
		values.put(DataColumns.COLUMN_STR_CAMERA4_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA4_PASSWORD, "4321");

		values.put(DataColumns.COLUMN_STR_CAMERA5_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA5_PORT, "7554");
		values.put(DataColumns.COLUMN_STR_CAMERA5_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA5_PASSWORD, "4321");

		values.put(DataColumns.COLUMN_STR_CAMERA6_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA6_PORT, "8554");
		values.put(DataColumns.COLUMN_STR_CAMERA6_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA6_PASSWORD, "4321");

		values.put(DataColumns.COLUMN_STR_CAMERA7_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA7_PORT, "9554");
		values.put(DataColumns.COLUMN_STR_CAMERA7_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA7_PASSWORD, "!12345678u");

		values.put(DataColumns.COLUMN_STR_CAMERA8_IP, "118.45.29.69");
		values.put(DataColumns.COLUMN_INT_CAMERA8_PORT, "2554");
		values.put(DataColumns.COLUMN_STR_CAMERA8_ADMIN, "admin");
		values.put(DataColumns.COLUMN_STR_CAMERA8_PASSWORD, "4321");

		DataDB.getInstance(mContext).insert(values);

	}
	
	// GCM의 RegID를 Check후 서버에 등록한다. 
	private void gcmRegistCheck(){
		// GCM 등록 여부 확인
//		GCMRegistrar.checkDevice(this);
//		GCMRegistrar.checkManifest(this);

		gcm = GoogleCloudMessaging.getInstance(this);   //GCMRegistrar.getRegistrationId(this);
		regId = getRegistrationId(this);
		Log.d(TAG, "GCM RegID = " + regId);   	
      	
      	if (regId.isEmpty()) {
//      		GCMRegistrar.register(this, SENDER_ID);
			registerInBackground();
      	}
      	
//      	String insertPage = getString(R.string.url_insert_page, CommonData.DB_IP, CommonData.DB_PORT);
//       	Log.d(TAG, "insertPage = " + insertPage);
//       	GCMHttpConnect httpConnect = new GCMHttpConnect(insertPage + "?regID=" + regId + "&PhoneNumber=" + CommonData.PhoneNumber, null);
//     	httpConnect.start();
	}

	// 저장된 reg id 조회
	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context); // 이전에 저장해둔 등록 아이디를 SharedPreferences에서 가져온다.
		String registrationId = prefs.getString(PROPERTY_REG_ID, ""); // 저장해둔 등록 아이디가 없으면 빈 문자열을 반환한다.
		if (registrationId.isEmpty()) {
			System.out.println("************************************************* Registration not found.");
			return "";
		}

		// 앱이 업데이트 되었는지 확인하고, 업데이트 되었다면 기존 등록 아이디를 제거한다.
		// 새로운 버전에서도 기존 등록 아이디가 정상적으로 동작하는지를 보장할 수 없기 때문이다.
//		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
//		int currentVersion = getAppVersion(context);
//		if (registeredVersion != currentVersion) { // 이전에 등록 아이디를 저장한 앱의 버전과 현재 버전을 비교해 버전이 변경되었으면 빈 문자열을 반환한다.
//			System.out.println("************************************************* App version changed.");
//			return "";
//		}
		return registrationId;
	}

	private SharedPreferences getGCMPreferences(Context context) {
		return getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
	}

	// reg id 발급
	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(mContext);
					}
					regId = gcm.register(SENDER_ID);
					msg = "Device registered, registration ID=" + regId;

					// 서버에 발급받은 등록 아이디를 전송한다.
					// 등록 아이디는 서버에서 앱에 푸쉬 메시지를 전송할 때 사용된다.
					sendRegistrationIdToBackend();

					// 등록 아이디를 저장해 등록 아이디를 매번 받지 않도록 한다.
					storeRegistrationId(mContext, regId);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					// If there is an error, don't just keep trying to register.
					// Require the user to click a button again, or perform
					// exponential back-off.
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				System.out.println("****************************************************************************** msg : " + msg);
			}

		}.execute(null, null, null);
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	// SharedPreferences에 발급받은 등록 아이디를 저장해 등록 아이디를 여러 번 받지 않도록 하는 데 사용
	private void storeRegistrationId(Context context, String regid) {
		final SharedPreferences prefs = getGCMPreferences(context);
//		int appVersion = getAppVersion(context);
//		System.out.println("************************************************* Saving regId on app version " + appVersion);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regid);
//		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.apply();
	}

	// 등록 아이디를 서버(앱이랑 통신하는 서버)에 전달
	// 서버는 이 등록 아이디를 사용자마다 따로 저장해두었다가 특정 사용자에게 푸쉬 메시지를 전송할 때 사용할 수 도 있음
	private void sendRegistrationIdToBackend() {
		System.out.println("************************************************* 서버에 regid 전달 : " + regId);

//		HttpUtil hu = new HttpUtil(context);
//		String[] params = {SERVER_URL, "KEY:1234", "REG:" + regid};
//		hu.execute(params);
		String insertPage = getString(R.string.url_insert_page, CommonData.DB_IP, CommonData.DB_PORT);
		Log.d(TAG, "insertPage = " + insertPage);
//		GCMHttpConnect httpConnect = new GCMHttpConnect(insertPage + "?regID=" + regId + "&PhoneNumber=" + CommonData.PhoneNumber, null);
//		httpConnect.start();
	}

}
