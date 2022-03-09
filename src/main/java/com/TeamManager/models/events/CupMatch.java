package com.TeamManager.models.events;

import javax.persistence.Entity;

@Entity
public class CupMatch extends Match {
	
	private String stage;

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
