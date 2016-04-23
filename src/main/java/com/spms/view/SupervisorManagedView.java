
package com.spms.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.spms.entity.Supervisor;
import com.spms.service.SupervisorService;

@ManagedBean
@ViewScoped
public class SupervisorManagedView  implements Serializable{
  
	private static final long serialVersionUID = 1L; 
	
	@EJB
	SupervisorService supervisorService;
	
	public List<Supervisor> supervisors;
	public Supervisor selectedSupervisor;
	
	@PostConstruct
	public void postConstruct(){
	  refreshSupervisors();
	}
	
	private void refreshSupervisors(){
	  supervisors = supervisorService.findAll();
	}
	
	public List<Supervisor> getSupervisors() {
      return supervisors;
    }
	public void setSupervisors(List<Supervisor> supervisors) {
      this.supervisors = supervisors;
    }
	
	public Supervisor getSelectedSupervisor() {
      return selectedSupervisor;
    }
	public void setSelectedSupervisor(Supervisor selectedSupervisor) {
      this.selectedSupervisor = selectedSupervisor;
    }
	
}
