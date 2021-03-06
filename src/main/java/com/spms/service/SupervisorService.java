package com.spms.service;

import java.util.List;

import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;

public interface SupervisorService {

	Supervisor create(Supervisor supervisor);

	Supervisor find(Integer id);

	Supervisor update(Supervisor supervisor);

	void delete(Supervisor supervisor);

	List<Supervisor> getAllSupervisors();
	
	Supervisor findBySupervisorId(String supervisorId);
	
	Supervisor findByUsername(String username);
	
	Supervisor findSupervisorByStudentId(String studentId);

	StudentProjectStatus findStudentProjectStatus(Integer  id );
	
	void refershSupervisor(Supervisor supervisor);
	
}
