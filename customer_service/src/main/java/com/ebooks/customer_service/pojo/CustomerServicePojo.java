package com.ebooks.customer_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerServicePojo {

	private int customerId;
	private String customerName;
	private String customerPassword;
}
