package com.example.smartphonetemple2.sensor;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.CommonData;
import com.example.smartphonetemple2.data.FireData;
import com.example.smartphonetemple2.data.FireSensorData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FireSensorFragment extends Fragment {
	private static final String TAG = FireSensorFragment.class.getSimpleName();
	
	private Context mContext;
	
	private ListView mFireSensorListView;
	private FireSensorListViewAdapter mFireSensorListViewAdapter;
	private List<FireData> mFireData;
	private FireSensorData fireSensorData;
	
	private String mSensorInfoUrl;

	public FireSensorFragment(){

	}

	public FireSensorFragment newInstance(String url){

		FireSensorFragment fragment = new FireSensorFragment();
		Bundle bundle = new Bundle();
		bundle.putString(CommonData.STR_FIRE_SENSOR_URL, url);
		fragment.setArguments(bundle);

		return fragment;

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_fire_sensor, container, false);
		
		mContext = getActivity();

		initDefaultData();

		Log.d(TAG, "onCreateView - " + mSensorInfoUrl);
		
		mFireData = new ArrayList<FireData>();
		
		mFireSensorListView = (ListView)view.findViewById(R.id.listview_fire_sensor);
		mFireSensorListViewAdapter = new FireSensorListViewAdapter(mContext, mFireData);
		mFireSensorListView.setAdapter(mFireSensorListViewAdapter);
		
		
		return view;				
	}

	@Override
	public void onStart() {
		super.onStart();
		
		requestNetSensorInfo();

	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	private void initDefaultData(){
//		CommonData.dirCheck();

		if(getArguments() != null){
			mSensorInfoUrl = getArguments().getString(CommonData.STR_FIRE_SENSOR_URL, "");
		}
	}

	private void requestNetSensorInfo(){

	}
		
	private Handler mSensorNetworkHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case R.id.received_succeeded:

				String receiveMessage = (String) msg.obj;
				//System.out.println("recv data[sensor] : " + receiveMessage);
				
				/** json to object **/
				Gson gson = new Gson();
				fireSensorData = gson.fromJson(receiveMessage, FireSensorData.class);
				
				addSensorData(fireSensorData);	
				
//				if (isResumed()) mHandler.sendEmptyMessageDelayed(0, 1500);
				//System.out.println(mSensorTotalData.toString());
				
				Log.d(TAG, "불꽃 데이터 받기 성공");
			
								
				break;
			case R.id.received_failed:
				Log.d(TAG, "received_failed");
				Toast.makeText(mContext, "센서 정보를 받는데 실패 했습니다. 뒤로가기 하셨다가 다시 실행해주세요.", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
		
//	private Handler mHandler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			super.handleMessage(msg);
//			if (msg.what == 0) {
//				requestNetSensorInfo(mSensorInfoUrl);
//			} 
//		}
//	};
	
	private void addSensorData(FireSensorData totalData) {
		mFireData.clear();
		List<FireData> list = totalData.getData();
	
		for (int i=0; i<list.size(); i++) {
			FireData data = list.get(i);
			int alarmID = data.getAlarmID();
			int sensorID = data.getSensorID();
			String eventLevel = data.getEventLevel();
			String eventTime = data.getEventTime();
			String sensorName = data.getSensorName();
			String message = data.getMessage();
			
			FireData sensor = new FireData(alarmID, sensorID, sensorName, eventLevel, eventTime, message);
			
			mFireData.add(sensor);
		}	
		
		Log.d(TAG, "mFireData.toString() - " + mFireData.toString());
		
		mFireSensorListViewAdapter.notifyDataSetChanged();
	}
}
