package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.spms.entity.sec.SystemUser;

@Entity
@NamedQueries({ @NamedQuery(name = "Supervisor.findAll", query = "SELECT model FROM Supervisor model"),
	@NamedQuery(name = "Supervisor.findBySupervisorId", query = "SELECT model FROM Supervisor model WHERE model.supervisorId = :supervisorId"),
	@NamedQuery(name = "Supervisor.findByUsername", query = "SELECT model FROM Supervisor model WHERE model.username = :username") })
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "supervisorId"}) })
public class Supervisor extends SystemUser implements Serializable {
	private static final long serialVersionUID = 1L;

	// id (must be unique), name, surname, department, email address (must be
	// validated), telephone number.

	@Basic(optional = false)
	private String supervisorId;
	
	private String department;

	@Column(name = "telephone_number")
	private String telephoneNumber;
	
	public String getSupervisorId() {
		return supervisorId;
	}
	
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

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