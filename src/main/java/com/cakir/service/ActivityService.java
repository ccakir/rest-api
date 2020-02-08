package com.cakir.service;

import java.util.List;

import com.cakir.entity.Activity;

public interface ActivityService {
	
	List<Activity> getAllActivity();
	
	Activity createActivity(Activity activity);
	
	Activity updateActivity(Activity activity);
	
	void deleteActivity(long id);
	
	Activity getActivityById(long id);
	

}
