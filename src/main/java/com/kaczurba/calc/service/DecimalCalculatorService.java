package com.kaczurba.calc.service;

/**
 * Simple calculator service
 * 
 * @author witol
 *
 */
public interface DecimalCalculatorService {
	
	/**
	 * Performs add operation on two doubles. 
	 * @param a
	 * @param b
	 * @return
	 * @throws IllegalArgumentException when a or b is infinite or NaN
	 * @throws ArithmeticException when a or b is infinite or NaN
	 */
	public double add(double a, double b) throws IllegalArgumentException, ArithmeticException;
	
	/**
	 * Performs add operation on two doubles. 
	 * @param a
	 * @param b
	 * @return
	 * @throws IllegalArgumentException when a or b is infinite or NaN
	 * @throws ArithmeticException when a or b is infinite or NaN
	 */
	public double subtract(double a, double b)  throws IllegalArgumentException, ArithmeticException;

	/**
	 * Performs add operation on two doubles. 
	 * @param a
	 * @param b
	 * @return
	 * @throws IllegalArgumentException when a or b is infinite or NaN
	 * @throws ArithmeticException when a or b is infinite or NaN
	 */
	public double multiply(double a, double b) throws IllegalArgumentException, ArithmeticException;
	
	/**
	 * Performs divide operation on two doubles. 
	 * @param a
	 * @param b
	 * @return
	 * @throws IllegalArgumentException when a or b is infinite or NaN
	 * @throws ArithmeticException when a or b is infinite or NaN
	 */	
	public double divide(double a, double b) throws IllegalArgumentException, ArithmeticException;
}
