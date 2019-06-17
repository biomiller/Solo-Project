package com.bae.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(length = 50)
	private String name;
	@Column(length = 320, unique = true)
	private String email;
	@Column(length = 100)
	private String password;
	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private Set<Deck> decks = new HashSet<Deck>();


	// default constructor
	public User() {
		super();
	}
	
	

	public User(String name, String email, String password, Set<Deck> decks) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.decks = decks;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Deck> getDecks() {
		return decks;
	}

	public void setDecks(Set<Deck> decks) {
		this.decks = decks;
	}

}
