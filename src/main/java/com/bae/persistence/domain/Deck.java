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
/*	@ManyToOne (cascade = CascadeType.ALL)
	private User user;*/
/*    @OneToMany(cascade=CascadeType.ALL, mappedBy="deck") 
	private Set<DeckCard> cards = new HashSet<DeckCard>();*/


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


/*	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}*/


/*	public Set<DeckCard> getCards() {
		return cards;
	}


	public void setCards(Set<DeckCard> cards) {
		this.cards = cards;
	}
*/



}
