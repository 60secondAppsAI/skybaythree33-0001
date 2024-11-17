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

import com.skybaythree33.domain.BoardingPass;
import com.skybaythree33.dto.BoardingPassDTO;
import com.skybaythree33.dto.BoardingPassSearchDTO;
import com.skybaythree33.dto.BoardingPassPageDTO;
import com.skybaythree33.service.BoardingPassService;
import com.skybaythree33.dto.common.RequestDTO;
import com.skybaythree33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/boardingPass")
@RestController
public class BoardingPassController {

	private final static Logger logger = LoggerFactory.getLogger(BoardingPassController.class);

	@Autowired
	BoardingPassService boardingPassService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<BoardingPass> getAll() {

		List<BoardingPass> boardingPasss = boardingPassService.findAll();
		
		return boardingPasss;	
	}

	@GetMapping(value = "/{boardingPassId}")
	@ResponseBody
	public BoardingPassDTO getBoardingPass(@PathVariable Integer boardingPassId) {
		
		return (boardingPassService.getBoardingPassDTOById(boardingPassId));
	}

 	@RequestMapping(value = "/addBoardingPass", method = RequestMethod.POST)
	public ResponseEntity<?> addBoardingPass(@RequestBody BoardingPassDTO boardingPassDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = boardingPassService.addBoardingPass(boardingPassDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/boardingPasss")
	public ResponseEntity<BoardingPassPageDTO> getBoardingPasss(BoardingPassSearchDTO boardingPassSearchDTO) {
 
		return boardingPassService.getBoardingPasss(boardingPassSearchDTO);
	}	

	@RequestMapping(value = "/updateBoardingPass", method = RequestMethod.POST)
	public ResponseEntity<?> updateBoardingPass(@RequestBody BoardingPassDTO boardingPassDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = boardingPassService.updateBoardingPass(boardingPassDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
