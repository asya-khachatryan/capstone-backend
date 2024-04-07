package edu.aua.interviews.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    private Long id;

    @NotBlank(message = "required")
    private String firstName;

    @NotBlank(message = "required ")
    private String lastName;

    @Email
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
