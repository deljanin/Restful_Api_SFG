package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.mapper.CustomerMapper;
import com.deljanin.restfulApiSFG.api.v1.model.CustomerDTO;
import com.deljanin.restfulApiSFG.bootstrap.Bootstrap;
import com.deljanin.restfulApiSFG.domain.Customer;
import com.deljanin.restfulApiSFG.repositories.CategoryRepository;
import com.deljanin.restfulApiSFG.repositories.CustomerRepository;
import com.deljanin.restfulApiSFG.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Loading customers data. \n " + customerRepository.findAll().size());
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    void patchCustomerUpdateFirstName() throws Exception {
        String updatedName = "NewName";
        Long id = getCustomerIdValue();
        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String orgFirstName = originalCustomer.getFirstname();
        String orgLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);

        assertEquals(updatedName, updatedCustomer.getFirstname());
        assertNotEquals(orgFirstName, updatedCustomer.getFirstname());
        assertEquals(orgLastName, updatedCustomer.getLastname());
    }
    @Test
    void patchCustomerUpdateLastName() throws Exception {
        String updatedSurname = "NewSurname";
        Long id = getCustomerIdValue();
        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String orgFirstName = originalCustomer.getFirstname();
        String orgLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updatedSurname);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);

        assertEquals(orgFirstName, updatedCustomer.getFirstname());
        assertEquals(updatedSurname, updatedCustomer.getLastname());
        assertNotEquals(orgLastName, updatedCustomer.getLastname());
    }

    private Long getCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();
        return customers.get(0).getId();
    }
}
