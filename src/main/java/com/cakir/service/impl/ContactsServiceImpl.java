package com.cakir.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cakir.entity.Contacts;
import com.cakir.repository.ContactsRepository;
import com.cakir.service.ContactsService;

@Service
@Transactional
public class ContactsServiceImpl implements ContactsService {
	
	@Autowired
	private ContactsRepository contactsRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Contacts> getAllContacts() {
		
		return contactsRepository.findAll();
	}

	@Override
	public Contacts createContact(Contacts contact) {
		
		return contactsRepository.save(contact);
	}

	@Override
	public Contacts updateContact(Contacts contact) {
		
		return contactsRepository.save(contact);
	}

	@Override
	public void deleteContact(long id) {
		contactsRepository.deleteById(id);
		
	}

	@Override
	public List<Contacts> getContactByNachname(String nachname) {
		
		TypedQuery<Contacts> query = entityManager.createNamedQuery("Contacts.searchByNachname", Contacts.class)
				.setParameter("nachname", nachname);
				
		List<Contacts> list = query.getResultList();
		if(list.size() > 0 ) return list;
		else return null;
	}

	@Override
	public Contacts getContactById(long id) {
		
		return contactsRepository.findById(id).orElse(null);
	}

}
