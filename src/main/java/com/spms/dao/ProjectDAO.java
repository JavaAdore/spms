package com.spms.dao;

import java.util.List;

import javax.ejb.Local;

import com.spms.entity.JoinProjectRequest;
import com.spms.entity.Project;
import com.spms.entity.ProjectTopic;
import com.spms.entity.Supervisor;

@Local
public interface ProjectDAO {

	List<Project> getJoinableProject();

	void addNewJoinProjectRequest(JoinProjectRequest joinProjectRequest);

	List<JoinProjectRequest> loadJoinReqeusts(Supervisor supervisor);

	void delete(JoinProjectRequest joinProjectRequest);

	ProjectTopic findProjectTopic(Integer integer);

	List<ProjectTopic> getAllProjectTopics();

}
