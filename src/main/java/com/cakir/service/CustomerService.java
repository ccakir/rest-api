package com.cakir.service;

import java.util.List;

import com.cakir.entity.Customer;


public interface CustomerService {
	
	List<Customer> getAllCustomer();
	
	Customer createCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(long id);
	
	Customer getCustomerByName(String name);
	
	Customer getCustomerById(long id);
	
	
	
	
	
	

}
