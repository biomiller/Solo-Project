package com.bae.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deckId;
	@Column(length = 50)
	private String Name;
	@Column(length = 100)
	private String format;
	@ManyToMany
	@JoinTable(name = "DeckCard",
		joinColumns = {@JoinColumn (name = "deckId")}, 
		inverseJoinColumns = {@JoinColumn(name = "cardId")}
	) 
    private Set<Card> cards = new HashSet<Card>();
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;


	// default constructor
	public Deck() {
		super();
	}

	public int getDeckId() {
		return deckId;
	}

	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
