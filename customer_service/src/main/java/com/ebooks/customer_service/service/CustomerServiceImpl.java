package com.ebooks.customer_service.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ebooks.customer_service.dao.CustomerServiceDao;
import com.ebooks.customer_service.entity.CustomerServiceEntity;
import com.ebooks.customer_service.exception.CustomerNotFoundException;
import com.ebooks.customer_service.pojo.BookPojo;
import com.ebooks.customer_service.pojo.CustomerOutput;
import com.ebooks.customer_service.pojo.CustomerServicePojo;

@Service
public class CustomerServiceImpl implements CustomerService {

	static Logger LOG = Logger.getLogger(CustomerServiceImpl.class.getClass());

	@Autowired
	CustomerServiceDao customerServiceDao;

	public ResponseEntity<CustomerOutput> validateCustomer(CustomerServicePojo customerServicePojo) {
		// TODO Auto-generated method stub
		LOG.info("Enterd validateCustomer()");

		CustomerServiceEntity customerServiceEntity = customerServiceDao.findByCustomerNameAndCustomerPassword(
				customerServicePojo.getCustomerName(), customerServicePojo.getCustomerPassword());
		if (customerServiceEntity != null) {
			RestTemplate restTemplate = new RestTemplate();

			BookPojo[] bookPojo = restTemplate.getForObject("http://localhost:9085/ebooks/book/all", BookPojo[].class);

			CustomerOutput resultpojo = new CustomerOutput(customerServiceEntity.getCustomerId(),
					customerServiceEntity.getCustomerName(), null, bookPojo);

			ResponseEntity<CustomerOutput> result = new ResponseEntity<CustomerOutput>(resultpojo, HttpStatus.OK);
			LOG.info("Exited validateCustomer()");
			return result;
		} else {
			throw new CustomerNotFoundException(customerServicePojo.getCustomerName());
		}
	}
}
