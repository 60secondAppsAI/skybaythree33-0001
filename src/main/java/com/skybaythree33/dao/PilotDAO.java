package com.skybaythree33.dao;

import java.util.List;

import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.domain.Pilot;





public interface PilotDAO extends GenericDAO<Pilot, Integer> {
  
	List<Pilot> findAll();
	






}


