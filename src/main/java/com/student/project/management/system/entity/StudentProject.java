package com.student.project.management.system.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="student_project")
@PrimaryKeyJoinColumn(name="ID")
public class StudentProject extends Project implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	private Student student;


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	

}
