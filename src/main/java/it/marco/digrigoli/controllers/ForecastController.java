package it.marco.digrigoli.controllers;

import org.glassfish.jersey.internal.guava.Lists;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.marco.digrigoli.annotations.JWTTokenNeeded;
import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.entities.dto.RoleDTO;
import it.marco.digrigoli.services.GeocodingServiceImpl;
import it.marco.digrigoli.services.interfaces.IForecastService;
import it.marco.digrigoli.services.interfaces.IGeocodingService;
import it.marco.digrigoli.services.interfaces.IRoleService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Component
@Path("/forecast")
public class ForecastController {
	
	private IForecastService forecastService;
	
	public ForecastController(IForecastService forecastService) {
		this.forecastService = forecastService;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Forecast getByLatitudeAndLongitude(@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude) {
		return this.forecastService.getByLatitudeAndLongitude(latitude, longitude);
	}
	
	@POST
	public void postForecast(@RequestBody Forecast forecast) {
		this.forecastService.save(forecast);
	}
}
