package com.spms.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.dao.SupervisorDAO;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUserGroup;

@Stateless
public class SupervisorDAOImpl implements SupervisorDAO {

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@Override
	public Supervisor create(Supervisor supervisor) {
		em.persist(supervisor);
		SystemUserGroup systemUserGroup = new SystemUserGroup();
		systemUserGroup.setUsername(supervisor.getUsername());
		systemUserGroup.setGroupName("supervisor");
		em.persist(systemUserGroup);  
		return supervisor;
	}

	@Override
	public Supervisor find(Integer id) {
		return em.find(Supervisor.class, id);
	}

	@Override
	public Supervisor update(Supervisor supervisor) {
		return em.merge(supervisor);
	}

	@Override
	public void delete(Supervisor supervisor) {
		em.remove(em.getReference(Supervisor.class, supervisor.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supervisor> getAllSupervisors() {
		return em.createNamedQuery("Supervisor.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Supervisor findBySupervisorId(String supervisorId) {
		List<Supervisor>  resultList = em.createNamedQuery("Supervisor.findBySupervisorId").setParameter("supervisorId", supervisorId).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Supervisor findByUsername(String username) {
		List<Supervisor>  resultList = em.createNamedQuery("Supervisor.findByUsername").setParameter("username", username).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Supervisor findSupervisorByStudentId(String studentId) {
		Query query = em.createQuery("SELECT model.project.supervisor FROM  Student model WHERE model.studentId = :studentId");
		query.setParameter("studentId", studentId);
		List queryResult = query.getResultList();
		if(queryResult.size()>0)
			return (Supervisor)queryResult.get(0);
		return null;
	}

	@Override
	public StudentProjectStatus findStudentProjectStatus(
			Integer id) {
		return em.find(StudentProjectStatus.class, id);
	}

	@Override
	public void refershSupervisor(Supervisor supervisor) {
		em.refresh(supervisor);
	}

}
