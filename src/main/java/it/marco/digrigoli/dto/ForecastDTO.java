package it.marco.digrigoli.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import it.marco.digrigoli.entities.DailyForecastData;
import it.marco.digrigoli.entities.ForecastUnits;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ForecastDTO {

	@Id
	@GeneratedValue
	private Long id;
	private Long userId;
	private String city;
	private double latitude;
	private double longitude;
	private double elevation;
	private String timezone;
	private String timezoneAbbreviation;
	private DailyForecastData hourly;
	private ForecastUnits hourlyUnits;
	private DailyForecastData daily;
	private ForecastUnits dailyUnits;
	private DailyForecastData minutely15;
	private ForecastUnits minutely15Units;
	
}
