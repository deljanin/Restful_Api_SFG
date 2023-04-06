package com.deljanin.restfulApiSFG.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String firstname;
    private String lastname;
    @JsonProperty("customer_url")
    private String customer_url;
}
