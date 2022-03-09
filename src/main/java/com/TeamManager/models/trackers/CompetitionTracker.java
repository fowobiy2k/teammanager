package com.TeamManager.models.trackers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TeamManager.models.Expense;
import com.TeamManager.models.Player;
import com.TeamManager.models.events.Competition;
import com.TeamManager.models.events.Match;
import com.TeamManager.service.event.CompetitionService;
import com.TeamManager.service.event.MatchService;

public class CompetitionTracker {
	
	@Autowired
	CompetitionService competitionService;
	
	@Autowired
	MatchService matchService;
	
	private Competition competition;

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	public void closeCompetition() {
		
		if(!getCompetition().isClosed()) {
			getCompetition().setClosed(true);
			
			competitionService.createCompetition(getCompetition());
		}
		
	}
	
	public void openCompetition() {
		
		if(!getCompetition().isOpen()) {
			getCompetition().setOpen(true);
			
			competitionService.createCompetition(getCompetition());
		}
		
	}
	
	public Match rescheduleMatch(long matchId, String date, String time) {
//		Match match = matchService.findById(matchId);
//		match.setDate(date);
//		match.setTime(time);
		
		List<Match> matches = getCompetition().getMatches();
		
		for(Match m : matches) {
			if(m.getId() == matchId) {
				m.setDate(date);
				m.setTime(time);
				return matchService.createMatch(m);
			}
		}
		
		return null;
	}
	
	public boolean addExpense( Expense expense ) {
		boolean isSuccessful = getCompetition().getExpenses().add(expense);
		competitionService.createCompetition(getCompetition());
		
		return isSuccessful;
	}
	
	public boolean registerPlayer(Player player) {
		boolean registered = getCompetition().getRegisteredPlayers().add(player);
		competitionService.createCompetition(getCompetition());
		return registered;
	}
	
//	public void startMatch(long matchId) {
//		Match match = fetchMatch(matchId);
//	}
	
//	public int updatePoints(long matchId)
	
	private Match createMatchTracker(long matchId) {
//		MatchTracker tracker = new MatchTracker();
		List<Match> matches = getCompetition().getMatches();
		
		for(Match m : matches) {
			if(m.getId() == matchId) {
				return m;
			}
		}
		
		return null;
	}

}
