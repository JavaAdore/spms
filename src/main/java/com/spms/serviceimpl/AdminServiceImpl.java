package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spms.entity.Admin;
import com.spms.service.AdminService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AdminServiceImpl implements AdminService {

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@Override
	public Admin create(Admin admin) {
		em.persist(admin);
		return admin;
	}

	@Override
	public Admin find(Integer id) {
		return em.find(Admin.class, id);
	}

	@Override
	public Admin update(Admin admin) {
		return em.merge(admin);
	}

	@Override
	public void delete(Admin admin) {
		em.remove(em.getReference(Admin.class, admin.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> getAllAdmins() {
		return em.createNamedQuery("Admin.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Admin findByUsername(String username) {
		List<Admin>  resultList = em.createNamedQuery("Admin.findByUsername").setParameter("username", username).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
}
