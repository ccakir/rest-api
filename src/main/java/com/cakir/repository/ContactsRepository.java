package com.cakir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakir.entity.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
	
	Optional<Contacts> findByVorname(String vorname);
	
	
	

}
