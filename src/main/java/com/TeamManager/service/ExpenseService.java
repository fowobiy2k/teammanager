package com.TeamManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.Expense;
import com.TeamManager.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public Expense recordExpense(Expense expense) {
		return expenseRepository.save(expense);
	}
	
	public Expense findById(long id) {
		return expenseRepository.getById(id);
	}
	
	public List<Expense> getAllExpenses(){
		return expenseRepository.findAll();
	}
}
