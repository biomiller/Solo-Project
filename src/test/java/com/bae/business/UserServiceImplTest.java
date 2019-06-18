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
import com.bae.persistence.domain.Event;
import com.bae.persistence.domain.User;
import com.bae.persistence.repository.UserDBRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserDBRepository repo;
	
	private JSONUtil util;
	


	@Before
	public void setup() {
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test 
	public void getAllUsersTest() {
    	
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);
		
		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event); 
		
		ArrayList<User> users = new ArrayList<User>();
		User user = new User("Owen","dummyemail@gmail.com","password",decks, events);
		users.add(user);
		
		String retrievedUsers = util.getJSONForObject(users);
		
		Mockito.when(repo.getAllUsers()).thenReturn(retrievedUsers);

		
		assertEquals(Constants.MOCK_USER_DATA_ARRAY, service.getAllUsers());
		
	}
	
	@Test
	public void testGetUser() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);
		
		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);
		
		User user = new User("Owen","dummyemail@gmail.com","password",decks, events);
		
		String retrievedUser = util.getJSONForObject(user);

		
		Mockito.when(repo.getUser(0)).thenReturn(retrievedUser);
		
		assertEquals(Constants.MOCK_USER_OBJECT, service.getUser(0));

	}
	
	@Test
	public void testCreateUser() {
		Mockito.when(repo.createUser(Constants.MOCK_USER_OBJECT)).thenReturn("{\"message\": \"User successfully added.\"}");
		assertEquals(service.createUser(Constants.MOCK_USER_OBJECT), "{\"message\": \"User successfully added.\"}");
	}
	
	@Test
	public void testDeleteUser() {
		Mockito.when(repo.deleteUser(0)).thenReturn("{\"message\": \"User deleted.\"}");
		assertEquals(service.deleteUser(0), "{\"message\": \"User deleted.\"}");
	}
	
	@Test
	public void testUpdateUser() {
		Set<Deck> decks = new HashSet<Deck>(); 
		Deck deck = new Deck();
		decks.add(deck);
		
		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);
		
		User compUser = new User("Dave","fakeemail@gmail.com","login",decks, events);
		
		Mockito.when(repo.updateUser(0, util.getJSONForObject(compUser))).thenReturn("{\"message\": \"User updated.\"}");

		assertEquals(service.updateUser(0,util.getJSONForObject(compUser)), "{\"message\": \"User updated.\"}");
		
	}
	
	@Test
	public void testCreateDeck() {
				
		Deck newDeck = new Deck();
		
		Mockito.when(repo.createDeck(0, util.getJSONForObject(newDeck))).thenReturn("{\"message\": \"Deck successfully added.\"}");

		assertEquals(service.createDeck(0, util.getJSONForObject(newDeck)), "{\"message\": \"Deck successfully added.\"}");
		
		
	}
	
	@Test
	public void testAddEvent() {
				
		Event newEvent = new Event();
		
		Mockito.when(repo.addEvent(0, util.getJSONForObject(newEvent))).thenReturn("{\"message\": \"Event successfully added to user.\"}");

		assertEquals(service.addEvent(0, util.getJSONForObject(newEvent)), "{\"message\": \"Event successfully added to user.\"}");
		
		
	}
}
