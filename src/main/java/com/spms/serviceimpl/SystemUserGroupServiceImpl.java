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

import com.spms.dao.SystemUserDAO;
import com.spms.entity.sec.SystemUserGroup;
import com.spms.service.SystemUserGroupService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SystemUserGroupServiceImpl implements SystemUserGroupService {
	
	@PersistenceContext(unitName = "database")
	EntityManager em;
	
	@EJB
	private SystemUserDAO systemUserDAO;
	
	@Override
	public SystemUserGroup create(String username, String groupName) {
		SystemUserGroup systemUserGroup = new SystemUserGroup(username, groupName);
		em.persist(systemUserGroup);
		return systemUserGroup;
	}
	
	@Override
	public void delete(String username, String groupName) {
		em.remove(em.find(SystemUserGroup.class, new SystemUserGroup(username, groupName)));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SystemUserGroup find(String username, String groupName) {
		List<SystemUserGroup> resultList = em.createNamedQuery("SystemUserGroup.findUserGroup")
				.setParameter("username", username).setParameter("groupName", groupName).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
}
