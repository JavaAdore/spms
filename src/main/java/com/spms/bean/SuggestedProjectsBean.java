package com.spms.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import com.spms.entity.StudentProject;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.service.StudentService;
import com.spms.service.SupervisorService;

@ManagedBean
@ViewScoped
public class SuggestedProjectsBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<StudentProject> studentProjects;

	Supervisor supervisor = (Supervisor) ((HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true))
			.getAttribute("currentUser");

	@EJB
	private StudentService studentService;
	
	@EJB
	private SupervisorService supervisorService;
		
	private List<StudentProjectStatus> studentProjectStatus;
	
	private StudentProject studentProject;

	private List<StudentProjectStatus> getAllStudentProjectStatus() {
		List<StudentProjectStatus> list = new ArrayList<>();
		list.add(StudentProjectStatus.SUGGESTED_BY_STUDENT);
		list.add(StudentProjectStatus.ACCEPTED_BY_SUPERVISOR);
		list.add(StudentProjectStatus.REJECTED_BY_SUPERVISOR);
		return list;
	}

	@PostConstruct
	public void init() {
		loadSuggestedProjects();
		studentProjectStatus = getAllStudentProjectStatus();

	}
	
	public void save(StudentProject studentProject)
	{
		if(studentProject.getStatus().equals(StudentProjectStatus.ACCEPTED_BY_SUPERVISOR))
			studentProject.setSupervisor(supervisorService.find(supervisor.getId()));
		
		studentService.updateSuggestedProjectStatus(studentProject);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Student Project: " + studentProject.getTitle() + " " + studentProject.getStatus().getName()));
	}
	
	public void updateSuggestedProjectStatus(AjaxBehaviorEvent e) {
		
	}

	private void loadSuggestedProjects() {
		studentProjects = studentService
				.getAllSuggestedProjectForSupervisor(supervisor);

	}

	public List<StudentProject> getStudentProjects() {
		return studentProjects;
	}

	public void setStudentProjects(List<StudentProject> studentProjects) {
		this.studentProjects = studentProjects;
	}

	public List<StudentProjectStatus> getStudentProjectStatus() {
		return studentProjectStatus;
	}

	public void setStudentProjectStatus(
			List<StudentProjectStatus> studentProjectStatus) {
		this.studentProjectStatus = studentProjectStatus;
	}

	public StudentProject getStudentProject() {
		return studentProject;
	}

	public void setStudentProject(StudentProject studentProject) {
		this.studentProject = studentProject;
	}


}
