package com.cognizant.MovieBookingApp.Service.Impl;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.MovieBookingApp.Model.JwtRequest;
import com.cognizant.MovieBookingApp.Model.JwtResponse;
import com.cognizant.MovieBookingApp.Model.SecurityRequest;
import com.cognizant.MovieBookingApp.Model.SecurityResponse;

@Service
public class RestCallService {

	RestTemplate restTemplate = new RestTemplate();

	private static final String AUTORIZE_URL = "http://localhost:8082/api/v1/movieBooking/authorize";
//	private static final String AUTORIZE_URL = "http://35.155.69.186:8082/api/v1/movieBooking/authorize";

	private static final String LOGIN_URL = "http://localhost:8082/api/v1/movieBooking/generate-token";
//	private static final String LOGIN_URL = "http://35.155.69.186:8082/api/v1/movieBooking/generate-token";

	public boolean authorizeCall(String tokenHeader, ArrayList<String> role) {

		HttpEntity<SecurityRequest> securityRequest = new HttpEntity<>(new SecurityRequest(tokenHeader, role));

		ResponseEntity<SecurityResponse> securityStatus = null;

		try {
			securityStatus = restTemplate.exchange(AUTORIZE_URL, HttpMethod.POST, securityRequest,
					SecurityResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (securityStatus != null && (securityStatus.getBody().getToken() && securityStatus.getBody().getRole())) {
			return true;
		} else {
			return false;
		}
	}

	public ResponseEntity<?> loginCall(JwtRequest jwtRequest) {

		HttpEntity<JwtRequest> jwtRequest1 = new HttpEntity<>(jwtRequest);

		ResponseEntity<JwtResponse> jwtResponse = null;

		try {
			jwtResponse = restTemplate.exchange(LOGIN_URL, HttpMethod.POST, jwtRequest1, JwtResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jwtResponse != null && jwtResponse.getStatusCode() == HttpStatus.CREATED) {
			return jwtResponse;
		} else {
			return new ResponseEntity<String>("Jwt token not created", HttpStatus.UNAUTHORIZED);
		}
	}

}
