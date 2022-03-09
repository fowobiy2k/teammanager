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
@Table( name= "tbl_substitutions")
public class Substitution {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO )
	private long id;
	private String timeElapsed;
	
	@OneToOne( cascade= CascadeType.ALL )
	private Player out;
	
	@OneToOne( cascade= CascadeType.ALL )
	private Player in;

	public Substitution() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public Player getOut() {
		return out;
	}

	public void setOut(Player out) {
		this.out = out;
	}

	public Player getIn() {
		return in;
	}

	public void setIn(Player in) {
		this.in = in;
	}

}
