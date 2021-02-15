package com.epam.calculatorLambda.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.epam.calculatorLambda.services.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    public static <T> T calculate(T op1, T op2, BinaryOperator<T> operator) {
        return operator.apply(op1, op2);
    }

    @Override
    public Double calculate(Double number1, Double number2, String operation) {

        return switch (operation) {
            case "add" -> calculate(number1, number2, (x, y) -> x + y);
            case "sub" -> calculate(number1, number2, (x, y) -> x - y);
            case "mult" -> calculate(number1, number2, (x, y) -> x * y);
            case "div" -> calculate(number1, number2, (x, y) -> x / y);
            default -> 0.0;
        };
    }

    @Override
    public List<Double> calculateFromFile(MultipartFile file, String operation) throws Exception {

        return new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .parallel()
                .map(this::getDoubles)
                .map(doubles -> calculate(doubles[0],doubles[1],operation))
                .collect(Collectors.toList());

    }

    private Double[] getDoubles(String pair) {
        return Arrays.stream(pair.split(";"))
        		.map(Double::parseDouble)
        		.toArray(Double[]::new);
    }

}
