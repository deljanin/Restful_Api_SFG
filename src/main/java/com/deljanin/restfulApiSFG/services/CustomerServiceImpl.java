package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.mapper.CustomerMapper;
import com.deljanin.restfulApiSFG.api.v1.model.CustomerDTO;
import com.deljanin.restfulApiSFG.api.v1.model.CustomerListDTO;
import com.deljanin.restfulApiSFG.domain.Customer;
import com.deljanin.restfulApiSFG.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomer_url("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomer_url("/api/v1/customers/" + customer.getId());
                    return customerDTO;}).orElseThrow(RuntimeException::new); //TODO throw 404 not 500
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturn(customerMapper.customerDtoToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturn(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(customerDTO.getFirstname() != null) customer.setFirstname(customerDTO.getFirstname());
            if(customerDTO.getLastname() != null) customer.setLastname(customerDTO.getLastname());

            return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        }).orElseThrow(RuntimeException::new); //todo create better exception handling
    }

    public CustomerDTO saveAndReturn(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);

        returnCustomerDTO.setCustomer_url("/api/v1/customers/" + savedCustomer.getId());
        return returnCustomerDTO;
    }
}
