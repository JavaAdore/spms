package com.spms.converter;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.service.SupervisorService;

@ManagedBean
@ApplicationScoped
public class StudentProjectStatusConverter implements Converter{

	
	@EJB
	private SupervisorService supervisorService;
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String studentProjectStatus) {
		try
		{
			return supervisorService.findStudentProjectStatus(new Integer(studentProjectStatus));
		}catch(Exception ex)
		{
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try
		{
			return ((StudentProjectStatus)arg2).getId()+"";
		}catch(Exception ex)
		{
			return "";
		}
	}

}
