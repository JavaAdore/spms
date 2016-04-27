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
		ProjectTopic other = (ProjectTopic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
