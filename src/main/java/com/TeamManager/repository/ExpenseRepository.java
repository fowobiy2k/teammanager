package com.TeamManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TeamManager.models.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
