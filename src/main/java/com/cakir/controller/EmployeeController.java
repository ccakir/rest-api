package com.cakir.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cakir.entity.Employee;
import com.cakir.service.EmployeeService;
import com.cakir.util.RestPreconditions;

@RestController
@RequestMapping(value = "api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/")
	public ResponseEntity<List<Employee>> getAllEmployee() {

		return ResponseEntity.status(HttpStatus.OK).body(
				RestPreconditions.checkFound(employeeService.getAllEmployee()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {

		return ResponseEntity.status(HttpStatus.FOUND).body(
				RestPreconditions.checkFound(employeeService
						.getEmployeeById(id)));

	}

	@PostMapping(value = "/")
	public ResponseEntity<Employee> createEmployee(
			@Valid @RequestBody Employee employee) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
				employeeService.createEmployee(employee));

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
			@Valid @RequestBody Employee employee) {

		RestPreconditions.checkFound(employeeService.getEmployeeById(id));

		return new ResponseEntity<Employee>(
				employeeService.updateEmloyee(employee), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteEmployee(@PathVariable("id") long id) {
		RestPreconditions.checkFound(employeeService.getEmployeeById(id));
		employeeService.deleteEmployee(id);
	}
}
