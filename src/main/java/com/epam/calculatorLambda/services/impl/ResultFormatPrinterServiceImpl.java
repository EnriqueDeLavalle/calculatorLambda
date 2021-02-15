package com.epam.calculatorLambda.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.epam.calculatorLambda.services.ResultFormatPrinterService;
import com.epam.calculatorLambda.utils.CalculatorUtil;

@Service
public class ResultFormatPrinterServiceImpl implements ResultFormatPrinterService {

	@Override
	public String toFile(List<Double> result) throws IOException {
		Files.writeString(Path.of(CalculatorUtil.FILE_NAME),toScreen(result));
		return "The result was written to the file processed!";
	}

	@Override
	public String toScreen(List<Double> result) {
		return result.stream().map(String::valueOf).collect(Collectors.joining("\n"));
	}

}
