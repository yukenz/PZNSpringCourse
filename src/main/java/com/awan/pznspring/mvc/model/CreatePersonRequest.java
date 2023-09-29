package com.awan.pznspring.mvc.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreatePersonRequest {

    @NotBlank
    private String firstName;
    private String middleName;
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;

    //Nested Attr
    private CreateAddressRequest address;

    private List<String> hobbies;

    @Override
    public String toString() {
        return "CreatePersonRequest{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", hobbies=" + hobbies +
                '}';
    }
}
