package com.deljanin.restfulApiSFG.controllers.v1;

import com.deljanin.restfulApiSFG.api.v1.model.CustomerDTO;
import com.deljanin.restfulApiSFG.services.CustomerService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    public static final String NAME = "JONNEY";
    public static final String SURNAME = "BALONEY";
    public static final String CUSTOMER_URL = "/api/v1/customers/1";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getAllCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO(NAME,SURNAME, CUSTOMER_URL);
        CustomerDTO customer2 = new CustomerDTO(NAME+"YYY",SURNAME+"YYY","/api/v1/customers/2");
        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO customer = new CustomerDTO(NAME, SURNAME, CUSTOMER_URL);
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(SURNAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL)));

    }

    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jimme");
        customerDTO.setLastname("Bim");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customerDTO.getFirstname());
        returnDTO.setLastname(customerDTO.getLastname());
        returnDTO.setCustomer_url("/api/v1/customers/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Jimme")))
                .andExpect(jsonPath("$.lastname", equalTo("Bim")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));

    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jimme");
        customerDTO.setLastname("Bim");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customerDTO.getFirstname());
        returnDTO.setLastname(customerDTO.getLastname());
        returnDTO.setCustomer_url("/api/v1/customers/1");

        when(customerService.updateCustomer(1L, customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Jimme")))
                .andExpect(jsonPath("$.lastname", equalTo("Bim")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }
}