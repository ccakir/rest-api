package com.cakir.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cakir.entity.Location;
import com.cakir.service.LocationService;
import com.cakir.util.RestPreconditions;

@RestController
@RequestMapping(value = "api/location")
public class LocationController {

	@Autowired
	private LocationService locationService;

	@GetMapping(value = "/")
	public ResponseEntity<List<Location>> getAllLocations() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(RestPreconditions.checkFound(locationService
						.getAllLocations()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Location> findLocationById(@PathVariable("id") long id) {

		return ResponseEntity.status(HttpStatus.OK).body(
				RestPreconditions.checkFound(locationService
						.getLocationById(id)));
	}

	@PostMapping(value = "/")
	public ResponseEntity<Location> createLocation(
			@Valid @RequestBody Location location) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
				locationService.createLocation(location));

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable("id") long id,
			@Valid @RequestBody Location location) {

		RestPreconditions.checkFound(locationService.getLocationById(location
				.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(
				locationService.updateLocation(location));

	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLocation(@PathVariable("id") long id) {
		RestPreconditions.checkFound(locationService.getLocationById(id));
		locationService.deleteLocation(id);
	}

	@GetMapping(value = "/city/{city}")
	public ResponseEntity<Location> getLocationByCity(
			@PathVariable("city") String city) {

		Location found = locationService.getLocationByCity(city);
		RestPreconditions.checkNotNull(found);
		return ResponseEntity.status(HttpStatus.FOUND).body(found);

	}
}
