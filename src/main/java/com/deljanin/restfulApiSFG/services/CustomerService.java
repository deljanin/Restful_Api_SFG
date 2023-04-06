package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
}
