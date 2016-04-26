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

import com.spms.entity.Project;
import com.spms.entity.Supervisor;
import com.spms.service.ProjectService;

@ManagedBean
@ViewScoped
public class ProjectsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Project> supervisorProjects;

	Supervisor supervisor = (Supervisor) ((HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true))
			.getAttribute("currentUser");

	@EJB
	private ProjectService projectService;

	private Project project;

	@PostConstruct
	public void init() {
		refreshInstructorProjectsList();

	}

	public void prepareNewProjectToCreate() {
		try {
			project = new Project();
			project.setSupervisor(supervisor);

			project = projectService.create(project);

			supervisorProjects.add(project);
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Sorry not able to add project", ""));

		}
	}

	private void refreshInstructorProjectsList() {

		supervisorProjects = projectService.findBySupervisorId(supervisor
				.getSupervisorId());

	}

	public List<Project> getSupervisorProjects() {
		return supervisorProjects;
	}

	public void setSupervisorProjects(List<Project> supervisorProjects) {
		this.supervisorProjects = supervisorProjects;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}