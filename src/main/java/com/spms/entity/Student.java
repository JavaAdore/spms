package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

import com.spms.entity.sec.SystemUser;

@Entity
@NamedQueries({ @NamedQuery(name = "Student.findAll", query = "SELECT model FROM Student model"),
	@NamedQuery(name = "Student.findByStudentId", query = "SELECT model FROM Student model WHERE model.studentId = :studentId"),
	@NamedQuery(name = "Student.findByUsername", query = "SELECT model FROM Student model WHERE model.username = :username")})
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "studentId"}) })
@PrimaryKeyJoinColumn(name="id")
public class Student extends SystemUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Student: id (must be unique), name, surname, email address (must be
	// validated), course (e.g.
	// Computer Science).

	@Basic(optional = false)
	private String studentId;
	
	private String course;
	
	
	@ManyToOne
	@JoinColumn(name="project_id" , referencedColumnName = "id")
	private Project project;

	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@XmlTransient
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
