
package com.spms.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.spms.entity.Admin;
import com.spms.entity.sec.SystemUser;
import com.spms.service.AdminService;
import com.spms.service.SupervisorService;
import com.spms.service.SystemUserService;
import com.spms.util.Util;

@ManagedBean
@ViewScoped
public class AdminManagerView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	SystemUserService systemUserService;
	
	@EJB
	AdminService adminService;
	
	@EJB
	SupervisorService supervisorService;
	
	@PostConstruct
	public void postConstruct() {
		refreshAdminList();
	}

	
	private List<Admin> adminList;
	private Admin newAdmin;
	private Admin selectedAdmin;
	
	public void refreshAdminList() {
		adminList = adminService.getAllAdmins();
	}

	public void beforeCreateNewAdmin() {
		newAdmin = new Admin();
	}

	public void createNewAdmin() {
		SystemUser systemUser = systemUserService.findByUsername(newAdmin.getUsername());
		if(systemUser != null){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username: " + newAdmin.getUsername() + " Already Exist.", ""));
			return;
		}
		newAdmin.setPassword(Util.hashPassword(newAdmin.getPassword()));
		newAdmin = adminService.create(newAdmin);
		adminList.add(newAdmin);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Admin: " + newAdmin.getUsername() + " created successfully."));
	}

	public void updateSelectedAdmin() {
		SystemUser systemUser = systemUserService.findByUsername(selectedAdmin.getUsername());
		
		if(systemUser != null  && !systemUser.getId().equals(selectedAdmin.getId())){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username: " + selectedAdmin.getUsername() + " Already Exist.", ""));
			return;
		}
		adminService.update(selectedAdmin);
		refreshAdminList();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Admin: " + selectedAdmin.getUsername() + " successfully updated."));
	}

	public void deleteSelectedAdmin() {
		adminService.delete(selectedAdmin);
		adminList.remove(selectedAdmin);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Admin: " + selectedAdmin.getUsername() + " successfully deleted."));
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public Admin getNewAdmin() {
		return newAdmin;
	}

	public void setNewAdmin(Admin newAdmin) {
		this.newAdmin = newAdmin;
	}

	public Admin getSelectedAdmin() {
		return selectedAdmin;
	}

	public void setSelectedAdmin(Admin selectedAdmin) {
		this.selectedAdmin = selectedAdmin;
	}
	
}
