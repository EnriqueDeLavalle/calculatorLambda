package com.epam.calculatorLambda.utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CalculatorUtil {

	public static final String SCREEN = "SCREEN";
	public static final String FILE_NAME = "Result.txt";

	public static final List<String> operations = Arrays.asList("+", "-", "*", "/");
	public static final List<String> resultFormats = Arrays.asList("SCREEN", "FILE");

	public static Predicate<String> EvaluateOperation = str -> operations.contains(str);
	public static Predicate<String> EvaluateResultFormats = str -> resultFormats.contains(str);

}
