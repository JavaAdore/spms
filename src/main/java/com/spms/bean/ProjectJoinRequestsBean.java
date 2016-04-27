package com.spms.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.spms.entity.JoinProjectRequest;
import com.spms.entity.Supervisor;
import com.spms.service.ProjectService;

@ManagedBean
@ViewScoped
public class ProjectJoinRequestsBean implements Serializable {

	@EJB
	private ProjectService projectService;

	Supervisor supervisor = (Supervisor) ((HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true))
			.getAttribute("currentUser");

	private List<JoinProjectRequest> joinRequests;

	@PostConstruct
	public void init() {
		loadJoinReqeusts();
	}

	private void loadJoinReqeusts() {

		joinRequests = projectService.loadJoinReqeusts(supervisor);

	}
	
	
	public void acceptProject(JoinProjectRequest joinProjectRequest)
	{
		projectService.acceptJoinRequest(joinProjectRequest);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Now the student has been assigned to this project",""));
		loadJoinReqeusts();
	}
	
	
	public void reject(JoinProjectRequest joinProjectRequest)
	{
		projectService.rejectJoinRequest(joinProjectRequest);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"request rejected",""));

		loadJoinReqeusts();
	}
	
	
	public List<JoinProjectRequest> getJoinRequests() {
		return joinRequests;
	}

	public void setJoinRequests(List<JoinProjectRequest> joinRequests) {
		this.joinRequests = joinRequests;
	}
}
