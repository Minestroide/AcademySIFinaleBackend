package it.marco.digrigoli.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.repositories.CustomForecastRepository;
import it.marco.digrigoli.repositories.GeocodingRepository;
import reactor.core.publisher.Mono;

@Repository
public class ForecastRepositoryImpl implements CustomForecastRepository {

	private WebClient webClient;
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private ObjectMapper objectMapper;
	
	public ForecastRepositoryImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		
		this.webClient = WebClient.builder().baseUrl("https://api.open-meteo.com/v1").build();
	}

	@Override
	public Forecast findBySearchByLatitudeAndLongitude(double latitude, double longitude) {
		JsonNode node = this.webClient.get().uri("/forecast", uriBuilder -> {
			uriBuilder.queryParam("latitude", latitude);
			uriBuilder.queryParam("longitude", longitude);
			uriBuilder.queryParam("daily", "weather_code");
			uriBuilder.queryParam("daily", "temperature_2m_max");
			uriBuilder.queryParam("daily", "temperature_2m_min");
			uriBuilder.queryParam("daily", "apparent_temperature_max");
			uriBuilder.queryParam("daily", "apparent_temperature_min");
			uriBuilder.queryParam("hourly", "temperature_2m");
			uriBuilder.queryParam("hourly", "wind_speed_10m");
			uriBuilder.queryParam("minutely_15", "temperature_2m");
			return uriBuilder.build();
		}).retrieve().onStatus(HttpStatusCode::isError, response -> {
			response.bodyToMono(String.class).log();
			
			return Mono.error(new IllegalStateException());
		}).bodyToMono(JsonNode.class).block();
		
		logger.info(node.toPrettyString());
		
		
		try {
			return objectMapper.readValue(node.toString(), Forecast.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
