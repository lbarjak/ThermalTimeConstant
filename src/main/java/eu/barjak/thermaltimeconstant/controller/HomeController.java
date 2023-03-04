package eu.barjak.thermaltimeconstant.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eu.barjak.thermaltimeconstant.entity.GlobalVariables;
import eu.barjak.thermaltimeconstant.service.WeatherService;

@Controller
public class HomeController implements GlobalVariables {

	private WeatherService weatherService;

	@Autowired
	public void getWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	private void extracted(Model model) throws ParseException, IOException {
		model.addAttribute("indexOfMeasuredTemperatures", weatherService.weather());
		model.addAttribute("roomTemp1", ROOMTEMP1);
		model.addAttribute("outdoorTemp", OUTDOORTEMP);
		model.addAttribute("startDate", WeatherService.startDateString);
		model.addAttribute("startTime", WeatherService.startTimeString);
	}
	
	@GetMapping("/")
	public String home4(Model model) throws ParseException, IOException {
		extracted(model);
		return "index";
	}

}
