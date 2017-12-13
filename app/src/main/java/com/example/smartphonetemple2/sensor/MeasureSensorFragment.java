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
import com.example.smartphonetemple2.data.MeasureTotalData;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MeasureSensorFragment extends Fragment {
    private static final String TAG = MeasureSensorFragment.class.getSimpleName();

    private Context mContext;

    private MeasureTotalData mMeasureTotalData;
    private MeasureSensorListViewAdapter mSensorListViewAdapter;
    private String mMeasureInfoUrl;

    private Handler mHandler = new Handler();

    public MeasureSensorFragment() {

    }

    public MeasureSensorFragment newInstance(String url) {

        MeasureSensorFragment fragment = new MeasureSensorFragment();

        Bundle bundle = new Bundle();
        bundle.putString(CommonData.STR_MEASURE_SENSOR_URL, url);
        fragment.setArguments(bundle);

        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measure_sensor, container, false);

        mContext = getActivity();

        initDefaultData();

        RecyclerView mSensorListView = (RecyclerView) view.findViewById(R.id.listview_measure_sensor);
        mSensorListViewAdapter = new MeasureSensorListViewAdapter();
        mSensorListView.setAdapter(mSensorListViewAdapter);
        mSensorListView.setLayoutManager(new LinearLayoutManager(mContext));
        mSensorListView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

//        Log.d(TAG, "measure url = " + mMeasureInfoUrl);
//        Log.d(TAG, "CommonData.verify = " + CommonData.verify);

//     if( CommonData.verify )  // 서버에 이름이 저장 되어있으면 센서값 뿌려줌
//     {
        if (!mMeasureInfoUrl.isEmpty())
            requestNetMeasurInfo();
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

    private void initDefaultData() {

        if (getArguments() != null) {
            mMeasureInfoUrl = getArguments().getString(CommonData.STR_MEASURE_SENSOR_URL, "");
        }
    }


    private void requestNetMeasurInfo() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(mMeasureInfoUrl)
                .get()
                .build();

        final Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, mContext.getString(R.string.measure_sensor_request_fail_messsage), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String jsonData = response.body().string();
//					JSONObject Jobject = new JSONObject(jsonData);
//					JSONArray Jarray = Jobject.getJSONArray("data");
                    Log.d(TAG, "jsonData : " + jsonData);

                    Gson gson = new Gson();
                    mMeasureTotalData = gson.fromJson(jsonData, MeasureTotalData.class);

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mMeasureTotalData != null) {
                                mSensorListViewAdapter.notifyDataSetChanged();
                                mSensorListViewAdapter.setmMeasureDataList(mMeasureTotalData.getData());
                            }
                        }
                    });

                    // 2초 뒤 센서값 요청
                    mHandler.postDelayed(sensorUpdateValue, 2000);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Runnable sensorUpdateValue = new Runnable() {
        @Override
        public void run() {
            requestNetMeasurInfo();
        }
    };

}
