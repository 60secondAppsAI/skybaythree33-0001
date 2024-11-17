package com.skybaythree33.dao;

import java.util.List;

import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.domain.Terminal;





public interface TerminalDAO extends GenericDAO<Terminal, Integer> {
  
	List<Terminal> findAll();
	






}


