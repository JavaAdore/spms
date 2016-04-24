package com.spms.service;

import java.util.List;

import com.spms.entity.Admin;

public interface AdminService {

	Admin create(Admin admin);

	Admin find(Integer id);

	Admin update(Admin admin);

	void delete(Admin admin);

	List<Admin> getAllAdmins();
	
	Admin findByUsername(String username);

}
