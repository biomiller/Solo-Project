package com.bae.REST;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.UserService;

@RunWith(MockitoJUnitRunner.class)

public class UserControllerTest {
	
	@InjectMocks
	private UserController controller;
	
	@Inject
	private UserService service;
	

	@Before
	public void setup() {
	}
	
	@Test
	public void getAllUsersTest() {
    			
	}

}
