package com.spms.service;

import javax.ejb.Local;

import com.spms.entity.Admin;
import com.spms.entity.Student;
import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUser;

@Local
public interface SystemUserService {

	SystemUser findByUsername(String username);

	Admin findAdministratorByUserName(String userName);
	
	Supervisor findSupervisorByUserName(String userName);

	Student findStudentByUserName(String userName);

	void insertDefaultAdministrator();

	
}
