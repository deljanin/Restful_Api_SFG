package com.deljanin.restfulApiSFG.repositories;

import com.deljanin.restfulApiSFG.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
