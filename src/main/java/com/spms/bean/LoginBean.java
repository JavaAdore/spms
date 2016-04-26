package com.spms.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spms.entity.Admin;
import com.spms.entity.sec.SystemUser;
import com.spms.service.SystemUserService;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */

	@EJB
	SystemUserService systemUserService;

	private static final long serialVersionUID = 1L;

	private String userName;

	private String password;

	public void login() throws IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.login(userName, password);

			boolean admin = request.isUserInRole("admin");

			boolean supervisor = request.isUserInRole("supervisor");

			boolean student = request.isUserInRole("student");

			SystemUser systemUser = null;
			if (admin) {

				systemUser = systemUserService
						.findAdministratorByUserName(userName);

				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("admin/index.xhtml");
			} else if (supervisor) {
				systemUser = systemUserService
						.findSupervisorByUserName(userName);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("supervisor/index.xhtml");

			} else if (student) {
				systemUser = systemUserService.findStudentByUserName(userName);

				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("student/index.xhtml");

			}
			
			HttpSession httpSession = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);
			httpSession.setAttribute("currentUser", systemUser);

			System.out.println("worked fine ");
		} catch (ServletException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("invalid username or password"));
			e.printStackTrace();
		}

	}

	public void logout() throws ServletException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		request.logout();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
