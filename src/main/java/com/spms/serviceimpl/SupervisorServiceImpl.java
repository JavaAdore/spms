package com.spms.serviceimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spms.entity.Supervisor;
import com.spms.service.SupervisorService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SupervisorServiceImpl implements SupervisorService {

  @PersistenceContext(unitName="database")
  EntityManager em;

  @SuppressWarnings("unchecked")
  @Override
  public List<Supervisor> findAll() {
    return em.createNamedQuery("Supervisor.findAll").getResultList();
  }
}
