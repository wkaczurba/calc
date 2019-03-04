package com.kaczurba.calc.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.kaczurba.calc.service.DecimalCalculatorService;

/**
 * Simple Decimal calculator does not allow INF or NaN values.
 * Uses primitive double to skip Null-pointer checks and other issues. 
 * 
 * If INF or NaN is present as an argument it throws IllegalArgumentException
 * If INF or NaN is result of an opration - it throws ArithmeticArgumentException.
 * 
 * @author witol
 *
 */
@Component
public class SimpleDecimalCalculatorServiceImpl implements DecimalCalculatorService {
	
	/**
	 * Validates input parameters.
	 * 
	 * @param args
	 * @throws IllegalArgumentException when argument is NaN or Infinite.
	 */
	private void assertValidArguments(double... args) throws IllegalArgumentException {
		for (double arg : args) {
			Assert.isTrue(!Double.isNaN(arg), "Must not be NaN");
			Assert.isTrue(Double.isFinite(arg), "Must be finite");
		}
	}
	
	/**
	 * Validates result. Throws {@link ArithemticException} when {@code res} is NaN or Infinite
	 * 
	 * @param res
	 * @return
	 * @throws {@link ArithmeticException}, {@link IllegalArgumentException}
	 */
	private double validateResult(double res) throws ArithmeticException {
		if (Double.isInfinite(res)) {
			throw new ArithmeticException("Result is infinite");
		}
		if (Double.isNaN(res)) {
			throw new ArithmeticException("Result is not a number");
		}
		return res;
	}

	@Override
	public double add(double a, double b) throws IllegalArgumentException, ArithmeticException { 
		assertValidArguments(a, b);
		return validateResult(a + b);
	}

	@Override
	public double subtract(double a, double b) throws IllegalArgumentException, ArithmeticException {
		assertValidArguments(a, b);
		return validateResult(a - b);
	}

	@Override
	public double multiply(double a, double b) throws IllegalArgumentException, ArithmeticException {
		assertValidArguments(a, b);
		return validateResult(a * b);
	}

	@Override
	public double divide(double a, double b) throws IllegalArgumentException, ArithmeticException {
		assertValidArguments(a, b);
		return validateResult(a / b);
	}

}
