package com.spms.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spms.dao.SystemUserDAO;
import com.spms.entity.Admin;
import com.spms.entity.Student;
import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUserGroup;

@Stateless
public class SystemUserDAOImpl implements SystemUserDAO {

	@PersistenceContext(unitName = "database")
	EntityManager em;
	
	
	@Override
	public Admin findAdministratorByUserName(String userName) {
		Query query = em.createQuery("select x from  Admin x where x.username =:username ");
		query.setParameter("username", userName);
		List queryResult = query.getResultList();
		if(queryResult.size()>0)
		{
			return (Admin)queryResult.get(0);
		}
		return null;
	}

	@Override
	public Supervisor findSupervisorByUserName(String userName) {
		Query query = em.createQuery("select x from  Supervisor x where x.username =:username ");
		query.setParameter("username", userName);
		List queryResult = query.getResultList();
		if(queryResult.size()>0)
		{
			return (Supervisor)queryResult.get(0);
		}
		return null;
	}

	@Override
	public Student findStudentByUserName(String userName) {
		Query query = em.createQuery("select x from  Student x where x.username =:username ");
		query.setParameter("username", userName);
		List queryResult = query.getResultList();
		if(queryResult.size()>0)
		{
			return (Student)queryResult.get(0);
		}
		return null;
	}

	@Override
	public void saveAdmin(Admin admin) {
		em.persist(admin);
		
	}

	@Override
	public void saveSystemUserGroup(SystemUserGroup systemUserGroup) {

		em.persist(systemUserGroup);
	}

}
