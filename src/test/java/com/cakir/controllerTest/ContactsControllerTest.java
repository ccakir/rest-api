package com.cakir.controllerTest;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cakir.entity.Contacts;
import com.cakir.entity.Customer;
import com.cakir.service.ContactsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ContactsControllerTest {

	private MockMvc mvc;
	private final static String CONTENT_TYPE = "application/json";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ContactsService contactService;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetAllcontacts_WhenFound_ThenReturnContactsListAndStatusOk()
			throws Exception {

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

		when(contactService.getAllContacts()).thenReturn(list);

		MvcResult mvcResult = mvc
				.perform(get("/api/contacts/").accept(CONTENT_TYPE))
				.andExpect(status().isOk()).andReturn();

		verify(contactService, times(1)).getAllContacts();
		String responsebody = mvcResult.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(list))
				.isEqualToIgnoringWhitespace(responsebody);

	}

	@Test
	public void testGetAllcontacts_WhenNotFound_ThenReturnStatusNotFound()
			throws Exception {

		when(contactService.getAllContacts()).thenReturn(null);

		mvc.perform(get("api/contacts/")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindContactById_WhenContactFound_ThenReturnContactAndStatusOk()
			throws Exception {

		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Customer");

		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");
		contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		contact.setCustomer(customer);

		when(contactService.getContactById(contact.getId()))
				.thenReturn(contact);

		mvc.perform(get("/api/contacts/{id}", 1).contentType(CONTENT_TYPE))
				.andExpect(status().isFound())
				.andExpect(jsonPath("$.id", Matchers.is(1)));
		verify(contactService, times(1)).getContactById(1L);

	}

	@Test
	public void testFindContactById_WhenContactNotFound_ThenReturnStatusNotFound()
			throws Exception {

		when(contactService.getContactById(1L)).thenReturn(null);

		mvc.perform(get("/api/contacts/{id}", 1L)).andExpect(
				status().isNotFound());
	}

	@Test
	public void testCreateContact_WhenValidInput_ThenCreateContactAndReturnContactAndStatusCreated()
			throws Exception, Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Customer");

		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");
		contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		contact.setCustomer(customer);

		ResultActions actions = mvc
				.perform(post("/api/contacts/").contentType(CONTENT_TYPE)
						.content(objectMapper.writeValueAsString(contact)));

		ArgumentCaptor<Contacts> captor = ArgumentCaptor
				.forClass(Contacts.class);
		verify(contactService, times(1)).createContact(captor.capture());
		assertThat(captor.getValue().getVorname()).isEqualTo("Test-Vorname");
		actions.andExpect(status().isCreated());
		actions.andDo(print());
		
		

	}

	@Test
	public void testCreateContact_WhenInvalidInput_ThenReturnStatusBadrequest()
			throws Exception {

		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");

		mvc.perform(
				post("/api/contacts/").contentType(CONTENT_TYPE).content(
						objectMapper.writeValueAsString(contact))).andExpect(
				status().isBadRequest());
	}

	@Test
	public void testUpdateContact_whenIdFoundButContactInvalid_thenReturnStatusBadRequest()
			throws Exception {

		Contacts contact = new Contacts();
		contact.setId(1L);
		contact.setVorname("Test-Vorname");

		when(contactService.getContactById(1L)).thenReturn(contact);
		when(contactService.updateContact(contact)).thenReturn(contact);

		mvc.perform(put("/api/contacts/{id}", 1L).contentType(CONTENT_TYPE))
				.andExpect(status().isBadRequest());

	}
	
	@Test
	public void testUpdateContact_whenContactValid_thenReturnUpdatedContactAndStatusOk() throws Exception {
		
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Test-Customer");

		Contacts contact = new Contacts();
		contact.setId(10);
		contact.setVorname("Test-Vorname");
		contact.setNachname("Test-Nachname");
		contact.setEmail("test@email.com");
		contact.setCustomer(customer);
		
		when(contactService.getContactById(contact.getId())).thenReturn(contact);
		when(contactService.updateContact(contact)).thenReturn(contact);
		
		
		
		mvc.perform(put("/api/contacts/{id}", contact.getId())
				.header(
						HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(contact)))
				.andExpect(status().isOk())
				.andDo(print());
		
		ArgumentCaptor<Contacts> captor = ArgumentCaptor.forClass(Contacts.class);
		
		verify(contactService, times(1)).updateContact(captor.capture());
		assertThat(captor.getValue().getId()).isEqualTo(contact.getId());
		assertThat(captor.getValue().getCustomer()).isEqualTo(customer);
		
		
	}
}
