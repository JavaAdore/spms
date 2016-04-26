package com.spms.service;

import java.util.List;

import com.spms.entity.Student;
import com.spms.entity.StudentProject;
import com.spms.entity.Supervisor;

public interface StudentService {

	Student create(Student student);

	Student find(Integer id);

	Student update(Student student);

	void delete(Student student);

	List<Student> getAllStudents();
	
	Student findByStudentId(String studentId);
	
	Student findByUsername(String username);

	StudentProject getStudentProject(Student student);

	void save(StudentProject studentProject);

	List<StudentProject> getAllSuggestedProjectForSupervisor(Supervisor student);

	void updateSuggestedProjectStatus(StudentProject studentProject);

	Student findBySupervisorId(String supervisorId);
}
