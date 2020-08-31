package com.ebooks.admin_service.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebooks.admin_service.pojo.AdminServicePojo;
import com.ebooks.admin_service.service.AdminServiceImpl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin
@RestController
@RequestMapping("ebooks")
public class AdminServiceController {

	static Logger LOG = Logger.getLogger(AdminServiceController.class.getClass());

	@Autowired
	AdminServiceImpl adminService;

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		HashMap<String, String> map = new HashMap<>();
		String user = getUser(authHeader);
		String token = generateJwt(user);
		map.put("token", token);
		return map;
	}

	private String getUser(String authHeader) {
		String encodedCredentials = authHeader.substring(6);
		String up = new String(Base64.getDecoder().decode(encodedCredentials));
		String user = up.split(":")[0];
		return user;
	}

	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		return token;
	}

	// End point to validate admin

	@GetMapping("admin")
	public ResponseEntity<AdminServicePojo> validateAdmin(@RequestHeader("Authorization") String data) {
		BasicConfigurator.configure();
		LOG.info("Entered the end point \'validate\'");
		LOG.info("Exited end point \'validate\'");
		String token[] = data.split(":");
		AdminServicePojo adminServicePojo = new AdminServicePojo();
		adminServicePojo.setAdminName(token[0]);
		adminServicePojo.setAdminPassword(token[1]);

		return this.adminService.validateAdmin(adminServicePojo);
	}

	@GetMapping("/admin/{adminId}")
		AdminServicePojo getAdmin(@PathVariable("adminId") Integer adminId) {
		// LOG.info("Requested Received to getAdmin");
		LOG.info("entered the end point\'/admin\'");
		LOG.info("exited the end point\'/admin\'");
		return adminService.getAdmin(adminId);
	}

	@GetMapping("/admin/all")

	List<AdminServicePojo> getAllAdmins() {
		LOG.info("entered the end point\'/admin/all'");
		LOG.info("exited the end point\'/admin/all'");
		return adminService.getAllAdmins();
	}
}
