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
	
	private JSONUtil util;
	


	@Before
	public void setup() {
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	@Test
	public void getAllEventsTest() {
		
		ArrayList<Event> events = new ArrayList<Event>();
		Event event = new Event("M19 Prerelease", "limited", "Rogue Gaming", "2018-07-09");

		events.add(event);
		
		String retrievedEvents = util.getJSONForObject(events);
		
		Mockito.when(repo.getAllEvents()).thenReturn(retrievedEvents);
		
		assertEquals(Constants.MOCK_EVENT_DATA_ARRAY, service.getAllEvents());

	}
	
	@Test
	public void getEventTest() {
		
		Event event = new Event("M19 Prerelease", "limited", "Rogue Gaming", "2018-07-09");
		
		String retrievedEvent = util.getJSONForObject(event);
		
		Mockito.when(repo.getEvent(0)).thenReturn(retrievedEvent);
		
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
		Event compEvent = new Event("M19 Prerelease", "limited", "Rogue Gaming", "2018-07-09");
				
		Mockito.when(repo.updateEvent(0, util.getJSONForObject(compEvent))).thenReturn("{\"message\": \"Event updated.\"}");
	
		assertEquals(service.updateEvent(0, util.getJSONForObject(compEvent)), "{\"message\": \"Event updated.\"}");
	}
	

}
