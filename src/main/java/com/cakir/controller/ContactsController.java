package com.cakir.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.cakir.entity.Contacts;
import com.cakir.entity.Customer;
import com.cakir.service.ContactsService;
import com.cakir.util.RestPreconditions;

@RestController
@RequestMapping(value = "api/contacts")
public class ContactsController {

	@Autowired
	private ContactsService contactsService;

	@GetMapping(value = "/")
	public ResponseEntity<List<Contacts>> getAllContacts() {

		return ResponseEntity.status(HttpStatus.OK).body(
				RestPreconditions.checkFound(contactsService.getAllContacts()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Contacts> findContactById(@PathVariable("id") long id) {
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(RestPreconditions.checkFound(contactsService
						.getContactById(id)));
	}

	@PostMapping(value = "/")
	public ResponseEntity<Contacts> createContact(
			@Valid @RequestBody Contacts contact) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
				contactsService.createContact(contact));
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Contacts> updateContact(@PathVariable("id") long id,
			@Valid @RequestBody Contacts contact) {

		RestPreconditions.checkNotNull(contactsService.getContactById(contact
				.getId()));
		return new ResponseEntity<Contacts>(
				contactsService.updateContact(contact), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteContact(@PathVariable("id") long id) {
		RestPreconditions.checkFound(contactsService.getContactById(id));
		contactsService.deleteContact(id);
	}

	@GetMapping(value = "/search/{nachname}")
	public ResponseEntity<List<Contacts>> searchContactsByNachname(
			@PathVariable("nachname") String nachname) {

		List<Contacts> list = contactsService.getContactByNachname(nachname);

		RestPreconditions.checkFound(list);

		return ResponseEntity.status(HttpStatus.FOUND).body(list);
	}

}
