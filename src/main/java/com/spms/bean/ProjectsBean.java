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
import com.spms.entity.ProjectTopic;
import com.spms.entity.Supervisor;
import com.spms.service.ProjectService;

@ManagedBean
@ViewScoped
public class ProjectsBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Project> supervisorProjects;

	private List<ProjectTopic>projectTopics;
	
	
	Supervisor supervisor = (Supervisor) ((HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true))
			.getAttribute("currentUser");

	@EJB
	private ProjectService projectService;

	private Project project;

	@PostConstruct
	public void init() {
		refreshInstructorProjectsList();
		loadProjectTopics();
	}

	private void loadProjectTopics() {
		projectTopics = projectService.getAllProjectTopics();
		
	}

	public void prepareNewProjectToCreate() {

		project = new Project();
		project.setSupervisor(supervisor);

	}

	public void saveOrUpdateProject() {

		try {
			if(project.getId()==null){
			project = projectService.create(project);
			

			supervisorProjects.add(project);
			}else
			{
				project = projectService.update(project);
				refreshInstructorProjectsList();
			}
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Project has been updated successfully", ""));
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Sorry not able to update project", ""));

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

	public List<ProjectTopic> getProjectTopics() {
		return projectTopics;
	}

	public void setProjectTopics(List<ProjectTopic> projectTopics) {
		this.projectTopics = projectTopics;
	}

}
