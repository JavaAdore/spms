package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spms.entity.Project;
import com.spms.service.ProjectService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProjectServiceImpl implements ProjectService {

	@PersistenceContext(unitName = "database")
	EntityManager em;
	
	@Override
	public Project create(Project project) {
		em.persist(project);
		return project;
	}

	@Override
	public Project find(Integer id) {
		return em.find(Project.class, id);
	}

	@Override
	public Project update(Project project) {
		return em.merge(project);
	}

	@Override
	public void delete(Project project) {
		em.remove(em.getReference(Project.class, project.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getAllProjects() {
		return em.createNamedQuery("Project.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findBySupervisorId(String supervisorId) {
		return em.createNamedQuery("Project.findBySupervisorId").setParameter("supervisorId", supervisorId).getResultList();
	}
	
}
