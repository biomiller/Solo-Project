package com.bae.business;
import javax.inject.Inject;

import com.bae.persistence.repository.UserDBRepository;

public class UserServiceImpl implements UserService{
	
	@Inject
	UserDBRepository userRepo;

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
	public String getUserByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}

}
