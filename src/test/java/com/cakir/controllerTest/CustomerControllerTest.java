package com.cakir.controllerTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.cakir.RestApiApplication;
import com.cakir.entity.Customer;
import com.cakir.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApiApplication.class)
@WebAppConfiguration
public class CustomerControllerTest {

	private MockMvc mvc;
	private final static String CONTENT_TYPE = "application/json";
	

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CustomerService customerService;

	@Before
	public void setUp() throws Exception {

		mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}
	
	@Test
	public void findCustomerByName_givenName_ButNotFoundCustomer_ThenReturn_404() throws Exception {
		//given
		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setName("Test-Sirpress");
		customer1.setPlz("2231");
		customer1.setEmail("customer1@gmail.com");
		
		//when
		when(customerService.getCustomerByName(customer1.getName())).thenReturn(null);
		
		
		// then
		mvc.perform(
						get("/api/customer/search/{name}", customer1.getName())
								.contentType(CONTENT_TYPE).param("name",
										"Test")).andExpect(status().isNotFound());
	}

	@Test
	public void findCustomerByName_givenName_whenGettingCustomers_theReturn_200()
			throws Exception {

		// given
		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setName("Test-Sirpress");
		customer1.setPlz("2231");
		customer1.setEmail("customer1@gmail.com");

		

		// when
		when(customerService.getCustomerByName("Test-Sirpress")).thenReturn(
				customer1);

		// then
		mvc.perform(
				get("/api/customer/search/{name}", customer1.getName())
						.contentType(CONTENT_TYPE).param("name",
								"Test-Sirpress")).andExpect(status().isOk());

	}

	@Test
	public void CustomerDeleteTest() throws Exception {

		// given
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Name");
		long id = 1L;

		// when
		when(customerService.getCustomerById(id)).thenReturn(customer);
		Mockito.doNothing().when(customerService).deleteCustomer(id);

		// then
		mvc.perform(delete("/api/customer/{id}", 1L).contentType(CONTENT_TYPE))
				.andExpect(status().isNoContent());

		verify(customerService, Mockito.times(1)).deleteCustomer(id);

	}

	@Test
	public void whenFindCustomerByIdNotFound_thenReturn404() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setId(10L);
		customer.setName("Test-Name");
		customer.setEmail("test@gmail.com");
		given(customerService.getCustomerById(customer.getId())).willReturn(
				null);

		// then

		mvc.perform(
				put("/api/customer/{id}", 100L).contentType(CONTENT_TYPE)
						.content(objectMapper.writeValueAsString(customer)))
				.andExpect(status().isNotFound());

	}

	@Test
	public void updateCustomer_Test() throws Exception {

		// given
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Test-Name");
		customer.setEmail("test@gmail.com");

		// when
		when(customerService.updateCustomer(customer)).thenReturn(customer);
		when(customerService.getCustomerById(customer.getId())).thenReturn(
				customer);

		// then
		mvc.perform(
				put("/api/customer/1").content(
						objectMapper.writeValueAsString(customer)).header(
						HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Test-Name")))
				.andExpect(jsonPath("$.email", is("test@gmail.com")))
				.andDo(print());
		verify(customerService, Mockito.times(1)).updateCustomer(customer);

	}

	@Test
	public void whenInvalidUpdateInput_thenReturns400() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setEmail("test@gmail.com");

		// when
		ResultActions actions = mvc.perform(put("/api/customer/1").contentType(
				CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(customer)));

		// then
		actions.andExpect(status().isBadRequest());

	}

	@Test
	public void whenInvalidInput_thenReturns400() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setEmail("test@gmail.com");

		// when
		ResultActions actions = mvc.perform(post("/api/customer").contentType(
				CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(customer)));

		// then
		actions.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallGetAll_thenReturnsNoData() throws Exception {

		// given
		when(customerService.getAllCustomer()).thenReturn(
				Collections.emptyList());

		// when
		MvcResult mvcResult = mvc.perform(
				get("/api/customer").accept(CONTENT_TYPE)).andReturn();

		// then
		String responseBody = mvcResult.getResponse().getContentAsString();
		verify(customerService, Mockito.times(1)).getAllCustomer();
		assertThat(objectMapper.writeValueAsString(Collections.emptyList()))
				.isEqualToIgnoringWhitespace(responseBody);
	}

	@Test
	public void whenValidInput_thenReturns200() throws Exception {

		// given
		Customer customer = new Customer();
		customer.setName("Test-Customer");

		// when
		ResultActions actions = mvc.perform(post("/api/customer").contentType(
				CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(customer)));

		// then
		ArgumentCaptor<Customer> captor = ArgumentCaptor
				.forClass(Customer.class);
		Mockito.verify(customerService, Mockito.times(1)).createCustomer(
				captor.capture());
		assertThat(captor.getValue().getName()).isEqualTo("Test-Customer");
		actions.andExpect(status().isCreated());

	}

	@Test
	public void givenCustomer_whenGetCustomer_thenReturnJsonArray_Test()
			throws Exception {

		Customer customer = new Customer();
		customer.setName("Test-Halberg");

		Customer customer1 = new Customer();
		customer1.setName("Test-YeongWa");

		List<Customer> customerList = Arrays.asList(customer, customer1);

		given(customerService.getAllCustomer()).willReturn(customerList);

		mvc.perform(
				get("/api/customer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(
						jsonPath("$[0].name", Matchers.is(customer.getName())))
				.andExpect(
						jsonPath("$[1].name", Matchers.is(customer1.getName())));

	}

	@Test
	public void whenCustomerNotExits_theHttp400() throws Exception {
		Customer customer = new Customer();
		customer.setId(55);
		customer.setName("Test-Sirpress");

		mvc.perform(get("/api/customer/{id}", 55)).andExpect(
				status().is(HttpStatus.NOT_FOUND.value()));

	}

	@Test
	public void givenCustomer_whenGetCustomerById_thenReturnCustomer_Test()
			throws Exception {

		Customer customer = new Customer();
		customer.setId(255);
		customer.setName("Test-Sirpress");

		given(customerService.getCustomerById(customer.getId())).willReturn(
				customer);

		mvc.perform(
				get("/api/customer/{id}", 255).contentType(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		// .andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$.id", Matchers.is(255)));

	}

	@Test
	public void whenPostCustomer_thenAddCustomer_andCustomerReturn()
			throws Exception {
		Customer customer = new Customer();
		customer.setId(55);
		customer.setName("Test-Sirpress");

		given(customerService.createCustomer(customer)).willReturn(customer);

		mvc.perform(
				post("/api/customer").content(asJsonString(customer))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name", Matchers.is("Test-Sirpress")));

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
