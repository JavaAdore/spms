package com.spms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudentProjectStatus {

	public final static StudentProjectStatus SUGGESTED_BY_STUDENT = new StudentProjectStatus(
			1, "Suggested By Student");
	public final static StudentProjectStatus ACCEPTED_BY_SUPERVISOR = new StudentProjectStatus(
			2, "Approved By Supervisor");
	public final static StudentProjectStatus REJECTED_BY_SUPERVISOR = new StudentProjectStatus(
			3, "Regiected By Supervisor");

	public StudentProjectStatus() {

	}

	public StudentProjectStatus(Integer id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentProjectStatus(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
