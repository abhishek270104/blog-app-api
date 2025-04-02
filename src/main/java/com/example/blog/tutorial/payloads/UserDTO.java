package com.example.blog.tutorial.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class UserDTO {
    private UUID id;

    private String name;
    private String password;
    private String email;
    private String about;


}
