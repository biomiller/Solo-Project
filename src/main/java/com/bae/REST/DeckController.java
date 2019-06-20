package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.DeckServiceImpl;

@Path("Decks")
public class DeckController {
	
	@Inject
	private DeckServiceImpl service;
	
	@Path("/getAllDecks")
	@GET
	@Produces({ "application/JSON" })
	public String getAllDecks() {
		return service.getAllDecks();
	}
	
	
	@Path("/getDeck/{id}")
	@GET
	@Produces({ "application/JSON" })
	public String getDeck(@PathParam("id") int id) {
		return service.getDeck(id);
	}
	
	@Path("/deleteDeck/{id}")
	@DELETE
	@Produces({ "application/JSON" })
	public String deleteDeck(@PathParam("id") int id) {
		return service.deleteDeck(id);
	}
	
	@Path("/updateDeck/{id}")
	@PUT
	@Produces({"application/json"})
	public String updateDeck(@PathParam("id") int id, String deck) {
		return service.updateDeck(id, deck);
	}

	
}
