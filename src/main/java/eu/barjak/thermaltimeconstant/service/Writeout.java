package eu.barjak.thermaltimeconstant.service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

import eu.barjak.thermaltimeconstant.entity.GlobalVariables;
import eu.barjak.thermaltimeconstant.entity.Temperature;

public class Writeout implements GlobalVariables {
	
	public void toCSV() throws FileNotFoundException {
		String time;
		String day;
		Double outdoorTemp;
		Double roomTemp1;
		LocalDate date;
		int elapsedDays;
		Double forecast;
		PrintWriter output = new PrintWriter("hofokok.csv");
		for (Temperature temperature : TEMPERATURES) {
			time = temperature.getTime();
			day = temperature.getDay();
			outdoorTemp = temperature.getOutdoorTemp();
			roomTemp1 = temperature.getRoomTemp1();
			ROOMTEMP1.add(roomTemp1);
			date = temperature.getDate();
			elapsedDays = temperature.getElapsedDays();
			forecast = temperature.getForecast();
			output.println(date + "|" + time + "|" + day + "|" + outdoorTemp + "|" + elapsedDays + "|" + roomTemp1 + "|" + forecast);
		}
		output.close();
	}

}
