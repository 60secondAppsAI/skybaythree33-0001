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
import com.skybaythree33.dao.LuggageDAO;
import com.skybaythree33.domain.Luggage;
import com.skybaythree33.dto.LuggageDTO;
import com.skybaythree33.dto.LuggageSearchDTO;
import com.skybaythree33.dto.LuggagePageDTO;
import com.skybaythree33.dto.LuggageConvertCriteriaDTO;
import com.skybaythree33.dto.common.RequestDTO;
import com.skybaythree33.dto.common.ResultDTO;
import com.skybaythree33.service.LuggageService;
import com.skybaythree33.util.ControllerUtils;





@Service
public class LuggageServiceImpl extends GenericServiceImpl<Luggage, Integer> implements LuggageService {

    private final static Logger logger = LoggerFactory.getLogger(LuggageServiceImpl.class);

	@Autowired
	LuggageDAO luggageDao;

	


	@Override
	public GenericDAO<Luggage, Integer> getDAO() {
		return (GenericDAO<Luggage, Integer>) luggageDao;
	}
	
	public List<Luggage> findAll () {
		List<Luggage> luggages = luggageDao.findAll();
		
		return luggages;	
		
	}

	public ResultDTO addLuggage(LuggageDTO luggageDTO, RequestDTO requestDTO) {

		Luggage luggage = new Luggage();

		luggage.setLuggageId(luggageDTO.getLuggageId());


		luggage.setWeight(luggageDTO.getWeight());


		luggage.setLuggageType(luggageDTO.getLuggageType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		luggage = luggageDao.save(luggage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Luggage> getAllLuggages(Pageable pageable) {
		return luggageDao.findAll(pageable);
	}

	public Page<Luggage> getAllLuggages(Specification<Luggage> spec, Pageable pageable) {
		return luggageDao.findAll(spec, pageable);
	}

	public ResponseEntity<LuggagePageDTO> getLuggages(LuggageSearchDTO luggageSearchDTO) {
	
			Integer luggageId = luggageSearchDTO.getLuggageId(); 
  			String luggageType = luggageSearchDTO.getLuggageType(); 
 			String sortBy = luggageSearchDTO.getSortBy();
			String sortOrder = luggageSearchDTO.getSortOrder();
			String searchQuery = luggageSearchDTO.getSearchQuery();
			Integer page = luggageSearchDTO.getPage();
			Integer size = luggageSearchDTO.getSize();

	        Specification<Luggage> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, luggageId, "luggageId"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, luggageType, "luggageType"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("luggageType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Luggage> luggages = this.getAllLuggages(spec, pageable);
		
		//System.out.println(String.valueOf(luggages.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(luggages.getTotalPages()));
		
		List<Luggage> luggagesList = luggages.getContent();
		
		LuggageConvertCriteriaDTO convertCriteria = new LuggageConvertCriteriaDTO();
		List<LuggageDTO> luggageDTOs = this.convertLuggagesToLuggageDTOs(luggagesList,convertCriteria);
		
		LuggagePageDTO luggagePageDTO = new LuggagePageDTO();
		luggagePageDTO.setLuggages(luggageDTOs);
		luggagePageDTO.setTotalElements(luggages.getTotalElements());
		return ResponseEntity.ok(luggagePageDTO);
	}

	public List<LuggageDTO> convertLuggagesToLuggageDTOs(List<Luggage> luggages, LuggageConvertCriteriaDTO convertCriteria) {
		
		List<LuggageDTO> luggageDTOs = new ArrayList<LuggageDTO>();
		
		for (Luggage luggage : luggages) {
			luggageDTOs.add(convertLuggageToLuggageDTO(luggage,convertCriteria));
		}
		
		return luggageDTOs;

	}
	
	public LuggageDTO convertLuggageToLuggageDTO(Luggage luggage, LuggageConvertCriteriaDTO convertCriteria) {
		
		LuggageDTO luggageDTO = new LuggageDTO();
		
		luggageDTO.setLuggageId(luggage.getLuggageId());

	
		luggageDTO.setWeight(luggage.getWeight());

	
		luggageDTO.setLuggageType(luggage.getLuggageType());

	

		
		return luggageDTO;
	}

	public ResultDTO updateLuggage(LuggageDTO luggageDTO, RequestDTO requestDTO) {
		
		Luggage luggage = luggageDao.getById(luggageDTO.getLuggageId());

		luggage.setLuggageId(ControllerUtils.setValue(luggage.getLuggageId(), luggageDTO.getLuggageId()));

		luggage.setWeight(ControllerUtils.setValue(luggage.getWeight(), luggageDTO.getWeight()));

		luggage.setLuggageType(ControllerUtils.setValue(luggage.getLuggageType(), luggageDTO.getLuggageType()));



        luggage = luggageDao.save(luggage);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LuggageDTO getLuggageDTOById(Integer luggageId) {
	
		Luggage luggage = luggageDao.getById(luggageId);
			
		
		LuggageConvertCriteriaDTO convertCriteria = new LuggageConvertCriteriaDTO();
		return(this.convertLuggageToLuggageDTO(luggage,convertCriteria));
	}







}
