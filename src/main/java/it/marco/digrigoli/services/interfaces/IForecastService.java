package it.marco.digrigoli.services.interfaces;

import java.util.List;

import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;

public interface IForecastService {

	public Forecast getByLatitudeAndLongitude(double latitude, double longitude);
	
	public Forecast save(Forecast forecast);
	
}
