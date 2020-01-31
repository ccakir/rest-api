package com.cakir.service.implTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cakir.entity.Contacts;
import com.cakir.entity.Customer;
import com.cakir.service.ContactsService;
import com.cakir.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ContactsServiceImplIntegrationTest {
	
	@Autowired
	private ContactsService contactsService;
	
	@Autowired
	private CustomerService customerService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void createContact_Test() {
		
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Customer");
		
		customerService.createCustomer(customer);
		
		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");
		contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		contact.setCustomer(customer);
		
		Contacts result = contactsService.createContact(contact);
		
		assertNotNull(result);
		assertEquals(result.getNachname(), "Test-Nachname");
		
	}
	
	@Test
	public void getAllContacts_Test() {
		
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Customer");
		
		Customer customer1 = new Customer();
		customer1.setId(2L);
		customer1.setName("Test-Customer1");
		
		customerService.createCustomer(customer);
		customerService.createCustomer(customer1);
		
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
		contact1.setCustomer(customer1);
		
		Contacts result = contactsService.createContact(contact);
		Contacts result1 = contactsService.createContact(contact1);
		
		List<Contacts> list = contactsService.getAllContacts();
		
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}

}
