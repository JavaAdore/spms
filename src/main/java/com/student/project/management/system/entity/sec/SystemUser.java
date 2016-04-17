package com.student.project.management.system.entity.sec;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEMUSER")
@Inheritance(strategy=InheritanceType.JOINED)  
public class SystemUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "USER_NAME")
	private String email;

	@Column(name = "USERPASSWORD")
	private String password;
	
	
	 
	

	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private SystemRole role;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SystemRole getRole() {
		return role;
	}

	public void setRole(SystemRole role) {
		this.role = role;
	}

	

	public SystemUser() {
	}

}