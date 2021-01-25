package com.epam.calculatorLambda.utils;

public enum Operations {

	ADD("Addition"), SUB("Subtraction"), DIV("Divide"), MULT("Multuply");

	private final String operation;

	private Operations(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

}
