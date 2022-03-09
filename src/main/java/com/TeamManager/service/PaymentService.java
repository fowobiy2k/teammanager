package com.TeamManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.Payment;
import com.TeamManager.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment recordPayment(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public Payment findById(long id) {
		return paymentRepository.getById(id);
	}
	
	public List<Payment> getAllPayments(){
		return paymentRepository.findAll();
	}
}
