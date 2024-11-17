package com.skybaythree33.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybaythree33.dao.GenericDAO;
import com.skybaythree33.service.GenericService;
import com.skybaythree33.service.impl.GenericServiceImpl;
import com.skybaythree33.dao.TerminalDAO;
import com.skybaythree33.domain.Terminal;
import com.skybaythree33.dto.TerminalDTO;
import com.skybaythree33.dto.TerminalSearchDTO;
import com.skybaythree33.dto.TerminalPageDTO;
import com.skybaythree33.dto.TerminalConvertCriteriaDTO;
import com.skybaythree33.dto.common.RequestDTO;
import com.skybaythree33.dto.common.ResultDTO;
import com.skybaythree33.service.TerminalService;
import com.skybaythree33.util.ControllerUtils;





@Service
public class TerminalServiceImpl extends GenericServiceImpl<Terminal, Integer> implements TerminalService {

    private final static Logger logger = LoggerFactory.getLogger(TerminalServiceImpl.class);

	@Autowired
	TerminalDAO terminalDao;

	


	@Override
	public GenericDAO<Terminal, Integer> getDAO() {
		return (GenericDAO<Terminal, Integer>) terminalDao;
	}
	
	public List<Terminal> findAll () {
		List<Terminal> terminals = terminalDao.findAll();
		
		return terminals;	
		
	}

	public ResultDTO addTerminal(TerminalDTO terminalDTO, RequestDTO requestDTO) {

		Terminal terminal = new Terminal();

		terminal.setTerminalId(terminalDTO.getTerminalId());


		terminal.setName(terminalDTO.getName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		terminal = terminalDao.save(terminal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Terminal> getAllTerminals(Pageable pageable) {
		return terminalDao.findAll(pageable);
	}

	public Page<Terminal> getAllTerminals(Specification<Terminal> spec, Pageable pageable) {
		return terminalDao.findAll(spec, pageable);
	}

	public ResponseEntity<TerminalPageDTO> getTerminals(TerminalSearchDTO terminalSearchDTO) {
	
			Integer terminalId = terminalSearchDTO.getTerminalId(); 
 			String name = terminalSearchDTO.getName(); 
 			String sortBy = terminalSearchDTO.getSortBy();
			String sortOrder = terminalSearchDTO.getSortOrder();
			String searchQuery = terminalSearchDTO.getSearchQuery();
			Integer page = terminalSearchDTO.getPage();
			Integer size = terminalSearchDTO.getSize();

	        Specification<Terminal> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, terminalId, "terminalId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Terminal> terminals = this.getAllTerminals(spec, pageable);
		
		//System.out.println(String.valueOf(terminals.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(terminals.getTotalPages()));
		
		List<Terminal> terminalsList = terminals.getContent();
		
		TerminalConvertCriteriaDTO convertCriteria = new TerminalConvertCriteriaDTO();
		List<TerminalDTO> terminalDTOs = this.convertTerminalsToTerminalDTOs(terminalsList,convertCriteria);
		
		TerminalPageDTO terminalPageDTO = new TerminalPageDTO();
		terminalPageDTO.setTerminals(terminalDTOs);
		terminalPageDTO.setTotalElements(terminals.getTotalElements());
		return ResponseEntity.ok(terminalPageDTO);
	}

	public List<TerminalDTO> convertTerminalsToTerminalDTOs(List<Terminal> terminals, TerminalConvertCriteriaDTO convertCriteria) {
		
		List<TerminalDTO> terminalDTOs = new ArrayList<TerminalDTO>();
		
		for (Terminal terminal : terminals) {
			terminalDTOs.add(convertTerminalToTerminalDTO(terminal,convertCriteria));
		}
		
		return terminalDTOs;

	}
	
	public TerminalDTO convertTerminalToTerminalDTO(Terminal terminal, TerminalConvertCriteriaDTO convertCriteria) {
		
		TerminalDTO terminalDTO = new TerminalDTO();
		
		terminalDTO.setTerminalId(terminal.getTerminalId());

	
		terminalDTO.setName(terminal.getName());

	

		
		return terminalDTO;
	}

	public ResultDTO updateTerminal(TerminalDTO terminalDTO, RequestDTO requestDTO) {
		
		Terminal terminal = terminalDao.getById(terminalDTO.getTerminalId());

		terminal.setTerminalId(ControllerUtils.setValue(terminal.getTerminalId(), terminalDTO.getTerminalId()));

		terminal.setName(ControllerUtils.setValue(terminal.getName(), terminalDTO.getName()));



        terminal = terminalDao.save(terminal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TerminalDTO getTerminalDTOById(Integer terminalId) {
	
		Terminal terminal = terminalDao.getById(terminalId);
			
		
		TerminalConvertCriteriaDTO convertCriteria = new TerminalConvertCriteriaDTO();
		return(this.convertTerminalToTerminalDTO(terminal,convertCriteria));
	}







}
