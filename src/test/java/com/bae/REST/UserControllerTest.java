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
	public void testGetUserByEmail() {


		Mockito.when(service.getUserByEmail("dummyemail@gmail.com")).thenReturn(Constants.MOCK_USER_OBJECT);
		
		assertEquals(Constants.MOCK_USER_OBJECT, controller.getUserByEmail("dummyemail@gmail.com"));

	}
	
	@Test
	public void testCreateUser() {
		Mockito.when(service.createUser(Constants.MOCK_USER_OBJECT)).thenReturn("{\"message\": \"User successfully added.\"}");
		assertEquals("{\"message\": \"User successfully added.\"}", controller.createUser(Constants.MOCK_USER_OBJECT));
	}
	
	@Test
	public void testDeleteUser() {
		Mockito.when(service.deleteUser(0)).thenReturn("{\"message\": \"User deleted.\"}");
		assertEquals("{\"message\": \"User deleted.\"}", controller.deleteUser(0));
	}
	
	@Test
	public void testUpdateUser() {
		
		Mockito.when(service.updateUser(0, Constants.MOCK_USER_OBJECT)).thenReturn("{\"message\": \"User updated.\"}");

		assertEquals("{\"message\": \"User updated.\"}", controller.updateUser(0,Constants.MOCK_USER_OBJECT));
		
	}
	
	@Test
	public void testCreateDeck() {
				

		Mockito.when(service.createDeck(0, Constants.MOCK_DECK_OBJECT)).thenReturn("{\"message\": \"Deck successfully added.\"}");

		assertEquals("{\"message\": \"Deck successfully added.\"}", controller.createDeck(0, Constants.MOCK_DECK_OBJECT));
		
		
	}
	
	@Test
	public void testAddEvent() {
						
		Mockito.when(service.addEvent(0, 1)).thenReturn("{\"message\": \"Event successfully added to user.\"}");

		assertEquals("{\"message\": \"Event successfully added to user.\"}", controller.addEvent(0, 1));
		
		
	}
	
	@Test
	public void testRemoveEvent() {
						
		Mockito.when(service.removeEvent(0, 1)).thenReturn("{\"message\": \"Event successfully removed from user.\"}");

		assertEquals("{\"message\": \"Event successfully removed from user.\"}", controller.removeEvent(0, 1));
		
		
	}

}
