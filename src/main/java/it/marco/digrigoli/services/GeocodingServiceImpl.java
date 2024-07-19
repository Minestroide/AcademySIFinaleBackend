package it.marco.digrigoli.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.repositories.GeocodingRepository;
import it.marco.digrigoli.services.interfaces.IGeocodingService;

@Service
public class GeocodingServiceImpl implements IGeocodingService {

	private GeocodingRepository repo;
	
	public GeocodingServiceImpl(GeocodingRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Geocoding> searchByName(String name) {
		return repo.findBySearchByName(name);
	}


}
