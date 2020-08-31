package com.ebooks.customer_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebooks.customer_service.entity.CustomerServiceEntity;

@Repository
public interface CustomerServiceDao extends JpaRepository<CustomerServiceEntity, Integer> {

	CustomerServiceEntity findByCustomerNameAndCustomerPassword(String customerName, String customerPassword);
}
