package com.example.smartphonetemple2.sensor;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.CommonData;
import com.example.smartphonetemple2.data.SensorTotalData;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TotalSensorFragment extends Fragment {
	private static final String TAG = TotalSensorFragment.class.getSimpleName();

	private Context mContext;

	private SensorTotalData mSensorTotalData;
	private TotalSensorListViewAdapter mSensorListViewAdapter;

	private String mSensorInfoUrl;

	public TotalSensorFragment(){

	}

	public TotalSensorFragment newInstance(String url){

		TotalSensorFragment fragment = new TotalSensorFragment();

		Bundle bundle = new Bundle();
		bundle.putString(CommonData.STR_TOTAL_SENSOR_URL, url);
		fragment.setArguments(bundle);

		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		View view = inflater.inflate(R.layout.fragment_total_sensor, container, false);
		
		initDefaultData();
		
		mContext = getActivity();

		RecyclerView mSensorListView = (RecyclerView) view.findViewById(R.id.listview_total_sensor);
		mSensorListViewAdapter = new TotalSensorListViewAdapter();
		mSensorListView.setAdapter(mSensorListViewAdapter);
		mSensorListView.setLayoutManager(new LinearLayoutManager(mContext));
		mSensorListView.setItemAnimator(new DefaultItemAnimator());

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
//		Log.d(TAG, "CommonData.verify = " + CommonData.verify);

		//     if( CommonData.verify )  // 서버에 이름이 저장 되어있으면 센서값 뿌려줌
		//     {
		if (!mSensorInfoUrl.isEmpty()){
			requestNetSensorInfo();
		}else{

		}
		//     }

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
			mSensorInfoUrl = getArguments().getString(CommonData.STR_TOTAL_SENSOR_URL, "");
		}
	}	

	// 센서 정보 요청
	private void requestNetSensorInfo() {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(mSensorInfoUrl)
				.get()
				.build();

		Call call = client.newCall(request);

		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {


				e.printStackTrace();

				Toast.makeText(mContext, mContext.getString(R.string.total_sensor_request_fail_messsage), Toast.LENGTH_SHORT).show();
//				mHandler.sendEmptyMessage(CommonData.REQUEST_FAIL);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {

				try {
					String jsonData = response.body().string();
//					JSONObject Jobject = new JSONObject(jsonData);
//					JSONArray Jarray = Jobject.getJSONArray("data");
					Log.d(TAG, "jsonData : " + jsonData);

					Gson gson = new Gson();
					mSensorTotalData = gson.fromJson(jsonData, SensorTotalData.class);

//					mHandler.sendEmptyMessage(CommonData.UI_UPDATE);
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							if(mSensorTotalData != null) {
								mSensorListViewAdapter.setmSensorDataList(mSensorTotalData.getData());
								mSensorListViewAdapter.notifyDataSetChanged();
							}
						}
					});

					// 2초 뒤 센서값 요청
//					mHandler.sendEmptyMessageDelayed(CommonData.REQUEST_SERVER, 2000);
//					mHandler.postDelayed(sensorInterface, 2000);
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							requestNetSensorInfo();
						}
					}, 2000);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}



	private Handler mHandler = new Handler(); /*{

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if(msg.what == CommonData.UI_UPDATE){

				if(mSensorTotalData != null) {
					mSensorListViewAdapter.setmSensorDataList(mSensorTotalData.getData());
					mSensorListViewAdapter.notifyDataSetChanged();
				}

			}*//*else if(msg.what == CommonData.REQUEST_SERVER){

				requestNetSensorInfo();

			}*//*else if(msg.what == CommonData.REQUEST_FAIL){

				Toast.makeText(mContext, mContext.getString(R.string.total_sensor_request_fail_messsage), Toast.LENGTH_SHORT).show();

			}
		}
	};*/
}
