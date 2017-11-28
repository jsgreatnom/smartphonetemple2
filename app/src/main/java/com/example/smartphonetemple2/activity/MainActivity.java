package com.example.smartphonetemple2.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.CommonData;
import com.example.smartphonetemple2.live.LiveActivity;
import com.example.smartphonetemple2.sensor.SensorActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	
//	private RegistInfo registInfo;
	private boolean isResume = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(isResume){  // 처음에 한번만 동작한다.
			isResume = false;
//			String Url = String.format(getString(R.string.url_phone_info), CommonData.DB_IP, CommonData.DB_PORT, CommonData.PhoneNumber);
//			Log.d(TAG, "DB URL - " + Url);
//			registInfo = new RegistInfo();
//			registInfo.execute(Url);
		}		
	}

	public void onBtnClicked(View v) {
		int id = v.getId();
		Intent intent;
		
		switch (id) {
		case R.id.btn_sensor :
			intent = new Intent(MainActivity.this, SensorActivity.class);
			startActivity(intent);			
			break;
		case R.id.btn_live :
			intent = new Intent(MainActivity.this, LiveActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_setting:
			intent = new Intent(MainActivity.this, SettingActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			ApplicationEndQuestion();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void ApplicationEndQuestion() {
		AlertDialog.Builder alert_confirm = new AlertDialog.Builder(MainActivity.this);
		alert_confirm.setMessage("프로그램을 종료 하시겠습니까?").setCancelable(false)
				.setPositiveButton("확인", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						android.os.Process.killProcess(android.os.Process.myPid());
					}
				}).setNegativeButton("취소", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						return;
					}
				});
		AlertDialog alert = alert_confirm.create();
		alert.show();
	}
	
	// 서버에 이름이 등록이 되어있는지 요청
	private class RegistInfo extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			StringBuilder jsonHtml = new StringBuilder();
			try {
				URL url = new URL(params[0]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				if (conn != null) {
					conn.setConnectTimeout(10000);
					conn.setUseCaches(false);
					if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
						BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
						for (;;) {
							String line = br.readLine();
							if (line == null)
								break;
							jsonHtml.append(line + "\n");
						}
						br.close();
					}
					conn.disconnect();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return jsonHtml.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			String Name = "";

			try {
				JSONArray ja = new JSONArray(result);

				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					Name = jo.getString("UserName");
				}

				if (Name.equals("")){ // DB에 이름 있는지 확인
					CommonData.verify = false;
				}else{
					CommonData.verify = true;
				}
				
//				Log.d(TAG, "CommonData.verify - " + CommonData.verify);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}	
	}
}
