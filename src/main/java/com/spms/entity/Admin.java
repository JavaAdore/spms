package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

import com.spms.entity.sec.SystemUser;

@Entity
@NamedQueries({ @NamedQuery(name = "Admin.findAll", query = "SELECT model FROM Admin model"),
	@NamedQuery(name = "Admin.findByUsername", query = "SELECT model FROM Admin model WHERE model.username = :username")})
@PrimaryKeyJoinColumn(name="id")
public class Admin extends SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Admin() {
		super();
	}

	public Admin(String name, String surname, String email, String username, String password) {
		super(name, surname, email, username, password);
	}
	
}
