package com.bae.business;

public interface EventService {
	
	String getAllEvents();
	String getEvent(int id);
	String deleteEvent(int id);
	String updateEvent(int id, String event);
	String addUser(int id, String user);

}
