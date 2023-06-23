package com.cognizant.MovieBookingApp.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.MovieBookingApp.Entity.Movie;
import com.cognizant.MovieBookingApp.Enum.KafkaProducerMessage;
import com.cognizant.MovieBookingApp.Exception.MovieFoundException;
import com.cognizant.MovieBookingApp.Exception.MovieNotFoundException;
import com.cognizant.MovieBookingApp.KafkaProducer.Producer;
import com.cognizant.MovieBookingApp.Repository.MovieRepository;
import com.cognizant.MovieBookingApp.Service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	Producer producer;

	@Override
	public Movie addMovie(Movie movie) throws Exception {
		Movie movieTmp = getMovieByMovieName(movie.getMovieName());

		if (movieTmp != null) {
			producer.sendMsg(KafkaProducerMessage.ADD_MOVIE.getFailed());
			throw new MovieFoundException(movie.getMovieName() + " movie already present.");
		} else {
			return movieRepository.save(movie);
		}
	}

	@Override
	public List<Movie> getAllMovie() {
		List<Movie> movieList = movieRepository.findAll();
		if (movieList.isEmpty())
			producer.sendMsg(KafkaProducerMessage.FIND_ALL_MOVIE.getFailed());
		return movieList;
	}

	@Override
	public Movie getMovieByMovieName(String movieName) throws MovieNotFoundException {
		Movie movie = movieRepository.findByMovieName(movieName);
		return movie;
	}

	@Override
	public Movie updateMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public String deleteMovieByMovieName(String movieName) throws Exception {
		Movie movie = getMovieByMovieName(movieName);
		movieRepository.deleteById(movie.getId());
		return movieName + " movie is deleted succesfully!!.";
	}

}
