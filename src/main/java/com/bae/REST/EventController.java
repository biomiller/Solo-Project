package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.EventServiceImpl;

@Path("Events")
public class EventController {
	
	@Inject
	private EventServiceImpl service;
	
	@Path("getAllEvents")
	@GET
	@Produces({ "application/JSON" })
	public String getAllEvents() {
		return service.getAllEvents(); 
	}

	@Path("getEvent/{id}")
	@GET
	@Produces({ "application/JSON" })
	public String getEvent(@PathParam("id") int id) {
		return service.getEvent(id);
	}
	
	
	@Path("/deleteEvent/{id}")
	@DELETE
	@Produces({"application/json"})
	public String deleteEvent(@PathParam("id") int id) {
		return service.deleteEvent(id);
	}
	
	@Path("/updateEvent/{id}")
	@PUT
	@Produces({"application/json"})
	public String updateEvent(@PathParam("id") int id, String event) {
		return service.updateEvent(id, event);
	}
	
	@Path("/createEvent")
	@PUT
	@Produces({"application/json"})
	public String createEvent(String event) {
		return service.createEvent(event);
	}
	
	
	
	
	
	
	
}
