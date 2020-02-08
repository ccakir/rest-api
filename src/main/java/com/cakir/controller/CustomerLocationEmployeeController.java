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

import com.cakir.entity.CustomerLocationEmployee;
import com.cakir.service.CustomerLocationEmployeeService;
import com.cakir.util.RestPreconditions;

@RestController
@RequestMapping(value = "api/cle")
// cle --> CustomerLocationEmployee
public class CustomerLocationEmployeeController {

	@Autowired
	private CustomerLocationEmployeeService service;

	@GetMapping(value = "/")
	public ResponseEntity<List<CustomerLocationEmployee>> getAll() {

		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerLocationEmployee> getById(
			@PathVariable("id") long id) {

		return ResponseEntity.status(HttpStatus.OK).body(
				RestPreconditions.checkFound(service.getCusLocEmpById(id)));
	}

	@PostMapping(value = "/")
	public ResponseEntity<CustomerLocationEmployee> createCLE(
			@Valid @RequestBody CustomerLocationEmployee resource) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
				service.createCusLocEmp(resource));

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CustomerLocationEmployee> updateCLE(
			@PathVariable("id") long id,
			@Valid @RequestBody CustomerLocationEmployee resource) {

		RestPreconditions.checkFound(service.getCusLocEmpById(id));

		return new ResponseEntity<CustomerLocationEmployee>(
				service.updateCusLocAmp(resource), HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCLE(@PathVariable("id") long id) {
		
		RestPreconditions.checkFound(service.getCusLocEmpById(id));
		service.deleteCusLocEmp(id);
	}

}
