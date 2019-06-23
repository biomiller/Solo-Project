package com.bae.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.persistence.domain.Event;
import com.bae.util.Constants;
import com.bae.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)

public class EventDBRepositoryTest {

	@InjectMocks
	private EventDBRepository repo;

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
	public void testGetAllEvents() {

		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);

		ArrayList<Event> events = new ArrayList<Event>();
		Event event = new Event("M19 Prerelease", "limited", "Rogue Gaming", "2018-07-09");

		events.add(event);

		Mockito.when(query.getResultList()).thenReturn(events);
		assertEquals(Constants.MOCK_EVENT_DATA_ARRAY, repo.getAllEvents());

	}
	
	@Test
	public void testGetEvent() {

		Event event = new Event("M19 Prerelease", "limited", "Rogue Gaming", "2018-07-09");

		Mockito.when(manager.find(Event.class, 0)).thenReturn(event);

		assertEquals(Constants.MOCK_EVENT_OBJECT, repo.getEvent(0));

	}
	
	@Test
	public void testCreateEvent() {
		String reply = repo.createEvent(Constants.MOCK_EVENT_OBJECT);
		assertEquals("{\"message\": \"Event successfully created.\"}", reply);
	}
	
	@Test
	public void testDeleteEvent() {
		Mockito.when(manager.contains(manager.find(Event.class, 1))).thenReturn(true);
		String reply = repo.deleteEvent(0);
		assertEquals("{\"message\": \"Event deleted.\"}", reply);
	}
	
	@Test
	public void testDeleteEventNotExist() {
		Mockito.when(manager.contains(manager.find(Event.class, 5))).thenReturn(false);
		String reply = repo.deleteEvent(5);
		assertEquals("{\"message\": \"Event not found.\"}", reply);
	}
	
	@Test
	public void testUpdateUser() {
		
		Event event = new Event("M19 Prerelease", "limited", "Rogue Gaming", "2018-07-09");

		Event compEvent = new Event("WAR Draft", "Sealed", "Dark Sphere", "2018-07-11");

		Mockito.when(manager.find(Event.class, 1)).thenReturn(event);

		String reply = repo.updateEvent(1, util.getJSONForObject(compEvent));
		assertEquals("{\"message\": \"Event updated.\"}", reply);

	}
	
	@Test
	public void testUpdateUserNotExist() {
		
		Event compEvent = new Event("WAR Draft", "Sealed", "Dark Sphere", "2018-07-11");

		Mockito.when(manager.find(Event.class, 6)).thenReturn(null);

		String reply = repo.updateEvent(1, util.getJSONForObject(compEvent));
		assertEquals("{\"message\": \"Event not found.\"}", reply);

	}
	
	@Test
	public void testUpdateUserNullValues() {
		
		Event event = new Event("M19 Prerelease", "limited", "Rogue Gaming", "2018-07-09");

		Event compEvent = new Event(null, null, null, null);

		Mockito.when(manager.find(Event.class, 1)).thenReturn(event);

		String reply = repo.updateEvent(1, util.getJSONForObject(compEvent));
		assertEquals("{\"message\": \"Event updated.\"}", reply);

	}
}
