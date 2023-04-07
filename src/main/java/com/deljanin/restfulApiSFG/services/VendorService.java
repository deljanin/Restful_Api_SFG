package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO VendorDTO);
    VendorDTO updateVendor(Long id, VendorDTO VendorDTO);
    VendorDTO patchVendor(Long id, VendorDTO VendorDTO);
    void deleteVendorById(Long id);
}
