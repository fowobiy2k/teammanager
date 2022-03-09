package com.TeamManager.service.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.utitlity.Substitution;
import com.TeamManager.repository.utility.SubstitutionRepository;

@Service
public class SubstitutionService {
	
	@Autowired
	private SubstitutionRepository substitutionRepository;
	
	public Substitution recordSubstitution(Substitution sub) {
		return substitutionRepository.save(sub);
	}
	
	public Substitution findById(Long id) {
		return substitutionRepository.getById(id);
	}
	
	public List<Substitution> getAllSub() {
		return substitutionRepository.findAll();
	}

}
