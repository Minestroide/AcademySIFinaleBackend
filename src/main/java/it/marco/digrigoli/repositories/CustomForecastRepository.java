package it.marco.digrigoli.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;

public interface CustomForecastRepository {
	
	public Forecast findBySearchByLatitudeAndLongitude(double latitude, double longitude);

}