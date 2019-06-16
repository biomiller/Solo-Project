package com.bae.business;

public interface UserService {
	
	String getAllUsers();
	String createUser(String user);
	String deleteUser(int id);
	String updateUser(int id, String user);
	String getUser(int id);
	String createDeck(int id, String deck);

}
