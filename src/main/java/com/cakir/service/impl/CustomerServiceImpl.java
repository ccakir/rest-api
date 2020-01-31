package com.cakir.service.impl;

import java.util.List;
import javax.persistence.EntityManager;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import org.springframework.transaction.annotation.Transactional;

import com.cakir.entity.Customer;
import com.cakir.repository.CustomerRepository;
import com.cakir.service.CustomerService;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EntityManager entityManager;
	

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}


	@Override
	public Customer createCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}


	@Override
	public Customer updateCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}


	@Override
	public void deleteCustomer(long id) {
		
		customerRepository.deleteById(id);
	}


	
	@Override
	public Customer getCustomerByName(String name) {
		
		return customerRepository.findByName(name).orElse(null);
		
		
		
		
	}


	@Override
	public Customer getCustomerById(long id) {
		
		return customerRepository.findById(id).orElse(null);
	}


	



	

}
