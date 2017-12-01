package com.example.smartphonetemple2.live;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartphonetemple2.R;
import com.kimdh.dxmediaplayer.DXMediaPlayer;
import com.kimdh.dxmediaplayer.DXMediaPlayer.AsyncResultListener;
import com.kimdh.dxmediaplayer.DXMediaPlayer.DXEventListener;
import com.kimdh.dxmediaplayer.DXMediaPlayerFrame;
import com.kimdh.dxmediaplayer.DXMediaPlayerFrame.DXGestureEventListener;

public class VuRexPlayer extends LinearLayout implements DXGestureEventListener, DXEventListener, AsyncResultListener {

	public static final String TAG = VuRexPlayer.class.getSimpleName();

	private DXMediaPlayerFrame mPlayer;
	private TextView mTextTitle;

	private String mURL;

	public interface DXGestureEventListener {
		public void onGestureEventHandler(VuRexPlayer player, int event, MotionEvent e1, MotionEvent e2);
	}

	private DXGestureEventListener mGestureEventListener;

	public void setGestureEventListener(DXGestureEventListener listener) {
		mGestureEventListener = listener;
	}

	public interface DXEventListener {
		public void onDXEventHandler(VuRexPlayer player, int event_type, String strEvent);
	}

	private DXEventListener mEventListener;

	public void setDXEventListener(DXEventListener listener) {
		mEventListener = listener;
	}

	private final int CONNECT_TIMEOUT = 4;
	private boolean mIsReconnecting = false;
	private static final int RECONNECT_INTERVAL = 1;

	private static final int MESSAGE_PLAY_STOPPED = 0;
	private static final int MESSAGE_RECONNECT = 1;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case MESSAGE_PLAY_STOPPED:
				mPlayer.clearTextAll();
				mTextTitle.setText("");
				mTextTitle.setVisibility(View.GONE);
				break;
			case MESSAGE_RECONNECT:
				if (mIsReconnecting)
					connectPlayAsync(mURL);
				break;
			}
		}
	};

	public VuRexPlayer(Context context) {
		super(context);
		initLayout(context);
	}

	private void initLayout(Context context) {
		setOrientation(VERTICAL);

		mTextTitle = new TextView(context);
		mTextTitle.setGravity(Gravity.CENTER);
		mTextTitle.setBackgroundResource(R.drawable.metal_bar);
		// mTextTitle.setBackgroundColor(Color.rgb(209, 209, 211));
		mTextTitle.setTextColor(Color.BLACK);
		mTextTitle.setTextSize(15.0f);
		addView(mTextTitle, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, Util.convDP2PX(context, 20)));
		mTextTitle.setVisibility(View.GONE);

		mPlayer = new DXMediaPlayerFrame(context);
		mPlayer.setOnLayoutChangedEvent(false);
		addView(mPlayer, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		mPlayer.setGestureEventListener(this);
		mPlayer.setDXEventListener(this);
		mPlayer.setAsyncResultListener(this);

		setPadding(1, 1, 1, 1);
		setBackgroundColor(Color.rgb(20, 30, 30));
	}

	public boolean connectPlayAsync(String url) {
		synchronized (this) {
			mURL = url;
			mTextTitle.setVisibility(View.VISIBLE);
			mTextTitle.setText(getChannelName(url));
			mPlayer.setVodSession(false);
			mPlayer.setTimerEnable(false);
			if (mPlayer.connectPlayAsync(url, 1, CONNECT_TIMEOUT, 0) == 0) {
				mTextTitle.setVisibility(View.VISIBLE);
				return true;
			} else {
				mTextTitle.setVisibility(View.GONE);
				return false;
			}
		}
	}

	public boolean connectPlay(String url) {
		synchronized (this) {
			mURL = url;
			// mTextTitle.setText("서쪽상부-1");
			mTextTitle.setVisibility(View.GONE);
			mPlayer.setVodSession(false);
			mPlayer.setTimerEnable(false);
			if (mPlayer.connectPlay(url, 1, CONNECT_TIMEOUT, 0) == 0) {
//				mTextTitle.setVisibility(View.VISIBLE);
				return true;
			} else {
				mTextTitle.setVisibility(View.GONE);
				return false;
			}
		}
	}

	public void close() {
		synchronized (this) {
			mPlayer.close();
			mPlayer.clearTextAll();
			mTextTitle.setText("");
			mTextTitle.setVisibility(View.GONE);
			mIsReconnecting = false;
			mHandler.removeMessages(MESSAGE_RECONNECT);
		}
	}

	public void setTimerEnable(boolean enable) {
		mPlayer.setTimerEnable(enable);
	}

	public void setAspectRatio(boolean flag) {
		mPlayer.setAspectRatio(flag);
	}

	public void setBackgroundImage(Drawable drawable) {
		mPlayer.setBackgroundImage(drawable);
	}

	public void setConnectingImages(Drawable[] drawables) {
		mPlayer.setConnectingImages(drawables);
	}

	public void updatePlayerSize() {
		mPlayer.updatePlayerSize();
	}

	public void onDXEventHandler(int event_type, String strEvent) {
		switch (event_type) {
		case DXMediaPlayer.PLAYER_EVENT_STOPPED: {
			Message msg = Message.obtain(mHandler, MESSAGE_PLAY_STOPPED);
			mHandler.sendMessage(msg);
		}
			break;
		}

		if (mEventListener != null)
			mEventListener.onDXEventHandler(this, event_type, strEvent);
	}

	public void onAsyncResultHandler(int cmd, int result) {
		if (cmd == DXMediaPlayer.ASYNC_RESULT_CONNECTPLAY) {
			if (result < 0) {
				// mIsReconnecting = true;
				// mHandler.sendEmptyMessageDelayed(MESSAGE_RECONNECT,
				// RECONNECT_INTERVAL * 1000);
			}
		}
	}

	// 포트번호로 카메라 위치 구분한다...
	public String getChannelName(String url) {

		Uri uri = Uri.parse(url);
		int port = uri.getPort();

		Log.d(TAG, "DB port - " + port);

		if (port == 10203) {
			return "서쪽상부-1";
		} else if (port == 10303) {
			return "서쪽상부-2";
		} else if (port == 10403) {
			return "서쪽상부-3";
		} else if (port == 10503) {
			return "서쪽상부-4";
		} else if (port == 10603) {
			return "동쪽상부-1";
		} else if (port == 10703) {
			return "동쪽상부-2";
		} else if (port == 10803) {
			return "동쪽상부-3";
		} else if (port == 10903) {
			return "동쪽상부-4";
		} else if (port == 13103) {
			return "1층상부1";
		} else if (port == 13203) {
			return "1층상부2";
		} else if (port == 13303) {
			return "1층상부3";
		} else if (port == 13403) {
			return "1층상부4";
		} else if (port == 13503) {
			return "2층상부1";
		} else if (port == 13603) {
			return "2층상부2";
		} else if (port == 13703) {
			return "2층상부3";
		} else if (port == 14103) {
			return "1층상부1";
		} else if (port == 14203) {
			return "1층상부2";
		} else if (port == 14303) {
			return "1층상부3";
		} else if (port == 14403) {
			return "1층상부4";
		} else if (port == 14503) {
			return "2층상부1";
		} else if (port == 14603) {
			return "2층상부2";
		} else if (port == 14703) {
			return "2층상부3";
		} else {
			return "";
		}
	}
	
		
	public String getChannelUrl(){
		return mURL;
	}

	@Override
	public void onGestureEventHandler(DXMediaPlayerFrame arg0, int arg1, MotionEvent arg2, MotionEvent arg3) {
		if (mGestureEventListener != null)
			mGestureEventListener.onGestureEventHandler(this, arg1, arg2, arg3);

	}
}
