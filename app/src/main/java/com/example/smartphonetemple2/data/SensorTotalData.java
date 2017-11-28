package com.example.smartphonetemple2.data;

import java.util.ArrayList;

public class SensorTotalData {
	private ArrayList<SensorListData> data = new ArrayList<SensorListData>();
	
	public void setData(ArrayList<SensorListData> data){
		this.data = data;
	}
	
	public ArrayList<SensorListData> getData(){
		return data;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "SensorTotalData [data=" + data + "]";
	}
	
	
}
