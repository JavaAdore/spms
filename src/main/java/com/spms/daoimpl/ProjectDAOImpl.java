package com.spms.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.dao.ProjectDAO;
import com.spms.entity.JoinProjectRequest;
import com.spms.entity.Project;
import com.spms.entity.ProjectTopic;
import com.spms.entity.Supervisor;

@Stateless
public class ProjectDAOImpl implements ProjectDAO {

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@Override
	public List<Project> getJoinableProject() {
		Query query = em
				.createQuery("select  model from Project model where model.supervisor is not null");
		return query.getResultList();
	}

	@Override
	public void addNewJoinProjectRequest(JoinProjectRequest joinProjectRequest) {
		em.persist(joinProjectRequest);

	}

	@Override
	public List<JoinProjectRequest> loadJoinReqeusts(Supervisor supervisor) {
		Query query = em
				.createQuery("select model from JoinProjectRequest model where model.project.supervisor =:supervisor ");
		query.setParameter("supervisor", supervisor);
		return query.getResultList();
	}

	@Override
	public void delete(JoinProjectRequest joinProjectRequest) {

		Query query = em
				.createQuery("delete from JoinProjectRequest where id =:id");
		query.setParameter("id", joinProjectRequest.getId());
		query.executeUpdate();
	}

	@Override
	public ProjectTopic findProjectTopic(Integer integer) {
		return em.find(ProjectTopic.class, integer);
	}

	@Override
	public List<ProjectTopic> getAllProjectTopics() {
		Query query = em.createQuery("select x from ProjectTopic x ");
		return query.getResultList();
	}

}
