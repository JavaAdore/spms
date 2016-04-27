package com.spms.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.dao.StudentDAO;
import com.spms.dao.SystemUserDAO;
import com.spms.entity.Admin;
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
		Query query = em.createQuery("select x from StudentProject x where x.student =:student");
		query.setParameter("student", student);
		List queryResult = query.getResultList();
		if(queryResult.isEmpty()==false)
		{
			return (StudentProject)queryResult.get(0);
		}
		return null;
	}

	@Override
	public void save(StudentProject studentProject) {
		
		em.persist(studentProject);
	}

	@Override
	public void saveOrUpdateStudentProjectStatus(
			StudentProjectStatus studentProjectStatus) {
		em.merge(studentProjectStatus);
		
	}
	
	
	
	

}