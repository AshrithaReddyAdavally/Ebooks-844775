package com.ebooks.customer_service.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ebooks.customer_service.exception.CustomerNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@SuppressWarnings("unchecked")
	@ResponseBody

	// Handling exception if customer not found
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Map customerNotFoundhandler(CustomerNotFoundException ex) {
		Map map = new HashMap();
		map.put("errorCode", HttpStatus.NOT_FOUND);
		map.put("errorMessage", ex.getMessage());
		map.put("errorDate", new Date());
		return map;
	}
}
