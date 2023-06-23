package com.cognizant.MovieBookingApp.Controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.MovieBookingApp.Entity.Ticket;
import com.cognizant.MovieBookingApp.Enum.KafkaProducerMessage;
import com.cognizant.MovieBookingApp.KafkaProducer.Producer;
import com.cognizant.MovieBookingApp.Service.TicketService;
import com.cognizant.MovieBookingApp.Service.Impl.RestCallService;

@RestController
@RequestMapping("/api/v1/movieBooking")
@CrossOrigin("*")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	RestCallService restCall;

	@Autowired
	Producer producer;

	private boolean autorizeStatus = false;

	@PostMapping("/user/book")
	public ResponseEntity<?> bookTicket(@RequestBody Ticket ticket,
			@RequestHeader(name = "Authorization", required = true) String tokenHeader) throws Exception {

		autorizeStatus = restCall.authorizeCall(tokenHeader,
				new ArrayList<String>(Arrays.asList("ROLE_USER", "ROLE_ADMIN")));

		if (autorizeStatus) {
			String msg = ticketService.bookTicket(ticket);
			producer.sendMsg(KafkaProducerMessage.BOOK_MOVIE.getSuccess());
			return new ResponseEntity<>(msg,HttpStatus.OK);
		} else {
			producer.sendMsg(KafkaProducerMessage.BOOK_MOVIE.getAuthorizeStatus());
			return new ResponseEntity<String>("Not Authorized", HttpStatus.OK);
		}
	}

//	@DeleteMapping("/admin/{transactionId}")
//	public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId,
//			@RequestHeader(name = "Authorization", required = true) String tokenHeader)
//			throws MovieNotFoundException, TicketNotFoundException {
//
//		autorizeStatus = restCall.authorizeCall(tokenHeader,
//				new ArrayList<String>(Arrays.asList("ROLE_USER", "ROLE_ADMIN")));
//
//		if (autorizeStatus) {
//			ticketService.deleteTransaction(transactionId);
//			producer.sendMsg(KafkaProducerMessage.DELETE_TRANSACTION.getSuccess());
//			return new ResponseEntity<String>("Trnsaction successfully deleted", HttpStatus.OK);
//		} else {
//			producer.sendMsg(KafkaProducerMessage.DELETE_TRANSACTION.getAuthorizeStatus());
//			return new ResponseEntity<String>("Not Authorized", HttpStatus.OK);
//		}
//	}

	@GetMapping("/kafkaSample/{msg}")
	public ResponseEntity<String> kafkaSample(@PathVariable String msg) {
		producer.sendMsg(msg);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
