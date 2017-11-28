package com.example.smartphonetemple2.data;

public class FireData {
	
	private int alarmID;
	private int sensorID;
	private String sensorName;
	private String eventLevel;
	private String eventTime;
	private String message;
	
	public FireData(int alarmID, int sensorID, String sensorName, String EventLevel, String EventTime, String Message) {
		this.alarmID = alarmID;
		this.sensorID = sensorID;
		this.sensorName = sensorName;
		this.eventLevel = EventLevel;
		this.eventTime = EventTime;
		this.message = Message;
	}
	
	public int getAlarmID(){
		return alarmID;
	}
	
	public int getSensorID(){
		return sensorID;
	}
	
	public String getSensorName(){
		return sensorName;
	}
	
	public String getEventLevel(){
		return eventLevel;
	}
	public String getEventTime(){
		return eventTime;
	}
	
	public String getMessage(){
		return message;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "alarmID : " + alarmID + ", sensorID : " + sensorID + ", sensorName : " + sensorName + ", eventLevel : " + eventLevel
				+ ", eventTime : " + eventTime + ", message : " + message;
	} 
	
	
	
}
