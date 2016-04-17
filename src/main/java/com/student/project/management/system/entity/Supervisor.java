package com.student.project.management.system.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.student.project.management.system.entity.sec.SystemUser;

@Entity
@PrimaryKeyJoinColumn(name="ID")  
public class Supervisor extends SystemUser implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	
	
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
