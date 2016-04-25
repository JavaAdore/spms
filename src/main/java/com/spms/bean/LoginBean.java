package com.spms.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */

	

	private static final long serialVersionUID = 1L;

	private String userName = "mahmoud";

	private String password = null;      

	public void login() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.login(userName, password);
			boolean obj = request.isUserInRole("admin");
			System.out.println("worked fine ");
		} catch (ServletException e) {
			e.printStackTrace(); 
		}  

	}
	
	public void logout() throws ServletException
	{
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

	public static void main(String[] args) {

	}

	public String hashPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.update("123456".getBytes("UTF-8"));
		byte[] digest = md.digest();
		BigInteger bigInt = new BigInteger(1, digest);

		String hashedPassword = bigInt.toString(16);
		return hashedPassword;
	}

}
