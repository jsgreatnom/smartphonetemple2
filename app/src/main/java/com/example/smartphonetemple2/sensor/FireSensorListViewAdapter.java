package com.example.smartphonetemple2.sensor;

import java.util.List;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.FireData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FireSensorListViewAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<FireData> mFireDataList;
	private LayoutInflater mLayoutInflater;
	
	public FireSensorListViewAdapter(Context context, List<FireData> fireDataList) {
		mContext = context;
		mFireDataList = fireDataList;
		mLayoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFireDataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mFireDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemLayout = convertView;
		ViewHolder holder = null;
		
		if (itemLayout == null) {
			itemLayout = mLayoutInflater.inflate(R.layout.layout_listview_fire_sensor, null);
			holder = new ViewHolder();
			holder.mModuleName = (TextView)itemLayout.findViewById(R.id.text_module_name);
			holder.textFire1Stat = (TextView)itemLayout.findViewById(R.id.text_fire1_stat);
			holder.textFire2Stat = (TextView)itemLayout.findViewById(R.id.text_fire2_stat);
			holder.layoutImageFire1 = (RelativeLayout)itemLayout.findViewById(R.id.layout_image_fire1);
			holder.layoutImageFire2 = (RelativeLayout)itemLayout.findViewById(R.id.layout_image_fire2);
			holder.imageFire1 = (ImageView)itemLayout.findViewById(R.id.image_fire1);
			holder.imageFire2 = (ImageView)itemLayout.findViewById(R.id.iamge_fire2);
			
			itemLayout.setTag(holder);
		} else {
			holder = (ViewHolder)itemLayout.getTag();
		}
		
		FireData data = mFireDataList.get(position);
		
//		holder.mModuleName.setText(data.getModuleName());
//		
//		if(data.isFireSensorStat1()){
//			holder.imageFire1.setBackgroundResource(R.drawable.fire1_on);
//			holder.textFire1Stat.setText(mContext.getString(R.string.fire_sensor_on));
//		}else{
//			holder.imageFire1.setBackgroundResource(R.drawable.fire1_off);
//			holder.textFire1Stat.setText(mContext.getString(R.string.fire_sensor_off));
//		}
//		
//		if(data.isFireSensorStat2()){
//			holder.imageFire2.setBackgroundResource(R.drawable.fire2_on);
//			holder.textFire2Stat.setText(mContext.getString(R.string.fire_sensor_on));
//		}else{
//			holder.imageFire2.setBackgroundResource(R.drawable.fire2_off);
//			holder.textFire2Stat.setText(mContext.getString(R.string.fire_sensor_off));
//		}
		
		
		return itemLayout;
	}
	
	class ViewHolder {
		TextView mModuleName;
		TextView textFire1Stat;
		TextView textFire2Stat;
		RelativeLayout layoutImageFire1;
		RelativeLayout layoutImageFire2;
		ImageView imageFire1;
		ImageView imageFire2;
		
	}

}
