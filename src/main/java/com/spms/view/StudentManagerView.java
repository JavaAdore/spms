
package com.spms.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.spms.entity.Student;
import com.spms.entity.sec.SystemUser;
import com.spms.entity.sec.SystemUserGroup;
import com.spms.service.StudentService;
import com.spms.service.SystemUserGroupService;
import com.spms.service.SystemUserService;
import com.spms.util.Util;

@ManagedBean
@ViewScoped
public class StudentManagerView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	SystemUserService systemUserService;
	
	@EJB
	StudentService studentService;
	
	@EJB
	SystemUserGroupService systemUserGroupService;

	private List<Student> studentList;
	private Student newStudent;
	private Student selectedStudent;
	
	@PostConstruct
	public void postConstruct() {
		refreshStudentList();
	}
	
	
	public void refreshStudentList() {
		studentList = studentService.getAllStudents();
	}

	public void beforeCreateNewStudent() {
		newStudent = new Student();
	}

	public void createNewStudent() {
		Student student = studentService.findByStudentId(newStudent.getStudentId());
		if(student != null){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID: " + newStudent.getUsername() + " Already Exist.", ""));
			return;
		}
		
		SystemUser systemUser = systemUserService.findByUsername(newStudent.getUsername());
		if(systemUser != null){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username: " + newStudent.getUsername() + " Already Exist.", ""));
			return;
		}
		newStudent.setPassword(Util.hashPassword(newStudent.getPassword()));
		newStudent = studentService.create(newStudent);
		studentList.add(newStudent);
		
		SystemUserGroup systemUserGroup = systemUserGroupService.find(newStudent.getUsername(), "student");
		if(systemUserGroup == null){
			systemUserGroup = new SystemUserGroup();
			systemUserGroupService.create(newStudent.getUsername(), "student");
		}
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Student: " + newStudent.getUsername() + " created successfully."));
	}

	public void updateSelectedStudent() {
		Student student = studentService.findByStudentId(selectedStudent.getStudentId());
		if(student != null && !student.getId().equals(selectedStudent.getId())){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID: " + selectedStudent.getUsername() + " Already Exist.", ""));
			return;
		}
		
		SystemUser systemUser = systemUserService.findByUsername(selectedStudent.getUsername());
		if(systemUser != null && !systemUser.getId().equals(selectedStudent.getId())){
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username: " + selectedStudent.getUsername() + " Already Exist.", ""));
			return;
		}
		studentService.update(selectedStudent);
		refreshStudentList();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Student: " + selectedStudent.getUsername() + " successfully updated."));
	}

	public void deleteSelectedStudent() {
		studentService.delete(selectedStudent);
		systemUserGroupService.delete(selectedStudent.getUsername(), "student");
		studentList.remove(selectedStudent);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Student: " + selectedStudent.getUsername() + " successfully deleted."));
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public Student getNewStudent() {
		return newStudent;
	}
	public void setNewStudent(Student newStudent) {
		this.newStudent = newStudent;
	}
	
	public Student getSelectedStudent() {
		return selectedStudent;
	}
	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

}
