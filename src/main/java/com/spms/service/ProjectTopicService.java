package com.spms.service;

import java.util.List;

import com.spms.entity.ProjectTopic;

public interface ProjectTopicService {

	ProjectTopic create(ProjectTopic projectTopic);

	ProjectTopic find(Integer id);

	ProjectTopic update(ProjectTopic projectTopic);

	void delete(ProjectTopic projectTopic);

	List<ProjectTopic> getAllProjectTopics();
	
	ProjectTopic findByTopicTitle(String topicTitle);

}
