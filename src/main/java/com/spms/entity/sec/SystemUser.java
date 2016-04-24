package com.spms.entity.sec;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "SystemUser.findAll", query = "SELECT model FROM SystemUser model"),
	@NamedQuery(name = "SystemUser.findByUsername", query = "SELECT model FROM SystemUser model WHERE model.username = :username")})
@Table(name = "SYSTEMUSER")
@Inheritance(strategy = InheritanceType.JOINED)
public class SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String surname;

	@Basic(optional = false)
	private String email;

	@Basic(optional = false)
	@Column(name = "USER_NAME")
	private String username;

	@Basic(optional = false)
	@Column(name = "USERPASSWORD")
	private String password;

	public SystemUser() {
	}

	// @ManyToOne
	// @JoinColumn(name = "GROUP_ID")
	// private SystemRole role;

	public SystemUser(String name, String surname, String email, String username, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public SystemRole getRole() {
	// return role;
	// }
	//
	// public void setRole(SystemRole role) {
	// this.role = role;
	// }

}
