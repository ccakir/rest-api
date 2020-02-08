package com.cakir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakir.entity.Activity;
import com.cakir.repository.ActivityRepository;
import com.cakir.service.ActivityService;
import com.cakir.util.RestPreconditions;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Activity> getAllActivity() {
		
		return activityRepository.findAll();
	}

	@Override
	public Activity createActivity(Activity activity) {
		
		return activityRepository.save(activity);
	}

	@Override
	public Activity updateActivity(Activity activity) {
		
		return activityRepository.save(activity);
	}

	@Override
	public void deleteActivity(long id) {
		
		activityRepository.deleteById(id);
		
	}

	@Override
	public Activity getActivityById(long id) {
		
		return activityRepository.findById(id).orElse(null);
	}

}
