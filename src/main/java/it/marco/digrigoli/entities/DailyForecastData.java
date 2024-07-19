package it.marco.digrigoli.entities;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity(name = "forecast_data")
public class DailyForecastData {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = Integer.MAX_VALUE)
	private List<String> time;
	@JsonAlias("temperature_2m_max")
	@Column(length = Integer.MAX_VALUE)
	private List<Float> temperature2mMax;
	@JsonAlias("temperature_2m_min")
	@Column(length = Integer.MAX_VALUE)
	private List<Float> temperature2mMin;
	@JsonAlias("temperature_2m")
	@Column(length = Integer.MAX_VALUE)
	private List<Float> temperature2m;
	@JsonAlias("wind_speed_10m")
	@Column(length = Integer.MAX_VALUE)
	private List<Float> windSpeed10m;
	@JsonAlias("weather_code")
	@Column(length = Integer.MAX_VALUE)
	private List<Integer> weatherCode;

}
