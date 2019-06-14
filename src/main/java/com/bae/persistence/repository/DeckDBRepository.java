package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Deck;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default

public class DeckDBRepository implements DeckRepository {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllDecks() {
		Query query = manager.createQuery("Select a FROM Deck a");
		Collection<Deck> decks = (Collection<Deck>) query.getResultList();
		return util.getJSONForObject(decks);
	}

	@Override
	public String getDeck(int id) {
		return util.getJSONForObject(manager.find(Deck.class, id));
	}
	
	@Transactional(REQUIRED)
	public String deleteDeck(int id) {
		if(manager.contains(manager.find(Deck.class, id))) {
			manager.remove(manager.find(Deck.class, id));
		}
		return "{\"message\": \"Deck deleted.\"}";
	}
	
	@Transactional(REQUIRED)
	public String updateDeck(int id, String deck) {
		Deck compDeck = util.getObjectForJSON(deck, Deck.class);
		Deck oldDeck = manager.find(Deck.class, id);
		
		if(oldDeck != null) {
			if(compDeck.getCards() != null) {
				oldDeck.setCards(compDeck.getCards());
			}
			if(compDeck.getName() != null) {
				oldDeck.setName(compDeck.getName());
			}
			if(compDeck.getFormat() != null) {
				oldDeck.setFormat(compDeck.getFormat());
			}
			
		}
		return "{\"message\": \"Deck updated.\"}";
	}



}
