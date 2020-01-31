package com.cakir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cakir.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	Optional<Customer> findByName(String name);
	
	Customer findByPlz(String plz);
	
	
	

}
