package com.TeamManager.models.events;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class CupCompetition extends Competition {
	
	@ElementCollection
	private List<String> stages;
	
	private String highestStageReached;

	public List<String> getStages() {
		return stages;
	}

	public void setStages(List<String> stages) {
		this.stages = stages;
	}

	public String getHighestStageReached() {
		return highestStageReached;
	}

	public void setHighestStageReached(String highestStageReached) {
		this.highestStageReached = highestStageReached;
	}

}
