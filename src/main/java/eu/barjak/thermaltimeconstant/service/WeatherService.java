package eu.barjak.thermaltimeconstant.service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import eu.barjak.thermaltimeconstant.entity.GlobalVariables;

@Service
public class WeatherService implements GlobalVariables {
	
	public static String startDateString = "2023-02-25";
	static String endDateString = "2023-03-05";
	public static String startTimeString = "17:30";
	static Double initialRoomTemperature = 17d;
	static final Double thermalTimeConstant = 50d;//min. 3.3 max. 122
	
	public int weather() throws ParseException, IOException {
    	LocalDate startDate = LocalDate.parse(startDateString);
    	LocalDate endDate = LocalDate.parse(endDateString);
    	
    	LOCALDATES.clear();
    	TEMPERATURES_MAP.clear();
    	TEMPERATURES.clear();
    	ROOMTEMP1.clear();
    	OUTDOORTEMP.clear();
    	FORECAST.clear();
    	
    	
    	Dates dates = new Dates();
		dates.elapsedDays(startDate, endDate);//startDate - endDate --> LOCALDATES
		
		WeatherQuery weatherQuery = new WeatherQuery();
		int indexOfMeasuredTemperatures = weatherQuery.steps();//LOCALDATES --> TEMPERATURES_MAP
		
		if(indexOfMeasuredTemperatures > 0) {
			Calculation calculation = new Calculation();
			calculation.calculation(thermalTimeConstant, startTimeString, initialRoomTemperature);
			Double last24hAverage = calculation.last24hAverage(indexOfMeasuredTemperatures);
			calculation.forecast(indexOfMeasuredTemperatures, last24hAverage);
			
			new Writeout().toCSV();
		}
		return indexOfMeasuredTemperatures;
	}

}
