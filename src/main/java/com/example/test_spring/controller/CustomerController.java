package com.example.test_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test_spring.dto.CustomerDTO;
import com.example.test_spring.service.CustomerService;

@RestController
@RequestMapping("/customers") // Base URL for the customer endpoints
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Endpoint to create a customer
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return ResponseEntity.status(201).body("Customer created successfully");
    }

    // Endpoint to get a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id); // This will return a ResponseEntity from service
    }

    // Endpoint to update a customer by ID
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomerById(id, customerDTO); // This will return a ResponseEntity from service
    }
}
