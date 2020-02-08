package com.cakir.service;

import java.util.List;

import com.cakir.entity.Employee;


public interface EmployeeService {
	
	List<Employee> getAllEmployee();
	
	Employee createEmployee(Employee employee);
	
	Employee updateEmloyee(Employee employee);
	
	void deleteEmployee(long id);
	
	Employee getEmployeeById(long id);

}
