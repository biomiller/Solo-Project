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
public class UserDBRepository implements UserRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllUsers() {
		Query query = manager.createQuery("Select a FROM User a");

		Collection<User> users = (Collection<User>) query.getResultList();

		return util.getJSONForObject(users);
	}

	@Override
	public String getUser(int id) {
		return util.getJSONForObject(manager.find(User.class, id));
	}

	@Override
	@Transactional(REQUIRED)
	public String createUser(String user) {
		User newUser = util.getObjectForJSON(user, User.class);
		manager.persist(newUser);
		return "{\"message\": \"User successfully added.\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteUser(int id) {
		if (manager.contains(manager.find(User.class, id))) {
			manager.remove(manager.find(User.class, id));
		return "{\"message\": \"User deleted.\"}";
		}
		else {
			return "{\"message\": \"User not found.\"}";
		}
	}

	@Override
	@Transactional(REQUIRED)
	public String updateUser(int id, String user) {
		User compUser = util.getObjectForJSON(user, User.class);
		User oldUser = manager.find(User.class, id);

		if (oldUser != null) {
			if (compUser.getName() != null) {
				oldUser.setName(compUser.getName());
			}
			if (compUser.getPassword() != null) {
				oldUser.setPassword(compUser.getPassword());
			}
			if (compUser.getEmail() != null) {
				oldUser.setEmail(compUser.getEmail());
			}

			manager.persist(oldUser);
			return "{\"message\": \"User updated.\"}";

		}
		else {
			return "{\"message\": \"User not found.\"}";
		}
	}

	@Override
	@Transactional(REQUIRED)
	public String createDeck(int id, String deck) {
		Deck newDeck = util.getObjectForJSON(deck, Deck.class);
		manager.find(User.class, id).getDecks().add(newDeck);
		manager.persist(newDeck);
		return "{\"message\": \"Deck successfully added.\"}";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String addEvent(int id, String event) {
		Event newEvent = util.getObjectForJSON(event, Event.class); 
		User user = manager.find(User.class, id);
		user.getEvents().add(newEvent);
		manager.persist(user);
		return "{\"message\": \"Event successfully added to user.\"}";
	} 
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util; 
	}

}
