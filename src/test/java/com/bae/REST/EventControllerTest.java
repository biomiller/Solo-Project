package com.bae.REST;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bae.business.EventServiceImpl;
import com.bae.util.Constants;

@RunWith(MockitoJUnitRunner.class)

public class EventControllerTest {

	@InjectMocks
	private EventController controller;

	@Mock
	private EventServiceImpl service;

	@Before
	public void setup() {
	}

	@Test
	public void getAllEventsTest() {

		Mockito.when(service.getAllEvents()).thenReturn(Constants.MOCK_EVENT_DATA_ARRAY);

		assertEquals(Constants.MOCK_EVENT_DATA_ARRAY, controller.getAllEvents());

	}

	@Test
	public void getEventTest() {

		Mockito.when(service.getEvent(0)).thenReturn(Constants.MOCK_EVENT_OBJECT);

		assertEquals(Constants.MOCK_EVENT_OBJECT, controller.getEvent(0));

	}

	@Test
	public void createEventTest() {
		Mockito.when(service.createEvent(Constants.MOCK_EVENT_OBJECT)).thenReturn("{\"message\": \"Event successfully created.\"}");
		assertEquals(controller.createEvent(Constants.MOCK_EVENT_OBJECT),"{\"message\": \"Event successfully created.\"}");
	}

	@Test
	public void deleteEventTest() {
		Mockito.when(service.deleteEvent(0)).thenReturn("{\"message\": \"Event deleted.\"}");
		assertEquals(controller.deleteEvent(0), "{\"message\": \"Event deleted.\"}");
	}

	@Test
	public void updateEventTest() {

		Mockito.when(service.updateEvent(0, Constants.MOCK_EVENT_OBJECT))
				.thenReturn("{\"message\": \"Event updated.\"}");

		assertEquals(controller.updateEvent(0, Constants.MOCK_EVENT_OBJECT), "{\"message\": \"Event updated.\"}");
	}

}
