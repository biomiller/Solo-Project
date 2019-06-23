package com.bae.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


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
		service.setUtil(util);
	}

	@Test  
	public void getAllUsersTest() {
    	
		 
		Mockito.when(repo.getAllUsers()).thenReturn(Constants.MOCK_USER_DATA_ARRAY);

		
		assertEquals(Constants.MOCK_USER_DATA_ARRAY, service.getAllUsers());
		
	}
	
	@Test 
	public void testGetUser() {


		Mockito.when(repo.getUser(0)).thenReturn(Constants.MOCK_USER_OBJECT);
		
		assertEquals(Constants.MOCK_USER_OBJECT, service.getUser(0));

	}
	
	@Test
	public void testGetUserByEmail() {

		Mockito.when(repo.getUserByEmail("dummyemail@gmail.com")).thenReturn(Constants.MOCK_USER_OBJECT);
				
		assertEquals(Constants.MOCK_USER_OBJECT, service.getUserByEmail("dummyemail@gmail.com", "password"));

	}
	
	@Test
	public void testCreateUser() {
		Mockito.when(repo.createUser(Constants.MOCK_USER_OBJECT)).thenReturn("{\"message\": \"User successfully added.\"}");
		assertEquals("{\"message\": \"User successfully added.\"}", service.createUser(Constants.MOCK_USER_OBJECT));
	}
	
	@Test
	public void testDeleteUser() {
		Mockito.when(repo.deleteUser(0)).thenReturn("{\"message\": \"User deleted.\"}");
		assertEquals("{\"message\": \"User deleted.\"}", service.deleteUser(0));
	}
	
	@Test
	public void testUpdateUser() {
		
		Mockito.when(repo.updateUser(0, Constants.MOCK_USER_OBJECT)).thenReturn("{\"message\": \"User updated.\"}");

		assertEquals("{\"message\": \"User updated.\"}", service.updateUser(0,Constants.MOCK_USER_OBJECT));
		
	}
	
	@Test
	public void testCreateDeck() {
				

		Mockito.when(repo.createDeck(0, Constants.MOCK_DECK_OBJECT)).thenReturn("{\"message\": \"Deck successfully added.\"}");

		assertEquals("{\"message\": \"Deck successfully added.\"}", service.createDeck(0, Constants.MOCK_DECK_OBJECT));
		
		
	}
	
	@Test
	public void testAddEvent() {
						
		Mockito.when(repo.addEvent(0, 1)).thenReturn("{\"message\": \"Event successfully added to user.\"}");

		assertEquals("{\"message\": \"Event successfully added to user.\"}", service.addEvent(0, 1));
		
		
	}
	
	@Test
	public void testRemoveEvent() {
						
		Mockito.when(repo.removeEvent(0, 1)).thenReturn("{\"message\": \"Event successfully removed from user.\"}");

		assertEquals("{\"message\": \"Event successfully removed from user.\"}", service.removeEvent(0, 1));
		
		
	}
}
