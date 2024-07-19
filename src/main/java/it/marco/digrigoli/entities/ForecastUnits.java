package it.marco.digrigoli.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity(name = "forecast_unit")

public class ForecastUnits {

	@Id
	@GeneratedValue
	private Long id;
	@JsonAlias("temperature_2m")
	private String temperature2m;
	@JsonAlias("wind_speed_10m")
	private String windSpeed10m;
	
}
