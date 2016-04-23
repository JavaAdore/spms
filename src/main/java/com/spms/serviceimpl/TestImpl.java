package com.spms.serviceimpl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.service.TestService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TestImpl implements TestService {

	@PersistenceContext(unitName="database")
	EntityManager em;
	
	@Override 
	public void test() {
		
		Query query = em.createQuery("select o from Student o");
		
		System.out.println("query.getResultList().size() = " + query.getResultList().size());
		System.out.println("+++++++Done+++++++");
		
	}
  
}
