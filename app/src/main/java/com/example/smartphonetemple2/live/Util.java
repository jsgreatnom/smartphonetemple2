package com.example.smartphonetemple2.live;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

public class Util {
	public static int convDP2PX(Context context, float dp) {
	    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
	    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
	}

	public static int getStatusBarHeight(Context pContext) {
		Resources resources = pContext.getResources();
		int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");		
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}

	public static int getActionBarHeight(Context pContext) {		
		TypedValue tv = new TypedValue();
		int actionBarHeight = 0;
		if (pContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,pContext.getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}

	public static int getNavigationBarHeight(Context pContext) {
		Resources resources = pContext.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}	
	
	public static int getRealContentSize(Activity context) {
		int statusBarHeight = Util.getStatusBarHeight(context);
		int actionBarHeight = Util.getActionBarHeight(context);
		int navigationBarHeight = Util.getNavigationBarHeight(context);
		
		statusBarHeight = 0;
		actionBarHeight = 0;
		navigationBarHeight = 0;
				
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int totalHeight = 0;
		if (android.os.Build.VERSION.SDK_INT < 14 /* 2.2<= x(320x480),(480x800) <= 4.0 */) {
			totalHeight = (statusBarHeight + actionBarHeight + navigationBarHeight) * 2;
		} 
		else if(android.os.Build.VERSION.SDK_INT >= 14 /* x(480x800) >= 4.0 */) {
			totalHeight = statusBarHeight + actionBarHeight;
		}
		int thisHeight = metrics.heightPixels - totalHeight;
		return thisHeight;
	}	
	
	public static int byteArrayToInt(byte[] b, int offset) {
	    int value = 0;
	    for (int i = 0; i < 4; i++) {
	        int shift = (4 - 1 - i) * 8;
	        value += (b[i+offset] & 0x000000FF) << shift;
	    }
	    return value;
	}
	
	public static String getLocalIpAddress() {
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			
			while (en.hasMoreElements()) {
				NetworkInterface interf = en.nextElement();
				Enumeration<InetAddress> ips = interf.getInetAddresses();
				
				while (ips.hasMoreElements()) {
					InetAddress inetAddress = ips.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						if (inetAddress instanceof Inet4Address)
							return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("Error", ex.toString());
		}
		return "";
	}
	
	public static String getVersionName(Context context)
	{
	    try {
	        PackageInfo pi= context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	        return pi.versionName;
	    } catch (NameNotFoundException e) {
	        return null;
	    }
	}	
}
