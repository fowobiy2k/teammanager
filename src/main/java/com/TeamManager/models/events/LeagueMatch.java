package com.TeamManager.models.events;

import javax.persistence.Entity;

@Entity
public class LeagueMatch extends Match {
	
	private String matchDay;

	public String getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(String matchDay) {
		this.matchDay = matchDay;
	}

}
