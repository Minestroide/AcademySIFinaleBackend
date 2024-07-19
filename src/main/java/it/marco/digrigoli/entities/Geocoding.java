package it.marco.digrigoli.entities;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity(name = "geocoding")
public class Geocoding {

	@Id
	private Long id;
	private String name;
	private double latitude;
	private double longitude;
	private double elevation;
	private String countryCode;
	private String timezone;
	private List<String> postcodes;
	private Long countryId;
	private String country;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
}
