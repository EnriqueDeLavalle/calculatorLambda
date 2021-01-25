package com.epam.calculatorLambda.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.epam.calculatorLambda.services.CalculatorService;
import com.epam.calculatorLambda.services.ResultFormatPrinterService;
import com.epam.calculatorLambda.utils.CalculatorUtil;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

	@Autowired
	private CalculatorService calculator;

	@Autowired
	private ResultFormatPrinterService resultFormatPrinter;

	@GetMapping
	public ResponseEntity<String> calculate(@RequestParam("number1") Double number1,
			@RequestParam("number2") Double number2, @RequestParam("operation") String operation,
			@RequestParam("resultFormat") String resultFormat) {

		// Validating parameters
		if (!CalculatorUtil.EvaluateOperation.test(operation)) {
			return new ResponseEntity<>("Invalid operation!", HttpStatus.BAD_REQUEST);
		}
		if (!CalculatorUtil.EvaluateResultFormats.test(resultFormat)) {
			return new ResponseEntity<>("Invalid result format!", HttpStatus.BAD_REQUEST);
		}

		// Processing request
		Double result = calculator.calculate(number1, number2, operation);

		// Evaluating and sending the result in the format requested
		if (CalculatorUtil.SCREEN.equals(resultFormat)) {
			return new ResponseEntity<>(resultFormatPrinter.toScreen(Arrays.asList(result)), HttpStatus.OK);
		} else {

			try {
				return new ResponseEntity<>(resultFormatPrinter.toFile(Arrays.asList(result)), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>("The result couldn't be written to the file processed!",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

	}

	@PostMapping
	public ResponseEntity<String> calculateFromFile(@RequestPart("file") MultipartFile file,
			@RequestPart("operation") String operation, @RequestParam("resultFormat") String resultFormat) {

		// Validating parameters
		if (null == file.getOriginalFilename()) {
			return new ResponseEntity<>("Invalid file!", HttpStatus.BAD_REQUEST);
		}
		if (!CalculatorUtil.EvaluateOperation.test(operation)) {
			return new ResponseEntity<>("Invalid operation!", HttpStatus.BAD_REQUEST);
		}
		if (!CalculatorUtil.EvaluateResultFormats.test(resultFormat)) {
			return new ResponseEntity<>("Invalid result format!", HttpStatus.BAD_REQUEST);
		}

		List<Double> results = new ArrayList<Double>();
		try {
			results = calculator.calculateFromFile(file, operation);
		} catch (IOException e1) {
			return new ResponseEntity<>("The result couldn't be processed!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Evaluating and sending the result in the format requested
		if (CalculatorUtil.SCREEN.equals(resultFormat)) {
			return new ResponseEntity<>(resultFormatPrinter.toScreen(results), HttpStatus.OK);
		} else {
			try {
				return new ResponseEntity<>(resultFormatPrinter.toFile(results), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>("The result couldn't be written to the file processed!",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

	}

}
