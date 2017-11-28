package com.example.smartphonetemple2.sensor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartphonetemple2.R;
import com.example.smartphonetemple2.data.MeasureListData;

import java.util.List;

public class MeasureSensorListViewAdapter extends RecyclerView.Adapter<MeasureSensorListViewAdapter.ViewHolder> {
	private List<MeasureListData> mMeasureDataList;

	public MeasureSensorListViewAdapter() {
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listview_measure_sensor, parent, false);

		return new MeasureSensorListViewAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		MeasureListData data = mMeasureDataList.get(position);

		holder.mLocation.setText(data.getLOCATION());
		holder.mSensorName.setText(data.getSENSORNAME());
		holder.mInstName.setText(data.getINSTNAME());
		holder.mMeasure.setText(data.getMEASURE());
		holder.mStatus.setText(data.getSTATUS());

	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemCount() {

		if(mMeasureDataList == null){
			return 0;
		}else {
			return mMeasureDataList.size();
		}
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView mLocation;
		private TextView mSensorName;
		private TextView mInstName;
		private TextView mMeasure;
		private TextView mStatus;

		public ViewHolder(View itemView) {
			super(itemView);

			mLocation = (TextView)itemView.findViewById(R.id.text_measure_location);
			mSensorName = (TextView)itemView.findViewById(R.id.text_measure_name);
			mInstName = (TextView)itemView.findViewById(R.id.text_measure_id);
			mMeasure = (TextView)itemView.findViewById(R.id.text_measure_status);
			mStatus = (TextView)itemView.findViewById(R.id.text_measure_event);
		}

	}

	public void setmMeasureDataList(List<MeasureListData> mMeasureDataList) {
		this.mMeasureDataList = mMeasureDataList;
	}

}
