package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spms.entity.ProjectTopic;
import com.spms.service.ProjectTopicService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProjectTopicServiceImpl implements ProjectTopicService {

	@PersistenceContext(unitName = "database")
	EntityManager em;
	
	@Override
	public ProjectTopic create(ProjectTopic projectTopic) {
		em.persist(projectTopic);
		return projectTopic;
	}

	@Override
	public ProjectTopic find(Integer id) {
		return em.find(ProjectTopic.class, id);
	}

	@Override
	public ProjectTopic update(ProjectTopic projectTopic) {
		return em.merge(projectTopic);
	}

	@Override
	public void delete(ProjectTopic projectTopic) {
		em.remove(em.getReference(ProjectTopic.class, projectTopic.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectTopic> getAllProjectTopics() {
		return em.createNamedQuery("ProjectTopic.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProjectTopic findByTopicTitle(String topicTitle) {
		List<ProjectTopic>  resultList = em.createNamedQuery("ProjectTopic.findByTopicTitle").setParameter("topicTitle", topicTitle).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
}
