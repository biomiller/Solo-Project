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


	@Before
	public void setup() {

	}

	@Test
	public void testGetAllDecks() {


		Mockito.when(repo.getAllDecks()).thenReturn(Constants.MOCK_DECK_DATA_ARRAY);
		assertEquals(Constants.MOCK_DECK_DATA_ARRAY, service.getAllDecks());

	}

	@Test
	public void testGetDeck() {


		Mockito.when(repo.getDeck(0)).thenReturn(Constants.MOCK_DECK_OBJECT);
		assertEquals(Constants.MOCK_DECK_OBJECT, service.getDeck(0));

	}
	
	@Test
	public void testDeleteDeck() {
		Mockito.when(repo.deleteDeck(0)).thenReturn("{\"message\": \"Deck deleted.\"}");
		assertEquals("{\"message\": \"Deck deleted.\"}", service.deleteDeck(0));
	}
	
	@Test
	public void testUpdateDeck() {
				
		Mockito.when(repo.updateDeck(0, Constants.MOCK_DECK_OBJECT)).thenReturn("{\"message\": \"Deck updated.\"}");

		assertEquals("{\"message\": \"Deck updated.\"}", service.updateDeck(0,Constants.MOCK_DECK_OBJECT));
		
	}
}
