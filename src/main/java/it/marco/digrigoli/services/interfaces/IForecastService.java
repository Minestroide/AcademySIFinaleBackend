package it.marco.digrigoli.services.interfaces;

import java.util.List;
import java.util.Optional;

import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.entities.User;

public interface IForecastService {

	public Forecast getByLatitudeAndLongitude(double latitude, double longitude);
	
	public Forecast save(Forecast forecast);
	
	public List<Forecast> getByUser(User user);
	
	public Optional<Forecast> getById(Long id);
	
	public void delete(Forecast forecast);
	
}
