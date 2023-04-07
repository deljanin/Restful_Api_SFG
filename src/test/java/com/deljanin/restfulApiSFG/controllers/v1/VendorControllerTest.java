package com.deljanin.restfulApiSFG.controllers.v1;

import com.deljanin.restfulApiSFG.api.v1.model.VendorDTO;
import com.deljanin.restfulApiSFG.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.deljanin.restfulApiSFG.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {
    private final String NAME = "SomeCoolVendorName";
    private final String URL = "/api/v1/vendors/2";

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllVendors() throws Exception {
        VendorDTO vendor1 = new VendorDTO(NAME, URL);
        VendorDTO vendor2 = new VendorDTO(NAME+"_Modified",URL+"1");
        List<VendorDTO> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void getVendorById() throws Exception {
        VendorDTO vendor = new VendorDTO(NAME, URL);
        when(vendorService.getVendorById(anyLong())).thenReturn(vendor);

        mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", equalTo(NAME)))
                        .andExpect(jsonPath("$.vendor_url", equalTo(URL)));
    }

    @Test
    void createNewVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);


        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());

        returnDTO.setVendor_url(URL);

        when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name", equalTo(NAME)))
                        .andExpect(jsonPath("$.vendor_url", equalTo(URL)));
    }

    @Test
    void patchVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setVendor_url(URL);

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", equalTo(NAME)))
                        .andExpect(jsonPath("$.vendor_url", equalTo(URL)));
    }


    @Test
    void deleteVendor() throws Exception {
        mockMvc.perform(delete(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }
}