package com.spms.service;

import com.spms.entity.sec.SystemUser;

public interface SystemUserService {

	SystemUser findByUsername(String username);

}
