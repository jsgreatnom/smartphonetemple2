package com.example.smartphonetemple2.data;

import java.util.ArrayList;

public class FireSensorData {
	
private ArrayList<FireData> data = new ArrayList<FireData>();
	
	public void setData(ArrayList<FireData> data){
		this.data = data;
	}
	
	public ArrayList<FireData> getData(){
		return data;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FireSensorData [data=" + data + "]";
	}

}
