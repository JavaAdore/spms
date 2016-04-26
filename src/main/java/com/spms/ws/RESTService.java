package com.spms.ws;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.spms.entity.Project;
import com.spms.entity.Student;
import com.spms.entity.Supervisor;
import com.spms.service.ProjectService;
import com.spms.service.StudentService;
import com.spms.service.SupervisorService;

@Stateless
@Path("/v0.1")
public class RESTService {
	
	@PersistenceContext(unitName = "database")
	EntityManager em;
	
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
			Project project1 = new Project();
			project1.setId(1);
			project1.setTitle("PROJECT_1 Title");
			project1.setDescription("PROJECT_1 Desciption");
			return Response.status(Status.OK).entity(project1).build();
//			return Response.status(Status.OK).entity(projectService.findBySupervisorId(supervisorid)).build();
		}
//		Project project1 = new Project();
//		project1.setId(1);
//		project1.setTitle("PROJECT_1 Title");
//		project1.setDescription("PROJECT_1 Desciption");
//		if(supervisorid.equals("all")){
//			List<Project> projects = new ArrayList<>();
//			Project project2 = new Project();
//			project2.setId(2);
//			project2.setTitle("PROJECT_2 Title");
//			project2.setDescription("PROJECT_2 Desciption");
//			projects.add(project1);
//			projects.add(project2);
//			return Response.status(Status.OK).entity(projects).build();
//		}
//		return Response.status(Status.OK).entity(project1).build();
	}
	
	@GET
	@Path("/supervisor/{studentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSupervisor(@PathParam("studentid") String studentid) {
		if(studentid.equals("all")){
			return Response.status(Status.OK).entity(em.createNamedQuery("Supervisor.findAll").getResultList()).build();
//			return Response.status(Status.OK).entity(supervisorService.getAllSupervisors()).build();
		} else{
			Supervisor supervisor1= new Supervisor();
			supervisor1.setId(1);supervisor1.setName("Supervisor test1");
			supervisor1.setEmail("supervisorEmailTest1@email.com");
			return Response.status(Status.OK).entity(supervisor1).build();
//			return Response.status(Status.OK).entity(supervisorService.getAllSupervisors()).build();
		}
//		Supervisor supervisor1= new Supervisor();
//		supervisor1.setId(1);supervisor1.setName("Supervisor test1");
//		supervisor1.setEmail("supervisorEmailTest1@email.com");
//		if(studentid.equals("all")){
//			List<Supervisor> supervisors = new ArrayList<>();
//			Supervisor supervisor2= new Supervisor();
//			supervisor2.setId(2);supervisor2.setName("Supervisor test2");
//			supervisor2.setEmail("supervisorEmailTest2@email.com");
//			supervisors.add(supervisor1);
//			supervisors.add(supervisor2);
//			return Response.status(Status.OK).entity(supervisors).build();
//		}
//		return Response.status(Status.OK).entity(supervisor1).build();
	}
	
	@GET
	@Path("/student/{supervisorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@PathParam("supervisorid") String supervisorid) {
		if(supervisorid.equals("all")){
			return Response.status(Status.OK).entity(studentService.getAllStudents()).build();
		} else{
			Student student1 = new Student();
			student1.setId(1);
			student1.setName("Student1 name test1");
			return Response.status(Status.OK).entity(student1).build();
		}
		
//		Student student1 = new Student();
//		student1.setId(1);
//		student1.setName("Student1 name test1");
//		if(supervisorid.equals("all")){
//			Student student2 = new Student();
//			student2.setId(2);
//			student2.setName("Student2 name test2");
//			List<Student> students = new ArrayList<>();
//			students.add(student1);
//			students.add(student2);
//			return Response.status(Status.OK).entity(students).build();
//		}
//		return Response.status(Status.OK).entity(student1).build();
	}

}
