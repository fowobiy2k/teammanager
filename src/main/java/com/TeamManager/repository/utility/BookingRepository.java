package com.TeamManager.repository.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TeamManager.models.utitlity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
