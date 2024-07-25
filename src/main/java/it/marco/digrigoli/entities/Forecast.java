package it.marco.digrigoli.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

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
@Entity(name = "forecast")
public class Forecast {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User user;
	private String city;
	private double latitude;
	private double longitude;
	private double elevation;
	private String timezone;
	private String timezoneAbbreviation;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private DailyForecastData hourly;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ForecastUnits hourlyUnits;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private DailyForecastData daily;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ForecastUnits dailyUnits;
	@JsonAlias("minutely_15")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private DailyForecastData minutely15;
	@JsonAlias("minutely_15_units")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ForecastUnits minutely15Units;
	
	public Long getUserId() {
		if(this.getUser() == null) return 0L;
		
		return this.getUser().getId();
	}
	
}
