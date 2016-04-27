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
import javax.servlet.http.HttpSession;

import com.spms.entity.Student;
import com.spms.entity.StudentProject;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.service.StudentService;
import com.spms.service.SupervisorService;

@ManagedBean
@ViewScoped
public class StudentProjectBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StudentProject studentProject;

	private List<Supervisor> supervisors;

	@EJB
	StudentService studentService;

	@EJB
	SupervisorService supervisorService;


	Student student = (Student) ((HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true))
			.getAttribute("currentUser");

	@PostConstruct
	public void init() {
		supervisors = supervisorService.getAllSupervisors();

		StudentProject tempStudentProject = studentService
				.getStudentProject(student);
		if (tempStudentProject != null) {
			studentProject = tempStudentProject;
		}
		
	}


	public void prepareNewStudentProjectToCreate() {
		studentProject = new StudentProject();
		studentProject.setStudent(student);
		studentProject.setStatus(StudentProjectStatus.SUGGESTED_BY_STUDENT);

	}

	public void saveStudentProejct() {

		try {
			studentService.save(studentProject);

			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"Student Project has been created successfully",
									""));
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Sorry not able to create Student  project", ""));

		}

	}

	public StudentProject getStudentProject() {
		return studentProject;
	}

	public void setStudentProject(StudentProject studentProject) {
		this.studentProject = studentProject;
	}

	public List<Supervisor> getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(List<Supervisor> supervisors) {
		this.supervisors = supervisors;
	}

	
}
