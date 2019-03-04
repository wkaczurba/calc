package com.kaczurba.calc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration test with full context + MVC-Mock
 * Only two.
 * 
 * @author witol
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CalcApplicationTests {

    @Autowired
    private MockMvc mvc;	

	@Test
	public void multiplyTest() throws Exception {
		this.mvc.perform(get("/multiply")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "3.0")
				.param("b", "4.0"))
			.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"result\":12.0}"));
	}
	
	@Test
	public void divideNaNTest() throws Exception {
		this.mvc.perform(get("/divide")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("a", "0")
				.param("b", "0"))
			.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"status\":\"INVALID_RESULT\",\"message\":\"Result is not a number\"}"));			
			
	}	

}
