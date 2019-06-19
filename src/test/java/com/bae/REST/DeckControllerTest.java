package com.bae.REST;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.DeckServiceImpl;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)


public class DeckControllerTest {
	
	@InjectMocks
	private DeckController controller;

	@Mock
	private DeckServiceImpl service;


	@Before
	public void setup() {
	}

	@Test
	public void testGetAllDecks() {


		Mockito.when(service.getAllDecks()).thenReturn(Constants.MOCK_DECK_DATA_ARRAY);
		assertEquals(Constants.MOCK_DECK_DATA_ARRAY, controller.getAllDecks());

	}

	@Test
	public void testGetDeck() {


		Mockito.when(service.getDeck(0)).thenReturn(Constants.MOCK_DECK_OBJECT);
		assertEquals(Constants.MOCK_DECK_OBJECT, controller.getDeck(0));

	}
	
	@Test
	public void testDeleteDeck() {
		Mockito.when(service.deleteDeck(0)).thenReturn("{\"message\": \"Deck deleted.\"}");
		assertEquals(controller.deleteDeck(0), "{\"message\": \"Deck deleted.\"}");
	}
	
	@Test
	public void testUpdateDeck() {
				
		Mockito.when(service.updateDeck(0, Constants.MOCK_DECK_OBJECT)).thenReturn("{\"message\": \"Deck updated.\"}");

		assertEquals(controller.updateDeck(0,Constants.MOCK_DECK_OBJECT), "{\"message\": \"Deck updated.\"}");
		
	}

}
