package com.TeamManager.service.trackers;

import java.util.List;

//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.TeamManager.models.Expense;
import com.TeamManager.models.Player;
import com.TeamManager.models.events.Match;
import com.TeamManager.models.goals.Goal;
import com.TeamManager.models.utitlity.Booking;
import com.TeamManager.models.utitlity.Substitution;
import com.TeamManager.service.ExpenseService;
import com.TeamManager.service.PlayerService;
import com.TeamManager.service.event.MatchService;
import com.TeamManager.service.goal.GoalService;
import com.TeamManager.service.utility.BookingService;
import com.TeamManager.service.utility.SubstitutionService;


public class MatchTracker {
	
	@Autowired
	SubstitutionService substitutionService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	GoalService goalService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	ExpenseService expenseService;
	
//	@Id
//	@GeneratedValue(strategy= GenerationType.AUTO )
//	private long id;
	
//	@OneToOne( cascade= CascadeType.ALL )
	private Match match;
	
		
	public MatchTracker() {
		
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	
	
	public String fetchScores() {
		
		return getMatch().fetchScores();
	}
	
	public void startMatch() {
		getMatch().setStarted(true);
	}
	
	public void endMatch() {
		
		getMatch().setEnded(true);
		
	}
	
	public List<Substitution> makeSubstitution(long out, long in, String time) {
		Player playerIn = playerService.findById(in);
		Player playerOut = playerService.findById(out);
		
		Substitution sub = new Substitution();
		sub.setIn(playerIn);
		sub.setOut(playerOut);
		sub.setTimeElapsed(time);
		
		
		getMatch().getSubstitutions().add(substitutionService.recordSubstitution(sub));
		
		matchService.createMatch(getMatch());
		
		return getMatch().getSubstitutions();
	}
	
	public List<Goal> recordGoalScored(long scorerId, long assistantId, String timeElapsed) {
		Player scorer = playerService.findById(scorerId);
		Player assistant = playerService.findById(assistantId);
		
		Goal goal = new Goal();
		goal.setScorer(scorer);
		goal.setAssist(assistant);
		goal.setTimeElapsed(timeElapsed);
		
		
		getMatch().getHomeGoals().add(goalService.recordGoal(goal));
		
		return getMatch().getHomeGoals();
		
	}
	
	public int recordGoalConceded() {
		int output = getMatch().getGoalsConceded() + 1;
		getMatch().setGoalsConceded(output);
		
		matchService.createMatch(getMatch());
		
		return output;
	}
	
	public void startHalfTimeBreak() {
		getMatch().setAtHalfTime(true);
		
		matchService.createMatch(getMatch());
	}
	
	public boolean recordBooking(Booking booking) {
		return getMatch().getBookings().add(bookingService.recordBooking(booking));
	}
	
	public int totalFoulWon() {
		return getMatch().getFreeKicks() + getMatch().getPenalties();
	}
	
	public int totalFoulCommitted() {
		return getMatch().getOppFreeKicks() + getMatch().getOppPenalties();
	}
	
	public void addHomeFreeKick() {
		getMatch().setFreeKicks(getMatch().getFreeKicks() + 1);
		matchService.createMatch(getMatch());
	}
	
	public void addOpponentFreeKick() {
		getMatch().setOppFreeKicks(getMatch().getOppFreeKicks() + 1);
		matchService.createMatch(getMatch());
	}
	
	public void addHomeCornerKick() {
		getMatch().setCornerKicks(getMatch().getCornerKicks() + 1);
		matchService.createMatch(getMatch());
	}
	
	public void addOpponentCornerKick() {
		getMatch().setOppCornerKicks(getMatch().getOppCornerKicks() + 1);
		matchService.createMatch(getMatch());
	}
	
	public void addHomeThrowIn() {
		getMatch().setThrowIns(getMatch().getThrowIns() + 1);
		matchService.createMatch(getMatch());
	}
	
	public void addOpponentThrowIn() {
		getMatch().setOppThrowIns(getMatch().getOppThrowIns() + 1);
		matchService.createMatch(getMatch());
	}
	
	public void addHomePenalty() {
		getMatch().setPenalties(getMatch().getPenalties() + 1);
		matchService.createMatch(getMatch());
	}
	
	public void addOpponentPenalty() {
		getMatch().setOppPenalties(getMatch().getOppPenalties() + 1);
		matchService.createMatch(getMatch());
	}
	
	public boolean addToLineup(long playerId) {
		boolean added = getMatch().getLineup().add(playerService.findById(playerId));
		matchService.createMatch(getMatch());
		return added;
	}
	
	public boolean addExpense(Expense expense) {
		boolean added = getMatch().getExpenses().add(expenseService.recordExpense(expense));
		matchService.createMatch(getMatch());
		return added;
	}

}
