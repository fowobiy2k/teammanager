package com.TeamManager.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.Expense;
import com.TeamManager.models.Player;
import com.TeamManager.models.events.Match;
import com.TeamManager.models.goals.Goal;
import com.TeamManager.models.utitlity.Booking;
import com.TeamManager.models.utitlity.Substitution;
import com.TeamManager.repository.event.MatchRepository;
import com.TeamManager.service.ExpenseService;
import com.TeamManager.service.PlayerService;
import com.TeamManager.service.goal.GoalService;
import com.TeamManager.service.utility.BookingService;
import com.TeamManager.service.utility.SubstitutionService;

@Service
public class MatchService {
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	SubstitutionService substitutionService;
	
//	@Autowired
//	MatchService matchService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	GoalService goalService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	ExpenseService expenseService;
	
	
	public Match createMatch(Match match) {
		return matchRepository.save(match);
	}
	
	public Match findById(Long id) {
		return matchRepository.getById(id);
	}
	
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}
	
	
	public void startMatch(long id) {
		MatchTracker matchTracker = new MatchTracker();
		Match match = findById(id);
		matchTracker.setMatch(match);
		matchTracker.startMatch();
	}
	
	public void endMatch(long id) {
		MatchTracker matchTracker = createMatchTracker(id);
		matchTracker.endMatch();
	}
	
	public boolean addToLineup(long matchId, long playerId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		return matchTracker.addToLineup(playerId);
	}
	
	public List<Player> getMatchLineup(long matchId){
		MatchTracker matchTracker = createMatchTracker(matchId);
		return matchTracker.getMatchLineup();
	}
	
	public boolean makeSubstitution(long matchId, long out, long in, String time) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		return matchTracker.makeSubstitution(out, in, time);
	}
	
	public List<Substitution> getSubstitutionList(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		return matchTracker.getMatch().getSubstitutions();
	}
	
	public boolean recordGoalScored(long matchId, long scorerId, long assistantId, String timeElapsed) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		return matchTracker.recordGoalScored(scorerId, assistantId, timeElapsed);
	}
	
	public void recordGoalConceded(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		matchTracker.recordGoalConceded();
	}
	
	public void startHalfTime(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		matchTracker.startHalfTimeBreak();
	}
	
	public void endHalfTime(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		matchTracker.endHalfTimeBreak();
	}
	
	public void cancelMatch(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		matchTracker.cancelMatch();
	}
	
	public boolean recordBooking(long matchId, long playerId, String timeElapsed, int cardType ) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		
		 
		return matchTracker.recordBooking(playerId, timeElapsed, cardType);
	}
	
	public List<Booking> getMatchBooking(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		
		 
		return matchTracker.getMatchBookings(matchId);
	}
	
	public void addFreekicks(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		matchTracker.addFreeKick();
	}
	
	public void addOppFreekicks(long matchId) {
		MatchTracker matchTracker = createMatchTracker(matchId);
		matchTracker.addOpponentFreeKick();
	}
	
	private MatchTracker createMatchTracker(long matchId) {
		MatchTracker matchTracker = new MatchTracker();
		Match match = findById(matchId);
		matchTracker.setMatch(match);
		
		return matchTracker;
	}
	
	

private class MatchTracker {
	

	private Match match;
	
		
	public MatchTracker() {
		
	}

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
		Match match = getMatch();
		match.setStarted(true);
		matchRepository.save(match);
	}
	
	public void endMatch() {
		
		Match match = getMatch();
		match.setInSecondHalf(false);
		match.setEnded(true);
		matchRepository.save(match);
		
	}
	
	public boolean makeSubstitution(long out, long in, String time) {
		Player playerIn = playerService.findById(in);
		Player playerOut = playerService.findById(out);
		
		Substitution sub = new Substitution();
		sub.setIn(playerIn);
		sub.setOut(playerOut);
		sub.setTimeElapsed(time);
		
		Match match = getMatch();
		boolean isSuccessful = match.getSubstitutions().add(substitutionService.recordSubstitution(sub));
		createMatch(match);
		
		return isSuccessful;
	}
	
	public boolean recordGoalScored(long scorerId, long assistantId, String timeElapsed) {
		Player scorer = playerService.findById(scorerId);
		Player assistant = playerService.findById(assistantId);
		
		Goal goal = new Goal();
		goal.setScorer(scorer);
		goal.setAssist(assistant);
		goal.setTimeElapsed(timeElapsed);
		
		Match match = getMatch();
		boolean added = match.getHomeGoals().add(goalService.recordGoal(goal));
		createMatch(match);
		
		return added;
		
	}
	
	public void recordGoalConceded() {
		Match match = getMatch();
		match.setGoalsConceded(match.getGoalsConceded() + 1);
		
		createMatch(match);
		
	}
	
	public void startHalfTimeBreak() {
		Match match = getMatch();
		match.setHalfTimeScore(getHalfTimeScore());
		match.setAtHalfTime(true);
	
		createMatch(match);
	}
	
	public void endHalfTimeBreak() {
		Match match = getMatch();
		
		match.setAtHalfTime(false);
		match.setInSecondHalf(true);
	
		createMatch(match);
	}

	private String[] getHalfTimeScore() {
		if(getMatch().isHomeMatch()) {
			String homeScore = String.valueOf(getMatch().getHomeGoals().size());
			String awayScore = String.valueOf(getMatch().getGoalsConceded());
			
			String[] halfTimeScore = {homeScore, awayScore};
			
			return halfTimeScore;
		} else {
			String homeScore = String.valueOf(getMatch().getGoalsConceded());
			String awayScore = String.valueOf(getMatch().getHomeGoals().size());
			
			String[] halfTimeScore = {homeScore, awayScore};
			
			return halfTimeScore;
		}
	}
	
	public void cancelMatch() {
		Match match = getMatch();
		match.setCancelled(true);
		createMatch(match);
	}
	
	public boolean recordBooking(long playerId, String timeElapsed, int cardType) {
		Match match = getMatch();
		
		Booking booking = new Booking();
		booking.setPlayer(playerService.findById(playerId));
		booking.setTimeElapsed(timeElapsed);
		if(cardType == 1)
			booking.setCardIssued(booking.getCards()[0]);
		else booking.setCardIssued(booking.getCards()[1]);
		
		boolean recorded = match.getBookings().add(bookingService.recordBooking(booking));
		createMatch(match);
		
		return recorded;
	}
	
	public List<Booking> getMatchBookings(long matchId) {
		return getMatch().getBookings();
	}
	
	public void addFreeKick() {
		Match match = getMatch();
		match.setFreeKicks(match.getFreeKicks() + 1);
		createMatch(match);
	}
	
	public void addOpponentFreeKick() {
		Match match = getMatch();
		match.setOppFreeKicks(match.getOppFreeKicks() + 1);
		createMatch(match);
	}
	
//	public void addOpponentFreeKick() {
//		getMatch().setOppFreeKicks(getMatch().getOppFreeKicks() + 1);
//		matchService.createMatch(getMatch());
//	}
//	
//	public void addHomeCornerKick() {
//		getMatch().setCornerKicks(getMatch().getCornerKicks() + 1);
//		matchService.createMatch(getMatch());
//	}
//	
//	public void addOpponentCornerKick() {
//		getMatch().setOppCornerKicks(getMatch().getOppCornerKicks() + 1);
//		matchService.createMatch(getMatch());
//	}
//	
//	public void addHomeThrowIn() {
//		getMatch().setThrowIns(getMatch().getThrowIns() + 1);
//		matchService.createMatch(getMatch());
//	}
//	
//	public void addOpponentThrowIn() {
//		getMatch().setOppThrowIns(getMatch().getOppThrowIns() + 1);
//		matchService.createMatch(getMatch());
//	}
//	
//	public void addHomePenalty() {
//		getMatch().setPenalties(getMatch().getPenalties() + 1);
//		matchService.createMatch(getMatch());
//	}
//	
//	public void addOpponentPenalty() {
//		getMatch().setOppPenalties(getMatch().getOppPenalties() + 1);
//		matchService.createMatch(getMatch());
//	}
	
	public boolean addToLineup(long playerId) {
		Match match = getMatch();
		boolean added = match.getLineup().add(playerService.findById(playerId));
		createMatch(getMatch());
		return added;
	}
	
//	public boolean addExpense(Expense expense) {
//		boolean added = getMatch().getExpenses().add(expenseService.recordExpense(expense));
//		matchService.createMatch(getMatch());
//		return added;
//	}
	
	public List<Player> getMatchLineup(){
		return getMatch().getLineup();
	}

}


}
