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

@RunWith(MockitoJUnitRunner.class)

public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserDBRepository repo;
	
	
	@Before
	public void setup() {
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
}
