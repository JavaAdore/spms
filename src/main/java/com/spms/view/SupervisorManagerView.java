
package com.spms.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.spms.entity.Supervisor;
import com.spms.entity.sec.SystemUser;
import com.spms.entity.sec.SystemUserGroup;
import com.spms.service.SupervisorService;
import com.spms.service.SystemUserGroupService;
import com.spms.service.SystemUserService;
import com.spms.util.Util;

@ManagedBean
@ViewScoped
public class SupervisorManagerView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	SystemUserService systemUserService;
	
	@EJB
	SupervisorService supervisorService;
	
	@EJB
	SystemUserGroupService systemUserGroupService;

	private List<Supervisor> supervisorList;
	private Supervisor newSupervisor;
	private Supervisor selectedSupervisor;
	
	@PostConstruct
	public void postConstruct() {
		refreshSupervisorList();
	}
	
	
	public void refreshSupervisorList() {
		supervisorList = supervisorService.getAllSupervisors();
	}

	public void beforeCreateNewSupervisor() {
		newSupervisor = new Supervisor();
	}

	public void createNewSupervisor() {
		Supervisor supervisor = supervisorService.findBySupervisorId(newSupervisor.getSupervisorId());
		if(supervisor != null){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID: " + newSupervisor.getSupervisorId() + " Already Exist.", ""));
			return;
		}
		
		SystemUser systemUser = systemUserService.findByUsername(newSupervisor.getUsername());
		if(systemUser != null){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username: " + newSupervisor.getUsername() + " Already Exist.", ""));
			return;
		}
		newSupervisor.setPassword(Util.hashPassword(newSupervisor.getPassword()));
		newSupervisor = supervisorService.create(newSupervisor);
		supervisorList.add(newSupervisor);
		
		SystemUserGroup systemUserGroup = systemUserGroupService.find(newSupervisor.getUsername(), "supervisor");
		if(systemUserGroup == null){
			systemUserGroup = new SystemUserGroup();
			systemUserGroupService.create(newSupervisor.getUsername(), "supervisor");
		}
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Supervisor: " + newSupervisor.getUsername() + " created successfully."));
	}

	public void updateSelectedSupervisor() {
		Supervisor supervisor = supervisorService.findBySupervisorId(selectedSupervisor.getSupervisorId());
		if(supervisor != null && !supervisor.getId().equals(selectedSupervisor.getId())){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID: " + selectedSupervisor.getSupervisorId() + " Already Exist.", ""));
			return;
		}
		
		SystemUser systemUser = systemUserService.findByUsername(selectedSupervisor.getUsername());
		if(systemUser != null && !systemUser.getId().equals(selectedSupervisor.getId())){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username: " + selectedSupervisor.getUsername() + " Already Exist.", ""));
			return;
		}  
		supervisorService.update(selectedSupervisor);
		refreshSupervisorList();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Supervisor: " + selectedSupervisor.getUsername() + " successfully updated."));
	}

	public void deleteSelectedSupervisor() {
		supervisorService.delete(selectedSupervisor);
		systemUserGroupService.delete(selectedSupervisor.getUsername(), "supervisor");
		supervisorList.remove(selectedSupervisor);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Supervisor: " + selectedSupervisor.getUsername() + " successfully deleted."));
	}

	public List<Supervisor> getSupervisorList() {
		return supervisorList;
	}

	public Supervisor getNewSupervisor() {
		return newSupervisor;
	}
	public void setNewSupervisor(Supervisor newSupervisor) {
		this.newSupervisor = newSupervisor;
	}
	
	public Supervisor getSelectedSupervisor() {
		return selectedSupervisor;
	}
	public void setSelectedSupervisor(Supervisor selectedSupervisor) {
		this.selectedSupervisor = selectedSupervisor;
	}


	public void setSupervisorList(List<Supervisor> supervisorList) {
		this.supervisorList = supervisorList;
	}

}
