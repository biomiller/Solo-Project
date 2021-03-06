package com.bae.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import com.bae.persistence.domain.Event;
import com.bae.persistence.domain.User;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)

public class UserDBRepositoryTest {

	@InjectMocks
	private UserDBRepository repo;

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
	public void testGetAllUsers() {

		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);

		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		ArrayList<User> users = new ArrayList<User>();
		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);
		users.add(user);

		Mockito.when(query.getResultList()).thenReturn(users);
		assertEquals(Constants.MOCK_USER_DATA_ARRAY, repo.getAllUsers());

	}

	@Test
	public void testGetUser() {

		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);

		
		Mockito.when(manager.find(User.class, 0)).thenReturn(user);

		assertEquals(Constants.MOCK_USER_OBJECT, repo.getUser(0));

	}
	
	@Test
	public void testGetUserByEmail() {

		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);

		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);
		
		String email = "dummyemail@gmail.com";
		
		Mockito.when(query.setParameter("email", email)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(user);

		assertEquals(Constants.MOCK_USER_OBJECT, repo.getUserByEmail(email));

	}

	@Test
	public void testCreateUser() {
		String reply = repo.createUser(Constants.MOCK_USER_OBJECT);
		assertEquals("{\"message\": \"User successfully added.\"}", reply);
	}

	@Test
	public void testDeleteUser() {
		Mockito.when(manager.contains(manager.find(User.class, 1))).thenReturn(true);
		String reply = repo.deleteUser(0);
		assertEquals("{\"message\": \"User deleted.\"}", reply);
	}

	@Test
	public void testDeleteUserNotExist() {
		Mockito.when(manager.contains(manager.find(User.class, 5))).thenReturn(false);
		String reply = repo.deleteUser(5);
		assertEquals("{\"message\": \"User not found.\"}", reply);
	}

	@Test
	public void testUpdateUser() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);
		
		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);

		User compUser = new User("Dave", "fakeemail@gmail.com", "login", decks, events);

		Mockito.when(manager.find(User.class, 1)).thenReturn(user);

		String reply = repo.updateUser(1, util.getJSONForObject(compUser));
		assertEquals("{\"message\": \"User updated.\"}", reply);

	}

	@Test
	public void testUpdateUserNotExist() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		User compUser = new User("Dave", "fakeemail@gmail.com", "login", decks, events);

		Mockito.when(manager.find(User.class, 2)).thenReturn(null);

		String reply = repo.updateUser(1, util.getJSONForObject(compUser));
		assertEquals("{\"message\": \"User not found.\"}", reply);

	}

	@Test
	public void testUpdateUserNullValues() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);
		User compUser = new User(null, null, null, null, null);

		Mockito.when(manager.find(User.class, 1)).thenReturn(user);

		String reply = repo.updateUser(1, util.getJSONForObject(compUser));
		assertEquals("{\"message\": \"User updated.\"}", reply);

	}

	@Test
	public void createDeckTest() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);

		Deck newDeck = new Deck();

		Mockito.when(manager.find(User.class, 1)).thenReturn(user);

		String reply = repo.createDeck(1, util.getJSONForObject(newDeck));
		assertEquals("{\"message\": \"Deck successfully added.\"}", reply);
	}
	
	@Test
	public void addEventTest() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);

		Event newEvent = new Event();


		Mockito.when(manager.find(User.class, 1)).thenReturn(user);
		Mockito.when(manager.find(Event.class, 1)).thenReturn(newEvent);


		String reply = repo.addEvent(1, 1);
		assertEquals("{\"message\": \"Event successfully added to user.\"}", reply);
	}
	
	@Test
	public void removeEventTest() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);

		Set<Event> events = new HashSet<Event>();
		Event event = new Event();
		events.add(event);

		User user = new User("Owen", "dummyemail@gmail.com", "password", decks, events);

		Event newEvent = new Event();


		Mockito.when(manager.find(User.class, 1)).thenReturn(user);
		Mockito.when(manager.find(Event.class, 1)).thenReturn(newEvent);


		String reply = repo.removeEvent(1, 1);
		assertEquals("{\"message\": \"Event successfully removed from user.\"}", reply);
	}

}
