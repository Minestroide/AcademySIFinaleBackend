package it.marco.digrigoli.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.marco.digrigoli.entities.Forecast;
import it.marco.digrigoli.entities.Geocoding;

public interface ForecastRepository extends CrudRepository<Forecast, Long> {


}