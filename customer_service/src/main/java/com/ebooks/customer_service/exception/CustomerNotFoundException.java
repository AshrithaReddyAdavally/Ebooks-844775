package com.ebooks.customer_service.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String customerName) {
		super("could not find customer" + customerName);
	}
}