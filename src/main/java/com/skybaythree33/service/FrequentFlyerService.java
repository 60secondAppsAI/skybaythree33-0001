package com.skybaythree33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybaythree33.domain.FrequentFlyer;
import com.skybaythree33.dto.FrequentFlyerDTO;
import com.skybaythree33.dto.FrequentFlyerSearchDTO;
import com.skybaythree33.dto.FrequentFlyerPageDTO;
import com.skybaythree33.dto.FrequentFlyerConvertCriteriaDTO;
import com.skybaythree33.service.GenericService;
import com.skybaythree33.dto.common.RequestDTO;
import com.skybaythree33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FrequentFlyerService extends GenericService<FrequentFlyer, Integer> {

	List<FrequentFlyer> findAll();

	ResultDTO addFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO);

	ResultDTO updateFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO);

    Page<FrequentFlyer> getAllFrequentFlyers(Pageable pageable);

    Page<FrequentFlyer> getAllFrequentFlyers(Specification<FrequentFlyer> spec, Pageable pageable);

	ResponseEntity<FrequentFlyerPageDTO> getFrequentFlyers(FrequentFlyerSearchDTO frequentFlyerSearchDTO);
	
	List<FrequentFlyerDTO> convertFrequentFlyersToFrequentFlyerDTOs(List<FrequentFlyer> frequentFlyers, FrequentFlyerConvertCriteriaDTO convertCriteria);

	FrequentFlyerDTO getFrequentFlyerDTOById(Integer frequentFlyerId);







}





