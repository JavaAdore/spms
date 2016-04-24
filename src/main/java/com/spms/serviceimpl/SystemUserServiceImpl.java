package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spms.entity.sec.SystemUser;
import com.spms.service.SystemUserService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SystemUserServiceImpl implements SystemUserService {

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public SystemUser findByUsername(String username) {
		List<SystemUser>  resultList = em.createNamedQuery("SystemUser.findByUsername").setParameter("username", username).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
}
