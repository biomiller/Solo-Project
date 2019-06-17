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
	@Transactional(REQUIRED)
	public String addUser(int id, String user) {
		User newUser = util.getObjectForJSON(user, User.class);
		manager.find(Event.class, id).getUsers().add(newUser);
		manager.persist(newUser);
		return "{\"message\": \"User successfully added to event.\"}";
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}
