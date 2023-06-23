package com.cognizant.MovieBookingApp.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	
	private String movieName;
	
	private int totalNoSeats;
	
	private int seatsAvailable;
	
	private int bookedSeats;
	
	private Long movie_id_fk;
	
	

	public Ticket() {
	}

	

	



	






	public Ticket(Long transactionId, String movieName, int totalNoSeats, int seatsAvailable, int bookedSeats,
			Long movie_id_fk) {
		super();
		this.transactionId = transactionId;
		this.movieName = movieName;
		this.totalNoSeats = totalNoSeats;
		this.seatsAvailable = seatsAvailable;
		this.bookedSeats = bookedSeats;
		this.movie_id_fk = movie_id_fk;
	}














	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getTotalNoSeats() {
		return totalNoSeats;
	}

	public void setTotalNoSeats(int totalNoSeats) {
		this.totalNoSeats = totalNoSeats;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public int getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}


	public Long getMovie_id_fk() {
		return movie_id_fk;
	}

	public void setMovie_id_fk(Long movie_id_fk) {
		this.movie_id_fk = movie_id_fk;
	}














	@Override
	public String toString() {
		return "Ticket [transactionId=" + transactionId + ", movieName=" + movieName + ", totalNoSeats=" + totalNoSeats
				+ ", seatsAvailable=" + seatsAvailable + ", bookedSeats=" + bookedSeats + ", movie_id_fk=" + movie_id_fk
				+ "]";
	}

    
	

	


	



	
	
	

}
