package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

import com.spms.entity.sec.SystemUser;

@Entity
@PrimaryKeyJoinColumn(name="ID")  
@NamedQueries({ @NamedQuery(name = "Supervisor.findAll", query = "SELECT model FROM Supervisor model")})
public class Supervisor extends SystemUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	id (must be unique), name, surname, department, email address (must be validated), telephone number.
	
	private String department;
	
	@Column(name = "telephone_number")
	private String telephoneNumber;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
}
