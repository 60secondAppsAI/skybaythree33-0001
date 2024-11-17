package com.skybaythree33.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="check_ins")
@Getter @Setter @NoArgsConstructor
public class CheckIn {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="check_in_id")
	private Integer checkInId;
    
  	@Column(name="check_in_date")
	private Date checkInDate;
    
  	@Column(name="boarding_gate")
	private String boardingGate;
    
	




}
