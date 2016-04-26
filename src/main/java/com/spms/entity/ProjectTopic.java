package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "ProjectTopic.findAll", query = "SELECT model FROM ProjectTopic model"),
	@NamedQuery(name = "ProjectTopic.findByTopicTitle", query = "SELECT model FROM ProjectTopic model WHERE model.topicTitle = :topicTitle") })
public class ProjectTopic implements Serializable {

	private static final long serialVersionUID = 1L;

	// Project Topic: Topic title (e.g. Computer Networks), Topic Description.

	@Id
	@GeneratedValue
	private Integer id;
	private String topicTitle;
	private String topicDescription;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

}
