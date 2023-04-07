package com.deljanin.restfulApiSFG.api.v1.mapper;

import com.deljanin.restfulApiSFG.api.v1.model.VendorDTO;
import com.deljanin.restfulApiSFG.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
