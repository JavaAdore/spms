package com.spms.dao;

import java.util.List;

import javax.ejb.Local;

import com.spms.entity.JoinProjectRequest;
import com.spms.entity.Project;

@Local
public interface ProjectDAO {

	List<Project> getJoinableProject();

	void addNewJoinProjectRequest(JoinProjectRequest joinProjectRequest);

}
