package com.cakir.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakir.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	

}
