package it.marco.digrigoli.controllers;

import java.util.List;
import java.util.Optional;

import org.glassfish.jersey.internal.guava.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import it.marco.digrigoli.annotations.JWTTokenNeeded;
import it.marco.digrigoli.dto.ForecastDTO;
import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.entities.User;
import it.marco.digrigoli.entities.dto.RoleDTO;
import it.marco.digrigoli.services.GeocodingServiceImpl;
import it.marco.digrigoli.services.interfaces.IForecastService;
import it.marco.digrigoli.services.interfaces.IGeocodingService;
import it.marco.digrigoli.services.interfaces.IRoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
	@JWTTokenNeeded
	public Response postForecast(@Context HttpServletRequest request, @RequestBody Forecast forecast, @QueryParam("city") String city) {
		
		if (request.getSession() == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		User authUser = (User) request.getSession().getAttribute("user");

		if (authUser == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
		forecast.setUser(authUser);
		forecast.setCity(city);
		
		this.forecastService.save(forecast);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@JWTTokenNeeded
	public Response deleteUserForecast(@Context HttpServletRequest request, @PathParam("id") Long id) {
		
		if (request.getSession() == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		User authUser = (User) request.getSession().getAttribute("user");

		if (authUser == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
		Optional<Forecast> forecast = forecastService.getById(id);
		
		if(forecast.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		if(!forecast.get().getUserId().equals(authUser.getId())) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		
		forecastService.delete(forecast.get());
		
		return Response.ok().build();
	}
	
	@GET
	@Path("/@me")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public Response getUserForecasts(@Context HttpServletRequest request) {
		
		if (request.getSession() == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		User authUser = (User) request.getSession().getAttribute("user");

		if (authUser == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		List<ForecastDTO> forecastDtos = authUser.getForecasts().stream().map((forecast) -> {
			return modelMapper.map(forecast, ForecastDTO.class);
		}).toList();
		
		return Response.ok().entity(forecastDtos).build();
	}
}
