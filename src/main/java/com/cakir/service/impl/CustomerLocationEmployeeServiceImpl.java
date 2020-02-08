package com.cakir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakir.entity.CustomerLocationEmployee;
import com.cakir.repository.CustomerLocationEmployeeRespository;
import com.cakir.service.CustomerLocationEmployeeService;
import com.cakir.util.RestPreconditions;

@Service
public class CustomerLocationEmployeeServiceImpl implements CustomerLocationEmployeeService{
	
	@Autowired
	private CustomerLocationEmployeeRespository cusLocEmpRepository;

	@Override
	public List<CustomerLocationEmployee> getAll() {
		
		return cusLocEmpRepository.findAll();
	}

	@Override
	public CustomerLocationEmployee createCusLocEmp(
			CustomerLocationEmployee resource) {
		
		return cusLocEmpRepository.save(resource);
	}

	@Override
	public CustomerLocationEmployee updateCusLocAmp(
			CustomerLocationEmployee resource) {
		return cusLocEmpRepository.save(resource);
	}

	@Override
	public void deleteCusLocEmp(long id) {
	
		cusLocEmpRepository.deleteById(id);
		
	}

	@Override
	public CustomerLocationEmployee getCusLocEmpById(long id) {
		
		return cusLocEmpRepository.findById(id).orElse(null);
	}

}
