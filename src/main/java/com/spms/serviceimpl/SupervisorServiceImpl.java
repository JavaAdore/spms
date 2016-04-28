package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.spms.dao.SupervisorDAO;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.service.SupervisorService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SupervisorServiceImpl implements SupervisorService {

	@EJB
	SupervisorDAO supervisorDAO;

	@Override
	public Supervisor create(Supervisor supervisor) {
		return supervisorDAO.create(supervisor);
	}

	@Override
	public Supervisor find(Integer id) {
		return supervisorDAO.find(id);
	}

	@Override
	public Supervisor update(Supervisor supervisor) {
		return supervisorDAO.update(supervisor);
	}

	@Override
	public void delete(Supervisor supervisor) {
		supervisorDAO.delete(supervisor);
	}

	@Override
	public List<Supervisor> getAllSupervisors() {
		return supervisorDAO.getAllSupervisors();
	}

	@Override
	public Supervisor findBySupervisorId(String supervisorId) {
		return supervisorDAO.findBySupervisorId(supervisorId);
	}

	@Override
	public Supervisor findByUsername(String username) {
		return supervisorDAO.findByUsername(username);
	}

	@Override
	public Supervisor findSupervisorByStudentId(String studentId) {
		return supervisorDAO.findSupervisorByStudentId(studentId);
	}

	@Override
	public StudentProjectStatus findStudentProjectStatus(Integer id) {
		return supervisorDAO.findStudentProjectStatus(id);
	}

	@Override
	public void refershSupervisor(Supervisor supervisor) {
		supervisorDAO.refershSupervisor(supervisor);
	}
}
