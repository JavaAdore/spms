package com.spms.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.dao.ProjectDAO;
import com.spms.entity.JoinProjectRequest;
import com.spms.entity.Project;

@Stateless
public class ProjectDAOImpl implements ProjectDAO  {

	
	@PersistenceContext(unitName = "database")
	EntityManager em;

	@Override
	public List<Project> getJoinableProject() {
		Query query = em.createQuery("select  model from Project model where model.supervisor is not null");
		return query.getResultList();
	}

	@Override
	public void addNewJoinProjectRequest(JoinProjectRequest joinProjectRequest) {
		em.persist(joinProjectRequest);
		
	}
	
	
	
	
}
