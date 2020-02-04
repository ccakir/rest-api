package com.cakir.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cakir.entity.Location;
import com.cakir.repository.LocationRepository;
import com.cakir.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}

	@Override
	public Location createLocation(Location location) {
		return locationRepository.save(location);
		
	}

	@Override
	public Location updateLocation(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public void deleteLocation(long id) {
		locationRepository.deleteById(id);
		
	}

	@Override
	public Location getLocationByCity(String city) {
		
		return locationRepository.findByCity(city).orElse(null);
	}

	

	@Override
	public Location getLocationById(long id) {
		
		return locationRepository.findById(id).orElse(null);
	}

}
