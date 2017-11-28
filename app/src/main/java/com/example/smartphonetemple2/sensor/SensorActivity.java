package com.example.smartphonetemple2.sensor;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.ToggleButton;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.CommonData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SensorActivity extends Activity implements OnTabChangeListener {
	private TabHost mTabHost;

	private static final String TAG = SensorActivity.class.getSimpleName();
	
	private final int TAB_NUM = 3;
	
	private TotalSensorFragment[] mTotalSensorFragment = new TotalSensorFragment[TAB_NUM];
	private MeasureSensorFragment[] mMeasureSensorFragment = new MeasureSensorFragment[TAB_NUM];
	private FireSensorFragment[] mFireSensorFragment = new FireSensorFragment[TAB_NUM];
	private Fragment[] mCurrentFragment = new Fragment[TAB_NUM];
	
	private ToggleButton sensorButton;
	private ToggleButton measureButton;
	private ToggleButton fireButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		
		mTabHost = (TabHost)findViewById(R.id.tabHost);
		mTabHost.setup();

        //Tab 1
        TabHost.TabSpec spec = mTabHost.newTabSpec("Tab Bridge");
        spec.setContent(R.id.tabBridge);
        spec.setIndicator(createTabView(this, R.drawable.selector_bridge));
        mTabHost.addTab(spec);

        //Tab 2
        spec = mTabHost.newTabSpec("Tab Gate1");
        spec.setContent(R.id.tabGate1);
        spec.setIndicator(createTabView(this, R.drawable.selector_gatehouse1));
        mTabHost.addTab(spec);

        //Tab 3
        spec = mTabHost.newTabSpec("Tab Gate2");
        spec.setContent(R.id.tabGate2);
        spec.setIndicator(createTabView(this, R.drawable.selector_gatehouse2));
        mTabHost.addTab(spec);
        
        mTabHost.setOnTabChangedListener(this);
        
		SimpleDateFormat curDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strTime = curDateFormat.format(currentTime);
     
        String sensorUrl = String.format(getString(R.string.url_prevention_info), CommonData.DB_IP, CommonData.DB_PORT, strTime);
        String measureUrl = String.format(getString(R.string.url_measur_info), CommonData.DB_IP, CommonData.DB_PORT);
        String fireUrl = String.format(getString(R.string.url_fire_sensor_info), CommonData.DB_IP, CommonData.DB_PORT);

		Log.d(TAG, "onCreate::sensorUrl - " + sensorUrl);
		Log.d(TAG, "onCreate::measureUrl - " + measureUrl);
		Log.d(TAG, "onCreate::fireUrl - " + fireUrl);

        // 교량
        mTotalSensorFragment[0] = new TotalSensorFragment().newInstance(sensorUrl);
        mMeasureSensorFragment[0] = new MeasureSensorFragment().newInstance(measureUrl);
        mFireSensorFragment[0] = new FireSensorFragment().newInstance(fireUrl);
        
        // 문루1
        mTotalSensorFragment[1] = new TotalSensorFragment().newInstance("");
        mMeasureSensorFragment[1] = new MeasureSensorFragment().newInstance("");
        mFireSensorFragment[1] = new FireSensorFragment().newInstance("");
        
        // 문루2
        mTotalSensorFragment[2] = new TotalSensorFragment().newInstance("");
        mMeasureSensorFragment[2] = new MeasureSensorFragment().newInstance("");
        mFireSensorFragment[2] = new FireSensorFragment().newInstance("");
                
	    FragmentManager fm = getFragmentManager();
	    
	    FragmentTransaction ft = fm.beginTransaction();
	    ft.add(R.id.fragment_container1, mTotalSensorFragment[0]);
	    ft.commit();

	    ft = fm.beginTransaction();
	    ft.add(R.id.fragment_container2, mTotalSensorFragment[1]);
	    ft.commit();

	    ft = fm.beginTransaction();
	    ft.add(R.id.fragment_container3, mTotalSensorFragment[2]);
	    ft.commit();
	    
	    for (int i = 0; i < TAB_NUM; i++)
	    	mCurrentFragment[i] = mTotalSensorFragment[i];
	    
	    sensorButton = (ToggleButton)findViewById(R.id.btnTotalSensor);
		measureButton = (ToggleButton)findViewById(R.id.btnMeasureSensor);
		fireButton = (ToggleButton)findViewById(R.id.btnFireSensor);
		
		sensorButton.setChecked(true);
	}

	private void updateFragment(int tabIndex, Fragment fragment) {
		int fragmentId = -1;
		
		if (tabIndex == 0) fragmentId = R.id.fragment_container1;
		else if (tabIndex == 1) fragmentId = R.id.fragment_container2;
		else if (tabIndex == 2) fragmentId = R.id.fragment_container3;
		
		if (fragmentId != -1) {
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(fragmentId, fragment);
			ft.commit();
			mCurrentFragment[tabIndex] = fragment;
		}
	}
	
	//하단 탭 구현
    private View createTabView(Context context, int backgroundResource) {

        View view = LayoutInflater.from(context).inflate(R.layout.view_menu_tab, null, false);
        ImageView iv = (ImageView) view.findViewById(R.id.tab_image);
        iv.setBackgroundResource(backgroundResource);
        return view;
    }
	
	@Override
	public void onTabChanged(String tabId) {
		System.out.println("Tab Index : " + tabId);
	}
	
	public void onSensorButtonClicked(View v) {
		int id = v.getId();
		int tabIndex = mTabHost.getCurrentTab();
		Fragment fragment = null;

		switch (id) {
		case R.id.btnTotalSensor :
			bottomButtonSelector(R.id.btnTotalSensor);
			fragment = mTotalSensorFragment[tabIndex];
			break;
		case R.id.btnMeasureSensor :
			bottomButtonSelector(R.id.btnMeasureSensor);
			fragment = mMeasureSensorFragment[tabIndex];
			break;
		case R.id.btnFireSensor :
			bottomButtonSelector(R.id.btnFireSensor);
			fragment = mFireSensorFragment[tabIndex];
			break;
		}
		
		if (fragment != null && fragment != mCurrentFragment[tabIndex]) {
			updateFragment(tabIndex, fragment);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void bottomButtonSelector(int resourceId){
		
		switch(resourceId){
		case R.id.btnTotalSensor :
			sensorButton.setChecked(true);
			measureButton.setChecked(false);
			fireButton.setChecked(false);
			break;
		case R.id.btnMeasureSensor :
			sensorButton.setChecked(false);
			measureButton.setChecked(true);
			fireButton.setChecked(false);
			break;
		case R.id.btnFireSensor :
			sensorButton.setChecked(false);
			measureButton.setChecked(false);
			fireButton.setChecked(true);
			break;
		}
		
	}
}
