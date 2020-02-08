package com.cakir.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakir.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
	

}
