package com.TeamManager.repository.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TeamManager.models.goals.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

}
