package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.EventService;

@Path("Events")
public class EventController {
	
	@Inject
	private EventService service;
	
	@Path("getAllEvents")
	@GET
	@Produces({ "application/JSON" })
	public String getAllEvents() {
		return service.getAllEvents();
	}

	@Path("getEvent/{id}")
	@GET
	@Produces({ "application/JSON" })
	public String getUser(@PathParam("id") int id) {
		return service.getEvent(id);
	}
	
	@Path("/addUser/{id}")
	@POST
	@Produces({"application/json"})
	public String addUser(@PathParam("id") int id, String user) {
		return service.addUser(id, user);
	}
}
