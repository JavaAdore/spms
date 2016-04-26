package com.spms.dao;

import javax.ejb.Local;

import com.spms.entity.Admin;
import com.spms.entity.Student;
import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUserGroup;

@Local
public interface SystemUserDAO {

	Admin findAdministratorByUserName(String userName);

	Supervisor findSupervisorByUserName(String userName);

	Student findStudentByUserName(String userName);

	void saveAdmin(Admin admin);

	void saveSystemUserGroup(SystemUserGroup systemUserGroup);

}
