package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_project")
public class StudentProject extends Project implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Student student;

	
	@ManyToOne
	private Supervisor suggestedSupervisor;
	
	@ManyToOne
	private StudentProjectStatus status;
	
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Supervisor getSuggestedSupervisor() {
		return suggestedSupervisor;
	}

	public void setSuggestedSupervisor(Supervisor suggestedSupervisor) {
		this.suggestedSupervisor = suggestedSupervisor;
	}

	public StudentProjectStatus getStatus() {
		return status;
	}

	public void setStatus(StudentProjectStatus status) {
		this.status = status;
	}

}
