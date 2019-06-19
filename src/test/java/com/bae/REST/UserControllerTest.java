package com.bae.REST;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.UserServiceImpl;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)

public class UserControllerTest {
	
	@InjectMocks
	private UserController controller;
	
	@Mock
	private UserServiceImpl service;
	

	@Before
	public void setup() {
	}
	
	@Test 
	public void getAllUsersTest() {
    	
		
		Mockito.when(service.getAllUsers()).thenReturn(Constants.MOCK_USER_DATA_ARRAY);

		
		assertEquals(Constants.MOCK_USER_DATA_ARRAY, controller.getAllUsers());
		
	}
	
	@Test
	public void testGetUser() {


		Mockito.when(service.getUser(0)).thenReturn(Constants.MOCK_USER_OBJECT);
		
		assertEquals(Constants.MOCK_USER_OBJECT, controller.getUser(0));

	}
	
	@Test
	public void testCreateUser() {
		Mockito.when(service.createUser(Constants.MOCK_USER_OBJECT)).thenReturn("{\"message\": \"User successfully added.\"}");
		assertEquals(controller.createUser(Constants.MOCK_USER_OBJECT), "{\"message\": \"User successfully added.\"}");
	}
	
	@Test
	public void testDeleteUser() {
		Mockito.when(service.deleteUser(0)).thenReturn("{\"message\": \"User deleted.\"}");
		assertEquals(controller.deleteUser(0), "{\"message\": \"User deleted.\"}");
	}
	
	@Test
	public void testUpdateUser() {
		
		Mockito.when(service.updateUser(0, Constants.MOCK_USER_OBJECT)).thenReturn("{\"message\": \"User updated.\"}");

		assertEquals(controller.updateUser(0,Constants.MOCK_USER_OBJECT), "{\"message\": \"User updated.\"}");
		
	}
	
	@Test
	public void testCreateDeck() {
				

		Mockito.when(service.createDeck(0, Constants.MOCK_DECK_OBJECT)).thenReturn("{\"message\": \"Deck successfully added.\"}");

		assertEquals(controller.createDeck(0, Constants.MOCK_DECK_OBJECT), "{\"message\": \"Deck successfully added.\"}");
		
		
	}
	
	@Test
	public void testAddEvent() {
						
		Mockito.when(service.addEvent(0, Constants.MOCK_EVENT_OBJECT)).thenReturn("{\"message\": \"Event successfully added to user.\"}");

		assertEquals(controller.addEvent(0, Constants.MOCK_EVENT_OBJECT), "{\"message\": \"Event successfully added to user.\"}");
		
		
	}

}
