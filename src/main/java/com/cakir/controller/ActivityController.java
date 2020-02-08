package com.cakir.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cakir.entity.Activity;
import com.cakir.service.ActivityService;
import com.cakir.util.RestPreconditions;

@RestController
@RequestMapping(value = "api/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@GetMapping(value = "/")
	public ResponseEntity<List<Activity>> getAllActivity() {

		return ResponseEntity.status(HttpStatus.OK).body(
				RestPreconditions.checkFound(activityService.getAllActivity()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Activity> getActivityById(@PathVariable("id") long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(
				RestPreconditions.checkFound(activityService
						.getActivityById(id)));

	}

	@PostMapping(value = "/")
	public ResponseEntity<Activity> createActivity(
			@Valid @RequestBody Activity activity) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
				activityService.createActivity(activity));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Activity> updateActivity(@PathVariable("id") long id,
			@Valid @RequestBody Activity activity) {

		RestPreconditions.checkNotNull(activityService.getActivityById(id));
		
		return new ResponseEntity<Activity>(
				activityService.updateActivity(activity), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteActivity(@PathVariable("id") long id) {
		RestPreconditions.checkNotNull(activityService.getActivityById(id));
		activityService.deleteActivity(id);
		
	}

}
