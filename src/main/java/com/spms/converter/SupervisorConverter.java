package com.spms.converter;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.spms.entity.Supervisor;
import com.spms.service.SupervisorService;

@ManagedBean
@ApplicationScoped
public class SupervisorConverter implements Converter{

	
	@EJB
	private SupervisorService supervisorService;
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String supervisorId) {
		try
		{
			return supervisorService.findBySupervisorId(supervisorId);
		}catch(Exception ex)
		{
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try
		{
			return ((Supervisor)arg2).getSupervisorId();
		}catch(Exception ex)
		{
			return "";
		}
	}

}
