package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.mapper.VendorMapper;
import com.deljanin.restfulApiSFG.api.v1.model.VendorDTO;
import com.deljanin.restfulApiSFG.domain.Vendor;
import com.deljanin.restfulApiSFG.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    private final String NAME = "SomeCoolVendorName";
    private final Long ID = 2L;
    private final String BASE_URL = "/api/v1/vendors/";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();
        assertEquals(4, vendorDTOS.size());
    }

    @Test
    void getVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));


        VendorDTO vendorDTO = vendorService.getVendorById(2L);

        assertEquals(NAME, vendorDTO.getName());
        assertEquals(BASE_URL+ID, vendorDTO.getVendor_url());
    }

    @Test
    void createNewVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor(ID,vendorDTO.getName());
        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(BASE_URL+ID, savedDTO.getVendor_url());
    }

    @Test
    void updateVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.updateVendor(ID, vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(BASE_URL+ID, savedDTO.getVendor_url());
    }

    @Test
    void patchVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        //when

        VendorDTO savedVendorDTO = vendorService.patchVendor(ID, vendorDTO);
//
        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
//        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void deleteVendorById() {
        vendorRepository.deleteById(ID);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}