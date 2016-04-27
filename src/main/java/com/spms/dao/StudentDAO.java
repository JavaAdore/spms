package com.spms.dao;

import java.util.List;

import javax.ejb.Local;

import com.spms.entity.Admin;
import com.spms.entity.Student;
import com.spms.entity.StudentProject;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUserGroup;

@Local
public interface StudentDAO {

	StudentProject getStudentProject(Student student);

	void save(StudentProject studentProject);

	void saveOrUpdateStudentProjectStatus(
			StudentProjectStatus studentProjectStatus);

	List<StudentProject> getAllSuggestedProjectForSupervisor(
			Supervisor supervisor);

	void setStudentProject(Student student, Integer id);

	void updateSuggestedProjectStatus(StudentProject studentProject);

	

}
