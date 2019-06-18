package com.bae.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.Deck;
import com.bae.persistence.domain.User;
import com.bae.persistence.repository.DeckDBRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)

public class DeckServiceImplTest {

	@InjectMocks
	private DeckServiceImpl service;

	@Mock
	private DeckDBRepository repo;

	private JSONUtil util;

	@Before
	public void setup() {
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllDecks() {

		ArrayList<Deck> decks = new ArrayList<Deck>();
		Deck deck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");
		decks.add(deck);

		String retrievedDecks = util.getJSONForObject(decks);

		Mockito.when(repo.getAllDecks()).thenReturn(retrievedDecks);
		assertEquals(Constants.MOCK_DECK_DATA_ARRAY, repo.getAllDecks());

	}

	@Test
	public void testGetDeck() {

		Deck deck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");

		String retrievedDeck = util.getJSONForObject(deck);

		Mockito.when(repo.getDeck(0)).thenReturn(retrievedDeck);
		assertEquals(Constants.MOCK_DECK_OBJECT, repo.getDeck(0));

	}
	
	@Test
	public void testDeleteDeck() {
		Mockito.when(repo.deleteDeck(0)).thenReturn("{\"message\": \"Deck deleted.\"}");
		assertEquals(service.deleteDeck(0), "{\"message\": \"Deck deleted.\"}");
	}
	
	@Test
	public void testUpdateDeck() {
		
		Deck compDeck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");
		
		Mockito.when(repo.updateDeck(0, util.getJSONForObject(compDeck))).thenReturn("{\"message\": \"Deck updated.\"}");

		assertEquals(service.updateDeck(0,util.getJSONForObject(compDeck)), "{\"message\": \"Deck updated.\"}");
		
	}
}
