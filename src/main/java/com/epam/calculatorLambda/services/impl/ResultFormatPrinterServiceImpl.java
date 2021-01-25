package com.epam.calculatorLambda.services.impl;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.epam.calculatorLambda.services.ResultFormatPrinterService;
import com.epam.calculatorLambda.utils.CalculatorUtil;

@Service
public class ResultFormatPrinterServiceImpl implements ResultFormatPrinterService {

	@Override
	public String toFile(List<Double> result) throws IOException {

		String toPrint = toScreen(result);
		FileOutputStream fos = new FileOutputStream(CalculatorUtil.FILE_NAME);
		DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
		outStream.writeBytes(toPrint);
		outStream.close();

		return "The result was written to the file processed!";
	}

	@Override
	public String toScreen(List<Double> result) {
		return result.stream().map(String::valueOf).collect(Collectors.joining("\n"));
	}

}
