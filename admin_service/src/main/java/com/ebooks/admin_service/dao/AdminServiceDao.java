package com.ebooks.admin_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebooks.admin_service.entity.AdminServiceEntity;


@Repository
public interface AdminServiceDao extends JpaRepository<AdminServiceEntity, Integer>{

	AdminServiceEntity findByAdminNameAndAdminPassword(String adminName, String adminPassword);
}
