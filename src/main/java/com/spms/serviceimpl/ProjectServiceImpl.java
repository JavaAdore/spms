package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.dao.ProjectDAO;
import com.spms.dao.StudentDAO;
import com.spms.entity.JoinProjectRequest;
import com.spms.entity.Project;
import com.spms.entity.ProjectTopic;
import com.spms.entity.Student;
import com.spms.entity.StudentProject;
import com.spms.entity.Supervisor;
import com.spms.exception.JoinRequestSentBeforeException;
import com.spms.exception.StudentAlreadyAssignedToProjectException;
import com.spms.service.ProjectService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProjectServiceImpl implements ProjectService {

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@EJB
	private ProjectDAO projectDAO;

	@EJB
	private StudentDAO studentDAO;

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
		return em.createNamedQuery("Project.findBySupervisorId")
				.setParameter("supervisorId", supervisorId).getResultList();
	}

	@Override
	public List<Project> getJoinableProjects() {

		return projectDAO.getJoinableProject();
	}

	@Override
	public void requestToJoinProject(Student student, Project project)
			throws StudentAlreadyAssignedToProjectException, JoinRequestSentBeforeException {

		Student STD = studentDAO.findStudent(student.getId());
		StudentProject persistedStudentProject = studentDAO
				.getStudentProject(student);
		JoinProjectRequest requestedJoin = studentDAO
				.getStudentProjectRequest(student);

		if (STD.getProject() != null || persistedStudentProject != null) {
			throw new StudentAlreadyAssignedToProjectException();
		}
		if (requestedJoin != null) {
			throw new JoinRequestSentBeforeException();
		}

		JoinProjectRequest joinProjectRequest = new JoinProjectRequest();
		joinProjectRequest.setProject(project);
		joinProjectRequest.setStudent(student);

		projectDAO.addNewJoinProjectRequest(joinProjectRequest);

	}

	@Override
	public List<JoinProjectRequest> loadJoinReqeusts(Supervisor supervisor) {
		return projectDAO.loadJoinReqeusts( supervisor);
	}

	@Override
	public void acceptJoinRequest(JoinProjectRequest joinProjectRequest) {
		
		Student student = studentDAO.findStudent(joinProjectRequest.getStudent().getId());
		student.setProject(joinProjectRequest.getProject());
		studentDAO.updateStudent(student);
		projectDAO.delete(joinProjectRequest);
	}

	@Override
	public void rejectJoinRequest(JoinProjectRequest joinProjectRequest) {
		projectDAO.delete(joinProjectRequest);
		
	}

	@Override
	public ProjectTopic findProjectTopic(Integer integer) {
		return projectDAO.findProjectTopic(integer);
	}

	@Override
	public List<ProjectTopic> getAllProjectTopics() {
		return projectDAO.getAllProjectTopics();
	}

	

}
