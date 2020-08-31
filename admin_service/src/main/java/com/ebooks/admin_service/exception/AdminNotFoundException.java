package com.ebooks.admin_service.exception;

public class AdminNotFoundException extends RuntimeException {

	public AdminNotFoundException(String adminName) {
		super("Could not find admin" + adminName);
		}
}
