
package com.spms.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.spms.entity.ProjectTopic;
import com.spms.service.ProjectTopicService;

@ManagedBean
@ViewScoped
public class ProjectTopicManagerView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	ProjectTopicService projectTopicService;
	
	@PostConstruct
	public void postConstruct() {
		refreshProjectTopicList();
	}

	private List<ProjectTopic> projectTopicList;
	private ProjectTopic newProjectTopic;
	private ProjectTopic selectedProjectTopic;
	
	public void refreshProjectTopicList() {
		projectTopicList = projectTopicService.getAllProjectTopics();
	}

	public void beforeCreateNewProjectTopic() {
		newProjectTopic = new ProjectTopic();
	}

	public void createNewProjectTopic() {
		ProjectTopic projectTopic = projectTopicService.findByTopicTitle(newProjectTopic.getTopicTitle());
		if(projectTopic != null){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Topic Title: " + newProjectTopic.getTopicTitle() + " Already Exist.", ""));
			return;
		}
		newProjectTopic = projectTopicService.create(newProjectTopic);
		projectTopicList.add(newProjectTopic);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Project Topic: " + newProjectTopic.getTopicTitle() + " created successfully."));
	}

	public void updateSelectedProjectTopic() {
		ProjectTopic projectTopic = projectTopicService.findByTopicTitle(selectedProjectTopic.getTopicTitle());
		if(projectTopic != null && !projectTopic.getId().equals(selectedProjectTopic.getId())){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Topic Title: " + selectedProjectTopic.getTopicTitle() + " Already Exist.", ""));
			return;
		}
		projectTopicService.update(selectedProjectTopic);
		refreshProjectTopicList();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Project Topic: " + selectedProjectTopic.getTopicTitle() + " updated successfully."));
	}

	public void deleteSelectedProjectTopic() {
		projectTopicService.delete(selectedProjectTopic);
		projectTopicList.remove(selectedProjectTopic);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Project Topic: " + selectedProjectTopic.getTopicTitle() + " deleted successfully."));
	}

	public List<ProjectTopic> getProjectTopicList() {
		return projectTopicList;
	}

	public ProjectTopic getNewProjectTopic() {
		return newProjectTopic;
	}
	
	public void setNewProjectTopic(ProjectTopic newProjectTopic) {
		this.newProjectTopic = newProjectTopic;
	}

	public ProjectTopic getSelectedProjectTopic() {
		return selectedProjectTopic;
	}
	
	public void setSelectedProjectTopic(ProjectTopic selectedProjectTopic) {
		this.selectedProjectTopic = selectedProjectTopic;
	}

}
