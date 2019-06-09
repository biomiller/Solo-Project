package com.bae.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deckID;
	@Column(length = 50)
	private String Name;
/*	@Column(length = 320)
	private int userId;*/
	@Column(length = 100)
	private String format;

	// default constructor
	public Deck() {
		super();
	}

}
