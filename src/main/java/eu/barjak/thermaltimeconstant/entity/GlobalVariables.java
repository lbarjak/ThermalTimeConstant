package eu.barjak.thermaltimeconstant.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface GlobalVariables {

	ArrayList<LocalDate> LOCALDATES = new ArrayList<>();
	LinkedHashMap<LocalDate, ArrayList<Temperature>> TEMPERATURES_MAP = new LinkedHashMap<>();
	ArrayList<Temperature> TEMPERATURES = new ArrayList<>();
	ArrayList<Double> ROOMTEMP1 = new ArrayList<>();
	ArrayList<Double> OUTDOORTEMP = new ArrayList<>();

}
