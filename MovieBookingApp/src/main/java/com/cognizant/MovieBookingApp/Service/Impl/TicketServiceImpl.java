package com.cognizant.MovieBookingApp.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.MovieBookingApp.Entity.Movie;
import com.cognizant.MovieBookingApp.Entity.Ticket;
import com.cognizant.MovieBookingApp.Exception.MovieNotFoundException;
import com.cognizant.MovieBookingApp.Exception.TicketNotFoundException;
import com.cognizant.MovieBookingApp.Repository.TicketRepository;
import com.cognizant.MovieBookingApp.Service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	MovieServiceImpl moviesServiceImpl;

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public String bookTicket(Ticket ticket) throws Exception {

		String movieName = ticket.getMovieName();
		int seatsBooked = ticket.getBookedSeats();

		Movie movie = moviesServiceImpl.getMovieByMovieName(movieName);

		if (movie.getSeatsAvailable() <= 0)
			return "Sorry seats are not available.";
		else if (movie.getSeatsAvailable() < seatsBooked)
			return movie.getSeatsAvailable() + " seats available.";

		movie.setSeatsAvailable(movie.getSeatsAvailable() - seatsBooked);

		addTicket(movie, seatsBooked);

		moviesServiceImpl.updateMovie(movie);

		return seatsBooked + " seats booked for " + movie.getMovieName() + " movie.";
	}

	@Override
	public Ticket addTicket(Movie movie, int seatsBooked) {

		Ticket ticket = new Ticket();
		ticket.setMovieName(movie.getMovieName());
		ticket.setTotalNoSeats(movie.getTotalNoseats());
		ticket.setSeatsAvailable(movie.getSeatsAvailable());
		ticket.setBookedSeats(seatsBooked);
		ticket.setMovie_id_fk(movie.getId());

		return ticketRepository.save(ticket);
	}

	@Override
	public void deleteTransaction(Long id) throws MovieNotFoundException, TicketNotFoundException {
		Ticket ticket =ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Transaction id :"+id+" not found."));
		Movie movie = moviesServiceImpl.getMovieByMovieName(ticket.getMovieName());
		movie.setSeatsAvailable(movie.getSeatsAvailable() + ticket.getBookedSeats());
		ticketRepository.delete(ticket);
	}

}
