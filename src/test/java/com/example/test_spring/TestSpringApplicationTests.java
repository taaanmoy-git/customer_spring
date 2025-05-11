package com.example.test_spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.test_spring.dto.CustomerDTO;
import com.example.test_spring.entity.Customer;
import com.example.test_spring.exception.CustomerNotFoundException;
import com.example.test_spring.repository.CustomerRepository;
import com.example.test_spring.serviceimpl.CustomerServiceImpl;

@SpringBootTest
class TestSpringApplicationTests {
	
	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@BeforeEach
	public void innitialize() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("CustomerNotFoundException Test")
	public void testCustomerNotFoundException() {

		// Arrange
		Long customerId = 100L;
		Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
		assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById(customerId));
	}
	
	@Test
	@DisplayName("CustomerFound Test")
	public void testCustomerFound() {
		Long customerId =100L;
		Customer customer = new Customer(customerId, "Ram Das", 21);
		Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		ResponseEntity<?> response = customerService.findCustomerById(customerId);
		CustomerDTO customerDTO = (CustomerDTO) response.getBody();
		
		Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals("Ram Das", customerDTO.getName());
		assertEquals(21, customerDTO.getAge());
	}

}
