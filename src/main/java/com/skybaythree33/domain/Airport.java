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
@Table(name="airports")
@Getter @Setter @NoArgsConstructor
public class Airport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="airport_id")
	private Integer airportId;
    
  	@Column(name="name")
	private String name;
    
  	@Column(name="country")
	private String country;
    
  	@Column(name="city")
	private String city;
    
	




}
