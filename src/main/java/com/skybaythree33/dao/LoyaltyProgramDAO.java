package com.skybaythree33.dao;

import java.util.List;

import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.domain.LoyaltyProgram;





public interface LoyaltyProgramDAO extends GenericDAO<LoyaltyProgram, Integer> {
  
	List<LoyaltyProgram> findAll();
	






}


