package com.TeamManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TeamManager.models.Expense;
import com.TeamManager.models.Payment;
import com.TeamManager.models.Player;
import com.TeamManager.models.events.Competition;
import com.TeamManager.models.events.Match;
import com.TeamManager.models.goals.Goal;
import com.TeamManager.models.utitlity.Booking;
import com.TeamManager.models.utitlity.Substitution;
import com.TeamManager.service.ExpenseService;
import com.TeamManager.service.PaymentService;
import com.TeamManager.service.PlayerService;
import com.TeamManager.service.event.CompetitionService;
import com.TeamManager.service.event.MatchService;
import com.TeamManager.service.goal.GoalService;

@RestController
@RequestMapping("/pfa")
public class CoreController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private CompetitionService competitionService;
	
	@Autowired
	private GoalService goalService;
	
	@PostMapping("/addexpense")
	public Expense addExpense(@RequestBody Expense expense) {
		return expenseService.recordExpense(expense);
	}
	
	@GetMapping("/allexpenses")
	public List<Expense> getAllExpenses(){
		return expenseService.getAllExpenses();
	}
	
	@PostMapping("/addpayment")
	public Payment addPayment(@RequestBody Payment payment) {
		return paymentService.recordPayment(payment);
	}
	
	@GetMapping("/allpayments")
	public List<Payment> getAllPayments(){
		return paymentService.getAllPayments();
	}
	
	@PostMapping("/addplayer")
	public Player addPlayer(@RequestBody Player player) {
		return playerService.createPlayer(player);
	}
	
	@GetMapping("/allplayers")
	public List<Player> getAllPlayers(){
		return playerService.getAllPlayers();
	}
	
	@PostMapping("/addmatch")
	public Match addMatch(@RequestBody Match match) {
		return matchService.createMatch(match);
	}
	
	@GetMapping("/allmatches")
	public List<Match> getAllMatches(){
		return matchService.getAllMatches();
	}
	
	@PostMapping("/addcompetition")
	public Competition addCompetition(@RequestBody Competition competition) {
		return competitionService.createCompetition(competition);
	}
	
	@GetMapping("/allcompetitions")
	public List<Competition> getAllCompetitions(){
		return competitionService.getAllCompetitions();
	}
	
	@PutMapping("/startmatch/{matchId}")
	public void startMatch(@PathVariable long matchId) {
		matchService.startMatch(matchId);
	}
	
	@PutMapping("/endmatch/{matchId}")
	public void endMatch(@PathVariable long matchId) {
		matchService.endMatch(matchId);
	}
	
	@PutMapping("/updateLineup/{matchId}/{playerId}")
	public void updateLineup(@PathVariable long matchId, @PathVariable long playerId) {
		matchService.addToLineup(matchId, playerId);
	}
	
	@GetMapping("/matchlineup/{matchId}")
	public List<Player> getmatchLineup(@PathVariable long matchId){
		return matchService.getMatchLineup(matchId);
	}
	
	@PutMapping("/makeSubstitution/{matchId}/{playerIn}/{playerOut}/{time}")
	public boolean makeSubstitution(@PathVariable long matchId, @PathVariable long playerIn, @PathVariable long playerOut, @PathVariable String time) {
		return matchService.makeSubstitution(matchId, playerOut, playerIn, time);
	}
	
	@GetMapping("/substitutionlist/{matchId}")
	public List<Substitution> getListOfSubstitutions(@PathVariable long matchId){
		return matchService.getSubstitutionList(matchId);
	}
	
	@PutMapping("/recordGoalScored")
	public boolean recordGoalScored(@RequestParam long matchId, @RequestParam long scorerId, @RequestParam long assistantId, @RequestParam String timeElapsed) {
		return matchService.recordGoalScored(matchId, scorerId, assistantId, timeElapsed);
	}
	
	@PutMapping("/recordgoalconceded/{matchId}")
	public void recordGoalConceded(@PathVariable long matchId) {
		matchService.recordGoalConceded(matchId);
	}
	
	@GetMapping("/getmatchgoals/{matchId}")
	public List<Goal> getMatchGoals(@PathVariable long matchId){
		return matchService.findById(matchId).getHomeGoals();
	}
	
	@GetMapping("/allgoals")
	public List<Goal> getAllGoals(){
		return goalService.getAllGoals();
	}
	
	@PutMapping("/starthalftime/{matchId}")
	public void startHalfTime(@PathVariable long matchId) {
		matchService.startHalfTime(matchId);
	}
	
	@PutMapping("/endhalftime/{matchId}")
	public void endHalfTime(@PathVariable long matchId) {
		matchService.endHalfTime(matchId);
	}
	
	@PutMapping("/cancelmatch/{matchId}")
	public void cancelMatch(@PathVariable long matchId) {
		matchService.cancelMatch(matchId);
	}
	
	@PutMapping("/recordbooking")
	public void recordBooking(@RequestParam long matchId, @RequestParam long playerId, String timeElapsed, int cardType) {
		matchService.recordBooking(matchId, playerId, timeElapsed, cardType);
	}
	
	@GetMapping("/matchbookings/{matchId}")
	public List<Booking> getMatchBookings(@PathVariable long matchId){
		return matchService.getMatchBooking(matchId);
	}
	
	@PutMapping("/recordfreekick/{matchId}")
	public void recordFreekick(@PathVariable long matchId) {
		matchService.addFreekicks(matchId);
	}
	
	@PutMapping("/recordoppfreekick/{matchId}")
	public void recordOppFreekick(@PathVariable long matchId) {
		matchService.addOppFreekicks(matchId);
	}

}
