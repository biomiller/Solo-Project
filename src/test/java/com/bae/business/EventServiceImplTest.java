package com.bae.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.repository.EventDBRepository;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)

public class EventServiceImplTest {
	
	@InjectMocks
	private EventServiceImpl service;

	@Mock
	private EventDBRepository repo;
	

	
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
		assertEquals("{\"message\": \"Event successfully created.\"}", service.createEvent(Constants.MOCK_EVENT_OBJECT));
	}
	
	@Test
	public void deleteEventTest() {
		Mockito.when(repo.deleteEvent(0)).thenReturn("{\"message\": \"Event deleted.\"}");
		assertEquals("{\"message\": \"Event deleted.\"}", service.deleteEvent(0));
	}
	
	@Test
	public void updateEventTest() {
						
		Mockito.when(repo.updateEvent(0, Constants.MOCK_EVENT_OBJECT)).thenReturn("{\"message\": \"Event updated.\"}");
	
		assertEquals("{\"message\": \"Event updated.\"}", service.updateEvent(0, Constants.MOCK_EVENT_OBJECT));
	}
	

}
