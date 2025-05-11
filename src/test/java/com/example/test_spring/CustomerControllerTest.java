package com.example.test_spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.test_spring.controller.CustomerController;
import com.example.test_spring.dto.CustomerDTO;
import com.example.test_spring.exception.CustomerNotFoundException;
import com.example.test_spring.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test getCustomerById - success")
    public void testGetCustomerByIdSuccess() throws Exception {
        // Arrange
        Long id = 1L;
        CustomerDTO customerDTO = new CustomerDTO(id, "Sita", 25);

        // Mocking the behavior of the service
        Mockito.when(customerService.findCustomerById(id))
            .thenReturn(new ResponseEntity<>(customerDTO, HttpStatus.OK));

        // Act
        MvcResult result = mockMvc.perform(get("/customers/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String responseBody = result.getResponse().getContentAsString();
        CustomerDTO responseDTO = objectMapper.readValue(responseBody, CustomerDTO.class);

        Assertions.assertEquals(id, responseDTO.getId());
        Assertions.assertEquals("Sita", responseDTO.getName());
        Assertions.assertEquals(25, responseDTO.getAge());
    }

    @Test
    @DisplayName("Test getCustomerById - not found")
    public void testGetCustomerByIdNotFound() throws Exception {
        Long id = 99L;

        // Simulating that no customer is found with the given ID
        Mockito.when(customerService.findCustomerById(id))
            .thenThrow(new CustomerNotFoundException("Customer with ID " + id + " not found."));

        // Act & Assert
        mockMvc.perform(get("/customers/{id}", id))
                .andExpect(status().isNotFound());
    }
}

