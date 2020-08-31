package com.ebooks.customer_service.service;

import org.springframework.http.ResponseEntity;

import com.ebooks.customer_service.pojo.CustomerOutput;
import com.ebooks.customer_service.pojo.CustomerServicePojo;

public interface CustomerService {

	ResponseEntity<CustomerOutput> validateCustomer(CustomerServicePojo customerServicePojo);
}
