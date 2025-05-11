package com.example.test_spring.serviceimpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.test_spring.dto.CustomerDTO;
import com.example.test_spring.entity.Customer;
import com.example.test_spring.exception.CustomerNotFoundException;
import com.example.test_spring.repository.CustomerRepository;
import com.example.test_spring.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	public CustomerRepository customerRepository;
	

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository= customerRepository;
	}

	@Override
	public void createCustomer(CustomerDTO customerDTO) {
		Customer customer = CustomerDTO.toEntity(customerDTO);
		customerRepository.save(customer);
		
	}

	@Override
	public ResponseEntity<CustomerDTO> findCustomerById(Long id) {
	    Optional<Customer> customer = customerRepository.findById(id);
	    if (customer.isPresent()) {
	        CustomerDTO customerDTO = CustomerDTO.toDTO(customer.get());
	        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	    } else {
	        // Handle the case where the customer is not found, e.g., throw an exception
	        throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
	    }
	}

	//vvi
	@Override
	public ResponseEntity<CustomerDTO> updateCustomerById(Long id,@RequestBody CustomerDTO customerDTO) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if(optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			customer.setName(customerDTO.getName());
			customer.setAge(customerDTO.getAge());
			customerRepository.save(customer);
			return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
		}else {
	        throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
		}
		
	}


}
