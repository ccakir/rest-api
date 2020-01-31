package com.cakir.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cakir.entity.Customer;
import com.cakir.service.CustomerService;
import com.cakir.util.RestPreconditions;


@RestController
@RequestMapping(value = "api/")
@Api(value = "Mein Customer API Dokument")
@EnableWebMvc
public class CustomerController {

	

	@Autowired
	private CustomerService customerService;

	@GetMapping(value="customer")
	@ApiOperation(value="Get All Customer Method")
	public ResponseEntity<List<Customer>> getAll(){

		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomer());
	}

	@GetMapping(value = "customer/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable("id") long id,
			HttpServletResponse response) {

		return ResponseEntity.status(HttpStatus.OK).body(RestPreconditions.checkFound(customerService.getCustomerById(id)));

	}
	
	@PostMapping(value="customer")
	@ApiOperation(value="Created a new Customer Method")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody @ApiParam(value="customer")Customer resource) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(resource));
	}
	
	@PutMapping(value="customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @Valid @RequestBody Customer resource) {
				
		RestPreconditions.checkNotNull(customerService.getCustomerById(resource.getId()));
		return new ResponseEntity<Customer>(customerService.updateCustomer(resource), HttpStatus.OK);
	}
	
	@DeleteMapping(value="customer/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("id") long id) {
		RestPreconditions.checkNotNull(customerService.getCustomerById(id));
		customerService.deleteCustomer(id);
	}
	
	@GetMapping(value="customer/search/{name}")
	public ResponseEntity<Customer> findCustomerByName(@PathVariable("name") String name){
		
		Customer customer = customerService.getCustomerByName(name);
		
		RestPreconditions.checkFound(customer);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
		
		
			
		
	}
	//sdfsd

}
