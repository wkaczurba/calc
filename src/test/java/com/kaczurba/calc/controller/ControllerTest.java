package com.kaczurba.calc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.kaczurba.calc.service.DecimalCalculatorService;

/**
 * Web-layer test only. Controller + ControllerAdvice + Service mock.
 * Demoes ArgumentCaptor, etc.
 * 
 * @author witol
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DecimalCalcController.class)
public class ControllerTest {
	
	@MockBean
	private DecimalCalculatorService dcs;
	
	@Autowired
	private MockMvc mvc;
	
	@Captor
	ArgumentCaptor<Double> a;
	
	@Captor
	ArgumentCaptor<Double> b;

	/**
	 * This demo is shows use of ArgumentCaptor.
	 * Shows also passing of "NaN"/"Infinity" into service.
	 *  
	 * @throws Exception
	 */
	@Test
	public void addNaN() throws Exception {
		doThrow(new IllegalArgumentException("exc")).when(dcs).add(anyDouble(), anyDouble());
		
		this.mvc.perform(get("/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "NaN")
				.param("b", "5.0"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"status\":\"INVALID_ARGUMENT\",\"message\":\"exc\"}"));
		
		verify(dcs, times(1)).add(a.capture(), b.capture());
		assertEquals(Double.NaN, (double) a.getValue(), 0);
		assertEquals(5, (double) b.getValue(), 0);
	}
	
	/**
	 * This demo is shows use of ArgumentCaptor.
	 * Shows also passing of "NaN"/"Infinity" into service.
	 *  
	 * @throws Exception
	 */
	@Test
	public void addInf() throws Exception {
		doThrow(new IllegalArgumentException("exc")).when(dcs).add(anyDouble(), anyDouble());
		
		this.mvc.perform(get("/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "Infinity")
				.param("b", "5.0"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"status\":\"INVALID_ARGUMENT\",\"message\":\"exc\"}"));
		
		verify(dcs, times(1)).add(a.capture(), b.capture());
		assertEquals(Double.POSITIVE_INFINITY, (double) a.getValue(), 0);
		assertEquals(5, (double) b.getValue(), 0);
	}
	
	/**
	 * This demo is shows use of ArgumentCaptor.
	 * Shows also passing of "NaN"/"Infinity" into service.
	 *  
	 * @throws Exception
	 */
	@Test
	public void divNaN() throws Exception {
		doThrow(new ArithmeticException("ar")).when(dcs).divide(anyDouble(), anyDouble());
		
		this.mvc.perform(get("/divide")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "0")
				.param("b", "0"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"status\":\"INVALID_RESULT\",\"message\":\"ar\"}"));
	}	
	
	@Test
	public void add() throws Exception {
		doReturn(3.0).when(dcs).add(1.0, 2.0);
		
		this.mvc.perform(get("/add")
				.param("a", "1.0")
				.param("b", "2.0"))
			.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"result\":3.0}"));
	}

	@Test
	public void subtract() throws Exception {
		doReturn(-1.0).when(dcs).subtract(1.0, 2.0);
		this.mvc.perform(get("/subtract")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "1.0")
				.param("b", "2.0"))
			.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"result\":-1.0}"));
	}	
	
	@Test
	public void multiply() throws Exception {
		doReturn(12.0).when(dcs).multiply(3.0, 4.0);
		this.mvc.perform(get("/multiply")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "3.0")
				.param("b", "4.0"))
			.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"result\":12.0}"));
	}	
	
	@Test
	public void divide() throws Exception {
		doReturn(0.5).when(dcs).divide(3.0, 6.0);
		this.mvc.perform(get("/divide")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "3.0")
				.param("b", "6.0"))
			.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"result\":0.5}"));
	}		
}
