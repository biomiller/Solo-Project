package com.bae.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.Event;
import com.bae.persistence.repository.EventDBRepository;
import com.bae.persistence.repository.UserDBRepository;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)

public class EventServiceImplTest {
	
	@InjectMocks
	private EventServiceImpl service;

	@Mock
	private EventDBRepository repo;
	

	@Before
	public void setup() {
	}
	
	@Test
	public void getAllEventsTest() {
		
		
		Mockito.when(repo.getAllEvents()).thenReturn(Constants.MOCK_EVENT_DATA_ARRAY);
		
		assertEquals(Constants.MOCK_EVENT_DATA_ARRAY, service.getAllEvents());

	}
	
	@Test
	public void getEventTest() {
		
		
		Mockito.when(repo.getEvent(0)).thenReturn(Constants.MOCK_EVENT_OBJECT);
		
		assertEquals(Constants.MOCK_EVENT_OBJECT, service.getEvent(0));

	}
	
	@Test
	public void createEventTest() {
		Mockito.when(repo.createEvent(Constants.MOCK_EVENT_OBJECT)).thenReturn("{\"message\": \"Event successfully created.\"}");
		assertEquals(service.createEvent(Constants.MOCK_EVENT_OBJECT), "{\"message\": \"Event successfully created.\"}");
	}
	
	@Test
	public void deleteEventTest() {
		Mockito.when(repo.deleteEvent(0)).thenReturn("{\"message\": \"Event deleted.\"}");
		assertEquals(service.deleteEvent(0), "{\"message\": \"Event deleted.\"}");
	}
	
	@Test
	public void updateEventTest() {
						
		Mockito.when(repo.updateEvent(0, Constants.MOCK_EVENT_OBJECT)).thenReturn("{\"message\": \"Event updated.\"}");
	
		assertEquals(service.updateEvent(0, Constants.MOCK_EVENT_OBJECT), "{\"message\": \"Event updated.\"}");
	}
	

}
