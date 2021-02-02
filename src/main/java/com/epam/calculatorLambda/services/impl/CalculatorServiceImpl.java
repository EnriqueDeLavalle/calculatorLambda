package com.epam.calculatorLambda.services.impl;

import com.epam.calculatorLambda.services.CalculatorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    public static <T> T calculate(T op1, T op2, BinaryOperator<T> operator) {
        return operator.apply(op1, op2);
    }

    @Override
    public Double calculate(Double number1, Double number2, String operation) {

        // Evaluating operation
        return switch (operation) {
            case "+" -> calculate(number1, number2, (x, y) -> x + y);
            case "-" -> calculate(number1, number2, (x, y) -> x - y);
            case "*" -> calculate(number1, number2, (x, y) -> x * y);
            case "/" -> calculate(number1, number2, (x, y) -> x / y);
            default -> 0.0;
        };
    }

    @Override
    public List<Double> calculateFromFile(MultipartFile file, String operation) throws Exception {

        InputStream inputStream = file.getInputStream();

        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .parallel()
                .map(this::getDoubles)
                .map(doubles -> calculate(doubles[0],doubles[1],operation))
                .collect(Collectors.toList());

    }

    private Double[] getDoubles(String pair) {
        return Arrays.stream(pair.split(";")).map(Double::parseDouble).toArray(Double[]::new);
    }

}
