package com.cakir.service.implTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cakir.entity.Customer;
import com.cakir.repository.CustomerRepository;
import com.cakir.service.impl.CustomerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {

	

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void getAllCustomer_Test_ReturnAllCustomer() {
		// given
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Halberg");

		Customer customer2 = new Customer();
		customer2.setId(2L);
		customer2.setName("Test-Sirpress");

		List<Customer> listCustomer = new ArrayList<Customer>();
		listCustomer.add(customer);
		listCustomer.add(customer2);

		// when
		when(customerService.getAllCustomer()).thenReturn(listCustomer);

		// then

		assertNotEquals(listCustomer.size(), 0);

	}

	@Test
	public void createCustomer_Test_ReturnCustomer() {

		// given
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setName("Test-Halberg");

		// when
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		Customer createdCustomer = customerService.createCustomer(customer);

		// given
		assertEquals(createdCustomer.getName(), customer.getName());

	}

	@Test
	public void updateCustomer_Test_Return_UpdatedCustomer() {

		// given
		Customer customer = new Customer();
		customer.setName("Test-Halberg");

		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		Customer createdCustomer = customerService.createCustomer(customer);

		createdCustomer.setName("Test-Sirpress");

		Customer updatedCustomer = customerService
				.updateCustomer(createdCustomer);

		assertEquals(updatedCustomer.getName(), "Test-Sirpress");

	}
	
	@Test
	public void getCustomerByName_Test_whenFind_ThenReturnCustomer() {
		
		//given
		Customer customer = new Customer();
		customer.setName("Test-Halberg");
		customer.setEmail("test@gmail.com");
		
		Customer customer1 = new Customer();
		customer1.setName("Test-Sirpress");
		
		//when
		when(customerRepository.findByName(customer.getName())).thenReturn(Optional.of(customer));
		
		Customer foundCustomer = customerService.getCustomerByName(customer.getName());
		
		assertTrue(!foundCustomer.equals(null));
				
		
	}
	
	@Test
	public void getCustomerByName_Test_whenNotFind_ThenReturnNull() {
		
		//given
		Customer customer = new Customer();
		customer.setName("Test-Halberg");
		customer.setEmail("test@gmail.com");
		
		Customer customer1 = new Customer();
		customer1.setName("Test-Sirpress");
		
		//when
		when(customerRepository.findByName(customer.getName())).thenReturn(null);
		
		Customer foundCustomer = customerService.getCustomerByName(customer1.getName());
		
		assertTrue(foundCustomer==null);
				
		
	}

}
