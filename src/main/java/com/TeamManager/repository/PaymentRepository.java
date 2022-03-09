package com.TeamManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TeamManager.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
