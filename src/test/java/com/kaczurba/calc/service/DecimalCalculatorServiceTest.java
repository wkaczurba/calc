package com.kaczurba.calc.service;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kaczurba.calc.service.impl.SimpleDecimalCalculatorServiceImplTest;

/**
 * Tests autowired component-scanned DocumentService
 * 
 * NOTE: This test overlaps with {@link SimpleDecimalCalculatorServiceImplTest}
 * for purpose of showing Bringing up GeneralApplicationContext.
 * 
 * @author witol
 *
 */
@RunWith(SpringRunner.class)
public class DecimalCalculatorServiceTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Autowired
	DecimalCalculatorService dcs;
	
	@ComponentScan
	@Configuration
	public static class Config {
		// Any additional config here.
	}
	
	@Test
	public void smokeTest() {
		assertNotNull("DecimalCalculatorService not found", dcs);
		assertEquals("com.kaczurba.calc.service.impl.SimpleDecimalCalculatorServiceImpl", 
				dcs.getClass().getName());
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
