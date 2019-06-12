package com.bae.persistence.repository;

public interface UserRepository {
	
	String getAllUsers();
	String createUser(String user);
	String deleteUser(int id);
	String updateUser(int id, String user);
	String getUser(int id);

}
