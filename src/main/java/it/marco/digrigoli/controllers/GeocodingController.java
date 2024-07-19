package it.marco.digrigoli.controllers;

import org.glassfish.jersey.internal.guava.Lists;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import it.marco.digrigoli.annotations.JWTTokenNeeded;
import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.entities.dto.RoleDTO;
import it.marco.digrigoli.services.GeocodingServiceImpl;
import it.marco.digrigoli.services.interfaces.IGeocodingService;
import it.marco.digrigoli.services.interfaces.IRoleService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Component
@Path("/geocoding")
public class GeocodingController {
	
	private IGeocodingService geocodingService;
	
	public GeocodingController(GeocodingServiceImpl geocodingService) {
		this.geocodingService = geocodingService;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Geocoding> getByName(@QueryParam("name") String name) {
		return this.geocodingService.searchByName(name);
	}
}
