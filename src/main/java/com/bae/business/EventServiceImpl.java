package com.bae.business;

import javax.inject.Inject;

import com.bae.persistence.repository.EventDBRepository;

public class EventServiceImpl implements EventService {

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
		return eventRepo.deleteEvent(id);
	}

	@Override
	public String updateEvent(int id, String event) {
		return eventRepo.updateEvent(id, event);
	}

	public String createEvent(String event) {
		return eventRepo.createEvent(event);
	}


}
