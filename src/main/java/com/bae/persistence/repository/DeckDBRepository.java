package com.bae.persistence.repository;

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
	public String createDeck(String deck) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDeck(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateDeck(int id, String deck) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeck(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
