package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Project.findAll", query = "SELECT model FROM Project model"),
	@NamedQuery(name = "Project.findBySupervisorId", query = "SELECT model FROM Project model WHERE model.supervisor.supervisorId = :supervisorId") })
@Inheritance(strategy = InheritanceType.JOINED)
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	// Project: title, description, required skills (e.g. Java, J2EE etc.),
	// Status (e.g. Accepted, Proposed, Available).

	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private String description;
	private String requiredSkills;
	
	@ManyToOne
	private Supervisor supervisor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	
	public Supervisor getSupervisor() {
		return supervisor;
	}
	
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

}
