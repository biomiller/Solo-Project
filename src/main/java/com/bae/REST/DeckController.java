package com.bae.REST;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.bae.business.DeckService;

@Path("Decks")
public class DeckController {
	
	@Inject
	private DeckService service;
	
	@Path("getAllDecks")
	@GET
	@Produces({ "application/JSON" })
	public String getAllDecks() {
		return service.getAllDecks();
	}
	
	
	@Path("getDeck")
	@GET
	@Produces({ "application/JSON" })
	public String getDeck(@PathParam("id") int id) {
		return service.getDeck(id);
	}
	
	@Path("/createDeck")
	@POST
	@Produces({"application/json"})
	public String createDeck(String deck) {
		return service.createDeck(deck);
	}
}
