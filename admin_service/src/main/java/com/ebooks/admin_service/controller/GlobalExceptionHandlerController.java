package com.ebooks.admin_service.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ebooks.admin_service.exception.AdminNotFoundException;
import com.ebooks.admin_service.exception.UnauthenticatedException;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@SuppressWarnings("unchecked")
	@ResponseBody

	// Handling exception if admin not found
	@ExceptionHandler(AdminNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Map AdminNotFoundhandler(AdminNotFoundException ex) {
		Map map = new HashMap();
		map.put("errorCode", HttpStatus.NOT_FOUND);
		map.put("errorMessage", ex.getMessage());
		map.put("errorDate", new Date());
		return map;
	}
}
