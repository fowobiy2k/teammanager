package com.TeamManager.service.goal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.goals.Goal;
import com.TeamManager.repository.goal.GoalRepository;

@Service
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	
	public Goal recordGoal(Goal goal) {
		return goalRepository.save(goal);
	}
	
	public Goal getById(Long id) {
		return goalRepository.getById(id);
	}
	
	public List<Goal> getAllGoals() {
		return goalRepository.findAll();
	}

}
