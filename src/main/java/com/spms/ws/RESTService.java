package com.spms.ws;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.spms.entity.Project;


@Path("/v0.1")
public class RESTService {

	
	
	@GET
	@Path("/project/{supervisorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoi(@PathParam("supervisorid") String supervisorid) {
		Project project = new Project();
		project.setId(1);
		project.setDescription("SPMS PROJECT");
		project.setTitle("SPMS");
		return Response.status(Status.OK).entity(project).build();
	}
}
