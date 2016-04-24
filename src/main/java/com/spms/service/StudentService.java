package com.spms.service;

import java.util.List;

import com.spms.entity.Student;

public interface StudentService {

	Student create(Student student);

	Student find(Integer id);

	Student update(Student student);

	void delete(Student student);

	List<Student> getAllStudents();
	
	Student findByStudentId(String studentId);
	
	Student findByUsername(String username);

}
