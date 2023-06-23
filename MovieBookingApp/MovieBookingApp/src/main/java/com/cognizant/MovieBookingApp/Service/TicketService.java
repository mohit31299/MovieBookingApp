package com.cognizant.MovieBookingApp.Service;

import com.cognizant.MovieBookingApp.Entity.Movie;
import com.cognizant.MovieBookingApp.Entity.Ticket;
import com.cognizant.MovieBookingApp.Exception.MovieNotFoundException;
import com.cognizant.MovieBookingApp.Exception.TicketNotFoundException;

public interface TicketService {

	String bookTicket(Ticket ticket) throws Exception;

	Ticket addTicket(Movie movie, int bookedSeats);
	
	void deleteTransaction(Long id) throws MovieNotFoundException, TicketNotFoundException;
	

}
