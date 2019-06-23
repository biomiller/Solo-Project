package com.bae.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.Deck;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)

public class DeckDBRepositoryTest {

	@InjectMocks
	private DeckDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllDecks() {

		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);

		ArrayList<Deck> decks = new ArrayList<Deck>();
		Deck deck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");
		decks.add(deck);

		Mockito.when(query.getResultList()).thenReturn(decks);
		assertEquals(Constants.MOCK_DECK_DATA_ARRAY, repo.getAllDecks());

	}

	@Test
	public void testGetDeck() {

		Deck deck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");

		Mockito.when(manager.find(Deck.class, 0)).thenReturn(deck);

		assertEquals(Constants.MOCK_DECK_OBJECT, repo.getDeck(0));

	}

	@Test
	public void testDeleteDeck() {
		Mockito.when(manager.contains(manager.find(Deck.class, 0))).thenReturn(true);
		String reply = repo.deleteDeck(0);
		assertEquals("{\"message\": \"Deck deleted.\"}", reply);
	}

	@Test
	public void testDeleteDeckNotExist() {
		Mockito.when(manager.contains(manager.find(Deck.class, 1))).thenReturn(false);
		String reply = repo.deleteDeck(1);
		assertEquals("{\"message\": \"Deck not found.\"}", reply);
	}

	@Test
	public void testUpdateDeck() {

		ArrayList<Deck> decks = new ArrayList<Deck>();
		Deck deck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");
		decks.add(deck);

		Deck compDeck = new Deck("testDeck2", "M16", "4 Lightning Bolt, 4 Lava Spike, 4 Goblin Guide, 48 Mountains");

		Mockito.when(manager.find(Deck.class, 0)).thenReturn(deck);

		String reply = repo.updateDeck(0, util.getJSONForObject(compDeck));
		assertEquals("{\"message\": \"Deck updated.\"}", reply);

	}

	@Test
	public void testUpdateDeckNotExist() {

		ArrayList<Deck> decks = new ArrayList<Deck>();
		Deck deck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");
		decks.add(deck);

		Deck compDeck = new Deck("testDeck2", "M16", "4 Lightning Bolt, 4 Lava Spike, 4 Goblin Guide, 48 Mountains");

		Mockito.when(manager.find(Deck.class, 1)).thenReturn(null);

		String reply = repo.updateDeck(1, util.getJSONForObject(compDeck));
		assertEquals("{\"message\": \"Deck not found.\"}", reply);

	}

	@Test
	public void testUpdateDeckNullValues() {

		ArrayList<Deck> decks = new ArrayList<Deck>();
		Deck deck = new Deck("testDeck1", "M19", "4 Lightning Bolt, 4 Lava Spike, 52 Mountains");
		decks.add(deck);

		Deck compDeck = new Deck(null, null, null);

		Mockito.when(manager.find(Deck.class, 0)).thenReturn(deck);

		String reply = repo.updateDeck(0, util.getJSONForObject(compDeck));
		assertEquals("{\"message\": \"Deck updated.\"}", reply);

	}

}
