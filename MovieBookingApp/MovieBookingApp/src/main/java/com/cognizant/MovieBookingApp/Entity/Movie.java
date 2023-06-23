package com.cognizant.MovieBookingApp.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String movieName;
	
	private float rating;
	
	private String genre;
	
	private String audio;
	
	private String theatreName;
	
	private int totalNoseats;	
	
	private int seatsAvailable;
	
	private String imgUrl;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_id_fk", referencedColumnName = "id")
	private List<Ticket> ticket = new ArrayList<>();

	public Movie() {
	}

	

	public Movie(Long id, String movieName, float rating, String genre, String audio, String theatreName,
			int totalNoseats, int seatsAvailable,String imgUrl, List<Ticket> ticket) {
		super();
		this.id = id;
		this.movieName = movieName;
		this.rating = rating;
		this.genre = genre;
		this.audio = audio;
		this.theatreName = theatreName;
		this.totalNoseats = totalNoseats;
		this.seatsAvailable = seatsAvailable;
		this.ticket = ticket;
		this.imgUrl = imgUrl;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getTotalNoseats() {
		return totalNoseats;
	}

	public void setTotalNoseats(int totalNoseats) {
		this.totalNoseats = totalNoseats;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}



	public float getRating() {
		return rating;
	}



	public void setRating(float rating) {
		this.rating = rating;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public String getAudio() {
		return audio;
	}



	public void setAudio(String audio) {
		this.audio = audio;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}



	public void setImgYrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}




	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieName=" + movieName + ", rating=" + rating + ", genre=" + genre + ", audio="
				+ audio + ", theatreName=" + theatreName + ", totalNoseats=" + totalNoseats + ", seatsAvailable="
				+ seatsAvailable + ", imgUrl=" + imgUrl + ", ticket=" + ticket + "]";
	}

	
	
	
	

}
