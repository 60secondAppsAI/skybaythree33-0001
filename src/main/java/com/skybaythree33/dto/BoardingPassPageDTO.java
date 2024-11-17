package com.skybaythree33.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardingPassPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<BoardingPassDTO> boardingPasss;
}




