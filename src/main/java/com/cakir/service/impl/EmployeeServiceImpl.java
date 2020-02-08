package com.cakir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakir.entity.Employee;
import com.cakir.repository.EmployeeRepository;
import com.cakir.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployee() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee createEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmloyee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(long id) {
		
		employeeRepository.deleteById(id);
		
	}

	@Override
	public Employee getEmployeeById(long id) {
		
		return employeeRepository.findById(id).orElse(null);
	}

}
