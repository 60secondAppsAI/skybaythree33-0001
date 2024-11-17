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
import com.skybaythree33.dao.FrequentFlyerDAO;
import com.skybaythree33.domain.FrequentFlyer;
import com.skybaythree33.dto.FrequentFlyerDTO;
import com.skybaythree33.dto.FrequentFlyerSearchDTO;
import com.skybaythree33.dto.FrequentFlyerPageDTO;
import com.skybaythree33.dto.FrequentFlyerConvertCriteriaDTO;
import com.skybaythree33.dto.common.RequestDTO;
import com.skybaythree33.dto.common.ResultDTO;
import com.skybaythree33.service.FrequentFlyerService;
import com.skybaythree33.util.ControllerUtils;





@Service
public class FrequentFlyerServiceImpl extends GenericServiceImpl<FrequentFlyer, Integer> implements FrequentFlyerService {

    private final static Logger logger = LoggerFactory.getLogger(FrequentFlyerServiceImpl.class);

	@Autowired
	FrequentFlyerDAO frequentFlyerDao;

	


	@Override
	public GenericDAO<FrequentFlyer, Integer> getDAO() {
		return (GenericDAO<FrequentFlyer, Integer>) frequentFlyerDao;
	}
	
	public List<FrequentFlyer> findAll () {
		List<FrequentFlyer> frequentFlyers = frequentFlyerDao.findAll();
		
		return frequentFlyers;	
		
	}

	public ResultDTO addFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO) {

		FrequentFlyer frequentFlyer = new FrequentFlyer();

		frequentFlyer.setFrequentFlyerId(frequentFlyerDTO.getFrequentFlyerId());


		frequentFlyer.setAccumulatedMiles(frequentFlyerDTO.getAccumulatedMiles());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		frequentFlyer = frequentFlyerDao.save(frequentFlyer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<FrequentFlyer> getAllFrequentFlyers(Pageable pageable) {
		return frequentFlyerDao.findAll(pageable);
	}

	public Page<FrequentFlyer> getAllFrequentFlyers(Specification<FrequentFlyer> spec, Pageable pageable) {
		return frequentFlyerDao.findAll(spec, pageable);
	}

	public ResponseEntity<FrequentFlyerPageDTO> getFrequentFlyers(FrequentFlyerSearchDTO frequentFlyerSearchDTO) {
	
			Integer frequentFlyerId = frequentFlyerSearchDTO.getFrequentFlyerId(); 
  			String sortBy = frequentFlyerSearchDTO.getSortBy();
			String sortOrder = frequentFlyerSearchDTO.getSortOrder();
			String searchQuery = frequentFlyerSearchDTO.getSearchQuery();
			Integer page = frequentFlyerSearchDTO.getPage();
			Integer size = frequentFlyerSearchDTO.getSize();

	        Specification<FrequentFlyer> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, frequentFlyerId, "frequentFlyerId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<FrequentFlyer> frequentFlyers = this.getAllFrequentFlyers(spec, pageable);
		
		//System.out.println(String.valueOf(frequentFlyers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(frequentFlyers.getTotalPages()));
		
		List<FrequentFlyer> frequentFlyersList = frequentFlyers.getContent();
		
		FrequentFlyerConvertCriteriaDTO convertCriteria = new FrequentFlyerConvertCriteriaDTO();
		List<FrequentFlyerDTO> frequentFlyerDTOs = this.convertFrequentFlyersToFrequentFlyerDTOs(frequentFlyersList,convertCriteria);
		
		FrequentFlyerPageDTO frequentFlyerPageDTO = new FrequentFlyerPageDTO();
		frequentFlyerPageDTO.setFrequentFlyers(frequentFlyerDTOs);
		frequentFlyerPageDTO.setTotalElements(frequentFlyers.getTotalElements());
		return ResponseEntity.ok(frequentFlyerPageDTO);
	}

	public List<FrequentFlyerDTO> convertFrequentFlyersToFrequentFlyerDTOs(List<FrequentFlyer> frequentFlyers, FrequentFlyerConvertCriteriaDTO convertCriteria) {
		
		List<FrequentFlyerDTO> frequentFlyerDTOs = new ArrayList<FrequentFlyerDTO>();
		
		for (FrequentFlyer frequentFlyer : frequentFlyers) {
			frequentFlyerDTOs.add(convertFrequentFlyerToFrequentFlyerDTO(frequentFlyer,convertCriteria));
		}
		
		return frequentFlyerDTOs;

	}
	
	public FrequentFlyerDTO convertFrequentFlyerToFrequentFlyerDTO(FrequentFlyer frequentFlyer, FrequentFlyerConvertCriteriaDTO convertCriteria) {
		
		FrequentFlyerDTO frequentFlyerDTO = new FrequentFlyerDTO();
		
		frequentFlyerDTO.setFrequentFlyerId(frequentFlyer.getFrequentFlyerId());

	
		frequentFlyerDTO.setAccumulatedMiles(frequentFlyer.getAccumulatedMiles());

	

		
		return frequentFlyerDTO;
	}

	public ResultDTO updateFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO) {
		
		FrequentFlyer frequentFlyer = frequentFlyerDao.getById(frequentFlyerDTO.getFrequentFlyerId());

		frequentFlyer.setFrequentFlyerId(ControllerUtils.setValue(frequentFlyer.getFrequentFlyerId(), frequentFlyerDTO.getFrequentFlyerId()));

		frequentFlyer.setAccumulatedMiles(ControllerUtils.setValue(frequentFlyer.getAccumulatedMiles(), frequentFlyerDTO.getAccumulatedMiles()));



        frequentFlyer = frequentFlyerDao.save(frequentFlyer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FrequentFlyerDTO getFrequentFlyerDTOById(Integer frequentFlyerId) {
	
		FrequentFlyer frequentFlyer = frequentFlyerDao.getById(frequentFlyerId);
			
		
		FrequentFlyerConvertCriteriaDTO convertCriteria = new FrequentFlyerConvertCriteriaDTO();
		return(this.convertFrequentFlyerToFrequentFlyerDTO(frequentFlyer,convertCriteria));
	}







}
