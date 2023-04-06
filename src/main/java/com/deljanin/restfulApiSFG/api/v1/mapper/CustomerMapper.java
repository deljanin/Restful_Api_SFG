package com.deljanin.restfulApiSFG.api.v1.mapper;

import com.deljanin.restfulApiSFG.api.v1.model.CustomerDTO;
import com.deljanin.restfulApiSFG.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
