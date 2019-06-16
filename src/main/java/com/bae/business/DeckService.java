package com.bae.business;

public interface DeckService {
	
	String getAllDecks();
	String deleteDeck(int id);
	String updateDeck(int id, String deck);
	String getDeck(int id);
}
