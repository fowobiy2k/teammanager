package com.TeamManager.repository.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TeamManager.models.utitlity.Substitution;

@Repository
public interface SubstitutionRepository extends JpaRepository<Substitution, Long> {

}
