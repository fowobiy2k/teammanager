package com.TeamManager.models.utitlity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.TeamManager.models.Player;

@Entity
@Table(name= "tbl_bookings")
public class Booking {
	
	public enum Card {
		YELLOW, RED
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO )
	private long id;
	private Card cardIssued;
	private String timeElapsed;
	
	@OneToOne( cascade= CascadeType.ALL )
	private Player player;

	public Booking() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Card getCardIssued() {
		return cardIssued;
	}

	public void setCardIssued(Card cardIssued) {
		this.cardIssued = cardIssued;
	}

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Card[] getCards() {
		Card[] cards = {Card.YELLOW, Card.RED};
		
		return cards;
	}

}
