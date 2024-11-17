package com.skybaythree33.dao;

import java.util.List;

import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.domain.Passenger;





public interface PassengerDAO extends GenericDAO<Passenger, Integer> {
  
	List<Passenger> findAll();
	






}


