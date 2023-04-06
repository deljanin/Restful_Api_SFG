package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.mapper.CustomerMapper;
import com.deljanin.restfulApiSFG.api.v1.model.CategoryDTO;
import com.deljanin.restfulApiSFG.api.v1.model.CustomerDTO;
import com.deljanin.restfulApiSFG.domain.Category;
import com.deljanin.restfulApiSFG.domain.Customer;
import com.deljanin.restfulApiSFG.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    void getAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        assertEquals(3, customerDTOS.size());
    }

    @Test
    void getCustomerById() throws Exception {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Jimme");
        customer1.setLastname("SomeCreativeSurname");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertEquals("Jimme", customerDTO.getFirstname());
        assertEquals("SomeCreativeSurname", customerDTO.getLastname());
    }

    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jimme");
        customerDTO.setLastname("SomeCreativeSurname");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(customerDTO.getLastname(), savedDto.getLastname());
        assertEquals("/api/v1/customers/1", savedDto.getCustomer_url());
    }


    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jimme");
        customerDTO.setLastname("SomeCreativeSurname");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDto = customerService.updateCustomer(1L ,customerDTO);

        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals(customerDTO.getLastname(), savedDto.getLastname());
        assertEquals("/api/v1/customers/1", savedDto.getCustomer_url());

    }
}