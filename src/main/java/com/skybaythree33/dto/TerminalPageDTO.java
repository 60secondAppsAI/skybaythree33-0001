package com.skybaythree33.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TerminalPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<TerminalDTO> terminals;
}





