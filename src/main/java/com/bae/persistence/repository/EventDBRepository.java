package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.Deck;
import com.bae.persistence.domain.Event;
import com.bae.persistence.domain.User;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class EventDBRepository implements EventRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllEvents() {
		Query query = manager.createQuery("Select a FROM Event a");
		Collection<Event> events = (Collection<Event>) query.getResultList();
		return util.getJSONForObject(events);
	}

	@Override
	public String getEvent(int id) {
		return util.getJSONForObject(manager.find(Event.class, id));

	}
	
	@Override
	@Transactional(REQUIRED)
	public String createEvent(String event) {
		Event newEvent = util.getObjectForJSON(event, Event.class); 
		manager.persist(newEvent);
		return "{\"message\": \"Event successfully created.\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteEvent(int id) {
		if (manager.contains(manager.find(Event.class, id))) {
			manager.remove(manager.find(Event.class, id));
			return "{\"message\": \"Event deleted.\"}";
		} else {
			return "{\"message\": \"Event not found.\"}";
		}
	}

	@Override
	@Transactional(REQUIRED)
	public String updateEvent(int id, String event) {
		Event compEvent = util.getObjectForJSON(event, Event.class);
		Event oldEvent = manager.find(Event.class, id);

		if (oldEvent != null) {
			if (compEvent.getEventDate() != null) {
				oldEvent.setEventDate(compEvent.getEventDate());
			}
			if (compEvent.getName() != null) {
				oldEvent.setName(compEvent.getName());
			}
			if (compEvent.getFormat() != null) {
				oldEvent.setFormat(compEvent.getFormat());
			}
			if (compEvent.getLocation() != null) {
				oldEvent.setLocation(compEvent.getLocation());
			}

			return "{\"message\": \"Event updated.\"}";
		} else {
			return "{\"message\": \"Event not found.\"}";
		}
	}



	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}
