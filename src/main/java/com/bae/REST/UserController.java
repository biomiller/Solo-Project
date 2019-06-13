package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.UserService;

@Path("Users")
public class UserController {

	@Inject
	private UserService service;
	
	@Path("getAllUsers")
	@GET
	@Produces({ "application/JSON" })
	public String getAllUsers() {
		return service.getAllUsers();
	}
	
	@Path("getUser")
	@GET
	@Produces({ "application/JSON" })
	public String getUser(@PathParam("id") int id) {
		return service.getUser(id);
	}
	
	@Path("/createUser")
	@POST
	@Produces({"application/json"})
	public String addUser(String user) {
		return service.createUser(user);
	}
	
}
