package com.cakir.service;

import java.util.List;

import com.cakir.entity.CustomerLocationEmployee;

public interface CustomerLocationEmployeeService {
	
	List<CustomerLocationEmployee> getAll();
	
	CustomerLocationEmployee createCusLocEmp(CustomerLocationEmployee resource);
	
	CustomerLocationEmployee updateCusLocAmp(CustomerLocationEmployee resource);
	
	void deleteCusLocEmp(long id);
	
	CustomerLocationEmployee getCusLocEmpById(long id);
}
