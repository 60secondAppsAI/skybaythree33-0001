package com.skybaythree33.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skybaythree33.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skybaythree33.domain.Ticket;
import com.skybaythree33.dto.TicketDTO;
import com.skybaythree33.dto.TicketSearchDTO;
import com.skybaythree33.dto.TicketPageDTO;
import com.skybaythree33.service.TicketService;
import com.skybaythree33.dto.common.RequestDTO;
import com.skybaythree33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/ticket")
@RestController
public class TicketController {

	private final static Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	TicketService ticketService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Ticket> getAll() {

		List<Ticket> tickets = ticketService.findAll();
		
		return tickets;	
	}

	@GetMapping(value = "/{ticketId}")
	@ResponseBody
	public TicketDTO getTicket(@PathVariable Integer ticketId) {
		
		return (ticketService.getTicketDTOById(ticketId));
	}

 	@RequestMapping(value = "/addTicket", method = RequestMethod.POST)
	public ResponseEntity<?> addTicket(@RequestBody TicketDTO ticketDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ticketService.addTicket(ticketDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/tickets")
	public ResponseEntity<TicketPageDTO> getTickets(TicketSearchDTO ticketSearchDTO) {
 
		return ticketService.getTickets(ticketSearchDTO);
	}	

	@RequestMapping(value = "/updateTicket", method = RequestMethod.POST)
	public ResponseEntity<?> updateTicket(@RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ticketService.updateTicket(ticketDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
