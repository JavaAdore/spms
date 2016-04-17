package com.student.project.management.system.serviceimpl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.student.project.management.system.entity.Student;
import com.student.project.management.system.service.TestService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TestImpl implements TestService {

	@PersistenceContext
	EntityManager em;
	
	@Override 
	public void test() {
		
		Query query = em.createQuery("select o from Student o");
		query.getResultList();
		
	}
  
}
