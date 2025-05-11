package com.example.test_spring.dto;

import com.example.test_spring.entity.Customer;

public class CustomerDTO {

	public  Long id;
	public String name;
	public int age;
	
	public CustomerDTO() {
		
	}
	
	public CustomerDTO(Long id, String name,int age) {
		this.id=id;
		this.name=name;
		this.age=age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	// Convert Entity To DTO
	public static CustomerDTO toDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setAge(customer.getAge());
		
		return customerDTO;
		
	}
	
	// Convert DTO To Entity
	public static Customer toEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setName(customerDTO.getName());
		customer.setAge(customerDTO.getAge());
		return customer;
	}
}
