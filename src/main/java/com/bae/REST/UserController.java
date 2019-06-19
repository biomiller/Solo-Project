package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.UserServiceImpl;

@Path("Users")
public class UserController {

	@Inject
	private UserServiceImpl service;
	
	@Path("getAllUsers")
	@GET
	@Produces({ "application/JSON" })
	public String getAllUsers() {
		return service.getAllUsers();
	}
	
	@Path("getUser/{id}")
	@GET
	@Produces({ "application/JSON" })
	public String getUser(@PathParam("id") int id) {
		return service.getUser(id);
	}
	
	@Path("/createUser")
	@POST
	@Produces({"application/json"})
	public String createUser(String user) {
		return service.createUser(user);
	}
	
	@Path("deleteUser/{id}")
	@DELETE
	@Produces({ "application/JSON" })	
	public String deleteUser(@PathParam("id") int id) {
		return service.deleteUser(id);
	}
	
	@Path("/createDeck/{id}")
	@POST
	@Produces({"application/json"})
	public String createDeck(@PathParam("id") int id, String deck) {
		return service.createDeck(id, deck);
	}
	
	@Path("/addEvent/{id}")
	@POST
	@Produces({"application/json"})
	public String addEvent(@PathParam("id") int userId, int eventId) {
		return service.addEvent(userId, eventId);
	}
	
	@Path("/updateUser/{id}")
	@PUT
	@Produces({"application/json"})
	public String updateUser(@PathParam("id") int id, String user) {
		return service.updateUser(id, user);
	}
	
}
