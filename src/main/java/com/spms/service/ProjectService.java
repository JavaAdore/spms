package com.spms.service;

import java.util.List;

import com.spms.entity.Project;
import com.spms.entity.Student;
import com.spms.exception.StudentAlreadyAssignedToProjectException;

public interface ProjectService {

	Project create(Project project);

	Project find(Integer id);

	Project update(Project project);

	void delete(Project project);

	List<Project> getAllProjects();
	
	List<Project> findBySupervisorId(String supervisorId);

	List<Project> getJoinableProjects();

	void requestToJoinProject(Student student, Project project) throws StudentAlreadyAssignedToProjectException;

}
