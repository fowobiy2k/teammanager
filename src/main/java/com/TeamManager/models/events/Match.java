package com.TeamManager.models.events;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.TeamManager.models.Expense;
import com.TeamManager.models.Player;
import com.TeamManager.models.goals.Goal;
import com.TeamManager.models.utitlity.Booking;
import com.TeamManager.models.utitlity.Substitution;

@Entity
@Table( name= "tbl_matches")
@Inheritance( strategy= InheritanceType.SINGLE_TABLE)
public class Match implements EventInterface {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO )
	private long id;
	private String date;
	private String venue;
	private String opponent;
	private String time;
	private boolean isHomeMatch;
	private int goalsConceded;
//	private int foulsCommitted;
//	private int foulsWon;
	private int yellowCards;
	private int oppYellowCards;
	private int cornerKicks;
	private int oppCornerKicks;
	private int freeKicks;
	private int oppFreeKicks;
	private int throwIns;
	private int oppThrowIns;
	private int penalties;
	private int oppPenalties;
	private int redCards;
	private int oppRedCards;
	private int shotOnTarget;
	private int oppShotOnTarget;
	private int oppBookings;
	private boolean isEnded;
	private boolean isStarted;
	private boolean atHalfTime;
	private boolean inSecondHalf;
	private boolean cancelled;
	private String[] halfTimeScore;

	@OneToMany( cascade= CascadeType.ALL )
	@JoinTable( name= "tbl_match_sub" )
	private List<Substitution> substitutions;
	
	@OneToMany( cascade= CascadeType.ALL )
	@JoinTable( name= "tbl_match_goals" )
	private List<Goal> homeGoals;
	
	@OneToMany( cascade= CascadeType.ALL )
	@JoinTable( name= "tbl_match_lineup" )
	private List<Player> lineup;
	

	@OneToMany( cascade= CascadeType.ALL )
	@JoinTable( name= "tbl_match_expenses" )
	private List<Expense> expenses;
	
	@OneToMany( cascade= CascadeType.ALL )
	@JoinTable( name= "tbl_match_bookings" )
	private List<Booking> bookings;

	public Match() {
		
	}

		
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getVenue() {
		return venue;
	}


	public void setVenue(String venue) {
		this.venue = venue;
	}


	public String getOpponent() {
		return opponent;
	}


	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public boolean isHomeMatch() {
		return isHomeMatch;
	}


	public void setHomeMatch(boolean isHomeMatch) {
		this.isHomeMatch = isHomeMatch;
	}


	public int getGoalsConceded() {
		return goalsConceded;
	}


	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}
	
	
	public int getYellowCards() {
		return yellowCards;
	}


	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}


	public int getOppYellowCards() {
		return oppYellowCards;
	}


	public void setOppYellowCards(int oppYellowCards) {
		this.oppYellowCards = oppYellowCards;
	}


	public int getCornerKicks() {
		return cornerKicks;
	}


	public void setCornerKicks(int cornerKicks) {
		this.cornerKicks = cornerKicks;
	}


	public int getOppCornerKicks() {
		return oppCornerKicks;
	}


	public void setOppCornerKicks(int oppCornerKicks) {
		this.oppCornerKicks = oppCornerKicks;
	}


	public int getFreeKicks() {
		return freeKicks;
	}


	public void setFreeKicks(int freeKicks) {
		this.freeKicks = freeKicks;
	}


	public int getOppFreeKicks() {
		return oppFreeKicks;
	}


	public void setOppFreeKicks(int oppFreeKicks) {
		this.oppFreeKicks = oppFreeKicks;
	}


	public int getThrowIns() {
		return throwIns;
	}


	public void setThrowIns(int throwIns) {
		this.throwIns = throwIns;
	}


	public int getOppThrowIns() {
		return oppThrowIns;
	}


	public void setOppThrowIns(int oppThrowIns) {
		this.oppThrowIns = oppThrowIns;
	}


	public int getPenalties() {
		return penalties;
	}


	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}


	public int getOppPenalties() {
		return oppPenalties;
	}


	public void setOppPenalties(int oppPenalties) {
		this.oppPenalties = oppPenalties;
	}


	public int getRedCards() {
		return redCards;
	}


	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}


	public int getOppRedCards() {
		return oppRedCards;
	}


	public void setOppRedCards(int oppRedCards) {
		this.oppRedCards = oppRedCards;
	}


	public int getShotOnTarget() {
		return shotOnTarget;
	}


	public void setShotOnTarget(int shotOnTarget) {
		this.shotOnTarget = shotOnTarget;
	}


	public int getOppShotOnTarget() {
		return oppShotOnTarget;
	}


	public void setOppShotOnTarget(int oppShotOnTarget) {
		this.oppShotOnTarget = oppShotOnTarget;
	}


	public int getOppBookings() {
		return oppBookings;
	}


	public void setOppBookings(int oppBookings) {
		this.oppBookings = oppBookings;
	}


	public boolean isEnded() {
		return isEnded;
	}


	public void setEnded(boolean isEnded) {
		this.isEnded = isEnded;
	}


	public boolean isStarted() {
		return isStarted;
	}


	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}


	public List<Substitution> getSubstitutions() {
		return substitutions;
	}


	public void setSubstitutions(List<Substitution> substitutions) {
		this.substitutions = substitutions;
	}


	public List<Goal> getHomeGoals() {
		return homeGoals;
	}


	public void setHomeGoals(List<Goal> homeGoals) {
		this.homeGoals = homeGoals;
	}


	public List<Player> getLineup() {
		return lineup;
	}


	public void setLineup(List<Player> lineup) {
		this.lineup = lineup;
	}


	public List<Expense> getExpenses() {
		return expenses;
	}


	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}


	public boolean isAtHalfTime() {
		return atHalfTime;
	}

	public void setAtHalfTime(boolean atHalfTime) {
		this.atHalfTime = atHalfTime;
	}
	

	public boolean isInSecondHalf() {
		return inSecondHalf;
	}


	public void setInSecondHalf(boolean inSecondHalf) {
		this.inSecondHalf = inSecondHalf;
	}


	public List<Booking> getBookings() {
		return bookings;
	}
	
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public String[] getHalfTimeScore() {
		return halfTimeScore;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}


	public void setHalfTimeScore(String[] halfTimeScore) {
		this.halfTimeScore = halfTimeScore;
	}


	public int countDownToEvent() {
		return 0;
	}
	
	public String fetchScores() {
		
		String output = "";
		
		if( isHomeMatch() ) {
			output = "Pioneers F.A.  " + getHomeGoals().size() + " - " + getGoalsConceded() + "  " + getOpponent();
		} else output = getOpponent() + "  " + getGoalsConceded() + " - " + getHomeGoals().size()  + "  Pioneers F.A.";
		
		return output;
	}
	
}
