package com.student.project.management.system.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.student.project.management.system.entity.sec.SystemUser;

@Entity
@PrimaryKeyJoinColumn(name="ID")  
public class Student extends SystemUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String course;
	@Column(name = "telephone_number")
	private String telephoneNumber;
	
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	
	
	

	
	

}
