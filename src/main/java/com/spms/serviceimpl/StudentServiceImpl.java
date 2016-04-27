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

import com.spms.dao.StudentDAO;
import com.spms.entity.Student;
import com.spms.entity.StudentProject;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.exception.StudentAlreadyAssignedToProjectException;
import com.spms.service.StudentService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StudentServiceImpl implements StudentService {

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@EJB
	StudentDAO studentDAO;

	@Override
	public Student create(Student student) {
		em.persist(student);
		return student;
	}

	@Override
	public Student find(Integer id) {
		return em.find(Student.class, id);
	}

	@Override
	public Student update(Student student) {
		return em.merge(student);
	}

	@Override
	public void delete(Student student) {
		em.remove(em.getReference(Student.class, student.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents() {
		return em.createNamedQuery("Student.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student findByStudentId(String studentId) {
		List<Student> resultList = em
				.createNamedQuery("Student.findByStudentId")
				.setParameter("studentId", studentId).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student findByUsername(String username) {
		List<Student> resultList = em
				.createNamedQuery("Student.findByUsername")
				.setParameter("username", username).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public StudentProject getStudentProject(Student student) {
		// TODO Auto-generated method stub
		return studentDAO.getStudentProject(student);
	}

	@Override
	public void save(StudentProject studentProject)
			throws StudentAlreadyAssignedToProjectException {

		Student student = studentDAO.findStudent(studentProject.getId());
		StudentProject persistedStudentProject = studentDAO
				.getStudentProject(student);

		if (student.getProject() != null || persistedStudentProject != null) {
			throw new StudentAlreadyAssignedToProjectException();
		}
		studentDAO.save(studentProject);
	}

	public void saveOrUpdateStudentProjectStatus(
			StudentProjectStatus studentProjectStatus) {
		studentDAO.saveOrUpdateStudentProjectStatus(studentProjectStatus);
	}

	@Override
	public List<StudentProject> getAllSuggestedProjectForSupervisor(
			Supervisor supervisor) {
		return studentDAO.getAllSuggestedProjectForSupervisor(supervisor);
	}

	@Override
	public void updateSuggestedProjectStatus(StudentProject studentProject) {
		if (studentProject.getStatus().equals(
				StudentProjectStatus.ACCEPTED_BY_SUPERVISOR)) {
			studentProject.setSupervisor(studentProject
					.getSuggestedSupervisor());
			studentDAO.updateSuggestedProjectStatus(studentProject);
			studentDAO.setStudentProject(studentProject.getStudent(), studentProject.getId());
		} else
			studentDAO.updateSuggestedProjectStatus(studentProject);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Student findBySupervisorId(String supervisorId) {
		Query query = em.createQuery("SELECT model FROM  Student model WHERE model.project.supervisor.supervisorId = :supervisorId");
		query.setParameter("supervisorId", supervisorId);
		List queryResult = query.getResultList();
		if(queryResult.size()>0){
			return (Student) queryResult.get(0);
		}
		return null;
	}

}
