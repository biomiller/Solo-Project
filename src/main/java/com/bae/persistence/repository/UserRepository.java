package com.bae.persistence.repository;

public interface UserRepository {
	
	String getAllUsers();
	String createUser(String account);
	String deleteUser(int id);
	String updateUser(int id, String account);
	String getUser(int id);

}
