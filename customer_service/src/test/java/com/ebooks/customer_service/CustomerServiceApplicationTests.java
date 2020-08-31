package com.ebooks.customer_service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebooks.customer_service.dao.CustomerServiceDao;
import com.ebooks.customer_service.entity.CustomerServiceEntity;
import com.ebooks.customer_service.pojo.CustomerOutput;
import com.ebooks.customer_service.pojo.CustomerServicePojo;
import com.ebooks.customer_service.service.CustomerService;
import com.ebooks.customer_service.service.CustomerServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceApplicationTests {

	@Autowired
	private CustomerServiceImpl customerService;
	
	@MockBean
	private CustomerServiceDao customerServiceDao;

	@Test
	public void testValidate() throws Exception{
		CustomerServiceEntity customerServiceEntity = new CustomerServiceEntity(1,"alley","alley");
		CustomerServicePojo customerServicePojo = new CustomerServicePojo(1,"alley","alley");
		when(customerServiceDao.findByCustomerNameAndCustomerPassword("alley","alley")).thenReturn(customerServiceEntity);
		ResponseEntity<CustomerOutput> result = customerService.validateCustomer(customerServicePojo);
		assertEquals(200,result.getStatusCodeValue());
		assertEquals(true,result.hasBody());
}

}
