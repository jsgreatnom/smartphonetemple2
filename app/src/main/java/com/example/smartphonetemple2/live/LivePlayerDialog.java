package com.example.smartphonetemple2.live;

import com.example.smartphonetemple2.R;
import com.kimdh.dxmediaplayer.DXMediaPlayer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LivePlayerDialog extends Dialog implements VuRexPlayer.DXEventListener, View.OnClickListener {
	private VuRexPlayer mPlayer;
	
	private DisplayMetrics mMetrics = new DisplayMetrics();
	
	private final int WINDOW_WIDTH = 340;
	private final int WINDOW_HEIGHT = 220;
	
	private final int PLAYER_WIDTH = 340;
	private final int PLAYER_HEIGHT = 220;	
	
	private static final int MESSAGE_PLAY_STOPPED = 0;
	
	private ImageButton cameraClose;
	private TextView cameraNameTextView;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {	
			case MESSAGE_PLAY_STOPPED :
				dismiss();
				break;
			}
		}
	};	

	public LivePlayerDialog(Context context) {
		super(context, R.style.AppDialogTheme);
		setContentView(R.layout.dialog_liveplayer);
		setOwnerActivity((Activity)context);
	}
	
	public void connectPlay(String url, String cameraName) {
		
		cameraNameTextView.setText(cameraName);
		
		mPlayer.setTimerEnable(false);
		if (!mPlayer.connectPlay(url)) {
			dismiss();
			Toast.makeText(getContext(), "접속실패", Toast.LENGTH_SHORT).show();
		}
	}		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		WindowManager.LayoutParams attrParams = getWindow().getAttributes(); 
		attrParams.width = Util.convDP2PX(getContext(), WINDOW_WIDTH);
		attrParams.height = Util.convDP2PX(getContext(), WINDOW_HEIGHT);
		attrParams.gravity = Gravity.CENTER;
		attrParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		attrParams.dimAmount = 0.2f;
		getWindow().setAttributes(attrParams);		
		
		cameraNameTextView = (TextView)findViewById(R.id.camera_name);
		cameraClose = (ImageButton)findViewById(R.id.camera_close);
		cameraClose.setOnClickListener(this);
		
		GridLayout gridPlayer = (GridLayout)findViewById(R.id.layout_player);		
		
		mPlayer = new VuRexPlayer(getContext());
		mPlayer.setBackgroundColor(Color.BLACK);
		mPlayer.setAspectRatio(false);
		mPlayer.setBackgroundImage(getContext().getResources().getDrawable(R.drawable.background_3));
		mPlayer.setDXEventListener(this);
		
		Drawable[] drawables = new Drawable[3];
		drawables[0] = getContext().getResources().getDrawable(R.drawable.connect_1);
		drawables[1] = getContext().getResources().getDrawable(R.drawable.connect_2);
		drawables[2] = getContext().getResources().getDrawable(R.drawable.connect_3);
		mPlayer.setConnectingImages(drawables);		
		
		GridLayout.LayoutParams params = new GridLayout.LayoutParams();			
		params.width = Util.convDP2PX(getContext(), PLAYER_WIDTH);
		params.height = Util.convDP2PX(getContext(), PLAYER_HEIGHT);
		params.setGravity(Gravity.CENTER);			
				
		gridPlayer.addView(mPlayer, params);		
	}
	
	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mPlayer.close();
	}	
	
	public void checkPlayerOrientation() {
		getOwnerActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		
		int playerWidth, playerHeight;
		
		if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {			
			WindowManager.LayoutParams attrParams = getWindow().getAttributes(); 
			attrParams.width = mMetrics.widthPixels;
			attrParams.height = Util.getRealContentSize(getOwnerActivity());
			attrParams.gravity = Gravity.CENTER;
			getWindow().setAttributes(attrParams);
			
			playerWidth = mMetrics.widthPixels;
			playerHeight = Util.getRealContentSize(getOwnerActivity());
		} else {
			WindowManager.LayoutParams attrParams = getWindow().getAttributes(); 
			attrParams.width = Util.convDP2PX(getContext(), WINDOW_WIDTH);
			attrParams.height = Util.convDP2PX(getContext(), WINDOW_HEIGHT);
			attrParams.gravity = Gravity.CENTER;
			getWindow().setAttributes(attrParams);
			
			playerWidth = Util.convDP2PX(getContext(), PLAYER_WIDTH);
			playerHeight = Util.convDP2PX(getContext(), PLAYER_HEIGHT);
		}
		
		GridLayout.LayoutParams params = (GridLayout.LayoutParams)mPlayer.getLayoutParams();
		params.setGravity(Gravity.CENTER);
		params.width = playerWidth;
		params.height = playerHeight;
		mPlayer.setLayoutParams(params);
		mPlayer.updatePlayerSize();
	}

	public void onDXEventHandler(VuRexPlayer player, int event_type, String strEvent) {
		if (event_type == DXMediaPlayer.PLAYER_EVENT_STOPPED) {
			Message msg = Message.obtain(mHandler, MESSAGE_PLAY_STOPPED);
			mHandler.sendMessage(msg);			
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.camera_close:
			mPlayer.close();
			dismiss();
			break;
		}
		
	}	
	

}
