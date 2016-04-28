package com.spms.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.dao.StudentDAO;
import com.spms.dao.SystemUserDAO;
import com.spms.entity.Admin;
import com.spms.entity.JoinProjectRequest;
import com.spms.entity.Project;
import com.spms.entity.Student;
import com.spms.entity.StudentProject;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUserGroup;

@Stateless
public class StudentDAOImpl implements StudentDAO {

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@Override
	public StudentProject getStudentProject(Student student) {
		Query query = em
				.createQuery("select x from StudentProject x where x.student =:student");
		query.setParameter("student", student);
		List queryResult = query.getResultList();
		if (queryResult.isEmpty() == false) {
			return (StudentProject) queryResult.get(0);
		}
		return null;
	}

	@Override
	public StudentProject save(StudentProject studentProject) {

		em.merge(studentProject);
		return studentProject;  
	}

	@Override
	public void saveOrUpdateStudentProjectStatus(
			StudentProjectStatus studentProjectStatus) {
		em.merge(studentProjectStatus);

	}

	@Override
	public List<StudentProject> getAllSuggestedProjectForSupervisor(
			Supervisor supervisor) {
		Query query = em
				.createQuery("select x from StudentProject x where x.suggestedSupervisor =:suggestedSupervisor and x.status <> :approved");
		query.setParameter("approved",
				StudentProjectStatus.ACCEPTED_BY_SUPERVISOR);
		query.setParameter("suggestedSupervisor", supervisor);
		return query.getResultList();
	}

	@Override
	public void setStudentProject(Student student, Integer id) {

		student.setProject(new Project(id));
		em.merge(student);
	}

	@Override
	public void updateSuggestedProjectStatus(StudentProject studentProject) {

		em.merge(studentProject);
	}

	@Override
	public Student findStudent(Integer id) {
		return em.find(Student.class, id);
	}

	@Override
	public JoinProjectRequest getStudentProjectRequest(Student student) {
		Query query = em
				.createQuery("select x from JoinProjectRequest x where x.student =:student");
		query.setParameter("student", student);
		List queryResult = query.getResultList();
		if (queryResult.isEmpty() == false) {
			return (JoinProjectRequest) queryResult.get(0);
		}
		return null;
	}

	@Override
	public void updateStudent(Student student) {
		em.merge(student);
	}

}
