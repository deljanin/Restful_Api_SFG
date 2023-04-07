package com.deljanin.restfulApiSFG.repositories;

import com.deljanin.restfulApiSFG.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
