package com.cakir.service;

import java.util.List;

import com.cakir.entity.Contacts;

public interface ContactsService {
	
	List<Contacts> getAllContacts();
	
	Contacts createContact(Contacts contact);
	
	Contacts updateContact(Contacts contact);
	
	void deleteContact(long id);
	
	List<Contacts> getContactByNachname(String nachname);
	
	Contacts getContactById(long id);

}
