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
		
		ArrayList<User> users = new ArrayList<User>();
		User user = new User("Owen","dummyemail@gmail.com","password",decks);
		users.add(user);
		
		Mockito.when(query.getResultList()).thenReturn(users);
		assertEquals(Constants.MOCK_USER_DATA_ARRAY, repo.getAllUsers());
		
	} 
	
	@Test
	public void testGetUser() {
		
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);
		
		User user = new User("Owen","dummyemail@gmail.com","password",decks);
		
		Mockito.when(manager.find(User.class,0)).thenReturn(user);
		
		assertEquals(Constants.MOCK_USER_OBJECT, repo.getUser(0));
		
	}
	
	
	@Test
	public void testCreateUser() {
		String reply = repo.createUser(Constants.MOCK_USER_OBJECT);
		assertEquals(reply, "{\"message\": \"User successfully added.\"}");
	}
	
	@Test
	public void testDeleteUser() {
		Mockito.when(manager.contains(manager.find(User.class,1))).thenReturn(true);
		String reply = repo.deleteUser(0);
		assertEquals(reply, "{\"message\": \"User deleted.\"}");
	}
	
	@Test
	public void testDeleteUserNotExist() {
		Mockito.when(manager.contains(manager.find(User.class,5))).thenReturn(false);
		String reply = repo.deleteUser(5);
		assertEquals(reply, "{\"message\": \"User not found.\"}");
	}
	
	@Test
	public void testUpdateUser() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);
		
		User user = new User("Owen","dummyemail@gmail.com","password",decks);
		User compUser = new User("Dave","fakeemail@gmail.com","login",decks);

		Mockito.when(manager.find(User.class, 1)).thenReturn(user);
			
		String reply = repo.updateUser(1,util.getJSONForObject(compUser));
		assertEquals(reply, "{\"message\": \"User updated.\"}");
		
	}
	
	@Test
	public void testUpdateUserNotExist() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);
		
		User user = new User("Owen","dummyemail@gmail.com","password",decks);
		User compUser = new User("Dave","fakeemail@gmail.com","login",decks);

		Mockito.when(manager.find(User.class, 2)).thenReturn(null);
			
		String reply = repo.updateUser(1,util.getJSONForObject(compUser));
		assertEquals(reply, "{\"message\": \"User not found.\"}");
		
	}
	
	@Test
	public void testUpdateUserNullValues() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);
		
		User user = new User("Owen","dummyemail@gmail.com","password",decks);
		User compUser = new User(null,null,null,null);

		Mockito.when(manager.find(User.class, 1)).thenReturn(user);
			
		String reply = repo.updateUser(1,util.getJSONForObject(compUser));
		assertEquals(reply, "{\"message\": \"User updated.\"}");
		
	}
	
	@Test
	public void createDeckTest() {
		Set<Deck> decks = new HashSet<Deck>();
		Deck deck = new Deck();
		decks.add(deck);
		User user = new User("Owen","dummyemail@gmail.com","password",decks);
		
		Deck newDeck = new Deck();
		
		Mockito.when(manager.find(User.class, 1)).thenReturn(user);
		
		String reply = repo.createDeck(1, util.getJSONForObject(newDeck));
		assertEquals(reply, "{\"message\": \"Deck successfully added.\"}");
	}
	
	
}
