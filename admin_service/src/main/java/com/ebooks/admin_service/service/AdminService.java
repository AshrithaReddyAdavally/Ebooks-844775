package com.ebooks.admin_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ebooks.admin_service.pojo.AdminServicePojo;

public interface AdminService {

	ResponseEntity<AdminServicePojo> validateAdmin(AdminServicePojo adminLoginPojo);

	AdminServicePojo getAdmin(Integer adminId);

	List<AdminServicePojo> getAllAdmins();
}
