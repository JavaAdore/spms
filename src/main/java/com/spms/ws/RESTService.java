package com.spms.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.spms.entity.Project;

@WebService
@Path("/v0.1")
public class RESTService {

	@PostConstruct
	public void postConstruct() {
		
	}
	
	@GET
	@Path("/project/{supervisorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoi(@PathParam("supervisorid") String supervisorid) {
		Project project = new Project();
		return Response.status(Status.OK).entity(project).build();
	}
}
