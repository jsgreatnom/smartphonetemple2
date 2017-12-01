package com.example.smartphonetemple2.live;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.example.smartphonetemple2.R;

public class LiveActivity extends Activity implements OnTabChangeListener {
	private TabHost mTabHost;
	
	private final int TAB_NUM = 3;
	private final int TAB1_CAMERA_CHANNEL = 8;
	private final int TAB2_CAMERA_CHANNEL = 7;
	private final int TAB3_CAMERA_CHANNEL = 7;

	private LiveFragment[] mLiveFragment = new LiveFragment[TAB_NUM];
	private Fragment[] mCurrentFragment = new Fragment[TAB_NUM];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live);
		
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
        
        mLiveFragment[0] = new LiveFragment("교량", TAB1_CAMERA_CHANNEL);
        mLiveFragment[1] = new LiveFragment("문루1", TAB2_CAMERA_CHANNEL);
        mLiveFragment[2] = new LiveFragment("문루2", TAB3_CAMERA_CHANNEL);
        
	    FragmentManager fm = getFragmentManager();
	    
	    FragmentTransaction ft = fm.beginTransaction();
	    ft.add(R.id.fragment_container1, mLiveFragment[0]);
	    ft.commit();

	    ft = fm.beginTransaction();
	    ft.add(R.id.fragment_container2, mLiveFragment[1]);
	    ft.commit();

	    ft = fm.beginTransaction();
	    ft.add(R.id.fragment_container3, mLiveFragment[2]);
	    ft.commit();
	    
	    for (int i = 0; i < TAB_NUM; i++)
	    	mCurrentFragment[i] = mLiveFragment[i];    
	   	   
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
		int index = mTabHost.getCurrentTab();
		for (int i=0; i<TAB_NUM; i++) {
			if (i == index) {
				mLiveFragment[i].connectPlay(index);
			} else {
				mLiveFragment[i].closeAll();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.live, menu);
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

	@Override
	protected void onStart() {
		super.onStart();
		mLiveFragment[0].connectPlay(0);
	}	
}
