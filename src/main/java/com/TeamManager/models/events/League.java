package com.TeamManager.models.events;

import javax.persistence.Entity;

@Entity
public class League extends Competition {
	
	private int teamsCount;

	public int getTeamsCount() {
		return teamsCount;
	}

	public void setTeamsCount(int teamsCount) {
		this.teamsCount = teamsCount;
	}

}
