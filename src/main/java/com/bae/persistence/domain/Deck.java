package com.bae.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deckId;
	@Column(length = 50)
	private String Name;
	@Column(length = 100)
	private String format;
	@ManyToOne (cascade = CascadeType.PERSIST)
	private User user;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="deck") 
	private Set<DeckCard> cards = new HashSet<DeckCard>();


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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<DeckCard> getCards() {
		return cards;
	}


	public void setCards(Set<DeckCard> cards) {
		this.cards = cards;
	}




}
