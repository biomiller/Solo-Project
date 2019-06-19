package com.bae.persistence.repository;

public interface UserRepository {
	
	String getAllUsers();
	String getUser(int id);
	String createUser(String user);
	String deleteUser(int id);
	String updateUser(int id, String user);
	String createDeck(int id, String deck);
	String addEvent(int userId, int eventId);


}
