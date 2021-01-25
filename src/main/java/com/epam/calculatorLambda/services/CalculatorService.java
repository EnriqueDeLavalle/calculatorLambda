package com.epam.calculatorLambda.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CalculatorService {

	Double calculate(Double number1, Double number2, String operation);

	List<Double> calculateFromFile(MultipartFile file, String operation) throws IOException;

}
