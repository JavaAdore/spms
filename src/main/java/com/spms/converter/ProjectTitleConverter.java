package com.spms.converter;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.spms.entity.ProjectTitle;
import com.spms.entity.ProjectTopic;
import com.spms.entity.StudentProjectStatus;
import com.spms.entity.Supervisor;
import com.spms.service.ProjectService;
import com.spms.service.SupervisorService;

@ManagedBean
@ApplicationScoped
public class ProjectTitleConverter implements Converter{

	
	@EJB
	private ProjectService projectService;
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String studentProjectStatus) {
		try
		{
			return projectService.findProjectTopic(new Integer(studentProjectStatus));
		}catch(Exception ex)
		{
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try
		{
			return ((ProjectTopic)arg2).getId()+"";
		}catch(Exception ex)
		{
			return "";
		}
	}

}
