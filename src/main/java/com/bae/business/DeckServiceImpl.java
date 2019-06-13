package com.bae.business;

import javax.inject.Inject;

import com.bae.persistence.repository.DeckDBRepository;

public class DeckServiceImpl implements DeckService {

	@Inject
	DeckDBRepository deckRepo;
	
	@Override
	public String getAllDecks() {
		return deckRepo.getAllDecks();
	}
	
	@Override
	public String getDeck(int id) {
		return deckRepo.getDeck(id);
	}

	@Override
	public String createDeck(String deck) {
		return deckRepo.createDeck(deck);
	}

	@Override
	public String deleteDeck(int id) {
		return deckRepo.deleteDeck(id);
	}

	@Override
	public String updateDeck(int id, String deck) {
		return deckRepo.updateDeck(id, deck);
	}

}
