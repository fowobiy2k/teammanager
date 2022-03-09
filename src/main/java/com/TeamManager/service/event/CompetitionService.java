package com.TeamManager.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.events.Competition;
import com.TeamManager.repository.event.CompetitionRepository;

@Service
public class CompetitionService {
	
	@Autowired
	private CompetitionRepository competitionRepository;
	
	public Competition createCompetition(Competition competition) {
		return competitionRepository.save(competition);
	}
	
	public Competition findById(Long id) {
		return competitionRepository.getById(id);
	}
	
	public List<Competition> getAllCompetitions() {
		return competitionRepository.findAll();
	}
	
}
