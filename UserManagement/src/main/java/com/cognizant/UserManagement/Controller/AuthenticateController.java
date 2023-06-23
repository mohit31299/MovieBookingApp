package com.cognizant.UserManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.UserManagement.Entity.User;
import com.cognizant.UserManagement.Exception.NotAuthenticatetException;
import com.cognizant.UserManagement.Model.JwtRequest;
import com.cognizant.UserManagement.Model.JwtResponse;
import com.cognizant.UserManagement.Model.SecurityRequest;
import com.cognizant.UserManagement.Model.SecurityResponse;
import com.cognizant.UserManagement.Repository.UserRepository;
import com.cognizant.UserManagement.Service.Impl.UserDetailServiceImpl;
import com.cognizant.UserManagement.Utils.JwtUtils;

@RestController
@RequestMapping("/api/v1/movieBooking")
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/generate-token")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest)
			throws NotAuthenticatetException {

		String token;

		UserDetails user = userDetailServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		

		token = jwtUtils.generateToken(user);

		if (passwordEncoder.matches(jwtRequest.getPassword(), user.getPassword())
				&& jwtRequest.getUserName().equals(user.getUsername()))
			return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.CREATED);
		else
			throw new NotAuthenticatetException("Bad Credentials");
	}

	@PostMapping("/authorize")
	public ResponseEntity<SecurityResponse> authorize(@RequestBody SecurityRequest securityEntity) {

		boolean roleStatus = false;

		if (securityEntity.getRole() != null && securityEntity.getToken().startsWith("Bearer")) {
			String token = securityEntity.getToken().substring(7);
			String username = jwtUtils.extractUsername(token);

			User user = userRepository.findByUsername(username);

			if (securityEntity.getRole().contains(user.getRole()))
				roleStatus = true;

			UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);

//			System.out.println("role "+userDetails.getAuthorities());

//			userDetails.getAuthorities().forEach(authority -> {
//				if (securityEntity.getRole().contains(authority.getAuthority())) {
//					System.out.println("role "+authority.getAuthority());
//					roleStatus = true;
//				}
//
//			});

			SecurityResponse securityResponse = new SecurityResponse(jwtUtils.validateToken(token, userDetails),
					roleStatus);

			return new ResponseEntity<SecurityResponse>(securityResponse, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
