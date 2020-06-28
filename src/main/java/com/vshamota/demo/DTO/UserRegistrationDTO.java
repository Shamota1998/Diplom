package com.vshamota.demo.DTO;

import com.vshamota.demo.domain.City;
import com.vshamota.demo.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRegistrationDTO {
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]{3,15}", message =  "Name should contain alphabetic symbols(3-15)")
    private String firstName;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]{3,15}", message =  "Last Name should contain alphabetic symbols(3-15)")
    private String lastName;
    @NotEmpty
    @Pattern(regexp = "^([A-Za-z]{4,10})([0-9]{0,5})$",
            message = "Login should contain alphabetic symbols(4-10) and/or digits(0-5)")
    private String login;
    @NotEmpty
    @Pattern(regexp = "^([A-Za-z]{5,10})([0-9]{0,5})$",
            message = "Password should contain only alphabetic symbols(5-10) and digits(0-5)" )
    private String password;
    @NotEmpty
    private String repeatedPass;
    @NotEmpty
    private String address;
    private Country country;
    private City city;
}
