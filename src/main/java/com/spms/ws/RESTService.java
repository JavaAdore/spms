package com.spms.ws;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.spms.entity.Student;
import com.spms.entity.Supervisor;
import com.spms.service.ProjectService;
import com.spms.service.StudentService;
import com.spms.service.SupervisorService;

@Stateless
@Path("/v0.1")
public class RESTService {
	
//	@PersistenceContext(unitName = "database")
//	EntityManager em;
	
	@EJB
	ProjectService projectService;
	
	@EJB
	SupervisorService supervisorService;
	
	@EJB
	StudentService studentService;
	
	@GET
	@Path("/project/{supervisorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(@PathParam("supervisorid") String supervisorid) {
		if(supervisorid.equals("all")){
			return Response.status(Status.OK).entity(projectService.getAllProjects()).build();
		} else{
			return Response.status(Status.OK).entity(projectService.findBySupervisorId(supervisorid)).build();
		}
	}
	
	@GET
	@Path("/supervisor/{studentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSupervisor(@PathParam("studentid") String studentid) {
		if(studentid.equals("all")){
//			return Response.status(Status.OK).entity(em.createNamedQuery("Supervisor.findAll").getResultList()).build();
			return Response.status(Status.OK).entity(supervisorService.getAllSupervisors()).build();
		} else{
			return Response.status(Status.OK).entity(supervisorService.findSupervisorByStudentId(studentid)).build();
		}
	}
	
	@GET
	@Path("/student/{supervisorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@PathParam("supervisorid") String supervisorid) {
		if(supervisorid.equals("all")){
			return Response.status(Status.OK).entity(studentService.getAllStudents()).build();
		} else{
			return Response.status(Status.OK).entity(studentService.findBySupervisorId(supervisorid)).build();
		}
	}

}
