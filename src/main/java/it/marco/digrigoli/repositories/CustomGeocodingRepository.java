package it.marco.digrigoli.repositories;

import java.util.List;
import java.util.Optional;

import it.marco.digrigoli.entities.Geocoding;

public interface CustomGeocodingRepository {
	
	public List<Geocoding> findBySearchByName(String name);

}