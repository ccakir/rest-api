package com.cakir.service;

import java.util.List;
import java.util.Optional;

import com.cakir.entity.Location;

public interface LocationService {
	

	List<Location> getAllLocations();
	
	Location createLocation(Location location);
	
	Location updateLocation(Location location);
	
	void deleteLocation(long id);
	
	Location getLocationByCity(String city);
	
	Location getLocationById(long id);
	
}
