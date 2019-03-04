package com.kaczurba.calc.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kaczurba.calc.bean.ErrorBean;
import com.kaczurba.calc.bean.ErrorBean.ErrorStatus;

@ControllerAdvice
public class RestControllerHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler
	public ErrorBean handleIllegalArgument(IllegalArgumentException ex) {
		return new ErrorBean(ErrorStatus.INVALID_ARGUMENT, ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler
	public ErrorBean handleArithmeticException(ArithmeticException ex) {
		return new ErrorBean(ErrorStatus.INVALID_RESULT, ex.getMessage());
	}
}
