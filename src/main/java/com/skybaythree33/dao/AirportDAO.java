package com.skybaythree33.dao;

import java.util.List;

import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.domain.Airport;





public interface AirportDAO extends GenericDAO<Airport, Integer> {
  
	List<Airport> findAll();
	






}


