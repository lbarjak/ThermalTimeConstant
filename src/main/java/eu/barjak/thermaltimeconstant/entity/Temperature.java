package eu.barjak.thermaltimeconstant.entity;

import java.time.LocalDate;

public class Temperature {
	
	private LocalDate date;
	private String time;
	private String day;
	private Double roomTemp1;
	private Double roomTemp2;
	private Double outdoorTemp;
	private int elapsedDays;
	private Double forecast;
	
	public Temperature() {
	}
	
	public void setForecast(Double forecast) {
		this.forecast = forecast;
	}
	public Double getForecast() {
		return forecast;
	}
	public void setElapsedDays(int elapsedDays) {
		this.elapsedDays = elapsedDays;
	}
	public int getElapsedDays() {
		return elapsedDays;
	}
	public Double getOutdoorTemp() {
		return outdoorTemp;
	}
	public void setOutdoorTemp(Double outdoorTemp) {
		this.outdoorTemp = outdoorTemp;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Double getRoomTemp1() {
		return roomTemp1;
	}
	public void setRoomTemp1(Double roomTemp1) {
		this.roomTemp1 = roomTemp1;
	}
	public Double getRoomTemp2() {
		return roomTemp2;
	}
	public void setRoomTemp2(Double roomTemp2) {
		this.roomTemp2 = roomTemp2;
	}

}
