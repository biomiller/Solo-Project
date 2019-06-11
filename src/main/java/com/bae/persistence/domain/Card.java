package com.bae.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cardId;
	private String name;
	private String mana_cost;
	private int cmc;
	private String type_line;
	@Column(length = 1000)
	private String oracle_text;
	@Column(nullable = true)
	private String loyalty;
	@Column(nullable = true)
	private String power;
	@Column(nullable = true)
	private String toughness;
	@Column(nullable = true)
	private char colours_001;
	@Column(nullable = true)
	private char colours_002;
	@Column(nullable = true)
	private char colours_003;
	private String set;
	private int collector_number;
	private String rarity;
	private String flavor_text;
	private String artist;
	

	
	public Card() {
		super();
	}
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMana_cost() {
		return mana_cost;
	}
	public void setMana_cost(String mana_cost) {
		this.mana_cost = mana_cost;
	}
	public int getCmc() {
		return cmc;
	}
	public void setCmc(int cmc) {
		this.cmc = cmc;
	}
	public String getType_line() {
		return type_line;
	}
	public void setType_line(String type_line) {
		this.type_line = type_line;
	}
	public String getOracle_text() {
		return oracle_text;
	}
	public void setOracle_text(String oracle_text) {
		this.oracle_text = oracle_text;
	}
	public int getLoyalty() {
		return loyalty;
	}
	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getToughness() {
		return toughness;
	}
	public void setToughness(int toughness) {
		this.toughness = toughness;
	}
	public char getColours_001() {
		return colours_001;
	}
	public void setColours_001(char colours_001) {
		this.colours_001 = colours_001;
	}
	public char getColours_002() {
		return colours_002;
	}
	public void setColours_002(char colours_002) {
		this.colours_002 = colours_002;
	}
	public char getColours_003() {
		return colours_003;
	}
	public void setColours_003(char colours_003) {
		this.colours_003 = colours_003;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public int getCollector_number() {
		return collector_number;
	}
	public void setCollector_number(int collector_number) {
		this.collector_number = collector_number;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public String getFlavour_text() {
		return flavor_text;
	}
	public void setFlavour_text(String flavour_text) {
		this.flavor_text = flavour_text;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	

}
