package com.spms.service;

import javax.ejb.Local;

import com.spms.entity.sec.SystemUserGroup;

@Local
public interface SystemUserGroupService {
	
	SystemUserGroup create(String username, String groupName);
	
	void delete(String username, String groupName);

	SystemUserGroup find(String username, String groupName);
	
}
