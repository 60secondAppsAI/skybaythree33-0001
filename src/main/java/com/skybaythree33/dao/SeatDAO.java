package com.skybaythree33.dao;

import java.util.List;

import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.domain.Seat;





public interface SeatDAO extends GenericDAO<Seat, Integer> {
  
	List<Seat> findAll();
	






}


