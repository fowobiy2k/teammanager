package com.TeamManager.service.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamManager.models.utitlity.Booking;
import com.TeamManager.repository.utility.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	public Booking recordBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public Booking findById(long id) {
		return bookingRepository.getById(id);
	}
	
	public List<Booking> getAllBooking(){
		return bookingRepository.findAll();
	}

}
