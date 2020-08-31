package com.ebooks.customer_service.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebooks.customer_service.pojo.CustomerOutput;
import com.ebooks.customer_service.pojo.CustomerServicePojo;
import com.ebooks.customer_service.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("login")
public class CustomerServiceController {

	@Autowired
	CustomerService customerService;
	static Logger LOG = Logger.getLogger(CustomerServiceController.class.getClass());

	// End point to validate customer

	@GetMapping("customer")
	public ResponseEntity<CustomerOutput> validateCustomer(@RequestHeader("Authorization") String data) {
		BasicConfigurator.configure();
		LOG.info("Entered the end point \'validate\'");
		LOG.info("Exited end point \'validate\'");
		String token[] = data.split(":");
		CustomerServicePojo customerServicePojo = new CustomerServicePojo();
		customerServicePojo.setCustomerName(token[0]);
		customerServicePojo.setCustomerPassword(token[1]);

		return this.customerService.validateCustomer(customerServicePojo);
	}
}
