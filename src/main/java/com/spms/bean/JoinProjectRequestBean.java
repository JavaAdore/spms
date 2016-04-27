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
import com.spms.entity.Student;
import com.spms.exception.StudentAlreadyAssignedToProjectException;
import com.spms.service.ProjectService;
import com.spms.service.StudentService;

@ManagedBean
@ViewScoped
public class JoinProjectRequestBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProjectService projectService;
	
	@EJB
	StudentService studentService;

	private List<Project> joinalbeProjects;

	Student student = (Student) ((HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true))
			.getAttribute("currentUser");

	@PostConstruct
	public void init() {
		
		
		joinalbeProjects = projectService.getJoinableProjects();
		refreshStudent();
	}

	private void refreshStudent() {
		student = studentService.find(student.getId());
	}

	public void requiredToJoinProject(Project project) {
		try {
			projectService.requestToJoinProject(student, project);
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"Your request has been sent successfuly to your supervisor",
									""));
			
			refreshStudent();
		} catch (StudentAlreadyAssignedToProjectException ex) {
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							FacesMessage.SEVERITY_INFO,
							"you have already been assigned to other project",
							""));
		}

		catch (Exception ex) {

		}
	}

	public List<Project> getJoinalbeProjects() {
		return joinalbeProjects;
	}

	public void setJoinalbeProjects(List<Project> joinalbeProjects) {
		this.joinalbeProjects = joinalbeProjects;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
