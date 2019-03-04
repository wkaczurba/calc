package com.kaczurba.calc.bean;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard result bean.
 * It can be extended to handle additional result information number system etc.
 * 
 * @author witol
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DecimalResultBean {
	
	private double result;
	
	// private Result res;
}
