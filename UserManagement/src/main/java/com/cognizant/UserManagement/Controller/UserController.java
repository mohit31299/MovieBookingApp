package com.cognizant.UserManagement.Controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.UserManagement.Entity.User;
import com.cognizant.UserManagement.Exception.UserFoundException;
import com.cognizant.UserManagement.Exception.WrongAnswerException;
import com.cognizant.UserManagement.Service.UserService;

@RestController
@RequestMapping("/api/v1/movieBooking")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public User saveUser(@RequestBody User user) throws UserFoundException {
		return userService.saveUser(user);
	}

	@PutMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody User user) throws WrongAnswerException {

		Optional<User> userTemp = Optional.of(userService.getUserByUsername(user.getUsername()));

		if (userTemp.isEmpty()) {
			throw new UsernameNotFoundException("User ID " + user.getId() + " is not registered!.");
		} else if (userTemp.get().getSercetAnswer().equals(user.getSercetAnswer())) {
			userTemp.get().setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			System.out.println("update: " + userTemp.get().getPassword());
			userService.updateUser(userTemp.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			throw new WrongAnswerException("Answer for secret question is Wrong!");
		}
	}

	@GetMapping("/current-user")
	public ResponseEntity<User> getCurrentUser(Principal principal) {
		return new ResponseEntity<User>(userService.getUserByUsername(principal.getName()), HttpStatus.OK);
	}

}
