package com.cognizant.MovieBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.MovieBookingApp.Entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{


}
