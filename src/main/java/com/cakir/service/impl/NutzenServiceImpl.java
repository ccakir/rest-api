package com.cakir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cakir.entity.Nutzen;
import com.cakir.repository.NutzenRepository;
import com.cakir.service.NutzenService;
import com.cakir.util.RestPreconditions;

@Service
@Transactional
public class NutzenServiceImpl implements NutzenService{
	
	@Autowired
	private NutzenRepository nutzenRepository;

	@Override
	public Nutzen createNutzen(Nutzen nutzen) {
		
		return nutzenRepository.save(nutzen);
	}

	@Override
	public Nutzen updateNutzen(Nutzen nutzen) {

		return nutzenRepository.save(nutzen);
	}

	@Override
	public void deleteNutzen(long id) {
		
		RestPreconditions.checkFound(nutzenRepository.findById(id));
		nutzenRepository.deleteById(id);
		
	}

	@Override
	public Nutzen getNutzenById(long id) {
		
		return nutzenRepository.findById(id).orElse(null);
	}

}
