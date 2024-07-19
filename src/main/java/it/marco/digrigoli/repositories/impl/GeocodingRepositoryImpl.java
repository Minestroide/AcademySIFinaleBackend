package it.marco.digrigoli.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import it.marco.digrigoli.entities.Geocoding;
import it.marco.digrigoli.repositories.CustomGeocodingRepository;
import it.marco.digrigoli.repositories.GeocodingRepository;

@Repository
public class GeocodingRepositoryImpl implements CustomGeocodingRepository {

	private WebClient webClient;
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private ObjectMapper objectMapper;
	
	public GeocodingRepositoryImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		
		this.webClient = WebClient.builder().baseUrl("https://geocoding-api.open-meteo.com/v1").build();
	}

	@Override
	public List<Geocoding> findBySearchByName(String name) {
		JsonNode node = this.webClient.get().uri("/search", uriBuilder -> {
			uriBuilder.queryParam("name", name);
			return uriBuilder.build();
		}).retrieve().bodyToMono(JsonNode.class).block();
		
		List<Geocoding> geocodings = new ArrayList<>();
		
		if(!node.has("results")) {
			return List.of();
		}
		
		for(JsonNode result : node.get("results")) {
			logger.info(result);
			
			try {
				geocodings.add(objectMapper.readValue(result.toString(), Geocoding.class));
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return geocodings;
	}
	
}
