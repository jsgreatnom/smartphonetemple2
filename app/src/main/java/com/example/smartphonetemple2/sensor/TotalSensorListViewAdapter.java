package com.example.smartphonetemple2.sensor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.SensorListData;

import java.util.ArrayList;

public class TotalSensorListViewAdapter extends RecyclerView.Adapter<TotalSensorListViewAdapter.ViewHolder> {
	private ArrayList<SensorListData> mSensorDataList;

	public TotalSensorListViewAdapter() {

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listview_total_sensor, parent, false);

		return new TotalSensorListViewAdapter.ViewHolder(view);

	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		SensorListData data = mSensorDataList.get(position);

		holder.mModuleName.setText(data.getModuleName());
		holder.mTemp.setText(String.valueOf(data.getTemperature()));
		holder.mHum.setText(String.valueOf(data.getHumidity()));
		holder.mSmoke.setText(String.valueOf(data.getSmoke()));
		holder.mStat.setText(String.valueOf(data.getSensorStat()));

		holder.layoutImageTemp.setBackgroundResource(getDangerImage(data.getTemperatureDangerIDX()));
		holder.layoutImageHuminity.setBackgroundResource(getDangerImage(data.getHumidityDangerIDX()));
		holder.layoutImagemageSmoke.setBackgroundResource(getDangerImage(data.getSmokeIDX()));
		holder.layoutImageStat.setBackgroundResource(getDangerImage(data.getSensorStat()));

		holder.imageTemp.setBackgroundResource(R.drawable.sensor_icon_temp);
		holder.imageHuminity.setBackgroundResource(R.drawable.sensor_icon_humidity);
		holder.imageSmoke.setBackgroundResource(R.drawable.sensor_icon_smoke);
		holder.imageStat.setBackgroundResource(R.drawable.sensor_icon_stat);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemCount() {

		if(mSensorDataList == null){
			return 0;
		}else {
			return mSensorDataList.size();
		}
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{

		private TextView mModuleName;
		private TextView mTemp;
		private TextView mHum;
		private TextView mSmoke;
		private TextView mStat;
		private RelativeLayout layoutImageTemp;
		private RelativeLayout layoutImageHuminity;
		private RelativeLayout layoutImagemageSmoke;
		private RelativeLayout layoutImageStat;
		private ImageView imageTemp;
		private ImageView imageHuminity;
		private ImageView imageSmoke;
		private ImageView imageStat;

		public ViewHolder(View itemView) {
			super(itemView);

			mModuleName = (TextView)itemView.findViewById(R.id.text_module_name);
			mTemp = (TextView)itemView.findViewById(R.id.text_temperature);
			mHum = (TextView)itemView.findViewById(R.id.text_humidity);
			mSmoke = (TextView)itemView.findViewById(R.id.text_smoke);
			mStat = (TextView)itemView.findViewById(R.id.text_stat);
			layoutImageTemp = (RelativeLayout)itemView.findViewById(R.id.layout_image_temperature);
			layoutImageHuminity = (RelativeLayout)itemView.findViewById(R.id.layout_iamge_humidity);
			layoutImagemageSmoke = (RelativeLayout)itemView.findViewById(R.id.layout_image_smoke);
			layoutImageStat = (RelativeLayout)itemView.findViewById(R.id.layout_image_stat);
			imageTemp = (ImageView)itemView.findViewById(R.id.image_temperature);
			imageHuminity = (ImageView)itemView.findViewById(R.id.iamge_humidity);
			imageSmoke = (ImageView)itemView.findViewById(R.id.image_smoke);
			imageStat = (ImageView)itemView.findViewById(R.id.image_stat);
		}
	}

	private int getDangerImage(String idxValue){

		int idx = Integer.parseInt(idxValue);
		
		if(idx == 1){
			return R.drawable.background_interest;
		}else if(idx == 2){
			return R.drawable.background_caution;
		}else if(idx == 3){
			return R.drawable.background_danger;
		}else if(idx == 4){
			return R.drawable.background_alert;
		}else{
			return R.drawable.sensor_icon_backgroud;
		}
		
	}

	public void setmSensorDataList(ArrayList<SensorListData> mSensorDataList) {
		this.mSensorDataList = mSensorDataList;
	}
}
