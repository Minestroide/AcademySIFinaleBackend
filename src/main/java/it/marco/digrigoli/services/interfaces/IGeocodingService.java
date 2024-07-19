package it.marco.digrigoli.services.interfaces;

import java.util.List;

import it.marco.digrigoli.entities.Geocoding;

public interface IGeocodingService {

	public List<Geocoding> searchByName(String name);
	
}
