package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.mapper.VendorMapper;
import com.deljanin.restfulApiSFG.api.v1.model.VendorDTO;
import com.deljanin.restfulApiSFG.controllers.v1.VendorController;
import com.deljanin.restfulApiSFG.domain.Vendor;
import com.deljanin.restfulApiSFG.exceptions.ResourceNotFoundException;
import com.deljanin.restfulApiSFG.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendor_url(createVendorUrl(vendor.getId()));
                    return vendorDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id).map(vendor -> {
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setVendor_url(createVendorUrl(id));
            return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturn(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturn(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName() != null) vendor.setName(vendorDTO.getName());
            return saveAndReturn(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);
        if(!vendorOptional.isPresent()) throw new ResourceNotFoundException();
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturn(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        returnVendorDTO.setVendor_url(createVendorUrl(savedVendor.getId()));
        return returnVendorDTO;
    }

    private String createVendorUrl(Long id){
        return VendorController.BASE_URL + "/" + id;
    }
}
