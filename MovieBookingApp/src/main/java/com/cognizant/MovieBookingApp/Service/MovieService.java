package com.cognizant.MovieBookingApp.Service;

import java.util.List;
import java.util.Optional;

import com.cognizant.MovieBookingApp.Entity.Movie;

public interface MovieService {
	
	Movie addMovie(Movie movie) throws Exception;
	
	Movie updateMovie(Movie movie);
	
	List<Movie> getAllMovie();
	
	Movie getMovieByMovieName(String movieName) throws Exception;
	
	String deleteMovieByMovieName(String movieName) throws Exception;
	

}
