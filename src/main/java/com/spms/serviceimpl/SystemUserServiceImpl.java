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

import com.spms.dao.StudentDAO;
import com.spms.dao.SystemUserDAO;
import com.spms.entity.Admin;
import com.spms.entity.Student;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUser;
import com.spms.entity.sec.SystemUserGroup;
import com.spms.service.SystemUserService;
import com.spms.util.Util;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SystemUserServiceImpl implements SystemUserService {

	@PostConstruct
	public void init() {

	}

	@EJB
	private SystemUserDAO systemUserDAO;

	@EJB
	StudentDAO studentDAO;

	@PersistenceContext(unitName = "database")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public SystemUser findByUsername(String username) {
		List<SystemUser> resultList = em
				.createNamedQuery("SystemUser.findByUsername")
				.setParameter("username", username).getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
	@Override
	public Admin findAdministratorByUserName(String userName) {
		return systemUserDAO.findAdministratorByUserName(userName);
	}
	
	@Override
	public Supervisor findSupervisorByUserName(String userName) {
		return systemUserDAO.findSupervisorByUserName(userName);
	}

	@Override
	public Student findStudentByUserName(String userName) {
		return systemUserDAO.findStudentByUserName(userName);
	}

	@Override
	public void insertDefaultAdministrator() {
		SystemUser systemUser = systemUserDAO
				.findAdministratorByUserName("admin1");
		if (systemUser == null) {
			Admin admin = new Admin();
			admin.setName("Admin1 name");
			admin.setUsername("admin1");
			admin.setSurname("admin1 surname");
			admin.setEmail("admin1@admin1.com");
			admin.setPassword(Util.hashPassword("admin1"));
			systemUserDAO.saveAdmin(admin);
			SystemUserGroup systemUserGroup = new SystemUserGroup();
			systemUserGroup.setGroupName("admin");
			systemUserGroup.setUsername("admin1");
			systemUserDAO.saveSystemUserGroup(systemUserGroup);

			studentDAO
					.saveOrUpdateStudentProjectStatus(StudentProjectStatus.SUGGESTED_BY_STUDENT);

			studentDAO
					.saveOrUpdateStudentProjectStatus(StudentProjectStatus.ACCEPTED_BY_SUPERVISOR);
			studentDAO
					.saveOrUpdateStudentProjectStatus(StudentProjectStatus.REJECTED_BY_SUPERVISOR);

		}
	}
}
