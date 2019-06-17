package com.bae.business;

import javax.inject.Inject;

import com.bae.persistence.repository.EventDBRepository;

public class EventServiceImpl implements EventService{
	
	@Inject
	EventDBRepository eventRepo;

	@Override
	public String getAllEvents() {
		return eventRepo.getAllEvents();
	}

	@Override
	public String getEvent(int id) {
		return eventRepo.getEvent(id);
	}

	@Override
	public String deleteEvent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateEvent(int id, String event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addUser(int id, String user) {
		return eventRepo.addUser(id, user);

	}

}
