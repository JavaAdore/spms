package com.spms.servlet;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;

import com.spms.service.SystemUserService;

public class AdminstratorCreatorServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private SystemUserService systemUserService;
	@Override
	public void init()
	{
		systemUserService.insertDefaultAdministrator();
	}
	
}
