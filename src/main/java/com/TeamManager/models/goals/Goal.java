package com.TeamManager.models.goals;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.TeamManager.models.Player;

@Entity
@Table( name= "tbl_goals")
@Inheritance( strategy= InheritanceType.SINGLE_TABLE)
public class Goal {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO )
	private long id;
	
	@ManyToOne( cascade= CascadeType.ALL )
	private Player scorer;
	
	@ManyToOne( cascade= CascadeType.ALL )
	private Player assist;
	
	private String timeElapsed;

	public Goal() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Player getScorer() {
		return scorer;
	}

	public void setScorer(Player scorer) {
		this.scorer = scorer;
	}

	public Player getAssist() {
		return assist;
	}

	public void setAssist(Player assist) {
		this.assist = assist;
	}

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	

}
