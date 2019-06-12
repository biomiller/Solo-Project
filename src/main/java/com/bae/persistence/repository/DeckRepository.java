package com.bae.persistence.repository;

public interface DeckRepository {
	
	String getAllDecks();
	String createDeck(String account);
	String deleteDeck(int id);
	String updateDeck(int id, String account);
	String getDeck(int id);

}
