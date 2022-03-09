package com.TeamManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TeamManager.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
