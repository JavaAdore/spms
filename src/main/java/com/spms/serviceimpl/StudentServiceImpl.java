package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spms.entity.Student;
import com.spms.service.StudentService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StudentServiceImpl implements StudentService {

	@PersistenceContext(unitName = "database")
	EntityManager em;
	
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
		List<Student>  resultList = em.createNamedQuery("Student.findByStudentId").setParameter("studentId", studentId).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student findByUsername(String username) {
		List<Student>  resultList = em.createNamedQuery("Student.findByUsername").setParameter("username", username).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

}
