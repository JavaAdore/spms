package com.spms.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spms.entity.sec.SystemUser;

@ManagedBean
@SessionScoped
public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String currentLogedUserName() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);

		SystemUser systemUser = (SystemUser) httpSession
				.getAttribute("currentUser");
		return systemUser.getSurname();
	}

	public void logout() throws ServletException, IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpServletRequest.logout();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("../login.xhtml");
	}

	public boolean isAdmin() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		return request.isUserInRole("admin");

	}

	public boolean isSupervisor() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		boolean supervisor = request.isUserInRole("supervisor");
		return supervisor;
	}

	public boolean isStudent() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		boolean student = request.isUserInRole("student");
		return student;
	}
}
