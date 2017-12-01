package com.example.smartphonetemple2.live;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.smartphonetemple2.R;
import com.kimdh.dxmediaplayer.DXMediaPlayerFrame;

public class LiveFragment extends Fragment implements VuRexPlayer.DXGestureEventListener{
	private static final String TAG = LiveFragment.class.getSimpleName();
	
//	private static final int MAX_CHANNEL = 8;
	
	private final int PLAYER_SIZE_WIDTH_PORTRAIT = 175;
	private final int PLAYER_SIZE_HEIGHT_PORTRAIT = 150;
	
	private final int PLAYER_MARGIN = 2;
	
	private Context mContext;
	
	private String []url = new String[8];
	
	private LivePlayerDialog mLivePlayerDlg;
	
	private class PSize {
		public int width, height;
		public PSize(int w, int h) { width = w; height = h; }
	}

	private DisplayMetrics mMetrics = new DisplayMetrics();
	
	private int mFullPlayerIndex = -1;	
	private View mView;
	
	private String mName;
	private int maxChannel = 0;
	
	public LiveFragment(String name, int maxChannel) {
		mName = name;
		this.maxChannel = maxChannel;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_live, container, false);
				
		
		mContext = getActivity();
		
		// player layout
		PSize size = getPlayerSize();
		
		GridLayout gridPlayer = (GridLayout)view.findViewById(R.id.layout_player);
		
		for (int i=0; i<maxChannel; i++) {
			VuRexPlayer player = new VuRexPlayer(getActivity());
			player.setTag(i);
			player.setGestureEventListener(this);
			player.setAspectRatio(false);
			player.setBackgroundImage(getResources().getDrawable(R.drawable.real_time_background));			
			
			Drawable[] drawables = new Drawable[3];
			drawables[0] = getResources().getDrawable(R.drawable.connect_1);
			drawables[1] = getResources().getDrawable(R.drawable.connect_2);
			drawables[2] = getResources().getDrawable(R.drawable.connect_3);
			player.setConnectingImages(drawables);		
			
			GridLayout.LayoutParams params = new GridLayout.LayoutParams();			
			params.width = size.width;
			params.height = size.height;						
			params.setGravity(Gravity.CENTER);			
			
			if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				params.setMargins(0, 0, 0, 0);
			} else {
				int margin = Util.convDP2PX(getActivity(), PLAYER_MARGIN);
				params.setMargins(margin, margin, margin, margin);
			}			 
			
			gridPlayer.addView(player, params);					
		}						
		
		mLivePlayerDlg = new LivePlayerDialog(getActivity());
		
		mView = view;
		return view;
	}

	private PSize getPlayerSize() {	
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);

		if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			if (mFullPlayerIndex == -1)
				return new PSize(mMetrics.widthPixels/2, Util.getRealContentSize(getActivity())/2);
			else
				return new PSize(mMetrics.widthPixels, Util.getRealContentSize(getActivity()));
		} else {
			if (mFullPlayerIndex == -1)
				return new PSize(Util.convDP2PX(getActivity(), PLAYER_SIZE_WIDTH_PORTRAIT), Util.convDP2PX(getActivity(), PLAYER_SIZE_HEIGHT_PORTRAIT));
			else
				return new PSize(Util.convDP2PX(getActivity(), PLAYER_SIZE_WIDTH_PORTRAIT*2), Util.convDP2PX(getActivity(), PLAYER_SIZE_HEIGHT_PORTRAIT*2));
		}
	}
	
	private VuRexPlayer getPlayer(int index) {
		GridLayout gridPlayer = (GridLayout)mView.findViewById(R.id.layout_player);
		VuRexPlayer player = (VuRexPlayer)gridPlayer.getChildAt(index);				
		return player;
	}
	
	public void connectPlay(int index) {
		
		
//		Cursor c = DataDB.getInstance(mContext).getIpPort(null, null, null, DataColumns.COLUMN_STR_CAMERA1_IP + " desc");
//		if(c.moveToFirst()){
//			do{
//				CommonData.DB_IP = c.getString(c.getColumnIndex(DataColumns.COLUMN_STR_DB_IP)); 
//				CommonData.DB_PORT = c.getInt(c.getColumnIndex(DataColumns.COLUMN_INT_DB_PORT)); 
//			}while(c.moveToNext());
//		}
//		
//		channel1Url
		
		
//		String url = "rtsp://192.168.1.211/stream1";
//		url = "rtsp://admin:4321@118.45.29.69:2554/profile2/media.smp";

		if (index == 0) {
			url[0] = "rtsp://admin:4321@183.106.182.239:10203/profile2/media.smp";
			url[1] = "rtsp://admin:4321@183.106.182.239:10303/profile2/media.smp";
			url[2] = "rtsp://admin:4321@183.106.182.239:10403/profile2/media.smp";
			url[3] = "rtsp://admin:4321@183.106.182.239:10503/profile2/media.smp";
			url[4] = "rtsp://admin:4321@183.106.182.239:10603/profile2/media.smp";
			url[5] = "rtsp://admin:4321@183.106.182.239:10703/profile2/media.smp";
			url[6] = "rtsp://admin:4321@183.106.182.239:10803/profile2/media.smp";
			url[7] = "rtsp://admin:4321@183.106.182.239:10903/profile2/media.smp";
		} else if(index == 1){
			url[0] = "rtsp://admin:admin7076**@183.106.182.239:13103/profile2/media.smp";
			url[1] = "rtsp://admin:admin7076**@183.106.182.239:13203/profile2/media.smp";
			url[2] = "rtsp://admin:admin7076**@183.106.182.239:13303/profile2/media.smp";
			url[3] = "rtsp://admin:admin7076**@183.106.182.239:13403/profile2/media.smp";
			url[4] = "rtsp://admin:admin7076**@183.106.182.239:13503/profile2/media.smp";
			url[5] = "rtsp://admin:admin7076**@183.106.182.239:13603/profile2/media.smp";
			url[6] = "rtsp://admin:admin7076**@183.106.182.239:13703/profile2/media.smp";
		} else if(index == 2){
			url[0] = "rtsp://admin:admin7076**@183.106.182.239:14103/profile2/media.smp";
			url[1] = "rtsp://admin:admin7076**@183.106.182.239:14203/profile2/media.smp";
			url[2] = "rtsp://admin:admin7076**@183.106.182.239:14303/profile2/media.smp";
			url[3] = "rtsp://admin:admin7076**@183.106.182.239:14403/profile2/media.smp";
			url[4] = "rtsp://admin:admin7076**@183.106.182.239:14503/profile2/media.smp";
			url[5] = "rtsp://admin:admin7076**@183.106.182.239:14603/profile2/media.smp";
			url[6] = "rtsp://admin:admin7076**@183.106.182.239:14703/profile2/media.smp";
		}

		for (int i=0; i<maxChannel; i++) {
			VuRexPlayer player = getPlayer(i);
			player.connectPlayAsync(url[i]);
			// 클릭시 영상 확대
//			player.setTag(url[i]);
//			player.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					
//					String url = (String)v.getTag();
//					
//					LivePlayerPopup dialog = new LivePlayerPopup(mContext, url);
//					dialog.show();
//				}
//			});
		}
	}
	
	public void close(int index) {
		if (index < maxChannel) {
			VuRexPlayer player = getPlayer(index);
			if (player != null) {
				player.close();
				if (mFullPlayerIndex == (Integer)player.getTag() && isResumed()) {
					mFullPlayerIndex = -1;
					//checkPlayerOrientation();			
				}
			}
		}
	}
	
	public void closeAll() {
		for (int i=0; i<maxChannel; i++) {
			close(i);
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {		
		super.onStop();
		closeAll();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onGestureEventHandler(VuRexPlayer player, int event, MotionEvent e1, MotionEvent e2) {
		if (event == DXMediaPlayerFrame.GESTURE_EVENT_DOUBLE_TAP) {
			
			mLivePlayerDlg.show();
			mLivePlayerDlg.connectPlay(player.getChannelUrl(), player.getChannelName(player.getChannelUrl()));
		}
		
	}
	
			
}
