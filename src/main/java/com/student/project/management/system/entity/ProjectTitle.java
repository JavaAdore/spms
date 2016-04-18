package com.student.project.management.system.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ProjectTitle implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Project project;

	@ManyToOne
	private ProjectTopic topic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectTopic getTopic() {
		return topic;
	}

	public void setTopic(ProjectTopic topic) {
		this.topic = topic;
	}
	
	

}
