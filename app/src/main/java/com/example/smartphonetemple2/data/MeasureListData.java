package com.example.smartphonetemple2.data;

import java.io.Serializable;


//"LOCATION":"북측단부","SENSORNAME":"거리계","INSTNAME":"B2","MEASURE":"2.94168750","STATUS":"안전
public class MeasureListData implements Serializable{
	
	private String LOCATION;         
	private String SENSORNAME;
	private String INSTNAME;
	private String MEASURE;
	private String STATUS;
	
	public MeasureListData(){
		super();
	}
	
	public MeasureListData(String LOCATION, String SENSORNAME, String INSTNAME, String MEASURE, String STATUS ){
		this.LOCATION = LOCATION;
		this.SENSORNAME = SENSORNAME;
		this.INSTNAME = INSTNAME;
		this.MEASURE = MEASURE;
		this.STATUS = STATUS;
	}
	
	public String getLOCATION(){
		return LOCATION;
	}
	public void setLOCATION(String LOCATION){
		this.LOCATION = LOCATION;
	}
	public String getSENSORNAME(){
		return SENSORNAME;
	}
	public void setSENSORNAME(String SENSORNAME){
		this.SENSORNAME = SENSORNAME;
	}
	public String getINSTNAME(){
		return INSTNAME;
	}
	public void setINSTNAME(String INSTNAME){
		this.INSTNAME = INSTNAME;
	}
	public String getMEASURE(){
		return MEASURE;
	}
	public void setMEASURE(String MEASURE){
		this.MEASURE = MEASURE;
	}
	public String getSTATUS(){
		return STATUS;
	}
	public void setSTATUS(String STATUS){
		this.STATUS = STATUS;
	}
	
	@Override
	public String toString() {
		return "MeasurListData [LOCATION=" + LOCATION + ", SENSORNAME=" + SENSORNAME + ", INSTNAME=" + INSTNAME + ", MEASURE=" + MEASURE + ", STATUS=" + STATUS + "]";
	}
}
