package com.ebooks.admin_service.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ebooks.admin_service.dao.AdminServiceDao;
import com.ebooks.admin_service.entity.AdminServiceEntity;
import com.ebooks.admin_service.exception.AdminNotFoundException;
import com.ebooks.admin_service.pojo.AdminServicePojo;

@Service
public class AdminServiceImpl implements AdminService {

	static Logger LOG = Logger.getLogger(AdminServiceImpl.class.getClass());

	@Autowired
	AdminServiceDao adminServiceDao;

	@Override
	public ResponseEntity<AdminServicePojo> validateAdmin(AdminServicePojo adminServicePojo) {
		LOG.info("Enterd validateAdmin()");

		AdminServiceEntity adminServiceEntity = adminServiceDao
				.findByAdminNameAndAdminPassword(adminServicePojo.getAdminName(), adminServicePojo.getAdminPassword());
		if (adminServiceEntity != null) {
			adminServicePojo = new AdminServicePojo(adminServiceEntity.getAdminId(), adminServiceEntity.getAdminName(),
					null);
			ResponseEntity<AdminServicePojo> result = new ResponseEntity<AdminServicePojo>(adminServicePojo,
					HttpStatus.OK);
			LOG.info("Exited validateAdmin()");
			BasicConfigurator.resetConfiguration();
			return result;
		} else {
			throw new AdminNotFoundException(adminServicePojo.getAdminName());
		}

	}

	@Override
	public AdminServicePojo getAdmin(Integer adminId) {
		// TODO Auto-generated method stub
		LOG.info("Enterd getAdmin()");

		AdminServicePojo adminServicePojo = null;
		Optional result = adminServiceDao.findById(adminId);
		if (result.isPresent()) {
			AdminServiceEntity adminServiceEntity = (AdminServiceEntity) result.get();
			adminServicePojo = new AdminServicePojo(adminServiceEntity.getAdminId(), adminServiceEntity.getAdminName(),
					null);
		}
		LOG.info("Exited getAdmin()");

		return adminServicePojo;
	}

	@Override
	public List<AdminServicePojo> getAllAdmins() {
		// TODO Auto-generated method stub
		LOG.info("Enterd getAllAdmin()");
		List<AdminServicePojo> allAdminServicePojo = new ArrayList();
		Iterable<AdminServiceEntity> allAdminServiceEntity = adminServiceDao.findAll();
		Iterator itr = allAdminServiceEntity.iterator();

		while (itr.hasNext()) {
			AdminServiceEntity adminServiceEntity = (AdminServiceEntity) itr.next();
			AdminServicePojo adminServicePojo = new AdminServicePojo(adminServiceEntity.getAdminId(),
					adminServiceEntity.getAdminName(), null);
			allAdminServicePojo.add(adminServicePojo);
		}
		LOG.info("Exited getAllAdmin()");

		return allAdminServicePojo;
	}
}
