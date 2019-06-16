/*package com.bae.persistence.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class DeckCard {
	
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deckCardnumber;
    @ManyToOne(cascade=CascadeType.PERSIST) 
    private Card card;
    @OneToOne(cascade=CascadeType.PERSIST) 
    private Deck deck;
    private int quantity;
	
    
    
    
    
    public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public int getDeckCardnumber() {
		return deckCardnumber;
	}
	public void setDeckCardnumber(int deckCardnumber) {
		this.deckCardnumber = deckCardnumber;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    
    
    
}
*/