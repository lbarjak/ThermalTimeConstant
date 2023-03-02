package eu.barjak.thermaltimeconstant.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import eu.barjak.thermaltimeconstant.entity.GlobalVariables;
import eu.barjak.thermaltimeconstant.entity.Temperature;

public class Calculation implements GlobalVariables {
	
	Double roomTemp1;
	Double roomTemp2;
	Double outdoorTemp;
	Double multiplier;

	public void calculation(Double thermalTimeConstant, String startTimeString, Double initialRoomTemperature) {
		Locale huLoc = new Locale("hu");
		DateTimeFormatter napNeveMagyarul = DateTimeFormatter.ofPattern("EEEE", huLoc);
		String day;
		Double exponent = -(10.0 / 60) / thermalTimeConstant;
		roomTemp1 = initialRoomTemperature;
		multiplier = Math.exp(exponent);

		for (LocalDate localDate : LOCALDATES) {
			for (Temperature temperature : TEMPERATURES_MAP.get(localDate)) {
				TEMPERATURES.add(temperature);
				temperature.setDate(localDate);
				day = localDate.format(napNeveMagyarul);
				temperature.setDay(day);
			}
		}

		for (int i = 0; i < TEMPERATURES.size(); i++) {
			TEMPERATURES.get(i).setElapsedDays(i / 144);
		}

		int startTimeIndex = searchStartTimeIndex(startTimeString);
		for (int i = startTimeIndex; i < TEMPERATURES.size(); i++) {
			TEMPERATURES.get(i).setRoomTemp1(roomTemp1);
			outdoorTemp = TEMPERATURES.get(i).getOutdoorTemp();
			if (outdoorTemp != null) {
				tau();
				TEMPERATURES.get(i).setRoomTemp2(roomTemp2);
				roomTemp1 = roomTemp2;
			}
		}
	}

	public void tau() {
		roomTemp2 = outdoorTemp + (roomTemp1 - outdoorTemp) * multiplier;
	}

	public int searchStartTimeIndex(String startTimeString) {
		String timeString;
		int startTimeIndex = 0;
		for (int i = 0; i < 144; i++) {
			timeString = TEMPERATURES.get(i).getTime();
			if (timeString.equals(startTimeString)) {
				startTimeIndex = i;
			}
		}
		return startTimeIndex;
	}

	public Double last24hAverage(int indexOfMeasuredTemperatures) {
		Double sum = 0.0;
		Double temp = 0.0;
		int divider = 144;
		for (int i = indexOfMeasuredTemperatures - 1; i > indexOfMeasuredTemperatures - 145; i--) {
			temp = TEMPERATURES.get(i).getOutdoorTemp();
			if (temp != null) {
				sum += temp;
			} else {
				divider--;
			}
		}
		return sum / divider;
	}

	public void forecast(int indexOfMeasuredTemperatures, Double last24hAverage) {
		roomTemp1 = TEMPERATURES.get(indexOfMeasuredTemperatures - 1).getRoomTemp2();
		for (int i = indexOfMeasuredTemperatures; i < TEMPERATURES.size(); i++) {
			TEMPERATURES.get(i).setOutdoorTemp(last24hAverage);
			TEMPERATURES.get(i).setRoomTemp1(roomTemp1);
			if(i >= indexOfMeasuredTemperatures) {
				TEMPERATURES.get(i).setForecast(roomTemp1);
			}
			outdoorTemp = TEMPERATURES.get(i).getOutdoorTemp();
			if (outdoorTemp != null) {
				tau();
				TEMPERATURES.get(i).setRoomTemp2(roomTemp2);
				roomTemp1 = roomTemp2;
			}
		}
	}


}
