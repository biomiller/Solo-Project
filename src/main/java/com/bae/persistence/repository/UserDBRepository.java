package com.bae.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.bae.persistence.domain.User;
import com.bae.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class UserDBRepository implements UserRepository{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;
	

	@Override
	public String getAllUsers() {
		Query query = manager.createQuery("Select u FROM User u");
		
		Collection<User> users = (Collection<User>) query.getResultList();

		return util.getJSONForObject(users);
	}
	
	@Override
	public String getUser(int id) {
		return util.getJSONForObject(manager.find(User.class, id));
	}

	@Override
	public String createUser(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUser(int id, String account) {
		// TODO Auto-generated method stub
		return null;
	}


}
