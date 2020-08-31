package com.ebooks.admin_service.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminServicePojo {

	private int adminId;
	private String adminName;
	private String adminPassword;
}
