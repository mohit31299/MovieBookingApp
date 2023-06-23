package com.cognizant.MovieBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.MovieBookingApp.Entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	Movie findByMovieName(String movieName);

}
