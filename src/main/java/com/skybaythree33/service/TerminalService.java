package com.skybaythree33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybaythree33.domain.Terminal;
import com.skybaythree33.dto.TerminalDTO;
import com.skybaythree33.dto.TerminalSearchDTO;
import com.skybaythree33.dto.TerminalPageDTO;
import com.skybaythree33.dto.TerminalConvertCriteriaDTO;
import com.skybaythree33.service.GenericService;
import com.skybaythree33.dto.common.RequestDTO;
import com.skybaythree33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TerminalService extends GenericService<Terminal, Integer> {

	List<Terminal> findAll();

	ResultDTO addTerminal(TerminalDTO terminalDTO, RequestDTO requestDTO);

	ResultDTO updateTerminal(TerminalDTO terminalDTO, RequestDTO requestDTO);

    Page<Terminal> getAllTerminals(Pageable pageable);

    Page<Terminal> getAllTerminals(Specification<Terminal> spec, Pageable pageable);

	ResponseEntity<TerminalPageDTO> getTerminals(TerminalSearchDTO terminalSearchDTO);
	
	List<TerminalDTO> convertTerminalsToTerminalDTOs(List<Terminal> terminals, TerminalConvertCriteriaDTO convertCriteria);

	TerminalDTO getTerminalDTOById(Integer terminalId);







}





