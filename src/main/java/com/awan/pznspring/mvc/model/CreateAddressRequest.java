package com.awan.pznspring.mvc.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequest {

    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String postCode;

    @Override
    public String toString() {
        return "CreateAddressRequest{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
