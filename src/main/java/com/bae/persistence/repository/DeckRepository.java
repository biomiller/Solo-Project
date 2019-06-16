package com.bae.persistence.repository;

public interface DeckRepository {
	
	String getAllDecks();
	String deleteDeck(int id);
	String updateDeck(int id, String deck);
	String getDeck(int id);

}
