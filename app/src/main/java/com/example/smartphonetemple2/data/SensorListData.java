package com.example.smartphonetemple2.data;

import java.io.Serializable;

public class SensorListData implements Serializable {

	private String Module;   			// 모듈ID
	private String Temperature;			// 온도값
	private String Humidity;			// 습도값
	private String Smoke;				// 연기값
	private String State;			// 감지
	private String ModuleName;			// 모듈이름
	private String TemperatureDangerIDX;			// 온드 위험등급
	private String HumidityDangerIDX;			// 습도 위험등급
	private String SmokeIDX;			// 연기 위험등급
	private String SensorStat;			// 센서 상태 0이면 정상 -99면 비정상
	
	public SensorListData(){
		super();
	}
	
	public SensorListData(String Module, String Temperature, String Humidity, String Smoke, String State, String ModuleName, 
			String TemperatureDangerIDX, String HumidityDangerIDX, String SmokeIDX, String SensorStat)
	{
		this.Module = Module;
		this.Temperature = Temperature;
		this.Humidity = Humidity;
		this.Smoke = Smoke;
		this.State = State;
		this.ModuleName = ModuleName;
		this.TemperatureDangerIDX = TemperatureDangerIDX;
		this.HumidityDangerIDX = HumidityDangerIDX;
		this.SmokeIDX = SmokeIDX;
		this.SensorStat = SensorStat;
	}
	
	public String getModule(){
		return Module;
	}
	public void setModule(String Module){
		this.Module = Module;
	}
	
	public String getTemperature(){
		return Temperature;
	}
	public void setTemperature(String Temperature){
		this.Temperature = Temperature;
	}
	
	public String getHumidity(){
		return Humidity;
	}
	public void setHumidity(String Humidity){
		this.Humidity = Humidity;
	}
	
	public String getSmoke(){
		return Smoke;
	}
	public void setSmoke(String Smoke){
		this.Smoke = Smoke;
	}
	
	public String getState(){
		return State;
	}
	public void setState(String State){
		this.State = State;
	}
	
	public String getModuleName(){
		return ModuleName;
	}
	public void setModuleName(String ModuleName){
		this.ModuleName = ModuleName;
	}
	
	public String getTemperatureDangerIDX(){
		return TemperatureDangerIDX;
	}
	public void setTemperatureDangerIDX(String TemperatureDangerIDX){
		this.TemperatureDangerIDX = TemperatureDangerIDX;
	}
	
	public String getHumidityDangerIDX(){
		return HumidityDangerIDX;
	}
	public void setHumidityDangerIDX(String HumidityDangerIDX){
		this.HumidityDangerIDX = HumidityDangerIDX;
	}
	
	public String getSmokeIDX(){
		return SmokeIDX;
	}
	public void setSmokeIDX(String SmokeIDX){
		this.SmokeIDX = SmokeIDX;
	}
	public String getSensorStat(){
		return SensorStat;
	}
	public void setSensorStat(String SensorStat){
		this.SensorStat = SensorStat;
	}
	
	@Override
	public String toString() {
		return "SensorListData [Module=" + Module + ", Temperature=" + Temperature + ", Humidity=" + Humidity + ", Smoke=" + Smoke + ", State=" + State + ", ModuleName=" + ModuleName + ", TemperatureDangerIDX=" + TemperatureDangerIDX + ", HumidityDangerIDX=" + HumidityDangerIDX + ", SmokeIDX=" + SmokeIDX + ", SensorStat=" + SensorStat + "]";
	}
}
