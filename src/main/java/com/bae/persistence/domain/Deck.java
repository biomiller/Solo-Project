package com.bae.persistence.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deckId;
	@Column(length = 50)
	private String name;
	@Column(length = 100)
	private String format;
	@Column(length = 10000)
	private String cards;


	// default constructor
	public Deck() {
		super();
	}
	


	public Deck(String name, String format, String cards) {
		super();
		this.name = name;
		this.format = format;
		this.cards = cards;
	}



	public int getDeckId() {
		return deckId;
	}


	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public String getCards() {
		return cards;
	}


	public void setCards(String cards) {
		this.cards = cards;
	}
	

}
