package eu.barjak.thermaltimeconstant.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import eu.barjak.thermaltimeconstant.entity.GlobalVariables;

public class WeatherQuery implements GlobalVariables {
	
	int id = 590; // MartonOMSZ 590, MartonBambi 444, LagymanyosOMSZ 615
	int indexOfTEMPERATURES = 0;

	public int steps() throws IOException, ParseException {
		LocalDate today = LocalDate.now();
		for (LocalDate localDate : LOCALDATES) {
			if (!localDate.isAfter(today)) {
				query(localDate);
			}
		}
		if (indexOfTEMPERATURES % 144 == 0) {
			System.out.println(today + ": nincs még mai adat");
		}
		return indexOfTEMPERATURES;
	}

	public void query(LocalDate actualDate) throws IOException, ParseException {
		URL url = new URL(
				"https://www.metnet.hu/online-allomasok?sub=showosdata&ostid=" + id + "&date=" + actualDate.toString());
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.contains("data: [")) {
				System.out.println(actualDate + ": adatok feldolgozása...");
				processing(inputLine, actualDate);
				break;
			}
		}
	}

	public void processing(String inputLine, LocalDate actualDate) {
		String outdoorTemperatureString;
		ArrayList<String> outdoorTemperatureList;
		Double outdoorTemperature;
		Pattern pattern = Pattern.compile("(?<=\\[).+(?=\\])");// a grafikon hőmérsékletértékei
		Matcher matcher = pattern.matcher(inputLine);
		matcher.find();
		outdoorTemperatureString = matcher.group();
		outdoorTemperatureList = new ArrayList<String>(Arrays.asList(outdoorTemperatureString.split(",")));
		for (int i = 0; i < outdoorTemperatureList.size(); i++) {// 144
			if (!outdoorTemperatureList.get(i).equals("null")) {
				outdoorTemperature = Double.parseDouble(outdoorTemperatureList.get(i));
				TEMPERATURES_MAP.get(actualDate).get(i).setOutdoorTemp(outdoorTemperature);
				indexOfTEMPERATURES++;
			}
		}
	}


}
