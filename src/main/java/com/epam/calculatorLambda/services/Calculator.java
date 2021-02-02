package com.epam.calculatorLambda.services;

import java.util.function.BinaryOperator;

@FunctionalInterface
public interface Calculator {
	public Double calculate(Double num1, Double num2, BinaryOperator<Double> operator);
}
