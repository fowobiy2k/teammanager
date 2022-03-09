package com.TeamManager.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TeamManager.models.events.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
