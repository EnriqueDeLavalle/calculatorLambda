package com.epam.calculatorLambda.services;

import java.io.IOException;
import java.util.List;

public interface ResultFormatPrinterService {
	
	String toFile(List<Double> results)throws IOException;
	String toScreen(List<Double> results);
	

}
