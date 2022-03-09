package com.TeamManager.models.events;

import java.math.BigDecimal;
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

@Entity
@Table( name= "tbl_competitions")
@Inheritance( strategy= InheritanceType.SINGLE_TABLE)
public class Competition implements EventInterface {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO )
	private long id;
	private String title;
	private String startDate;
	private String endDate;
	private String venue;
	private BigDecimal yellowCardFine;
	private BigDecimal redCardFine;
	private int points;
	private int goalsFor;
	private int goalsAgainst;
	private int goalDifference;
	private boolean isClosed;
	private boolean isOpen;
	
	@OneToMany( cascade= CascadeType.ALL)
	@JoinTable( name= "tbl_comp_matches")
	private List<Match> matches;
	
	@OneToMany( cascade= CascadeType.ALL)
	@JoinTable( name= "tbl_comp_matches")
	private List<Player> registeredPlayers;
	
	@OneToMany( cascade= CascadeType.ALL)
	@JoinTable( name= "tbl_comp_expenses")
	private List<Expense> expenses;
	
	public Competition() {
		
	}

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getVenue() {
		return venue;
	}



	public void setVenue(String venue) {
		this.venue = venue;
	}



	public int getPoints() {
		return points;
	}



	public void setPoints(int points) {
		this.points = points;
	}



	public boolean isClosed() {
		return isClosed;
	}



	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}



	public boolean isOpen() {
		return isOpen;
	}



	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	



	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<Match> getMatches() {
		return matches;
	}



	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}



	public List<Player> getRegisteredPlayers() {
		return registeredPlayers;
	}



	public void setRegisteredPlayers(List<Player> registeredPlayers) {
		this.registeredPlayers = registeredPlayers;
	}
	

	public BigDecimal getYellowCardFine() {
		return yellowCardFine;
	}

	public void setYellowCardFine(BigDecimal yellowCardFine) {
		this.yellowCardFine = yellowCardFine;
	}

	public BigDecimal getRedCardFine() {
		return redCardFine;
	}

	public void setRedCardFine(BigDecimal redCardFine) {
		this.redCardFine = redCardFine;
	}

	public int countDownToEvent() {
		return 0;
	}

}
