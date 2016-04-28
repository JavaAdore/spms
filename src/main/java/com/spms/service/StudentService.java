package com.spms.service;

import java.util.List;

import com.spms.entity.Student;
import com.spms.entity.StudentProject;
import com.spms.entity.Supervisor;
import com.spms.exception.StudentAlreadyAssignedToProjectException;

public interface StudentService {

	Student create(Student student);

	Student find(Integer id);

	Student update(Student student);

	void delete(Student student);

	List<Student> getAllStudents();
	
	Student findByStudentId(String studentId);
	
	Student findByUsername(String username);

	StudentProject getStudentProject(Student student);

	StudentProject save(StudentProject studentProject) throws StudentAlreadyAssignedToProjectException;

	List<StudentProject> getAllSuggestedProjectForSupervisor(Supervisor student);

	void updateSuggestedProjectStatus(StudentProject studentProject);

	Student findBySupervisorId(String supervisorId);
}
