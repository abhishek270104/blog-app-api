package com.example.blog.tutorial.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.UUID;

@NoArgsConstructor
@Data
public class UserDTO {
    private UUID id;

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @Email(message = "Email is not valid!!")
    @NotEmpty
    private String email;
    private String about;


}
