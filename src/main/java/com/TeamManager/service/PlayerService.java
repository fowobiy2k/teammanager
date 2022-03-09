package com.TeamManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.Player;
import com.TeamManager.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public Player createPlayer(Player player) {
		return playerRepository.save(player);
	}
	
	public Player findById(Long id) {
		return playerRepository.getById(id);
	}
	
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

}
