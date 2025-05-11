package com.example.test_spring.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.test_spring.dto.CustomerDTO;


public interface CustomerService {
	public void createCustomer(CustomerDTO customerDTO);
	public ResponseEntity<CustomerDTO> findCustomerById(Long id);
	public ResponseEntity<CustomerDTO> updateCustomerById(Long id,CustomerDTO customerDTO);
}
