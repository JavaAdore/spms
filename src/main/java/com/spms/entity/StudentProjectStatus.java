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
			3, "Rejected By Supervisor");

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentProjectStatus other = (StudentProjectStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
