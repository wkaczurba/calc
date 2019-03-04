package com.kaczurba.calc.service.impl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.kaczurba.calc.service.DecimalCalculatorService;

/**
 * This test class is only to demo lowest-level JUnit test.
 * Tests do OVERLAP with {@link SimpleDecimalCalculatorServiceImplTest}.
 * 
 * @author witol
 *
 */
@RunWith(JUnit4.class)
public class SimpleDecimalCalculatorServiceImplTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private DecimalCalculatorService dcs;
	
	@Before
	public void setup() {
		dcs = new SimpleDecimalCalculatorServiceImpl();
	}
	
	@Test
	public void add() {
		assertEquals(5.0, dcs.add(2, 3), 0.0);
	}

	@Test
	public void subtract() {
		assertEquals(5.0, dcs.add(2, 3), 0.0);
	}

	@Test
	public void multiply() {
		assertEquals(5.0, dcs.add(2, 3), 0.0);
	}
	
	@Test
	public void divide() {
		assertEquals(5.0, dcs.add(2, 3), 0.0);
	}
	
	@Test
	public void addNaN() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(containsString("Must not be NaN"));
		dcs.add(Double.NaN, 0);
	}
	
	@Test
	public void addInf() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(containsString("Must be finite"));
		dcs.add(Double.POSITIVE_INFINITY, 0);
	}	
	
	@Test
	public void divZeroNaN() {
		exception.expect(ArithmeticException.class);
		exception.expectMessage(containsString("Result is not a number"));
		dcs.divide(0, 0);
	}	
	
	@Test
	public void divZeroInf() {
		exception.expect(ArithmeticException.class);
		exception.expectMessage(containsString("Result is infinite"));
		dcs.divide(1, 0);
	}		
}
