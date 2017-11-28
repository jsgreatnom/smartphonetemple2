package com.example.smartphonetemple2.activity;



import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.db.DataDB;
import com.example.smartphonetemple2.db.DataDB.DataColumns;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


/** 
 *  환경 설정 화면
 *  서버와 IP카메라 정보를 설정한다.
 * */
public class SettingActivity extends Activity{
	private Context context;
	
	private EditText db_ip_text, db_port_text, db_admin_text, db_pass_text;
	private EditText camera1_ip_text, camera1_port_text, camera1_admin_text, camera1_pass_text;
	private EditText camera2_ip_text, camera2_port_text, camera2_admin_text, camera2_pass_text;
	private EditText camera3_ip_text, camera3_port_text, camera3_admin_text, camera3_pass_text;
	private EditText camera4_ip_text, camera4_port_text, camera4_admin_text, camera4_pass_text;
	private EditText camera5_ip_text, camera5_port_text, camera5_admin_text, camera5_pass_text;
	private EditText camera6_ip_text, camera6_port_text, camera6_admin_text, camera6_pass_text;
	private EditText camera7_ip_text, camera7_port_text, camera7_admin_text, camera7_pass_text;
	private EditText camera8_ip_text, camera8_port_text, camera8_admin_text, camera8_pass_text;
	private Button setting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		context = this;
		
		initDefaultView();
		
		
		Cursor c = DataDB.getInstance(context).getIpPort(null, null, null, DataColumns.COLUMN_STR_DB_IP + " desc");
		if(c.moveToFirst()){
			do{
				db_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_DB_IP))); 
				db_port_text.setText(String.valueOf(c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_DB_PORT )))); 
				db_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_DB_ADMIN)));	
				db_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_DB_PASSWORD)));
				 
				camera1_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA1_IP)));
				camera1_port_text.setText(String.valueOf(c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA1_PORT)))); 
				camera1_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA1_ADMIN)));
				camera1_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA1_PASSWORD)));
				
				camera2_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA2_IP)));
				camera2_port_text.setText(String.valueOf(c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA2_PORT)))); 
				camera2_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA2_ADMIN)));
				camera2_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA2_PASSWORD)));
				
				camera3_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA3_IP)));
				camera3_port_text.setText(String.valueOf(c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA3_PORT)))); 
				camera3_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA3_ADMIN)));
				camera3_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA3_PASSWORD)));
				
				camera4_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA4_IP)));
				camera4_port_text.setText(String.valueOf( c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA4_PORT)))); 
				camera4_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA4_ADMIN)));
				camera4_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA4_PASSWORD)));
				
				camera5_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA5_IP)));
				camera5_port_text.setText(String.valueOf( c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA5_PORT)))); 
				camera5_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA5_ADMIN)));
				camera5_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA5_PASSWORD)));
				
				camera6_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA6_IP)));
				camera6_port_text.setText(String.valueOf( c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA6_PORT)))); 
				camera6_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA6_ADMIN)));
				camera6_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA6_PASSWORD)));
				
				camera7_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA7_IP)));
				camera7_port_text.setText(String.valueOf( c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA7_PORT)))); 
				camera7_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA7_ADMIN)));
				camera7_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA7_PASSWORD)));
				
				camera8_ip_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA8_IP)));
				camera8_port_text.setText(String.valueOf( c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_CAMERA8_PORT)))); 
				camera8_admin_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA8_ADMIN)));
				camera8_pass_text.setText(c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_CAMERA8_PASSWORD)));
			}while(c.moveToNext());
		}
	}
	
	private void initDefaultView(){
		db_ip_text = (EditText)findViewById(R.id.db_server_ip);
		db_port_text = (EditText)findViewById(R.id.db_server_port);
		db_admin_text = (EditText)findViewById(R.id.db_server_admin);
		db_pass_text = (EditText)findViewById(R.id.db_server_password);
		camera1_ip_text = (EditText)findViewById(R.id.camera1_ip);
		camera1_port_text = (EditText)findViewById(R.id.camera1_port);
		camera1_admin_text = (EditText)findViewById(R.id.camera1_admin);
		camera1_pass_text = (EditText)findViewById(R.id.camera1_password);
		camera2_ip_text = (EditText)findViewById(R.id.camera2_ip);
		camera2_port_text = (EditText)findViewById(R.id.camera2_port);
		camera2_admin_text = (EditText)findViewById(R.id.camera2_admin);
		camera2_pass_text = (EditText)findViewById(R.id.camera2_password);
		camera3_ip_text = (EditText)findViewById(R.id.camera3_ip);
		camera3_port_text = (EditText)findViewById(R.id.camera3_port);
		camera3_admin_text = (EditText)findViewById(R.id.camera3_admin);
		camera3_pass_text = (EditText)findViewById(R.id.camera3_password);
		camera4_ip_text = (EditText)findViewById(R.id.camera4_ip);
		camera4_port_text = (EditText)findViewById(R.id.camera4_port);
		camera4_admin_text = (EditText)findViewById(R.id.camera4_admin);
		camera4_pass_text = (EditText)findViewById(R.id.camera4_password);
		camera5_ip_text = (EditText)findViewById(R.id.camera5_ip);
		camera5_port_text = (EditText)findViewById(R.id.camera5_port);
		camera5_admin_text = (EditText)findViewById(R.id.camera5_admin);
		camera5_pass_text = (EditText)findViewById(R.id.camera5_password);
		camera6_ip_text = (EditText)findViewById(R.id.camera6_ip);
		camera6_port_text = (EditText)findViewById(R.id.camera6_port);
		camera6_admin_text = (EditText)findViewById(R.id.camera6_admin);
		camera6_pass_text = (EditText)findViewById(R.id.camera6_password);
		camera7_ip_text = (EditText)findViewById(R.id.camera7_ip);
		camera7_port_text = (EditText)findViewById(R.id.camera7_port);
		camera7_admin_text = (EditText)findViewById(R.id.camera7_admin);
		camera7_pass_text = (EditText)findViewById(R.id.camera7_password);
		camera8_ip_text = (EditText)findViewById(R.id.camera8_ip);
		camera8_port_text = (EditText)findViewById(R.id.camera8_port);
		camera8_admin_text = (EditText)findViewById(R.id.camera8_admin);
		camera8_pass_text = (EditText)findViewById(R.id.camera8_password);
		setting = (Button)findViewById(R.id.setting_button);
		setting.setOnClickListener(mClickListener);
	}
	
	OnClickListener mClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId() == R.id.setting_button)
			{
				ContentValues values = new ContentValues();
				values.put(DataColumns.COLUMN_STR_DB_IP, db_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_DB_PORT, db_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_DB_ADMIN, db_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_DB_PASSWORD, db_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA1_IP, camera1_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA1_PORT, camera1_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA1_ADMIN, camera1_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA1_PASSWORD, camera1_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA2_IP, camera2_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA2_PORT, camera2_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA2_ADMIN, camera2_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA2_PASSWORD, camera2_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA3_IP, camera3_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA3_PORT, camera3_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA3_ADMIN, camera3_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA3_PASSWORD, camera3_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA4_IP, camera4_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA4_PORT, camera4_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA4_ADMIN, camera4_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA4_PASSWORD, camera4_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA5_IP, camera5_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA5_PORT, camera5_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA5_ADMIN, camera5_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA5_PASSWORD, camera5_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA6_IP, camera6_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA6_PORT, camera6_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA6_ADMIN, camera6_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA6_PASSWORD, camera6_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA7_IP, camera7_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA7_PORT, camera7_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA7_ADMIN, camera7_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA7_PASSWORD, camera7_pass_text.getText().toString());
				
				values.put(DataColumns.COLUMN_STR_CAMERA8_IP, camera8_ip_text.getText().toString());
				values.put(DataColumns.COLUMN_INT_CAMERA8_PORT, camera8_port_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA8_ADMIN, camera8_admin_text.getText().toString());
				values.put(DataColumns.COLUMN_STR_CAMERA8_PASSWORD, camera8_pass_text.getText().toString());
				
				DataDB.getInstance(context).update(values);
			}
		}
		
	};

}
