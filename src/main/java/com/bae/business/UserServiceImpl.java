package com.bae.business;
import javax.inject.Inject;

import com.bae.persistence.domain.User;
import com.bae.persistence.repository.UserDBRepository;
import com.bae.util.JSONUtil;

public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDBRepository userRepo;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllUsers() {
		return userRepo.getAllUsers();
	}

	@Override
	public String getUser(int id) {
		return userRepo.getUser(id);
	}
	
	@Override
	public String createUser(String user) {
		return userRepo.createUser(user);
	}

	@Override
	public String deleteUser(int id) {
		return userRepo.deleteUser(id);
	}

	@Override
	public String updateUser(int id, String user) {
		return userRepo.updateUser(id, user);
	}

	@Override
	public String createDeck(int id, String deck) {
		return userRepo.createDeck(id, deck);
	}

	@Override
	public String addEvent(int userId, int eventId) {
		return userRepo.addEvent(userId, eventId);
	}
	
	@Override
	public String removeEvent(int userId, int eventId) {
		return userRepo.removeEvent(userId, eventId);
	}

	@Override
	public String getUserByEmail(String email, String password) {
		
		String dbUserJSON = userRepo.getUserByEmail(email);
				
		User dbUser = util.getObjectForJSON(dbUserJSON, User.class);
		
		String dbUserPassword = dbUser.getPassword();
		
		if (dbUserPassword.equals(password)) {
			return dbUserJSON;
		}
		else{
			return "{\"message\": \"Password Incorrect\"}";
		}
	}
	
	public void setRepo(UserDBRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util; 
	}

}
