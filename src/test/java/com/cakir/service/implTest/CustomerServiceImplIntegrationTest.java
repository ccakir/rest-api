package com.cakir.service.implTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cakir.entity.Customer;
import com.cakir.repository.CustomerRepository;
import com.cakir.service.CustomerService;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceImplIntegrationTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void CreateCustomer_Test() {
		
		Customer customer = new Customer();
		customer.setName("Test-Name");
		customer.setId(1L);
		
		
		Customer result = customerService.createCustomer(customer);
		
		Assertions.assertNotNull(result);
		
	}
	
	@Test
	public void getAllCustomer_Test() {
		Customer customer1 = new Customer();
		customer1.setName("Test-Name");
		customer1.setId(1L);
		
		Customer customer2 = new Customer();
		customer2.setName("Test-Name2");
		customer2.setId(2L);
		
		Customer result1 = customerService.createCustomer(customer1);
		Customer result2 = customerService.createCustomer(customer2);
		
		List<Customer> listCustomer = customerService.getAllCustomer();
		
		assertTrue(listCustomer.size() > 0);
		Assertions.assertNotNull(listCustomer);
		Assertions.assertNotEquals(result1.getId(), result2.getId());
		
	}
	
	@Test
	public void updateCustomer_Test() {
		
		Customer customer1 = new Customer();
		
		customer1.setName("Test-Name");
		customer1.setEmail("test@email.com");
		
		Customer result = customerService.createCustomer(customer1);
		
		result.setName("Test-Update");
		result.setEmail("update@email.com");
		
		Customer updatedCustomer = customerService.updateCustomer(result);
		
		Assertions.assertNotNull(updatedCustomer);
		Assertions.assertNotSame(updatedCustomer.getEmail(), "test@email.com");
		Assertions.assertSame(updatedCustomer.getName(), "Test-Update");
		
	}
	
	@Test
	public void deleteCustomer_Test() {
		
		Customer customer3 = new Customer();
		customer3.setId(1L);
		customer3.setName("Test-Name");
		customer3.setEmail("test@email.com");
		
		Customer result = customerService.createCustomer(customer3);
		
		Assertions.assertNotNull(result);
		
		customerService.deleteCustomer(customer3.getId());
		
		Customer found = customerService.getCustomerById(customer3.getId());
		
		Assertions.assertNull(found);
	}

	@Test
	public void getCustomerByName_Test() {
		
		Customer customer4 = new Customer();
		customer4.setId(10L);
		customer4.setName("Test-Halberg");
		customer4.setEmail("test@email.com");
		
		Customer result = customerService.createCustomer(customer4);
		
		Assertions.assertNotNull(result);
		
		Customer search = customerService.getCustomerByName("Test-Halberg");
		
		Assertions.assertNotNull(search);
		
	}
}
