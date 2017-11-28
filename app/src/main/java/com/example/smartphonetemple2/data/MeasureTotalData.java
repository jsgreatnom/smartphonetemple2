package com.example.smartphonetemple2.data;

import java.util.ArrayList;

public class MeasureTotalData {
	private ArrayList<MeasureListData> data = new ArrayList<MeasureListData>();
	public void setData(ArrayList<MeasureListData> data){
		this.data = data;
	}
	
	public ArrayList<MeasureListData> getData(){
		return data;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MeasurTotalData [data=" + data + "]";
	}
}
