package com.skybaythree33.dao;

import java.util.List;

import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.domain.Crew;





public interface CrewDAO extends GenericDAO<Crew, Integer> {
  
	List<Crew> findAll();
	






}


