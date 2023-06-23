package com.cognizant.MovieBookingApp.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.MovieBookingApp.Entity.Movie;
import com.cognizant.MovieBookingApp.Enum.KafkaProducerMessage;
import com.cognizant.MovieBookingApp.KafkaProducer.Producer;
import com.cognizant.MovieBookingApp.Model.JwtRequest;
import com.cognizant.MovieBookingApp.Service.Impl.MovieServiceImpl;
import com.cognizant.MovieBookingApp.Service.Impl.RestCallService;

@RestController
@RequestMapping("/api/v1/movieBooking")
@CrossOrigin("*")
public class MovieController {

	@Autowired
	MovieServiceImpl movieServiceImpl;

	@Autowired
	RestCallService restCall;

	@Autowired
	Producer producer;

	private boolean autorizeStatus = false;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {
		return restCall.loginCall(jwtRequest);
	}

	@PostMapping("/admin/add")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie,
			@RequestHeader(name = "Authorization", required = true) String tokenHeader) throws Exception {

		autorizeStatus = restCall.authorizeCall(tokenHeader, new ArrayList<String>(Arrays.asList("ROLE_ADMIN")));

		if (autorizeStatus) {
			Movie movie1 = movieServiceImpl.addMovie(movie);
			producer.sendMsg(KafkaProducerMessage.ADD_MOVIE.getSuccess());
			return new ResponseEntity<String>(movie1.getMovieName()+" movie added successfully.", HttpStatus.CREATED);
		} else {
			producer.sendMsg(KafkaProducerMessage.ADD_MOVIE.getAuthorizeStatus());
			return new ResponseEntity<String>("Not Authorized", HttpStatus.OK);
		}
	}

	@GetMapping("/user/search/{movieName}")
	public ResponseEntity<?> getMovieByName(@PathVariable String movieName,
			@RequestHeader(name = "Authorization", required = true) String tokenHeader) throws Exception {

		autorizeStatus = restCall.authorizeCall(tokenHeader,
				new ArrayList<String>(Arrays.asList("ROLE_USER", "ROLE_ADMIN")));

		if (autorizeStatus) {
			Movie movie = movieServiceImpl.getMovieByMovieName(movieName);
			producer.sendMsg(KafkaProducerMessage.FIND_MOVIE_BY_NAME.getSuccess());
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} else {
			producer.sendMsg(KafkaProducerMessage.ADD_MOVIE.getAuthorizeStatus());
			return new ResponseEntity<String>("Not Authorized", HttpStatus.OK);
		}

	}

	@DeleteMapping("/admin/delete/{movieName}")
	public ResponseEntity<String> delteMovieByName(@PathVariable String movieName,
			@RequestHeader(name = "Authorization", required = true) String tokenHeader) throws Exception {

		autorizeStatus = restCall.authorizeCall(tokenHeader, new ArrayList<String>(Arrays.asList("ROLE_ADMIN")));

		if (autorizeStatus) {
			String msg = movieServiceImpl.deleteMovieByMovieName(movieName);
			producer.sendMsg(KafkaProducerMessage.DELETE_MOVIE.getSuccess());
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} else {
			producer.sendMsg(KafkaProducerMessage.DELETE_MOVIE.getAuthorizeStatus());
			return new ResponseEntity<String>("Not Authorized", HttpStatus.OK);
		}
	}

	@GetMapping("/user/searchAll")
	public ResponseEntity<?> getAllMovie(@RequestHeader(name = "Authorization", required = true) String tokenHeader)
			throws Exception {

		autorizeStatus = restCall.authorizeCall(tokenHeader,
				new ArrayList<String>(Arrays.asList("ROLE_USER", "ROLE_ADMIN")));

		if (autorizeStatus) {
			List<Movie> movie = movieServiceImpl.getAllMovie();
			producer.sendMsg(KafkaProducerMessage.FIND_ALL_MOVIE.getSuccess());
			return new ResponseEntity<List<Movie>>(movie, HttpStatus.OK);
		} else {
			producer.sendMsg(KafkaProducerMessage.FIND_ALL_MOVIE.getAuthorizeStatus());
			return new ResponseEntity<String>("Not Authorized", HttpStatus.OK);
		}
	}

//	@PutMapping("/admin/update")
//	public ResponseEntity<?> updateMovie(@RequestBody Movie movie,
//			@RequestHeader(name = "Authorization", required = true) String tokenHeader) throws Exception {
//
//		autorizeStatus = restCall.authorizeCall(tokenHeader, new ArrayList<String>(Arrays.asList("ROLE_ADMIN")));
//
//		if (autorizeStatus) {
//			Movie movie1 = movieServiceImpl.updateMovie(movie);
//			producer.sendMsg(KafkaProducerMessage.UPDATE_MOVIE.getSuccess());
//			return new ResponseEntity<Movie>(movie1, HttpStatus.CREATED);
//		} else {
//			producer.sendMsg(KafkaProducerMessage.UPDATE_MOVIE.getAuthorizeStatus());
//			return new ResponseEntity<String>("Not Authorized", HttpStatus.OK);
//		}
//	}

}
