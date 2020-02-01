package com.cakir.service.implTest;



import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cakir.entity.Contacts;
import com.cakir.entity.Customer;
import com.cakir.exceptions.ResourceNotFoundException;
import com.cakir.repository.ContactsRepository;
import com.cakir.service.impl.ContactsServiceImpl;
import com.cakir.service.impl.CustomerServiceImpl;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContactsServiceImplTest {

	@Mock
	private ContactsRepository contactsRepository;

	@InjectMocks
	private ContactsServiceImpl contactsService;

	@InjectMocks
	private CustomerServiceImpl customerservice;

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCreateContact() {
		//given
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Customer");
				
		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");
		contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		contact.setCustomer(customer);
		
		//when
		when(contactsRepository.save(any(Contacts.class))).thenReturn(contact);
		
		Contacts createdContact = contactsService.createContact(contact);
		
		//then
		assertEquals(createdContact.getId(), 1L);
	}
	
	@Test
	public void testCreateContactException() {
		//given
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Customer");
				
		Contacts contact = new Contacts();
		contact.setId(1L);
		//contact.setVorname("Test-Vorname");
		//contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		contact.setCustomer(customer);
		
		
		
		Assertions.assertThrows(ResourceNotFoundException.class,
				()-> {contactsService.createContact(contact);});
	}

	@Test
	public void testGetAllContacts_thenReturnAllContacts() {
		// given
		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");
		contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		

		Contacts contact1 = new Contacts();
		contact1.setId(2L);
		contact1.setVorname("Test-Vorname");
		contact1.setNachname("Test-Nachname");
		contact1.setEmail("test@email.com");
		

		List<Contacts> list = new ArrayList<>();
		list.add(contact);
		list.add(contact1);

		// when
		when(contactsRepository.findAll()).thenReturn(list);

		List<Contacts> result = contactsService.getAllContacts();

		// then
		assertTrue(result.size() > 0);

	}
	
	@Test
	public void testGetAllContactsWithCustomer() {
		
		//given
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Customer");
		Customer customer2 = new Customer();
		customer2.setId(2L);
		customer2.setName("Test-Customer2");
		
		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");
		contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		contact.setCustomer(customer);

		Contacts contact1 = new Contacts();
		contact1.setId(2L);
		contact1.setVorname("Test-Vorname");
		contact1.setNachname("Test-Nachname");
		contact1.setEmail("test@email.com");
		contact1.setCustomer(customer2);

		List<Contacts> list = new ArrayList<>();
		list.add(contact);
		list.add(contact1);
		
		//when
		when(contactsRepository.findAll()).thenReturn(list);
		
		List<Contacts> results = contactsService.getAllContacts();
		
		//then
		assertNotNull(results);
		assertEquals(results.get(0).getCustomer().getName(), "Test-Customer");
		assertEquals(results.get(1).getCustomer().getName(), "Test-Customer2");
		
	}

}
