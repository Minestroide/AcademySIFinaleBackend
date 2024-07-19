package it.marco.digrigoli.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.repositories.CustomForecastRepository;
import it.marco.digrigoli.repositories.ForecastRepository;
import it.marco.digrigoli.repositories.GeocodingRepository;
import it.marco.digrigoli.services.interfaces.IForecastService;
import it.marco.digrigoli.services.interfaces.IGeocodingService;

@Service
public class ForecastServiceImpl implements IForecastService {

	private CustomForecastRepository repo;
	private ForecastRepository dbRepo;
	
	public ForecastServiceImpl(CustomForecastRepository repo, ForecastRepository dbRepo) {
		this.repo = repo;
		this.dbRepo = dbRepo;
	}

	@Override
	public Forecast getByLatitudeAndLongitude(double latitude, double longitude) {
		return repo.findBySearchByLatitudeAndLongitude(latitude, longitude);
	}

	@Override
	public Forecast save(Forecast forecast) {
		return dbRepo.save(forecast);
	}
	


}
