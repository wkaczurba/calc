package com.kaczurba.calc.bean;

import lombok.Value;

@Value
public class ErrorBean {

	private ErrorStatus status; 
	private String message;
	
	public enum ErrorStatus {
		INVALID_ARGUMENT,
		INVALID_RESULT
	}
}
