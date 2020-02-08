package com.cakir.service;

import com.cakir.entity.Nutzen;

public interface NutzenService {

	Nutzen createNutzen(Nutzen nutzen);
	
	Nutzen updateNutzen(Nutzen nutzen);
	
	void deleteNutzen(long id);
	
	Nutzen getNutzenById(long id);
}
