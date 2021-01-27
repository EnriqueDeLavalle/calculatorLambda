package com.epam.calculatorLambda.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.epam.calculatorLambda.services.Calculator;
import com.epam.calculatorLambda.services.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public Double calculate(Double number1, Double number2, String operation) {

		// Evaluating operation
		switch (operation) {
		case "+" -> {
			Calculator calc = (num1, num2) -> {
				return num1 + num2;
			};
			return calc.calculate(number1, number2);
		}
		case "-" -> {
			Calculator calc = (num1, num2) -> {
				return num1 - num2;
			};
			return calc.calculate(number1, number2);
		}
		case "*" -> {
			Calculator calc = (num1, num2) -> {
				return num1 * num2;
			};
			return calc.calculate(number1, number2);
		}
		case "/" -> {
			Calculator calc = (num1, num2) -> {
				return num1 / num2;
			};
			return calc.calculate(number1, number2);
		}
		default -> {
			return 0.0;
		}
		}

	}

	@Override
	public List<Double> calculateFromFile(MultipartFile file, String operation) throws Exception {

		// Taking the input stream from the MultipartFile
		InputStream inputStream = file.getInputStream();

		// Taking the pair of numbers separated by semicolon from each line of the file
		// and applying the operation for each couple of numbers
		return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.toList()).parallelStream().map(pair -> {
					Double numbers[] = Stream.of(pair.split(";")).map(Double::parseDouble).toArray(Double[]::new);
					return calculate(numbers[0], numbers[1], operation);
				}).collect(Collectors.toList());

	}

}
